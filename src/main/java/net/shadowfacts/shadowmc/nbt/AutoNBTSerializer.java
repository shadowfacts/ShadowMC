package net.shadowfacts.shadowmc.nbt;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.shadowfacts.shadowlib.util.Pair;
import net.shadowfacts.shadowmc.util.LogHelper;

import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author shadowfacts
 */
public class AutoNBTSerializer {

	private static Map<Class, Pair> serializers = new HashMap<>();

	private static LogHelper log = new LogHelper("ShadowMC|AutoNBT");

	static {
		registerSerializer(NBTBase.class, NBTTagCompound::setTag, NBTTagCompound::getTag);
		registerSerializer(byte.class, NBTTagCompound::setByte, NBTTagCompound::getByte);
		registerSerializer(short.class, NBTTagCompound::setShort, NBTTagCompound::getShort);
		registerSerializer(int.class, NBTTagCompound::setInteger, NBTTagCompound::getInteger);
		registerSerializer(long.class, NBTTagCompound::setLong, NBTTagCompound::getLong);
		registerSerializer(float.class, NBTTagCompound::setFloat, NBTTagCompound::getFloat);
		registerSerializer(double.class, NBTTagCompound::setDouble, NBTTagCompound::getDouble);
		registerSerializer(String.class, NBTTagCompound::setString, NBTTagCompound::getString);
		registerSerializer(byte[].class, NBTTagCompound::setByteArray, NBTTagCompound::getByteArray);
		registerSerializer(int[].class, NBTTagCompound::setIntArray, NBTTagCompound::getIntArray);
		registerSerializer(NBTTagCompound.class, NBTTagCompound::setTag, NBTTagCompound::getCompoundTag);
		registerSerializer(boolean.class, NBTTagCompound::setBoolean, NBTTagCompound::getBoolean);
		registerSerializer(ItemStack.class, AutoNBTSerializer::serializeItemStack, AutoNBTSerializer::deserializeItemStack);
		registerSerializer(ItemStack[].class, AutoNBTSerializer::serializeItemStackArray, AutoNBTSerializer::deserializeItemStackArray);
	}

	public static <T> void registerSerializer(Class<T> clazz, NBTSerializer<T> serializer, NBTDeserializer<T> deserializer) {
		serializers.put(clazz, new Pair<>(serializer, deserializer));
	}

	@SuppressWarnings("unchecked")
	public static <T> Pair<NBTSerializer<T>, NBTDeserializer<T>> getSerializersFor(Class<T> clazz) {
		return serializers.get(clazz);
	}

	public static <T> NBTSerializer<T> getSerializerFor(Class<T> clazz) {
		return getSerializersFor(clazz).getLeft();
	}

	public static <T> NBTDeserializer<T> getDeserializerFor(Class<T> clazz) {
		return getSerializersFor(clazz).getRight();
	}

	public static NBTTagCompound serialize(Class<?> clazz, Object instance) {
		return serialize(clazz, instance, new NBTTagCompound());
	}

	public static NBTTagCompound serialize(Class<?> clazz, Object instance, NBTTagCompound tag) {
		Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> !Modifier.isStatic(f.getModifiers()))
				.filter(f -> f.isAnnotationPresent(AutoSerializeNBT.class) || clazz.isAnnotationPresent(AutoSerializeNBT.class))
				.forEach(f -> {
					try {
						NBTSerializer serializer = getSerializerFor(f.getType());

						f.setAccessible(true);

						serializer.serialize(tag, f.getName(), f.get(instance));

					} catch (ReflectiveOperationException e) {
						log.error("Couldn't serialize %s in %s", f.getName(), clazz.getName());
						e.printStackTrace();
					}
				});
		return tag;
	}

	public static void deserialize(Class<?> clazz, Object instance, NBTTagCompound tag) {
		Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> !Modifier.isStatic(f.getModifiers()))
				.filter(f -> f.isAnnotationPresent(AutoSerializeNBT.class) || clazz.isAnnotationPresent(AutoSerializeNBT.class))
				.forEach(f -> {
					try {
						NBTDeserializer deserializer = getDeserializerFor(f.getType());

						f.setAccessible(true);

						f.set(instance, deserializer.deserialize(tag, f.getName()));
					} catch (ReflectiveOperationException e) {
						log.error("Couldn't deserialize %s in %s", f.getName(), clazz.getName());
						e.printStackTrace();
					}
				});
	}

	private static void serializeItemStack(NBTTagCompound tag, String name, ItemStack val) {
		NBTTagCompound stackTag = new NBTTagCompound();
		val.writeToNBT(stackTag);
		tag.setTag(name, stackTag);
	}

	private static ItemStack deserializeItemStack(NBTTagCompound tag, String name) {
		return ItemStack.loadItemStackFromNBT(tag.getCompoundTag(name));
	}

	private static void serializeItemStackArray(NBTTagCompound tag, String name, ItemStack[] val) {
		NBTTagList tagList = new NBTTagList();
		for (ItemStack stack : val) {
			NBTTagCompound stackTag = new NBTTagCompound();
			stack.writeToNBT(stackTag);
			tagList.appendTag(stackTag);
		}
		tag.setTag(name, tagList);
	}

	private static ItemStack[] deserializeItemStackArray(NBTTagCompound tag, String name) {
		NBTTagList tagList = tag.getTagList(name, 10);
		ItemStack[] stacks = new ItemStack[tagList.tagCount()];

		for (int i = 0; i < tagList.tagCount(); i++) {
			stacks[i] = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i));
		}

		return stacks;
	}

}

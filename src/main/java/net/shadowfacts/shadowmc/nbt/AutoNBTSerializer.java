package net.shadowfacts.shadowmc.nbt;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.mirror.Mirror;
import net.shadowfacts.mirror.MirrorClass;
import net.shadowfacts.shadowlib.util.Pair;
import net.shadowfacts.shadowmc.fluid.CreativeFluidTank;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.util.LogHelper;
import net.shadowfacts.shadowmc.util.RedstoneMode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowfacts
 */
public class AutoNBTSerializer {

	private static Map<MirrorClass, Pair> serializers = new HashMap<>();

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
		registerSerializer(EnumFacing.class, AutoNBTSerializer::serializeEnumFacing, AutoNBTSerializer::deserializeEnumFacing);
		registerSerializer(BlockPos.class, AutoNBTSerializer::serializeBlockPos, AutoNBTSerializer::deserializeBlockPos);
		registerSerializer(RedstoneMode.class, AutoNBTSerializer::serializeRedstoneMode, AutoNBTSerializer::deserializeRedstoneMode);
		registerSerializer(Block.class, AutoNBTSerializer::serializeBlock, AutoNBTSerializer::deserializeBlock);
		registerSerializer(Item.class, AutoNBTSerializer::serializeItem, AutoNBTSerializer::deserializeItem);
		registerSerializer(FluidStack.class, AutoNBTSerializer::serializeFluidStack, AutoNBTSerializer::deserializeFluidStack);
		registerSerializer(Fluid.class, AutoNBTSerializer::serializeFluid, AutoNBTSerializer::deserializeFluid);
		registerSerializer(FluidTank.class, AutoNBTSerializer::serializeFluidTank, AutoNBTSerializer::deserializeFluidTank);
		registerSerializer(CreativeFluidTank.class, AutoNBTSerializer::serializeCreativeFluidTank, AutoNBTSerializer::deserializeCreativeFluidTank);
	}

	public static <T> void registerSerializer(MirrorClass<T> clazz, NBTSerializer<T> serializer, NBTDeserializer<T> deserializer) {
		serializers.put(clazz, new Pair<>(serializer, deserializer));
	}

	public static <T> void registerSerializer(Class<T> clazz, NBTSerializer<T> serializer, NBTDeserializer<T> deserializer) {
		registerSerializer(Mirror.of(clazz), serializer, deserializer);
	}

	@SuppressWarnings("unchecked")
	public static <T> Pair<NBTSerializer<T>, NBTDeserializer<T>> getSerializersFor(MirrorClass<T> clazz) {
		return serializers.get(clazz);
	}

	public static <T> NBTSerializer<T> getSerializerFor(MirrorClass<T> clazz) {
		return getSerializersFor(clazz).getLeft();
	}

	public static <T> NBTDeserializer<T> getDeserializerFor(MirrorClass<T> clazz) {
		return getSerializersFor(clazz).getRight();
	}

	public static NBTTagCompound serialize(Class<?> clazz, Object instance) {
		return serialize(clazz, instance, new NBTTagCompound());
	}

	public static NBTTagCompound serialize(Class<?> clazz, Object instance, NBTTagCompound tag) {
		Mirror.of(clazz)
				.declaredFields()
				.isNotStatic()
				.filter(f -> f.hasAnnotation(AutoSerializeNBT.class) || f.declaringClass().hasAnnotation(AutoSerializeNBT.class))
				.forEach(f -> {
					try {
						NBTSerializer serializer = getSerializerFor(f.type());
						f.setAccessible(true);
						serializer.serialize(tag, f.name(), f.get(instance));
					} catch (Exception e) {
						log.error("Couldn't serialize %s in %s", f.name(), clazz.getName());
						e.printStackTrace();
					}
				});
		return tag;
	}

	public static void deserialize(Class<?> clazz, Object instance, NBTTagCompound tag) {
		Mirror.of(clazz)
				.declaredFields()
				.isNotStatic()
				.filter(f -> f.hasAnnotation(AutoSerializeNBT.class) || f.declaringClass().hasAnnotation(AutoSerializeNBT.class))
				.forEach(f -> {
					try {
						NBTDeserializer deserializer = getDeserializerFor(f.type());
						f.setAccessible(true);
						f.set(instance, deserializer.deserialize(tag, f.name()));
					} catch (Exception e) {
						log.error("Couldn't deserialize %s in %s", f.name(), clazz.getName());
					}
				});
	}

	private static void serializeItemStack(NBTTagCompound tag, String name, ItemStack val) {
		if (val != null) {
			NBTTagCompound stackTag = new NBTTagCompound();
			val.writeToNBT(stackTag);
			tag.setTag(name, stackTag);
		}
	}

	private static ItemStack deserializeItemStack(NBTTagCompound tag, String name) {
		return tag.hasKey(name) ? ItemStack.loadItemStackFromNBT(tag.getCompoundTag(name)) : null;
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

	private static void serializeEnumFacing(NBTTagCompound tag, String name, EnumFacing val) {
		tag.setInteger(name, val.getIndex());
	}

	private static EnumFacing deserializeEnumFacing(NBTTagCompound tag, String name) {
		return EnumFacing.getFront(tag.getInteger(name));
	}

	private static void serializeBlockPos(NBTTagCompound tag, String name, BlockPos val) {
		tag.setLong(name, val.toLong());
	}

	private static BlockPos deserializeBlockPos(NBTTagCompound tag, String name) {
		return BlockPos.fromLong(tag.getLong(name));
	}

	private static void serializeRedstoneMode(NBTTagCompound tag, String name, RedstoneMode val) {
		tag.setInteger(name, val.ordinal());
	}

	private static RedstoneMode deserializeRedstoneMode(NBTTagCompound tag, String name) {
		return RedstoneMode.values()[tag.getInteger(name)];
	}

	private static void serializeBlock(NBTTagCompound tag, String name, Block val) {
		tag.setString(name, Block.REGISTRY.getNameForObject(val).toString());
	}

	private static Block deserializeBlock(NBTTagCompound tag, String name) {
		return Block.REGISTRY.getObject(new ResourceLocation(tag.getString(name)));
	}

	private static void serializeItem(NBTTagCompound tag, String name, Item val) {
		tag.setString(name, Item.REGISTRY.getNameForObject(val).toString());
	}

	private static Item deserializeItem(NBTTagCompound tag, String name) {
		return Item.REGISTRY.getObject(new ResourceLocation(tag.getString(name)));
	}

	private static void serializeFluidStack(NBTTagCompound tag, String name, FluidStack val) {
		NBTTagCompound stackTag = new NBTTagCompound();
		val.writeToNBT(stackTag);
		tag.setTag(name, stackTag);
	}

	private static FluidStack deserializeFluidStack(NBTTagCompound tag, String name) {
		return FluidStack.loadFluidStackFromNBT(tag.getCompoundTag(name));
	}

	private static void serializeFluid(NBTTagCompound tag, String name, Fluid val) {
		tag.setString(name, FluidRegistry.getFluidName(val));
	}

	private static Fluid deserializeFluid(NBTTagCompound tag, String name) {
		return FluidRegistry.getFluid(tag.getString(name));
	}

	private static void serializeFluidTank(NBTTagCompound tag, String name, FluidTank val) {
		NBTTagCompound tankTag = new NBTTagCompound();
		val.writeToNBT(tankTag);
		tag.setTag(name, tankTag);
	}

	private static FluidTank deserializeFluidTank(NBTTagCompound tag, String name) {
		return new FluidTank(0).readFromNBT(tag.getCompoundTag(name));
	}

	private static void serializeCreativeFluidTank(NBTTagCompound tag, String name, CreativeFluidTank val) {
		NBTTagCompound tankTag = new NBTTagCompound();
		val.writeToNBT(tankTag);
		tag.setTag(name, tankTag);
	}

	private static CreativeFluidTank deserializeCreativeFluidTank(NBTTagCompound tag, String name) {
		return (CreativeFluidTank)new CreativeFluidTank (0).readFromNBT(tag.getCompoundTag(name));
	}

}

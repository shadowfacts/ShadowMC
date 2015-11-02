package net.shadowfacts.shadowmc.util;

import com.google.gson.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Type;

/**
 * @author shadowfacts
 */
public class ItemStackUtils implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

	public static boolean areItemStacksEqual(ItemStack stack, ItemStack other) {
		if (stack == null || other == null) {
			return stack == null && other == null;
		} else if (stack.getItem() == null || other.getItem() == null) {
			return stack.getItem() == null && other.getItem() == null;
		}
		return stack.isItemEqual(other) &&
				stack.getItemDamage() == other.getItemDamage();
	}

	public static boolean areItemStacksEqualWithNBT(ItemStack stack, ItemStack other) {
		if (stack == null || other == null) {
			return stack == null && other == null;
		} else if (stack.getItem() == null || other.getItem() == null) {
			return stack.getItem() == null && other.getItem() == null;
		} else if (stack.isItemEqual(other) && stack.getItemDamage() == other.getItemDamage()) {
			if (stack.stackTagCompound == null) {
				return other.stackTagCompound == null;
			}

			return stack.stackTagCompound.equals(other.stackTagCompound);
		}

		return false;
	}

	@Override
	public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();

		String name;
		if (obj.get("name") != null) name = obj.get("name").getAsString();
		else throw new JsonParseException("name is a required property");

		String modId = obj.get("modId") != null ? obj.get("modId").getAsString() : "minecraft";
		int metadata = obj.get("metadata") != null ? obj.get("metadata").getAsInt() : 0;
		int stackSize = obj.get("stackSize") != null ? obj.get("stackSize").getAsInt() : 0;

		ItemStack stack = GameRegistry.findItemStack(modId, name, stackSize).copy();
		stack.setItemDamage(metadata);

		return stack;
	}

	@Override
	public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();

		GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(src.getItem());

		obj.add("name", context.serialize(id.name));
		obj.add("modId", context.serialize(id.modId));
		obj.add("metadata", context.serialize(src.getItemDamage()));
		obj.add("stackSize", context.serialize(src.stackSize));

		return obj;
	}

}

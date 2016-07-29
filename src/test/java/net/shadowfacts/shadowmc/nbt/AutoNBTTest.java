package net.shadowfacts.shadowmc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shadowfacts
 */
public class AutoNBTTest {

	@org.junit.Test
	public void testSerialize() {
		Test instance = new Test();
		NBTTagCompound tag = AutoNBTSerializer.serialize(Test.class, instance);

		assertEquals(42, tag.getInteger("i"));
		assertEquals("Test", tag.getString("Test"));
		assertEquals(true, tag.getBoolean("b"));
	}

	@org.junit.Test
	public void testDeserialize() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("i", 3);
		tag.setString("s", "Hello");
		tag.setBoolean("b", false);
		Test instance = new Test();
		AutoNBTSerializer.deserialize(Test.class, instance, tag);

		assertEquals(3, instance.i);
		assertEquals("Hello", instance.s);
		assertEquals(false, instance.b);
	}

	@AutoSerializeNBT
	public static class Test {
		public int i = 42;
		public String s = "Test";
		public boolean b = true;
	}

}

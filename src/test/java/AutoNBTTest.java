import net.minecraft.nbt.NBTTagCompound;
import net.shadowfacts.shadowmc.nbt.AutoNBTSerializer;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;

/**
 * @author shadowfacts
 */
@AutoSerializeNBT
public class AutoNBTTest {

	private float f;
	private boolean b;
	private String s;

	public static void main(String[] args) {
		AutoNBTTest test = new AutoNBTTest(3.14159f, false, "Hello");

		NBTTagCompound tag = AutoNBTSerializer.serialize(AutoNBTTest.class, test);

		AutoNBTTest test2 = new AutoNBTTest();
		AutoNBTSerializer.deserialize(AutoNBTTest.class, test2, tag);
	}

	public AutoNBTTest(float f, boolean b, String s) {
		this.f = f;
		this.b = b;
		this.s = s;
	}

	public AutoNBTTest() {
	}
}

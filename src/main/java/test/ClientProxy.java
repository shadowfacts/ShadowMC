package test;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	public static KeyBinding[] keyBindings;

	@Override
	public void init() {
		super.init();

		keyBindings = new KeyBinding[1];

		keyBindings[0] = new KeyBinding("key.opengui.desc", Keyboard.KEY_G, "key.test.category");

		ClientRegistry.registerKeyBinding(keyBindings[0]);
	}

}

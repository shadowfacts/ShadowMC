package test;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.shadowfacts.shadowmc.gui.GUIBuilder;
import net.shadowfacts.shadowmc.gui.component.GUIComponentText;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonText;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
@Mod(modid = "test")
public class TestMod {

	@SidedProxy(serverSide = "test.CommonProxy", clientSide = "test.ClientProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	public void openGUI() {
		GuiScreen gui = new GUIBuilder()
				.addComponent(new GUIComponentText(50, 50, "Hello, World!"))
				.addComponent(new GUIButtonText(50, 60, 100, 20, this::testPressed, "Test 1"))
				.wrap();
		Minecraft.getMinecraft().displayGuiScreen(gui);
	}

	public boolean testPressed(GUIButtonText button, MouseButton mouseButton) {
		switch (button.getText()) {
			case "Test 1":
				button.setText("Test 2");
				break;
			case "Test 2":
				button.setText("Test 1");
				break;
			default:
				return false;
		}
		return true;
	}

	@SubscribeEvent
	public void keyInput(InputEvent.KeyInputEvent event) {
		if (ClientProxy.keyBindings[0].isPressed()) {
			openGUI();
		}
	}


}

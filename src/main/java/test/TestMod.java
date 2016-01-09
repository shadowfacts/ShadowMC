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
import net.shadowfacts.shadowmc.gui.component.GUIVerticalBarIndicator;
import net.shadowfacts.shadowmc.gui.component.window.GUIComponentWindow;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonLink;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonText;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonToggle;
import net.shadowfacts.shadowmc.util.Color;
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
		Minecraft.getMinecraft().displayGuiScreen(buildGui3());
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

	public void togglePressed(GUIButtonToggle button) {
		System.out.println(String.format("Toggle button pressed, state is now %b", button.state));
	}

	@SubscribeEvent
	public void keyInput(InputEvent.KeyInputEvent event) {
		if (ClientProxy.keyBindings[0].isPressed()) {
			openGUI();
		}
	}

	private GuiScreen buildGui() {
		return new GUIBuilder()
				.addComponent(new GUIComponentText(50, 50, "Hello, World!")
						.setColor(Color.LIGHT_PURPLE))
				.addComponent(new GUIButtonText(50, 60, 100, 20, this::testPressed, "Test 1")
						.setColor(Color.DARK_BLUE)
						.setEnabled(false)
						.addTooltip("It's a button!"))
				.addComponent(new GUIButtonToggle(50, 90, guiButtonToggle -> {}))
				.addComponent(new GUIComponentWindow(100, 100, 150, 150, "It's a window!", true)
						.addComponent(new GUIButtonToggle(110, 140, this::togglePressed))
						.addComponent(new GUIButtonLink(110, 165, 50, 20, "GH", "https://github.com/shadowfacts/"))
						.setMainColor(new Color(0x55000000))
						.setTitleBarColor(new Color(0x99000000))
						.setTitleColor(Color.AQUA))
				.wrap();
	}

	private GuiScreen buildGui2() {
		return new GUIBuilder()
				.addComponent(new GUIComponentWindow(0, 0, 150, 150, "Window 1")
								.addComponent(new GUIComponentText(10, 30, "My GitHub profile:"))
								.addComponent(new GUIButtonLink(10, 48, 70, 20, "Link", "https://github.com/shadowfacts/"))
								.setMainColor(new Color(0x99000000))
								.setTitleBarColor(new Color(0x77FF0000)))
				.addComponent(new GUIComponentWindow(200, 0, 150, 150, "Window 2", true)
								.addComponent(new GUIButtonToggle(210, 30, this::togglePressed))
								.setTitleColor(new Color(0xFF00FF00)))

				.wrap();
	}

	private GuiScreen buildGui3() {
		return new GUIBuilder()
				.addComponent(new GUIVerticalBarIndicator(0, 0, 20, 100, this::getLevel)
								.setPrimaryColor(Color.PURE_BLUE)
								.setSecondaryColor(Color.DARK_BLUE)
								.addTooltip("Stuff and things"))
				.wrap();
	}

	private int i = 0;
	private float level;
	private float getLevel() {
		i++;
		if (i % 60 == 0) {
			level += .05f;
			if (level > 1) {
				level = 0;
			}
			i = 0;
		}
		return level;
	}


}

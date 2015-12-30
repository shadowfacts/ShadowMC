package test;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.shadowfacts.shadowmc.gui.mcwrapper.MCGui;

/**
 * @author shadowfacts
 */
@Mod(modid = "test")
public class TestMod {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void clientChat(ClientChatReceivedEvent event) {
		if (event.message.getUnformattedText().endsWith("test")) {
			Minecraft.getMinecraft().displayGuiScreen(new MCGui(new GuiTest(Minecraft.getMinecraft())));
		}
	}


}

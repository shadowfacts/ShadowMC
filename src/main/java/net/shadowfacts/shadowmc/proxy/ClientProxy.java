package net.shadowfacts.shadowmc.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.shadowfacts.shadowmc.flair.FlairManager;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		FlairManager.initClient();
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public EntityPlayer getClientPLayer() {
		return Minecraft.getMinecraft().player;
	}

	@Override
	public void registerItemModel(Item item, int meta, ResourceLocation id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(id, "inventory"));
	}

	@Override
	public void sendSpamlessMessage(EntityPlayer player, ITextComponent msg, int id) {
		GuiNewChat chat = Minecraft.getMinecraft().ingameGUI.getChatGUI();
		chat.printChatMessageWithOptionalDeletion(msg, id);
	}

}

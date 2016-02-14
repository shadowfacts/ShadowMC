package net.shadowfacts.shadowmc.imc;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.shadowfacts.shadowmc.util.LogHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public abstract class IMCHandler {

	private String owner;
	private LogHelper log;
	private Map<String, Consumer<NBTTagCompound>> handlers = new HashMap<>();

	protected IMCHandler(String owner) {
		this.owner = owner;
		log = new LogHelper(owner + "|IMCHandler");
		registerHandlers();
	}

	protected abstract void registerHandlers();

	private void addMessageHandler(String id, Consumer<NBTTagCompound> handler) {
		handlers.put(id, handler);
	}

	public void handleIMCEvent(FMLInterModComms.IMCEvent event) {
		for (FMLInterModComms.IMCMessage message : event.getMessages()) {
			if (!message.isNBTMessage()) {
				log.error("Cannot handle non-NBT IMC message");
				continue;
			}
			NBTTagCompound tag = message.getNBTValue();
			String id = tag.getString("MessageID");
			if (!handlers.containsKey(id)) {
				log.error("Cannot handle IMC message for invalid ID (%s)", id);
				continue;
			}
			handlers.get(id).accept(tag);
		}
	}

}

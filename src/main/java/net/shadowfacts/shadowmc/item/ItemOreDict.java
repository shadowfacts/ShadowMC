package net.shadowfacts.shadowmc.item;

import net.minecraftforge.oredict.OreDictionary;

/**
 * @author shadowfacts
 */
public class ItemOreDict extends ItemBase implements OreDictItem {

	private String oreDict;

	public ItemOreDict(String name, String oreDict) {
		super(name);
		this.oreDict = oreDict;
	}

	public ItemOreDict(String name) {
		this(name, name);
	}

	@Override
	public void registerOreDict() {
		OreDictionary.registerOre(oreDict, this);
	}

}

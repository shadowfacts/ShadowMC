package net.shadowfacts.shadowmc.structure;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StructureManager implements IForgeRegistry<Structure> {

	public static StructureManager INSTANCE = new StructureManager();

	private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Structure.BlockInfo.class, new Structure.BlockInfo.Serializer()).create();

	private BiMap<ResourceLocation, Structure> map = HashBiMap.create();
	private Map<ResourceLocation, IStructureReloadHandler> reloadHandlerMap = new HashMap<>();

	public Structure register(ResourceLocation name) {
		Structure structure = register(name, getClass().getResourceAsStream(String.format("/assets/%s/structures/%s.json", name.getResourceDomain(), name.getResourcePath())));
		registerReloadHandler(name, this::load);
		return structure;
	}

	public Structure register(ResourceLocation name, InputStream in) {
		Structure structure = load(name, in);
		register(structure);
		return structure;
	}

	public Structure load(ResourceLocation name) {
		return load(name, getClass().getResourceAsStream(String.format("/assets/%s/structures/%s.json", name.getResourceDomain(), name.getResourcePath())));
	}

	public Structure load(ResourceLocation name, InputStream in) {
		if (in == null) {
			throw new IllegalArgumentException(String.format("Cannot load non-existent structure %s from null InputStream", name));
		}
		Structure structure = gson.fromJson(new InputStreamReader(in), Structure.class);
		structure.setRegistryName(name);
		return structure;
	}

	public void registerReloadHandler(ResourceLocation structure, IStructureReloadHandler handler) {
		if (reloadHandlerMap.containsKey(structure)) {
			throw new IllegalArgumentException(String.format("Structure reload handler (%s) for '%s' is already registered to %s", handler, structure, reloadHandlerMap.get(structure)));
		}
		reloadHandlerMap.put(structure, handler);
	}

	public void reload() {
		for (Map.Entry<ResourceLocation, IStructureReloadHandler> e : reloadHandlerMap.entrySet()) {
			if (map.containsKey(e.getKey())) {
				Structure structure = e.getValue().reload(e.getKey());
				if (structure != null) {
					map.put(e.getKey(), structure);
				}
			}
		}
	}

	public String toJson(Structure structure) {
		return gson.toJson(structure, Structure.class);
	}

	@Override
	public Class<Structure> getRegistrySuperType() {
		return Structure.class;
	}

	@Override
	public void register(Structure value) {
		if (map.containsKey(value.getRegistryName())) {
			throw new IllegalArgumentException(String.format("Structure (%s) with name '%s' is already registered to %s", value, value.getRegistryName(), map.get(value.getRegistryName())));
		}
		map.put(value.getRegistryName(), value);
	}

	@Override
	public void registerAll(Structure... values) {
		for (Structure structure : values) {
			register(structure);
		}
	}

	@Override
	public boolean containsKey(ResourceLocation key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Structure value) {
		return map.containsValue(value);
	}

	@Override
	public Structure getValue(ResourceLocation key) {
		return map.get(key);
	}

	@Override
	public ResourceLocation getKey(Structure value) {
		return map.inverse().get(value);
	}

	@Override
	public Set<ResourceLocation> getKeys() {
		return map.keySet();
	}

	@Override
	public List<Structure> getValues() {
		return ImmutableList.copyOf(map.values());
	}

	@Override
	public Set<Map.Entry<ResourceLocation, Structure>> getEntries() {
		return map.entrySet();
	}

	@Override
	public <T> T getSlaveMap(ResourceLocation slaveMapName, Class<T> type) {
		return null;
	}

	@Override
	public Iterator<Structure> iterator() {
		return map.values().iterator();
	}

}

package net.shadowfacts.shadowmc.core;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * @author shadowfacts
 */
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("net.shadowfacts.shadowmc")
public class ShadowMCPlugin implements IFMLLoadingPlugin {

	public static final Logger log = LogManager.getLogger("ShadowMC|Core");

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{ "net.shadowfacts.shadowmc.core.ShadowMCTransformer" };
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}

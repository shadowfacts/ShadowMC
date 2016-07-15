package net.shadowfacts.shadowmc.ui;

import lombok.AllArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 * @author shadowfacts
 */
@AllArgsConstructor
public class UIDimensions {

	public final int width;
	public final int height;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UIDimensions that = (UIDimensions) o;

		if (width != that.width) return false;
		return height == that.height;

	}

	@Override
	public int hashCode() {
		int result = width;
		result = 31 * result + height;
		return result;
	}

	@Override
	public String toString() {
		return "UIDimensions{" +
				"preferredWidth=" + width +
				", height=" + height +
				'}';
	}

	public static UIDimensions max() {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		return new UIDimensions(res.getScaledWidth(), res.getScaledHeight());
	}

}

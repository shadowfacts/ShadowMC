package net.shadowfacts.shadowmc.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shadowfacts
 */
@AllArgsConstructor
@Getter
@Setter
public class Color {

//	MC colors
	public static final Color BLACK = new Color(0x00000);
	public static final Color DARK_BLUE = new Color(0x0000AA);
	public static final Color DARK_GREEN = new Color(0x00AA00);
	public static final Color DARK_AQUA = new Color(0x00AAAA);
	public static final Color DARK_RED = new Color(0xAA0000);
	public static final Color DARK_PURPLE = new Color(0xAA00AA);
	public static final Color GOLD = new Color(0xFFAA00);
	public static final Color GRAY = new Color(0xAAAAAA);
	public static final Color DARK_GRAY = new Color(0x555555);
	public static final Color BLUE = new Color(0x5555FF);
	public static final Color GREEN = new Color(0x55FF55);
	public static final Color AQUA = new Color(0x55FFFF);
	public static final Color LIGHT_PURPLE = new Color(0xFF55FF);
	public static final Color YELLOW = new Color(0xFFFF55);
	public static final Color WHITE = new Color(0xFFFFFF);

	private float alpha = 1;
	private float red;
	private float green;
	private float blue;

	public Color(int color) {
		alpha = (float)(color >> 24 & 255) / 255f;
		red = (float)(color >> 16 & 255) / 255f;
		green = (float)(color >> 8 & 255) / 255f;
		blue = (float)(color & 255) / 255f;
	}

	public int toARGB() {
		return Color.toARGB((int)(alpha * 255), (int)(red * 255), (int)(green * 255), (int)(blue * 255));
	}

	@Override
	public String toString() {
		return String.format("Color(alpha=%f, red=%f, green=%f, blue=%f, ARGB=%x", alpha, red, green, blue, toARGB());
	}

	public static int toARGB(int a, int r, int g, int b) {
		return ((a & 255) << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
	}

}

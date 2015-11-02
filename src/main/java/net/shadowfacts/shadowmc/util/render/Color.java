package net.shadowfacts.shadowmc.util.render;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shadowfacts
 */
@Getter
@Setter
@AllArgsConstructor
public class Color {

	private float red, blue, green, alpha;

	public Color(float red, float blue, float green) {
		this(red, blue, green, 1.0f);
	}

}

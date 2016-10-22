package net.shadowfacts.shadowmc.ui.util.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColorFactory implements ValueFactory<Color> {

	public static final ColorFactory INSTANCE = new ColorFactory();

	private static final Map<String, Color> keywords = new HashMap<>();

	static {
//		Other
		keywords.put("transparent", new Color(0, 0, 0, 0));

//		Level 1
		keywords.put("black", new Color(0x000000));
		keywords.put("silver", new Color(0xC0C0C0));
		keywords.put("gray", new Color(0x808080));
		keywords.put("grey", new Color(0x808080));
		keywords.put("white", new Color(0xFFFFFF));
		keywords.put("maroon", new Color(0x800000));
		keywords.put("red", new Color(0xFF0000));
		keywords.put("purple", new Color(0x800080));
		keywords.put("fuchsia", new Color(0xFF00FF));
		keywords.put("green", new Color(0x008000));
		keywords.put("lime", new Color(0x00FF00));
		keywords.put("olive", new Color(0x808000));
		keywords.put("yellow", new Color(0xFFFF00));
		keywords.put("navy", new Color(0x000080));
		keywords.put("blue", new Color(0x0000FF));
		keywords.put("teal", new Color(0x008080));
		keywords.put("aqua", new Color(0x00FFFF));
//		Level 2 rev 1
		keywords.put("orange", new Color(0xFFA500));
//		Level 3
		keywords.put("aliceblue", new Color(0xF0F8FF));
		keywords.put("antiquewhite", new Color(0xFAEBD7));
		keywords.put("aquamarine", new Color(0x7FFFD4));
		keywords.put("azure", new Color(0xF0FFFF));
		keywords.put("beige", new Color(0xF5F5DC));
		keywords.put("bisque", new Color(0xFFE4C4));
		keywords.put("blanchedalmond", new Color(0xFFEBCD));
		keywords.put("blueviolet", new Color(0x8A2BE2));
		keywords.put("brown", new Color(0xA52A2A));
		keywords.put("burlywood", new Color(0xDEB887));
		keywords.put("cadetblue", new Color(0x5F9EA0));
		keywords.put("chartreuse", new Color(0x7FFF00));
		keywords.put("chocolate", new Color(0xD2691E));
		keywords.put("coral", new Color(0xFF7F50));
		keywords.put("cornflowerblue", new Color(0x6495ED));
		keywords.put("cornsilk", new Color(0xFFF8DC));
		keywords.put("crimson", new Color(0xDC143C));
		keywords.put("darkblue", new Color(0x00008B));
		keywords.put("darkcyan", new Color(0x008B8B));
		keywords.put("darkgoldenrod", new Color(0xB8860B));
		keywords.put("darkgray", new Color(0xA9A9A9));
		keywords.put("darkgrey", new Color(0xA9A9A9));
		keywords.put("darkgreen", new Color(0x006400));
		keywords.put("darkkhaki", new Color(0xBDB76B));
		keywords.put("darkmagenta", new Color(0x8B008B));
		keywords.put("darkolivegreen", new Color(0x556B2F));
		keywords.put("darkorange", new Color(0xFF8C00));
		keywords.put("darkorchid", new Color(0x9932CC));
		keywords.put("darkred", new Color(0x8B0000));
		keywords.put("darksalmon", new Color(0xE9967A));
		keywords.put("darkseagreen", new Color(0x8FBC8F));
		keywords.put("darkslateblue", new Color(0x483D8B));
		keywords.put("darkslategray", new Color(0x2F4F4F));
		keywords.put("darkslategrey", new Color(0x2F4F4F));
		keywords.put("darkturquoise", new Color(0x00CED1));
		keywords.put("darkviolet", new Color(0x9400D3));
		keywords.put("deeppink", new Color(0xFF1493));
		keywords.put("deepskyblue", new Color(0x00BFFF));
		keywords.put("dimgray", new Color(0x696969));
		keywords.put("dimgrey", new Color(0x696969));
		keywords.put("dodgerblue", new Color(0x1E90FF));
		keywords.put("firebrick", new Color(0xB22222));
		keywords.put("floralwhite", new Color(0xFFFAF0));
		keywords.put("forestgreen", new Color(0x228B22));
		keywords.put("gainsboro", new Color(0xDCDCDC));
		keywords.put("ghostwhite", new Color(0xF8F8FF));
		keywords.put("gold", new Color(0xFFD700));
		keywords.put("goldenrod", new Color(0xDAA520));
		keywords.put("greenyellow", new Color(0xADFF2F));
		keywords.put("honeydew", new Color(0xF0FFF0));
		keywords.put("hotpink", new Color(0xFF69B4));
		keywords.put("indianred", new Color(0xCD5C5C));
		keywords.put("indigo", new Color(0x4B0082));
		keywords.put("ivory", new Color(0xFFFFF0));
		keywords.put("khaki", new Color(0xF0E68C));
		keywords.put("levender", new Color(0xE6E6FA));
		keywords.put("levenderblush", new Color(0xFFF0F5));
		keywords.put("lawngreen", new Color(0x7CFC00));
		keywords.put("lemonchiffon", new Color(0xFFFACD));
		keywords.put("lightblue", new Color(0xADD8E6));
		keywords.put("lightcoral", new Color(0xF08080));
		keywords.put("lightcyan", new Color(0xE0FFFF));
		keywords.put("lightgoldenrodyellow", new Color(0xFAFAD2));
		keywords.put("lightgray", new Color(0xD3D3D3));
		keywords.put("lightgrey", new Color(0xD3D3D3));
		keywords.put("lightgreen", new Color(0x90EE90));
		keywords.put("lightpink", new Color(0xFFB6C1));
		keywords.put("lightsalmon", new Color(0xFFA07A));
		keywords.put("lightseagreen", new Color(0x20B2AA));
		keywords.put("lightskyblue", new Color(0x87CEFA));
		keywords.put("lightslategray", new Color(0x778899));
		keywords.put("lightslategrey", new Color(0x778899));
		keywords.put("lightsteelblue", new Color(0xB0C4DE));
		keywords.put("lightyellow", new Color(0xFFFFE0));
		keywords.put("limegreen", new Color(0x32CD32));
		keywords.put("linen", new Color(0xFAF0E6));
		keywords.put("mediumaquamarine", new Color(0x66CDAA));
		keywords.put("mediumblue", new Color(0x0000CD));
		keywords.put("mediumorchid", new Color(0xBA55D3));
		keywords.put("mediumpurple", new Color(0x9370DB));
		keywords.put("mediumseagreen", new Color(0x3CB371));
		keywords.put("mediumslateblue", new Color(0x7B68EE));
		keywords.put("mediumspringgreen", new Color(0x00FA9A));
		keywords.put("mediumturquoise", new Color(0x48D1CC));
		keywords.put("mediumvioletred", new Color(0xC71585));
		keywords.put("midnightblue", new Color(0x191970));
		keywords.put("mintcream", new Color(0xF5FFFA));
		keywords.put("mistyrose", new Color(0xFFE4E1));
		keywords.put("moccasin", new Color(0xFFE4B5));
		keywords.put("navajowhite", new Color(0xFFDEAD));
		keywords.put("oldlace", new Color(0xFDF5E6));
		keywords.put("olivedrab", new Color(0x6B8E32));
		keywords.put("orangered", new Color(0xFF4500));
		keywords.put("orchid", new Color(0xDA70D6));
		keywords.put("palegoldenrod", new Color(0xEEE8AA));
		keywords.put("palegreen", new Color(0x98FB98));
		keywords.put("paleturquoise", new Color(0xAFEEEE));
		keywords.put("palevioletred", new Color(0xDB7093));
		keywords.put("papayawhip", new Color(0xFFEFD5));
		keywords.put("peachpuff", new Color(0xFFDAB9));
		keywords.put("peru", new Color(0xCD853F));
		keywords.put("pink", new Color(0xFFC0CB));
		keywords.put("plum", new Color(0xDDA0DD));
		keywords.put("powderblue", new Color(0xB0E0E6));
		keywords.put("rosybrown", new Color(0xBC8F8F));
		keywords.put("royalblue", new Color(0x4169E1));
		keywords.put("saddlebrown", new Color(0x8B4513));
		keywords.put("salmon", new Color(0xFA8072));
		keywords.put("sandybrown", new Color(0xF4A460));
		keywords.put("seagreen", new Color(0x2E8B57));
		keywords.put("seashell", new Color(0xFFF5EE));
		keywords.put("sienna", new Color(0xA0522D));
		keywords.put("skyblue", new Color(0x87CEEB));
		keywords.put("slateblue", new Color(0x6A5ACD));
		keywords.put("slategray", new Color(0x708090));
		keywords.put("slategrey", new Color(0x708090));
		keywords.put("snow", new Color(0xFFFAFA));
		keywords.put("springgreen", new Color(0x00FF7F));
		keywords.put("steelblue", new Color(0x4682B4));
		keywords.put("tan", new Color(0xD2B48C));
		keywords.put("thistle", new Color(0xD8BFD8));
		keywords.put("tomato", new Color(0xFF6347));
		keywords.put("turquoise", new Color(0x40E0D0));
		keywords.put("violet", new Color(0xEE82EE));
		keywords.put("wheat", new Color(0xF5DEB3));
		keywords.put("whitesmoke", new Color(0xF5F5F5));
		keywords.put("yellowgreen", new Color(0x9ACD32));
//		Level 4
		keywords.put("rebeccapurple", new Color(0x663399));
	}

	@Override
	public Color create(String s, Color defaultVal) {
		if (s != null && !s.isEmpty()) {
			if (s.startsWith("#")) {
				s = s.substring(1);

				if (s.length() == 3) {
					s = "" + s.charAt(0) + s.charAt(0) + s.charAt(1) + s.charAt(1) + s.charAt(2) + s.charAt(2);
				}

				try {
					return new Color(Integer.parseInt(s, 16));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
				String keyword = s.toLowerCase();
				Color color = keywords.get(keyword);
				if (color != null) {
					return color;
				}
			}
		}
		return defaultVal;
	}

}

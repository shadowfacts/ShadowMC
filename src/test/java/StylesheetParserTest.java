import net.shadowfacts.shadowlib.util.IOUtils;
import net.shadowfacts.shadowmc.ui.style.stylesheet.Stylesheet;
import net.shadowfacts.shadowmc.ui.style.stylesheet.StylesheetParser;

import java.io.IOException;

/**
 * @author shadowfacts
 */
public class StylesheetParserTest {

	public static void main(String... args) throws IOException {
		Stylesheet sheet = StylesheetParser.parse(IOUtils.copyToString(StylesheetParserTest.class.getResourceAsStream("test.uiss")));
		System.out.println("done");
	}

}

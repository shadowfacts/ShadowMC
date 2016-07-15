package net.shadowfacts.shadowmc.ui.style.stylesheet;

import lombok.AllArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shadowfacts
 */
@AllArgsConstructor
public enum Token {

	INCLUDE(Pattern.compile("@include ([\\w\\-_]+):([\\w\\-_]+)"), s -> {
		return true;
	}, (matcher, lines, stylesheet) -> {
		String id = matcher.group(1) + ":" + matcher.group(2);
		StylesheetParser.parse(StylesheetParser.load(id), stylesheet);
	}, false),
	ID(Pattern.compile("#([\\w\\d\\-_]+)( \\{)?"), s -> {
		return s.equals("}");
	}, (matcher, lines, stylesheet) -> {
		String id = matcher.group(1);

		for (int i = 0; i < lines.length; i++) {
			stylesheet.addIDRule(id, StylesheetParser.parseRule(lines[i]));
		}
	}, true),
	CLASS(Pattern.compile("\\.([\\w\\d\\-_]+)( \\{)?"), s -> {
		return s.equals("}");
	}, (matcher, lines, stylesheet) -> {
		String clazz = matcher.group(1);

		for (int i = 0; i < lines.length; i++) {
			stylesheet.addClassRule(clazz, StylesheetParser.parseRule(lines[i]));
		}
	}, true),
	TYPE(Pattern.compile("([\\w\\d\\-_]+)( \\{)?"), s -> {
		return s.equals("}");
	}, (matcher, lines, stylesheet) -> {
		String type = matcher.group(1);

		for (int i = 0; i < lines.length; i++) {
			stylesheet.addTypeRule(type, StylesheetParser.parseRule(lines[i]));
		}
	}, true);

	public final Pattern startLine;
	public final Predicate<String> stop;
	public final TokenHandler handler;
	public final boolean removeOneMore;

	public interface TokenHandler {
		void handle(Matcher matcher, String[] lines, Stylesheet stylesheet);
	}

}

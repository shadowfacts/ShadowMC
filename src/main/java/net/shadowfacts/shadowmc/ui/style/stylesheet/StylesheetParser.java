package net.shadowfacts.shadowmc.ui.style.stylesheet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowlib.util.IOUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * @author shadowfacts
 */
public class StylesheetParser {

	public static Stylesheet parse(String input) {
		Stylesheet sheet = new Stylesheet();
		parse(input, sheet);
		return sheet;
	}

	public static void parse(String input, Stylesheet sheet) {
		List<String> lines = Arrays.asList(input.split("\n"));
		lines = lines.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.filter(s -> !s.equals("{"))
				.filter(s -> !s.startsWith("//"))
				.collect(Collectors.toList());
		lines = new ArrayList<>(lines);

		lineLoop:
		while (lines.size() > 0) {
			for (Token token : Token.values()) {
				Matcher matcher = token.startLine.matcher(lines.get(0));
				if (matcher.matches()) {
					List<String> tokenLines = new ArrayList<>();
					lines.remove(0);
					while (!token.stop.test(lines.get(0))) {
						tokenLines.add(lines.remove(0));
					}
					if (token.removeOneMore) lines.remove(0);
					token.handler.handle(matcher, tokenLines.toArray(new String[tokenLines.size()]), sheet);
					continue lineLoop;
				}
			}
			throw new RuntimeException("No token was capable of parsing line: " + lines.get(0));
		}
	}

	public static Rule<?> parseRule(String s) {
		s = s.trim();
		String[] bits = s.split(":");
		String rule = bits[0].trim();
		String value = bits[1].trim();
		boolean important = value.endsWith("!important");

		if (important) {
			value = value.replaceAll("!important", "").trim();
		}

		return new Rule<>(rule, value, important);
	}

	public static String load(String id) {
		String[] bits = id.split(":");
		String domain = bits.length == 1 ? "minecraft" : bits[0];
		String path = bits.length == 1 ? bits[0] : bits[1];

		try {
			IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(domain, "gui/" + path + ".uiss"));
			return IOUtils.copyToString(resource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

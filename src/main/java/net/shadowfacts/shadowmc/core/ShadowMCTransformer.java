package net.shadowfacts.shadowmc.core;

import net.minecraft.launchwrapper.IClassTransformer;
import net.shadowfacts.shadowmc.core.transformers.ItemToolTransformer;
import net.shadowfacts.shadowmc.core.transformers.ScreenShotHelperTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ShadowMCTransformer implements IClassTransformer {

	private static final List<String> classes = Arrays.asList(
			"net.minecraft.item.ItemTool",
			"net.minecraft.util.ScreenShotHelper"
	);

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		boolean obfuscated = !name.equals(transformedName);
		int index = classes.indexOf(transformedName);
		return index != -1 ? transform(index, bytes, obfuscated) : bytes;
	}

	public static byte[] transform(int index, byte[] bytes, boolean obfuscated) {
		try {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(bytes);
			classReader.accept(classNode, 0);

			switch (index) {
				case 0:
					ItemToolTransformer.transform(classNode, obfuscated);
					break;
				case 1:
					ScreenShotHelperTransformer.transform(classNode, obfuscated);
					break;
			}

			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		} catch (Exception e) {
			ShadowMCCore.log.error("There was a problem transforming " + classes.get(index));
			e.printStackTrace();
		}

		return bytes;
	}

}

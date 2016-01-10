package net.shadowfacts.shadowmc.core.transformers;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.IChatComponent;
import net.shadowfacts.shadowmc.core.ShadowMCCore;
import net.shadowfacts.shadowmc.util.ScreenShotHelper;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.io.File;
import java.util.Optional;

/**
 * @author shadowfacts
 */
public class ScreenShotHelperTransformer implements Opcodes {

	public static void transform(ClassNode classNode, boolean obfuscated) {
		ShadowMCCore.log.info("Transforming net.minecraft.util.ScreenShotHelper");

		final String SAVE_SCREENSHOT = obfuscated ? "func_148259_a" : "saveScreenshot";
		final String SAVE_SCREENSHOT_DESC = Type.getMethodDescriptor(Type.getType(IChatComponent.class),
				Type.getType(File.class), Type.getType(String.class), Type.INT_TYPE, Type.INT_TYPE, Type.getType(Framebuffer.class));

		Optional<MethodNode> saveScreenshot = classNode.methods.stream()
				.filter(method -> method.name.equals(SAVE_SCREENSHOT))
				.filter(method -> method.desc.equals(SAVE_SCREENSHOT_DESC))
				.findFirst();

		if (saveScreenshot.isPresent()) {

			MethodNode method = saveScreenshot.get();

			InsnList toInsert = new InsnList();

			toInsert.add(new VarInsnNode(ALOAD, 0));
			toInsert.add(new VarInsnNode(ALOAD, 1));
			toInsert.add(new VarInsnNode(ILOAD, 2));
			toInsert.add(new VarInsnNode(ILOAD, 3));
			toInsert.add(new VarInsnNode(ALOAD, 4));
			toInsert.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ScreenShotHelper.class), "saveScreenshot", SAVE_SCREENSHOT_DESC, false));
			toInsert.add(new InsnNode(ARETURN));

			method.instructions.insertBefore(method.instructions.getFirst(), toInsert);

		} else {
			ShadowMCCore.log.error("The correct saveScreenshot method could not be found in ScreenShotHelper");
		}
	}

}

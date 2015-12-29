package net.shadowfacts.shadowmc.core.transformers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.core.ShadowMCPlugin;
import net.shadowfacts.shadowmc.event.EventManager;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

/**
 * @author shadowfacts
 */
public class ItemToolTransformer implements Opcodes {

	public static void transform(ClassNode classNode, boolean obfsucated) {
		ShadowMCPlugin.log.info("Transforming net.minecraft.item.ItemTool");

		final String ON_ITEM_USE = obfsucated ? "func_77648_a" : "onItemUse";
		Type intType = Type.getType(int.class);
		Type floatType = Type.getType(float.class);
		final String ON_ITEM_USE_DESC = Type.getMethodDescriptor(Type.getType(boolean.class),
				Type.getType(ItemStack.class), Type.getType(EntityPlayer.class), Type.getType(World.class),
				intType, intType, intType, intType, floatType, floatType, floatType);

		MethodNode onItemUse = new MethodNode(ACC_PUBLIC, ON_ITEM_USE, ON_ITEM_USE_DESC, null, null);

		LabelNode L0 = new LabelNode(new Label());

		onItemUse.instructions.add(L0);
		onItemUse.instructions.add(new VarInsnNode(ALOAD, 1));
		onItemUse.instructions.add(new VarInsnNode(ALOAD, 2));
		onItemUse.instructions.add(new VarInsnNode(ALOAD, 3));
		onItemUse.instructions.add(new VarInsnNode(ALOAD, 4));
		onItemUse.instructions.add(new VarInsnNode(ALOAD, 5));
		onItemUse.instructions.add(new VarInsnNode(FLOAD, 6));
		onItemUse.instructions.add(new VarInsnNode(FLOAD, 7));
		onItemUse.instructions.add(new VarInsnNode(FLOAD, 8));
		onItemUse.instructions.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventManager.class), "onToolUse", ON_ITEM_USE_DESC, false));
		onItemUse.instructions.add(new InsnNode(IRETURN));

		classNode.methods.add(onItemUse);
		ShadowMCPlugin.log.info("Finish transforming net.minecraft.item.ItemTool");
	}

}

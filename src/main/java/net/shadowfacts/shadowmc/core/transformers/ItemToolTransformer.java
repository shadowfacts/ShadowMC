package net.shadowfacts.shadowmc.core.transformers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.core.ShadowMCCore;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

/**
 * @author shadowfacts
 */
public class ItemToolTransformer implements Opcodes {

	public static void transform(ClassNode classNode, boolean obfsucated) {
		ShadowMCCore.log.info("Transforming net.minecraft.item.ItemTool");

		final String ON_ITEM_USE = obfsucated ? "func_77648_a" : "onItemUse";
		final String ON_ITEM_USE_DESC = Type.getMethodDescriptor(Type.getType(boolean.class),
				Type.getType(ItemStack.class), Type.getType(EntityPlayer.class), Type.getType(World.class),
				Type.getType(BlockPos.class), Type.getType(EnumFacing.class),
				Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE);

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
		onItemUse.instructions.add(new MethodInsnNode(INVOKESTATIC, "net/shadowfacts/shadowmc/event/EventManager", "onToolUse", ON_ITEM_USE_DESC, false));
		onItemUse.instructions.add(new InsnNode(IRETURN));

		classNode.methods.add(onItemUse);
		ShadowMCCore.log.info("Finish transforming net.minecraft.item.ItemTool");
	}

}

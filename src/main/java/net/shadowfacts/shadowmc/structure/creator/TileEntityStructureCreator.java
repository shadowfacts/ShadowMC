package net.shadowfacts.shadowmc.structure.creator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.shadowfacts.shadowlib.util.DesktopUtils;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.structure.Structure;
import net.shadowfacts.shadowmc.structure.StructureManager;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
public class TileEntityStructureCreator extends BaseTileEntity {

	@AutoSerializeNBT
	private int xSize = 3;
	@AutoSerializeNBT
	private int ySize = 3;
	@AutoSerializeNBT
	private int zSize = 3;

	private boolean firstTick = true;

	@Override
	public void onLoad() {
		if (worldObj.isRemote) {
			ShadowMC.network.sendToServer(new PacketRequestTEUpdate(this));
		}
	}

	void handleActivated(EntityPlayer player, EnumFacing side) {
		if (!worldObj.isRemote) {
			if (player.isSneaking()) {
				DesktopUtils.copyToClipboard(StructureManager.INSTANCE.toJson(new Structure(worldObj, getBox())));
				player.addChatComponentMessage(new TextComponentString("Copied structure JSON to clipboard"));
			} else {
				xSize += side.getFrontOffsetX();
				ySize += side.getFrontOffsetY();
				zSize += side.getFrontOffsetZ();
				markDirty();
				sync();
				player.addChatComponentMessage(new TextComponentString(String.format("%dx%dx%d", xSize, ySize, zSize)));
			}
		}
	}

	AxisAlignedBB getBox() {
		return new AxisAlignedBB(pos.getX() + 1, pos.getY(), pos.getZ() + 1, pos.getX() + 1 + xSize, pos.getY() + ySize, pos.getZ() + 1 + zSize);
	}

}

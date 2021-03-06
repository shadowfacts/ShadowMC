package net.shadowfacts.shadowmc.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.shadowfacts.mirror.Mirror;
import net.shadowfacts.mirror.MirrorClass;
import net.shadowfacts.mirror.MirrorField;
import net.shadowfacts.shadowlib.util.Pair;
import net.shadowfacts.shadowmc.util.Vector3d;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author shadowfacts
 */
public abstract class PacketBase<REQ extends PacketBase, REPLY extends IMessage> implements IMessage, IMessageHandler<REQ, REPLY> {

	private static Map<MirrorClass<?>, Pair<Reader, Writer>> handlers = new HashMap<>();

	static {
		addHandlers(byte.class, PacketBuffer::readByte, (b, buf) -> buf.writeByte(b));
		addHandlers(short.class, PacketBuffer::readShort, (s, buf) -> buf.writeShort(s));
		addHandlers(int.class, PacketBuffer::readInt, (i, buf) -> buf.writeInt(i));
		addHandlers(long.class, PacketBuffer::readLong, (l, buf) -> buf.writeLong(l));
		addHandlers(float.class, PacketBuffer::readFloat, (f, buf) -> buf.writeFloat(f));
		addHandlers(boolean.class, PacketBuffer::readBoolean, (b, buf) -> buf.writeBoolean(b));
		addHandlers(char.class, PacketBuffer::readChar, (c, buf) -> buf.writeChar(c));
		addHandlers(String.class, ByteBufUtils::readUTF8String, (s, buf) -> ByteBufUtils.writeUTF8String(buf, s));
		addHandlers(NBTTagCompound.class, ByteBufUtils::readTag, (tag, buf) -> ByteBufUtils.writeTag(buf, tag));
		addHandlers(ItemStack.class, ByteBufUtils::readItemStack, (stack, buf) -> ByteBufUtils.writeItemStack(buf, stack));
		addHandlers(BlockPos.class, PacketBuffer::readBlockPos, (pos, buf) -> buf.writeBlockPos(pos));
		addHandlers(ITextComponent.class, buf -> {
			try {
				return buf.readTextComponent();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}, (component, buf) -> buf.writeTextComponent(component));
		addHandlers(Vector3d.class, buf -> new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble()), (vec, buf) -> {
			buf.writeDouble(vec.x);
			buf.writeDouble(vec.y);
			buf.writeDouble(vec.z);
		});
		addHandlers(UUID.class, buf -> new UUID(buf.readLong(), buf.readLong()), (val, buf) -> {
			buf.writeLong(val.getMostSignificantBits());
			buf.writeLong(val.getLeastSignificantBits());
		});
		addHandlers(EnumHand.class, buf -> buf.readBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND, (val, buf) -> buf.writeBoolean(val == EnumHand.MAIN_HAND));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer packetBuf = new PacketBuffer(buf);
		try {
			Mirror.of(getClass())
					.declaredFields()
					.filter(PacketBase::accepts)
					.forEach(f -> read(f, packetBuf));
		} catch (Exception e) {
			throw new RuntimeException("Couldn't read packet " + getClass().getName(), e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer packetBuf = new PacketBuffer(buf);
		try {
			Mirror.of(getClass())
					.declaredFields()
					.filter(PacketBase::accepts)
					.forEach(f -> write(f, packetBuf));
		} catch (Exception e) {
			throw new RuntimeException("Couldn't write packet " + getClass().getName(), e);
		}
	}

	private void read(MirrorField f, PacketBuffer buf) {
		f.setAccessible(true);
		f.set(this, getHandlers(f).getLeft().read(buf));
	}

	private void write(MirrorField f, PacketBuffer buf) {
		f.setAccessible(true);
		getHandlers(f).getRight().write(f.get(this), buf);
	}

	private static boolean accepts(MirrorField f) {
		return !f.isFinal() && !f.isStatic() && !f.hasModifier(Modifier.TRANSIENT) &&
				handlers.containsKey(f.type());
	}

	public static Pair<Reader, Writer> getHandlers(MirrorField f) {
		return getHandlers(f.type());
	}

	public static Pair<Reader, Writer> getHandlers(MirrorClass<?> type) {
		return handlers.get(type);
	}

	public static <T> void addHandlers(Class<T> type, Reader<T> reader, Writer<T> writer) {
		handlers.put(Mirror.of(type), new Pair<>(reader, writer));
	}

	@FunctionalInterface
	public interface Reader<T> {
		T read(PacketBuffer buf);
	}

	@FunctionalInterface
	public interface Writer<T> {
		void write(T val, PacketBuffer buf);
	}

}

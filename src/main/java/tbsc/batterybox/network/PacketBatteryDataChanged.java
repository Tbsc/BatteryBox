package tbsc.batterybox.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import tbsc.batterybox.battery.TileBatteryBox;

/**
 * @author Tbsc on 10/11/2015, 15:48
 *
 * Gets sent whenever the battery box's data (specificly transfer rates) to notify the *server*.
 */
public class PacketBatteryDataChanged implements IMessage {

    public int newMaxExtract;
    public int newMaxReceive;
    public int x, y, z;

    public PacketBatteryDataChanged() {}

    public PacketBatteryDataChanged(int x, int y, int z, int newMaxExtract, int newMaxReceive) {
        this.newMaxExtract = newMaxExtract;
        this.newMaxReceive = newMaxReceive;

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.newMaxExtract = buf.readInt();
        this.newMaxReceive = buf.readInt();

        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(newMaxExtract);
        buf.writeInt(newMaxReceive);

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public static class Handler implements IMessageHandler<PacketBatteryDataChanged, IMessage> {

        // Packet is received on server, update tile
        @Override
        public IMessage onMessage(PacketBatteryDataChanged message, MessageContext ctx) {
            TileBatteryBox batteryBox = (TileBatteryBox) MinecraftServer.getServer().getEntityWorld().getTileEntity(message.x, message.y, message.z);
            batteryBox.setMaxExtract(message.newMaxExtract);
            batteryBox.setMaxReceive(message.newMaxReceive);
            return null;
        }

    }

}

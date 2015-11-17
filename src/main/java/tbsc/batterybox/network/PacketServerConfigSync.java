package tbsc.batterybox.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tbsc on 16/11/2015, 21:00
 */
public class PacketServerConfigSync implements IMessage {

    private ConcurrentHashMap<String, Integer> serverConfig = new ConcurrentHashMap<String, Integer>();

    @Override
    public void fromBytes(ByteBuf buf) {
        serverConfig.put("maxCapacityT1", buf.readInt());
        serverConfig.put("maxExtractT1", buf.readInt());
        serverConfig.put("maxReceiveT1", buf.readInt());

        serverConfig.put("maxCapacityT2", buf.readInt());
        serverConfig.put("maxExtractT2", buf.readInt());
        serverConfig.put("maxReceiveT2", buf.readInt());

        serverConfig.put("maxCapacityT3", buf.readInt());
        serverConfig.put("maxExtractT3", buf.readInt());
        serverConfig.put("maxReceiveT3", buf.readInt());

        serverConfig.put("maxCapacityT4", buf.readInt());
        serverConfig.put("maxExtractT4", buf.readInt());
        serverConfig.put("maxReceiveT4", buf.readInt());

        serverConfig.put("maxCapacityT5", buf.readInt());
        serverConfig.put("maxExtractT5", buf.readInt());
        serverConfig.put("maxReceiveT5", buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(serverConfig.get("maxCapacityT1"));
        buf.writeInt(serverConfig.get("maxExtractT1"));
        buf.writeInt(serverConfig.get("maxReceiveT1"));

        buf.writeInt(serverConfig.get("maxCapacityT2"));
        buf.writeInt(serverConfig.get("maxExtractT2"));
        buf.writeInt(serverConfig.get("maxReceiveT2"));

        buf.writeInt(serverConfig.get("maxCapacityT3"));
        buf.writeInt(serverConfig.get("maxExtractT3"));
        buf.writeInt(serverConfig.get("maxReceiveT3"));

        buf.writeInt(serverConfig.get("maxCapacityT4"));
        buf.writeInt(serverConfig.get("maxExtractT4"));
        buf.writeInt(serverConfig.get("maxReceiveT4"));

        buf.writeInt(serverConfig.get("maxCapacityT5"));
        buf.writeInt(serverConfig.get("maxExtractT5"));
        buf.writeInt(serverConfig.get("maxReceiveT5"));
    }

    public static class Handler implements IMessageHandler<PacketServerConfigSync, IMessage> {

        @Override
        public IMessage onMessage(PacketServerConfigSync message, MessageContext ctx) {
            return null;
        }

    }

}

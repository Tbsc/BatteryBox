package tbsc.batterybox.proxy;

import tbsc.batterybox.BatteryBox;
import tbsc.batterybox.battery.TileBatteryBox;
import tbsc.batterybox.network.PacketBatteryDataChanged;

/**
 * @author Tbsc on 10/11/2015, 18:21
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void sendPacketNewExtract(TileBatteryBox tile, int newMaxExtract) {
        if (tile.getWorldObj().isRemote)
            BatteryBox.network.sendToServer(new PacketBatteryDataChanged(tile.xCoord, tile.yCoord, tile.zCoord,
                    newMaxExtract, tile.getMaxReceive()));
    }

    @Override
    public void sendPacketNewReceive(TileBatteryBox tile, int newMaxReceive) {
        if (tile.getWorldObj().isRemote)
            BatteryBox.network.sendToServer(new PacketBatteryDataChanged(tile.xCoord, tile.yCoord, tile.zCoord,
                    tile.getMaxExtract(), newMaxReceive));
    }

}

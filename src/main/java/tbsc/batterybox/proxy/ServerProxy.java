package tbsc.batterybox.proxy;

import tbsc.batterybox.battery.TileBatteryBox;

/**
 * @author Tbsc on 10/11/2015, 18:21
 */
public class ServerProxy extends CommonProxy {

    @Override
    public void sendPacketNewExtract(TileBatteryBox tile, int newMaxExtract) {
        // NO-OP
    }

    @Override
    public void sendPacketNewReceive(TileBatteryBox tile, int newMaxReceive) {
        // NO-OP
    }

}

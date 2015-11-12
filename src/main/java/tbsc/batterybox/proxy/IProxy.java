package tbsc.batterybox.proxy;

import tbsc.batterybox.battery.TileBatteryBox;

/**
 * @author Tbsc on 10/11/2015, 18:21
 */
public interface IProxy {

    void sendPacketNewExtract(TileBatteryBox tile, int newMaxExtract);
    void sendPacketNewReceive(TileBatteryBox tile, int newMaxReceive);

}

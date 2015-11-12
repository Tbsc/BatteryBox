package tbsc.batterybox.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tbsc.batterybox.battery.TileBatteryBox;

/**
 * @author Tbsc on 6/11/2015, 9:15
 */
public class TileInit {

    public static void init() {
        GameRegistry.registerTileEntity(TileBatteryBox.class, "tileBatteryBox");
    }

}

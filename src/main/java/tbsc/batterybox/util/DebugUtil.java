package tbsc.batterybox.util;

import cpw.mods.fml.common.FMLLog;

/**
 * @author Tbsc on 12/11/2015, 17:58
 */
public class DebugUtil {

    public static void debug(Object... what) { // Simple debug method, used to check what is going
        FMLLog.info("EXECUTED: %s", what);
    }

}

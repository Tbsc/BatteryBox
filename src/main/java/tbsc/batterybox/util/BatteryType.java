package tbsc.batterybox.util;

/**
 * @author Tbsc on 6/11/2015, 7:54
 */
public enum BatteryType {

    COAL(0), IRON(1), GOLD(2), DIAMOND(3), CREATIVE(4), UNKNOWN(9001);

    int type;

    BatteryType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static BatteryType getFromId(int id) {
        switch (id) {
            case 0:
                return BatteryType.COAL;
            case 1:
                return BatteryType.IRON;
            case 2:
                return BatteryType.GOLD;
            case 3:
                return BatteryType.DIAMOND;
            case 4:
                return BatteryType.CREATIVE;
            default:
                return BatteryType.UNKNOWN;
        }
    }

}

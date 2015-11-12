package tbsc.batterybox.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tbsc.batterybox.battery.BlockBatteryBox;
import tbsc.batterybox.util.BatteryType;

/**
 * @author Tbsc on 5/11/2015, 20:32
 */
public class BlockInit {

    public static BlockBatteryBox batteryBoxT1;
    public static BlockBatteryBox batteryBoxT2;
    public static BlockBatteryBox batteryBoxT3;
    public static BlockBatteryBox batteryBoxT4;

    public static void init() {
        batteryBoxT1 = new BlockBatteryBox(BatteryType.COAL);
        batteryBoxT2 = new BlockBatteryBox(BatteryType.IRON);
        batteryBoxT3 = new BlockBatteryBox(BatteryType.GOLD);
        batteryBoxT4 = new BlockBatteryBox(BatteryType.DIAMOND);

        GameRegistry.registerBlock(batteryBoxT1, "batteryBoxT1");
        GameRegistry.registerBlock(batteryBoxT2, "batteryBoxT2");
        GameRegistry.registerBlock(batteryBoxT3, "batteryBoxT3");
        GameRegistry.registerBlock(batteryBoxT4, "batteryBoxT4");

        OreDictionary.registerOre("batteryBox", batteryBoxT1);
        OreDictionary.registerOre("batteryBoxT1", batteryBoxT1);
        OreDictionary.registerOre("batteryBoxL1", batteryBoxT1);
        OreDictionary.registerOre("batteryBoxL2", batteryBoxT1);
        OreDictionary.registerOre("batteryBoxL3", batteryBoxT1);

        OreDictionary.registerOre("batteryBox", batteryBoxT2);
        OreDictionary.registerOre("batteryBoxT2", batteryBoxT2);
        OreDictionary.registerOre("batteryBoxL2", batteryBoxT2);
        OreDictionary.registerOre("batteryBoxL3", batteryBoxT2);

        OreDictionary.registerOre("batteryBox", batteryBoxT3);
        OreDictionary.registerOre("batteryBoxT3", batteryBoxT3);
        OreDictionary.registerOre("batteryBoxL3", batteryBoxT3);

        OreDictionary.registerOre("batteryBox", batteryBoxT4);
        OreDictionary.registerOre("batteryBoxT4", batteryBoxT4);
    }

}

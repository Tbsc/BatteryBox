package tbsc.batterybox.client.gui;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.ConfigElement;
import tbsc.batterybox.BatteryBox;
import tbsc.batterybox.reference.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tbsc on 14/11/2015, 15:25
 */
public class GuiConfigBB extends GuiConfig {

    public GuiConfigBB(GuiScreen parent) {
        super(parent, getConfigElements(), Reference.MODID, true, true, EnumChatFormatting.AQUA + "BatteryBox Configuration");
    }

    /** Compiles a list of config elements
     *
     * Made by ljfa from FTB forums - thanks!
     * @return list of category config buttons
     */
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        list.add(categoryElement(Reference.Config.TIER_1, "Tier 1 (Coal)", "batterybox.cfg.tooltip.t1"));
        list.add(categoryElement(Reference.Config.TIER_2, "Tier 2 (Iron)", "batterybox.cfg.tooltip.t2"));
        list.add(categoryElement(Reference.Config.TIER_3, "Tier 3 (Gold)", "batterybox.cfg.tooltip.t3"));
        list.add(categoryElement(Reference.Config.TIER_3, "Tier 4 (Gold)", "batterybox.cfg.tooltip.t4"));
        list.add(categoryElement(Reference.Config.TIER_3, "Tier 5 (Creative)", "batterybox.cfg.tooltip.t5"));

        return list;
    }

    /** Creates a button linking to another screen where all options of the category are available
     *
     * Made by ljfa from FTB forums - thanks!
     * @param category category of which button will go to
     * @param name name of button
     * @param tooltip_key text to show on tooltip render
     * @return button config element
     */
    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
                new ConfigElement(BatteryBox.config.getCategory(category)).getChildElements());
    }

}

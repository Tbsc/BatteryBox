package tbsc.batterybox.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author Tbsc on 10/11/2015, 17:52
 */
public class RecipeInit {

    // Recipes
    public static void init() {
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockInit.batteryBoxT1,
                "RCR",
                "CIC",
                "RCR",
                'R', Items.redstone, 'C', Blocks.coal_block, 'I', Items.iron_ingot));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockInit.batteryBoxT2,
                "RIR",
                "IBI",
                "RIR",
                'R', Items.redstone, 'I', Blocks.iron_block, 'B', "batteryBoxL1"));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockInit.batteryBoxT3,
                "RGR",
                "GBG",
                "RGR",
                'R', Items.redstone, 'G', Blocks.gold_block, 'B', "batteryBoxL2"));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockInit.batteryBoxT4,
                "RDR",
                "DBD",
                "RDR",
                'R', Items.redstone, 'D', Blocks.diamond_block, 'B', "batteryBoxL3"));
    }

}

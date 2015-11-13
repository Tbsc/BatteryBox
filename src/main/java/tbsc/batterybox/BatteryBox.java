package tbsc.batterybox;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tbsc.batterybox.client.gui.GuiHandlerBB;
import tbsc.batterybox.init.BlockInit;
import tbsc.batterybox.init.RecipeInit;
import tbsc.batterybox.init.TileInit;
import tbsc.batterybox.network.PacketBatteryDataChanged;
import tbsc.batterybox.proxy.IProxy;
import tbsc.batterybox.reference.Reference;

/**
 * @author Tbsc on 5/11/2015, 20:26
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERION, dependencies = "required-after:CoFHCore")
public class BatteryBox {

    @Mod.Instance(Reference.MODID)
    public static BatteryBox instance;

    public static Configuration config; // Config, not used rn
    public static SimpleNetworkWrapper network; // Used for network stuff, mostly packets

    public static CreativeTabs tabBB; // BatteryBox's creative tab

    @SidedProxy(serverSide = Reference.SERVER_PROXY, clientSide = Reference.CLIENT_PROXY)
    public static IProxy proxy; // Proxy, used to call methods *only* on 1 side, depends on the side (client/server)

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Init stuff
        config = new Configuration(event.getSuggestedConfigurationFile());

        tabBB = new CreativeTabs(Reference.MODID) {

            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(BlockInit.batteryBoxT1);
            }

            @Override
            public String getTranslatedTabLabel() {
                return Reference.NAME;
            }
        };

        BlockInit.init();
        TileInit.init();
        syncConfig();

        this.network = NetworkRegistry.INSTANCE.newSimpleChannel("batteryBox");
        this.network.registerMessage(PacketBatteryDataChanged.Handler.class, PacketBatteryDataChanged.class, 0, Side.SERVER);

        // Register to waila
        FMLInterModComms.sendMessage("Waila", "register", "tbsc.batterybox.waila.BBWailaDataProvider.callbackRegister");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerBB()); // Register guis
        RecipeInit.init(); // Register recipes
    }

    public static void syncConfig() {
        try {
            // Load config
            config.load();

            // Read props from config, when there will be any
        } catch (Exception e) {
            // Exception
        } finally {
            // Save props to config
            if (config.hasChanged()) config.save();
        }
    }

}

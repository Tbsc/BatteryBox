package tbsc.batterybox.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tbsc.batterybox.battery.TileBatteryBox;
import tbsc.batterybox.reference.Reference;

/**
 * @author Tbsc on 6/11/2015, 9:18
 */
public class GuiHandlerBB implements IGuiHandler {

    // Since the battery boxes aren't containers (they only display, not store items), they have no server side
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    // Used to open guis based on the ID gived, IDs are unique to every mod (2 mods can have the id 1, it won't matter)
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_BB)
            return new GuiBatteryBox((TileBatteryBox) world.getTileEntity(x, y, z));

        return null;
    }

}

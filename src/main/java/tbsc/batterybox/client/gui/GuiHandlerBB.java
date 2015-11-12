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

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_BBT1)
            return new GuiBatteryBox((TileBatteryBox) world.getTileEntity(x, y, z));
        if (ID == Reference.GUI_BBT2)
            return new GuiBatteryBox((TileBatteryBox) world.getTileEntity(x, y, z));
        if (ID == Reference.GUI_BBT3)
            return new GuiBatteryBox((TileBatteryBox) world.getTileEntity(x, y, z));
        if (ID == Reference.GUI_BBT4)
            return new GuiBatteryBox((TileBatteryBox) world.getTileEntity(x, y, z));

        return null;
    }

}

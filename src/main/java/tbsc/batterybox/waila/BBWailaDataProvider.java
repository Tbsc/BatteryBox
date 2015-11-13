package tbsc.batterybox.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tbsc.batterybox.battery.TileBatteryBox;

import java.util.List;

/**
 * @author Tbsc on 7/11/2015, 8:28
 */
public class BBWailaDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    // Right now, all it does is add the stored energy to WAILA
    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        TileBatteryBox tile = (TileBatteryBox) iWailaDataAccessor.getTileEntity();
        list.clear(); // I don't want the default WAILA energy text, since it doesn't work on my mod
        list.add(String.format("%sRF / %sRF", tile.getEnergyStored(), tile.getMaxEnergyStored(ForgeDirection.DOWN)));
        list.add(String.format("In: %s RF/t, Out: %s RF/t", tile.getMaxReceive(), tile.getMaxExtract()));
        return list;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound nbtTagCompound, World world, int i, int i1, int i2) {
        return nbtTagCompound;
    }

    // Gets called by WAILA on runtime
    public static void callbackRegister(IWailaRegistrar registrar) {
        registrar.registerBodyProvider(new BBWailaDataProvider(), TileBatteryBox.class);
    }

}

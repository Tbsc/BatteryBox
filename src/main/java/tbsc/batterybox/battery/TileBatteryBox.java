package tbsc.batterybox.battery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import tbsc.batterybox.util.BatteryStatsUtil;
import tbsc.batterybox.util.BatteryType;

import java.util.List;

/**
 * @author Tbsc on 5/11/2015, 20:38
 */
public class TileBatteryBox extends TileBatteryBoxBB {

    private BatteryType type;
    protected int maxDefaultExtract;
    protected int maxDefaultReceive;
    protected int capacity;
    protected int maxExtract;
    protected int maxReceive;

    protected int energy;

    public TileBatteryBox(BatteryStatsUtil.BatteryStats batteryStats, BatteryType type) {
        this.type = type;
        this.maxDefaultExtract = batteryStats.getMaxDefaultExtract();
        this.maxDefaultReceive = batteryStats.getMaxDefaultReceive();
        this.capacity = batteryStats.getMaxEnergy();
        this.maxExtract = batteryStats.getMaxExtract();
        this.maxReceive = batteryStats.getMaxReceive();
    }

    @Override
    public void updateEntity() {

    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            NBTTagCompound nbt = new NBTTagCompound();
            writeToNBT(nbt);
            List<EntityPlayer> players = worldObj.playerEntities;
            for (EntityPlayer player : players) {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;
                playerMP.playerNetServerHandler.sendPacket(new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt));
            }
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
            NBTTagCompound nbt = new NBTTagCompound();
            writeToNBT(nbt);
            List<EntityPlayer> players = worldObj.playerEntities;
            for (EntityPlayer player : players) {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;
                playerMP.playerNetServerHandler.sendPacket(new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt));
            }
        }
        return energyExtracted;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        this.type = BatteryType.getFromId(tag.getInteger("BatteryType"));

        int energyStored = tag.getInteger("EnergyLevel");
        this.energy = energyStored;
        this.maxExtract = tag.getInteger("MaxExtract");
        this.maxReceive = tag.getInteger("MaxReceive");
        this.capacity = tag.getInteger("Capacity");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("BatteryType", type.getType());
        tag.setInteger("EnergyLevel", energy);
        tag.setInteger("MaxExtract", maxExtract);
        tag.setInteger("MaxReceive", maxReceive);
        tag.setInteger("Capacity", capacity);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return !(energy == capacity); // So it won't accept energy if full, kinda tricky but works
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();
        if (nbt.hasKey("EnergyLevel")) {
            this.energy = nbt.getInteger("EnergyLevel");
        }
        if (nbt.hasKey("BatteryType")) {
            this.type = BatteryType.getFromId(nbt.getInteger("BatteryType"));
        }
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    /* Energy Handling */

    public int getMaxDefaultExtract() {
        return maxDefaultExtract;
    }

    public int getMaxDefaultReceive() {
        return maxDefaultReceive;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    public void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    public int getMaxReceive() {
        return maxReceive;
    }

    public void setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    public int getEnergyStored() {
        return energy;
    }
}

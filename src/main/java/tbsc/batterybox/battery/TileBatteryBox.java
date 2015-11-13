package tbsc.batterybox.battery;

import cofh.api.energy.EnergyStorage;
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

    // Tile entity's data
    private BatteryType type;
    protected int maxDefaultExtract;
    protected int maxDefaultReceive;
    protected int capacity;
    protected int maxExtract;
    protected int maxReceive;

    protected int energy;

    // Data init
    public TileBatteryBox(BatteryStatsUtil.BatteryStats batteryStats, BatteryType type) {
        this.type = type;
        this.maxDefaultExtract = batteryStats.getMaxDefaultExtract();
        this.maxDefaultReceive = batteryStats.getMaxDefaultReceive();
        this.capacity = batteryStats.getMaxEnergy();
        this.maxExtract = batteryStats.getMaxExtract();
        this.maxReceive = batteryStats.getMaxReceive();

        this.storage = new EnergyStorage(capacity, maxExtract, maxReceive); // for the sake of WAILA
    }

    @Override
    public void updateEntity() {
        /*
        I don't have a reason to update the tile entity on tick, instead I'm doing it when an action is occuring, to
        save system resources (cuz doing stuff on every *tick* is system-intensive!)
        */
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive)); // Calculate energy received

        if (!simulate && type != BatteryType.CREATIVE) {
            energy += energyReceived; // Receive energy, UNLESS is set to simulate OR the batterybox is a creative type
        }

        // Send update packet to all players
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        List<EntityPlayer> players = worldObj.playerEntities;
        for (EntityPlayer player : players) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            playerMP.playerNetServerHandler.sendPacket(new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt));
        }

        return energyReceived; // Return energy received, for usage
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract)); // Calculate energy extracted

        if (!simulate && type != BatteryType.CREATIVE) {
            energy -= energyExtracted; // Extract energy, UNLESS is set to simulate extraction OR is a creative type
        }

        // Send update packet
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        List<EntityPlayer> players = worldObj.playerEntities;
        for (EntityPlayer player : players) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            playerMP.playerNetServerHandler.sendPacket(new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt));
        }

        return energyExtracted;
    }

    // Get NBT data, and reassign the tile's data to the 'newly' saved NBT data
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

    // Write data to NBT, for usage on next load
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("BatteryType", type.getType());
        tag.setInteger("EnergyLevel", energy);
        tag.setInteger("MaxExtract", maxExtract);
        tag.setInteger("MaxReceive", maxReceive);
        tag.setInteger("Capacity", capacity);
    }

    // It will accept energy from any side, as I don't have any IO configuration
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    // Packet used when updating tile entity
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt);
    }

    // Gets called when the above detailed packet gets received
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();
        if (nbt.hasKey("EnergyLevel")) {
            this.energy = nbt.getInteger("EnergyLevel"); // Update energy level
        }
        if (nbt.hasKey("BatteryType")) {
            this.type = BatteryType.getFromId(nbt.getInteger("BatteryType")); // Update type
        }
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord); // Mark the block for update, so it will be "reloaded"
    }

    @Override
    public void onChunkUnload() { // Write nbt data when unloading the chunk to save data
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
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

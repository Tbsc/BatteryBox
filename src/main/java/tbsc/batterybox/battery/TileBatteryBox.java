package tbsc.batterybox.battery;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import tbsc.batterybox.util.BatteryStatsUtil;
import tbsc.batterybox.util.BatteryType;

import java.util.List;

/**
 * @author Tbsc on 5/11/2015, 20:38
 */
public class TileBatteryBox extends TileBatteryBoxBB implements IEnergyConnection {

    /**
     * Tile entity's data as I'm *not* using {@link EnergyStorage}
     */
    private BatteryType type;
    protected int maxDefaultExtract;
    protected int maxDefaultReceive;
    protected int capacity;
    protected int maxExtract;
    protected int maxReceive;

    protected int energy;

    public TileBatteryBox() {}

    // Data init
    public TileBatteryBox(BatteryStatsUtil.BatteryStats batteryStats, BatteryType type) {
        this.type = type;
        this.maxDefaultExtract = batteryStats.getMaxDefaultExtract();
        this.maxDefaultReceive = batteryStats.getMaxDefaultReceive();
        this.capacity = batteryStats.getMaxEnergy();
        this.maxExtract = batteryStats.getMaxExtract();
        this.maxReceive = batteryStats.getMaxReceive();

        this.storage = new EnergyStorage(this.capacity, this.maxExtract, this.maxReceive);
    }

    @Override
    public void updateEntity() {
        storage.setEnergyStored(this.energy);
        storage.setMaxExtract(this.maxExtract);
        storage.setMaxReceive(this.maxReceive);
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

    /**
     * Get NBT data, and reassign the tile's data to the 'newly' saved NBT data
     */
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        this.type = BatteryType.getFromId(tag.getInteger("BatteryType"));

        this.energy = tag.getInteger("EnergyLevel");
        this.maxExtract = tag.getInteger("MaxExtract");
        this.maxReceive = tag.getInteger("MaxReceive");
        this.capacity = tag.getInteger("Capacity");

        FMLLog.info("%s capacity, %s maxextract, %s maxreceive, %s energy", capacity, maxExtract, maxReceive, energy);

        super.readFromNBT(tag);
    }

    /**
     * Write data to NBT, for usage on next load
     */
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("BatteryType", type.getType());
        tag.setInteger("EnergyLevel", energy);
        tag.setInteger("MaxExtract", maxExtract);
        tag.setInteger("MaxReceive", maxReceive);
        tag.setInteger("Capacity", capacity);

        super.writeToNBT(tag);
    }

    /**
     * It will accept energy from any side, as I don't have any IO configuration
     */
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    /**
     * Packet used when updating tile entity
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 7, nbt);
    }

    /**
     * Gets called when the above detailed packet gets received
     */
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

    public int getTypeColorInHex() {
        if (type == BatteryType.COAL)
            return 0x4D4D4D;
        if (type == BatteryType.IRON)
            return 0x8C8C8C;
        if (type == BatteryType.GOLD)
            return 0xFFFF55;
        if (type == BatteryType.DIAMOND)
            return 0x55FFFF;
        if (type == BatteryType.CREATIVE)
            return 0xFF55FF;
        return 0x000000;
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

    public int getCapacity() {
        return capacity;
    }

    public int getType() {
        return type.getType();
    }

}

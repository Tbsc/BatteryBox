package tbsc.batterybox.battery;

import cofh.api.block.IDismantleable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tbsc.batterybox.BatteryBox;
import tbsc.batterybox.reference.Reference;
import tbsc.batterybox.util.BatteryStatsUtil;
import tbsc.batterybox.util.BatteryType;

import java.util.ArrayList;

/**
 * @author Tbsc on 6/11/2015, 8:10
 */
public class BlockBatteryBox extends BlockContainer implements IDismantleable {

    protected BatteryType type; // Type of battery box; check BatteryType for more info on types
    private String unlocalized; // Its unlocalized name, used mostly for dynamic battery boxes using only 1 class

    public BlockBatteryBox(BatteryType type) {
        super(Material.iron); // The material it's "made" of
        this.type = type; // Set type, set on registration, check BlockInit
        this.setCreativeTab(BatteryBox.tabBB); // Set on BatteryBox's creative tab
        switch (type.getType()) { // Check the type, and using it set an unlocalized name
            case 0:
                this.unlocalized = "batteryBoxT1";
                break;
            case 1:
                this.unlocalized = "batteryBoxT2";
                break;
            case 2:
                this.unlocalized = "batteryBoxT3";
                break;
            case 3:
                this.unlocalized = "batteryBoxT4";
                break;
            case 4:
                this.unlocalized = "batteryBoxCreative";
        }
        this.setHardness(3.0F);
        if (type == BatteryType.CREATIVE) {
            this.setBlockUnbreakable();
        }
        this.setBlockName(this.unlocalized); // Set unlocalized name on block type
        this.setBlockTextureName(this.unlocalized); // Set texture
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    /**
     *  Used to create tile entities on block placement; switches the type to choose tile entity type
     */
    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch (type.getType()) {
            case 0:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(Reference.Config.
                        batteryRateConfig.get(Reference.Config.Tiers.T1_CAPACITY),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T1_OUT),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T1_IN)), this.type);
            case 1:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(Reference.Config.
                                batteryRateConfig.get(Reference.Config.Tiers.T2_CAPACITY),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T2_OUT),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T2_IN)), this.type);
            case 2:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(Reference.Config.
                                batteryRateConfig.get(Reference.Config.Tiers.T3_CAPACITY),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T3_OUT),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T3_IN)), this.type);
            case 3:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(Reference.Config.
                                batteryRateConfig.get(Reference.Config.Tiers.T4_CAPACITY),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T4_OUT),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T4_IN)), this.type);
            case 4:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(Reference.Config.
                                batteryRateConfig.get(Reference.Config.Tiers.CREATIVE_CAPACITY),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.CREATIVE_OUT),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.CREATIVE_IN)), this.type);
            default:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(Reference.Config.
                                batteryRateConfig.get(Reference.Config.Tiers.T1_CAPACITY),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T1_OUT),
                        Reference.Config.batteryRateConfig.get(Reference.Config.Tiers.T1_IN)), this.type);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitz) {
        if (!player.isSneaking()) {// Since gui id's are the same as type ids, I can use type.getType() instead
            player.openGui(BatteryBox.instance, Reference.GUI_BB, world, x, y, z); // Open gui on activate
            return true;
        }
        return false;
    }

    /* Pahimar's unlocalized name "utilities", github.com/pahimar */

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s:%s", Reference.TEXTURE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops) {
        ArrayList<ItemStack> dismantList = new ArrayList<ItemStack>();
        dismantList.add(new ItemStack(this, 1));
        if (returnDrops) player.inventory.addItemStackToInventory(dismantList.get(0));
        return dismantList;
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z) {
        return player.getDistanceSq(x, y, z) > 16; // Shouldn't allow player to dismantle from more then 16 blocks away
    }
}

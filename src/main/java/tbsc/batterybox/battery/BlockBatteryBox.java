package tbsc.batterybox.battery;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tbsc.batterybox.BatteryBox;
import tbsc.batterybox.reference.Reference;
import tbsc.batterybox.util.BatteryStatsUtil;
import tbsc.batterybox.util.BatteryType;

/**
 * @author Tbsc on 6/11/2015, 8:10
 */
public class BlockBatteryBox extends BlockContainer {

    private BatteryType type;
    private String unlocalized;

    public BlockBatteryBox(BatteryType type) {
        super(Material.iron);
        this.type = type;
        this.setCreativeTab(BatteryBox.tabBB);
        switch (type.getType()) {
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
        }
        this.setBlockName(this.unlocalized);
        this.setBlockTextureName(this.unlocalized);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch (type.getType()) {
            case 0:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(0, 400000, 100, 100), this.type);
            case 1:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(0, 2000000, 500, 500), this.type);
            case 2:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(0, 10000000, 2500, 2500), this.type);
            case 3:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(0, 100000000, 12000, 12000), this.type);
            default:
                return new TileBatteryBox(BatteryStatsUtil.getNewBatteryStats(0, 400000, 100, 100), this.type);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitz) {
        if (!player.isSneaking()) {// Since gui id's are the same as type ids, I can use type.getType() instead
            player.openGui(BatteryBox.instance, type.getType(), world, x, y, z);
            return true;
        }
        return false;
    }

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

}

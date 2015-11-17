package tbsc.batterybox.battery;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.StringUtils;
import tbsc.batterybox.init.BlockInit;
import tbsc.batterybox.reference.Reference;

import java.util.List;

/**
 * @author Tbsc on 15/11/2015, 17:07
 */
public class ItemBlockBatteryBox extends ItemBlock {

    public ItemBlockBatteryBox(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean p_77624_4_) {
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.batteryBoxT1)) {
            lore.add(getFormattedStringTier(EnumChatFormatting.DARK_GRAY, 1, "Coal"));
            lore.add(getFormattedStringCapacity(Reference.Config.getMaxDefaultCapacityT1()));
            lore.add(getFormattedStringTransfer(Reference.Config.getMaxDefaultReceiveT1(),
                    Reference.Config.getMaxDefaultExtractT1()));
        }
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.batteryBoxT2)) {
            lore.add(getFormattedStringTier(EnumChatFormatting.GRAY, 2, "Iron"));
            lore.add(getFormattedStringCapacity(Reference.Config.getMaxDefaultCapacityT2()));
            lore.add(getFormattedStringTransfer(Reference.Config.getMaxDefaultReceiveT2(),
                    Reference.Config.getMaxDefaultExtractT2()));
        }
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.batteryBoxT3)) {
            lore.add(getFormattedStringTier(EnumChatFormatting.YELLOW, 3, "Gold"));
            lore.add(getFormattedStringCapacity(Reference.Config.getMaxDefaultCapacityT3()));
            lore.add(getFormattedStringTransfer(Reference.Config.getMaxDefaultReceiveT3(),
                    Reference.Config.getMaxDefaultExtractT3()));
        }
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.batteryBoxT4)) {
            lore.add(getFormattedStringTier(EnumChatFormatting.AQUA, 4, "Diamond"));
            lore.add(getFormattedStringCapacity(Reference.Config.getMaxDefaultCapacityT4()));
            lore.add(getFormattedStringTransfer(Reference.Config.getMaxDefaultReceiveT4(),
                    Reference.Config.getMaxDefaultExtractT4()));
        }
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.batteryBoxCreative)) {
            lore.add(getFormattedStringTier(EnumChatFormatting.LIGHT_PURPLE, 5, "Creative"));
            lore.add(getFormattedStringCapacity(Reference.Config.getMaxDefaultCapacityCreative()));
            lore.add(getFormattedStringTransfer(Reference.Config.getMaxDefaultReceiveCreative(),
                    Reference.Config.getMaxDefaultExtractCreative()));
        }
    }

    private String getFormattedStringTransfer(int maxReceive, int maxExtract) {
        return String.format("%s%sMax In:%s %s%s %sRF%s, %s%sMax Out:%s %s%s %sRF", EnumChatFormatting.DARK_GREEN,
                EnumChatFormatting.BOLD, EnumChatFormatting.RESET, EnumChatFormatting.GREEN, maxReceive,
                EnumChatFormatting.DARK_RED, EnumChatFormatting.RESET, EnumChatFormatting.DARK_RED,
                EnumChatFormatting.BOLD, EnumChatFormatting.RESET, EnumChatFormatting.GREEN, maxExtract,
                EnumChatFormatting.DARK_RED);
    }

    private String getFormattedStringCapacity(int capacity) {
        return String.format("%s%sCapacity%s - %s%s %sRF", EnumChatFormatting.AQUA, EnumChatFormatting.BOLD,
                EnumChatFormatting.RESET, EnumChatFormatting.GREEN, capacity, EnumChatFormatting.DARK_RED);
    }

    private String getFormattedStringTier(EnumChatFormatting tierColor, int tier, String tierName) {
        return String.format("%sTier %s%s%s%s %s- %s%s", EnumChatFormatting.BOLD, EnumChatFormatting.RESET,
                tierColor, EnumChatFormatting.BOLD, tier, EnumChatFormatting.RESET, tierColor,
                StringUtils.capitalize(tierName));
    }

}

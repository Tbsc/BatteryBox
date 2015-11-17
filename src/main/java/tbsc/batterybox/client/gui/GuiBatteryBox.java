package tbsc.batterybox.client.gui;

import cofh.lib.audio.SoundBase;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import tbsc.batterybox.BatteryBox;
import tbsc.batterybox.battery.TileBatteryBox;
import tbsc.batterybox.reference.Reference;
import tbsc.batterybox.util.BatteryType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tbsc on 6/11/2015, 8:58
 */
public class GuiBatteryBox extends GuiScreen {

    public static final SoundHandler guiSoundManager = FMLClientHandler.instance().getClient().getSoundHandler();

    // Buttons
    public GuiButton buttonExtractMinus;
    public GuiButton buttonExtractPlus;
    public GuiButton buttonRecieveMinus;
    public GuiButton buttonRecievePlus;

    private TileBatteryBox tile; // The tile entity

    /**
     * Initiate
     * @param tile Used for getting energy info
     */
    public GuiBatteryBox(TileBatteryBox tile) {
        this.tile = tile;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground(); // Background, like normal
        super.drawScreen(mouseX, mouseY, partialTicks);

        int k = (this.width - 176) / 2;
        int l = (this.height - 166) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // This allows for dynamic color switching, I disable it
        GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.TEXTURE_PREFIX, "textures/gui/batteryBox.png")); // Set texture
        this.drawTexturedModalRect(k, l, 0, 0, 176, 166); // Draw the gui

        if (isMouseInEnergyBar(mouseX, mouseY)) { // If mouse is on the energy bar, render the tooltip, displaying max RF
            List<String> tooltip = new ArrayList<String>();
            if (tile.getType() == BatteryType.CREATIVE.getType()) tooltip.add("Unlimited RF");
            else tooltip.add(String.format("Maximum %s RF", tile.getCapacity()));
            drawToolTip(tooltip, mouseX, mouseY);
        }

        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.TEXTURE_PREFIX, "textures/gui/batteryBox.png")); // Set texture
        int percentage = tile.getEnergyStored() * 46 / tile.getCapacity(); // thanks aidancbrady from Mekanism for this line of code!
        this.drawTexturedModalRect(k + 67, l + 59, 176, 0, percentage, 12);

        String outText = "Out: " + tile.getMaxExtract(), inText = "In: " + tile.getMaxReceive(),
                energyText = tile.getEnergyStored() + " RF", boxText = I18n.format(String.format("batterybox.name.tier%s",
                 tile.getType() + 1));

        drawString(outText, 47, 77, 4210752); // Draw the out string
        drawString(inText, 128, 77, 4210752); // Draw the in string
        drawString(energyText, 91, 48, 4210752); // Draw the energy stored
        drawStringWithShadow(boxText, 91, 28, tile.getTypeColorInHex()); // Draw the box's name
    }

    private void drawString(String text, int width, int height, int color) {
        int k = (this.width - 176) / 2;
        int l = (this.height - 166) / 2;
        fontRendererObj.drawString(text, k + width - fontRendererObj.getStringWidth(text) / 2, l + height, color); // Draw the out string
    }

    private void drawStringWithShadow(String text, int width, int height, int color) {
        int k = (this.width - 176) / 2;
        int l = (this.height - 166) / 2;
        fontRendererObj.drawStringWithShadow(text, k + width - fontRendererObj.getStringWidth(text) / 2, l + height, color); // Draw the out string
    }

    private boolean isMouseInEnergyBar(int mouseX, int mouseY) {
        for (int x = 67; x < 113; x++) { // check if mouse is on the right X position
            for (int y = 59; y < 71; y++) { // check if mouse is on the right Y position
                if (mouseX == x && mouseY == y)
                    return true; // set true
            }
            return false; // not meeting conditions
        }
        return false; // not meeting conditions
    }

    protected void drawToolTip(List tooltip, int par2, int par3) {
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        if (!tooltip.isEmpty()) {
            int var5 = 0;
            Iterator var6 = tooltip.iterator();

            while (var6.hasNext()) {
                String var7 = (String)var6.next();
                int var8 = fontRendererObj.getStringWidth(var7);

                if (var8 > var5) {
                    var5 = var8;
                }
            }

            int var15 = par2 + 12;
            int var16 = par3 - 12;
            int var9 = 8;

            if (tooltip.size() > 1) {
                var9 += 2 + (tooltip.size() - 1) * 10;
            }

            int var10 = -267386864;
            this.drawGradientRect(var15 - 3, var16 - 4, var15 + var5 + 3, var16 - 3, var10, var10);
            this.drawGradientRect(var15 - 3, var16 + var9 + 3, var15 + var5 + 3, var16 + var9 + 4, var10, var10);
            this.drawGradientRect(var15 - 3, var16 - 3, var15 + var5 + 3, var16 + var9 + 3, var10, var10);
            this.drawGradientRect(var15 - 4, var16 - 3, var15 - 3, var16 + var9 + 3, var10, var10);
            this.drawGradientRect(var15 + var5 + 3, var16 - 3, var15 + var5 + 4, var16 + var9 + 3, var10, var10);
            int var11 = 1347420415;
            int var12 = (var11 & 16711422) >> 1 | var11 & -16777216;
            this.drawGradientRect(var15 - 3, var16 - 3 + 1, var15 - 3 + 1, var16 + var9 + 3 - 1, var11, var12);
            this.drawGradientRect(var15 + var5 + 2, var16 - 3 + 1, var15 + var5 + 3, var16 + var9 + 3 - 1, var11, var12);
            this.drawGradientRect(var15 - 3, var16 - 3, var15 + var5 + 3, var16 - 3 + 1, var11, var11);
            this.drawGradientRect(var15 - 3, var16 + var9 + 2, var15 + var5 + 3, var16 + var9 + 3, var12, var12);

            for (int var13 = 0; var13 < tooltip.size(); ++var13) {
                String var14 = (String)tooltip.get(var13);

                fontRendererObj.drawStringWithShadow(var14, var15, var16, -1);

                if (var13 == 0) {
                    var16 += 2;
                }

                var16 += 10;
            }
        }
    }

    /**
     * Opening this gui will *not* pause the game, unlike the esc screen, on single-player.
     * @return should pause game - no!
     */
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void playSound(String sound, float var1, float var2) {
        guiSoundManager.playSound(new SoundBase(sound, var1, var2));
    }

    @Override
    public void initGui() {
        super.initGui();
        int k = (this.width - 176) / 2;
        int l = (this.height - 166) / 2;

        // Register buttons
        this.buttonList.add(this.buttonExtractMinus = new GuiButton(0, k + 25, l + 87, 13, 14, ""));
        this.buttonList.add(this.buttonExtractPlus = new GuiButton(0, k + 55, l + 87, 13, 14, ""));
        this.buttonList.add(this.buttonRecieveMinus = new GuiButton(0, k + 107, l + 87, 13, 14, ""));
        this.buttonList.add(this.buttonRecievePlus = new GuiButton(0, k + 136, l + 87, 13, 14, ""));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        // Get button presses and change transfer speed + packets for client-server sync
        int newExtract = tile.getMaxExtract();
        int newReceive = tile.getMaxReceive();

        if (button == this.buttonExtractMinus) {
            if (tile.getType() == BatteryType.CREATIVE.getType()) return;
            if (!(tile.getMaxExtract() == 0)) {
                if (isShiftKeyDown())
                    newExtract = tile.getMaxExtract() - 500;
                else if (isCtrlKeyDown())
                    newExtract = tile.getMaxExtract() - 5;
                else
                    newExtract = tile.getMaxExtract() - 50;
                if (newExtract < 0)
                    newExtract = tile.getMaxDefaultExtract();
            }
        }
        if (button == this.buttonExtractPlus) {
            if (tile.getType() == BatteryType.CREATIVE.getType()) return;
            if (!(tile.getMaxExtract() == tile.getMaxDefaultExtract())) {
                if (isShiftKeyDown())
                    newExtract = tile.getMaxExtract() + 500;
                else if (isCtrlKeyDown())
                    newExtract = tile.getMaxExtract() + 5;
                else
                    newExtract = tile.getMaxExtract() + 50;
                if (newExtract > tile.getMaxDefaultExtract())
                    newExtract = tile.getMaxDefaultExtract();
            }
        }
        if (button == this.buttonRecieveMinus) {
            if (tile.getType() == BatteryType.CREATIVE.getType()) return;
            if (!(tile.getMaxReceive() == 0)) {
                if (isShiftKeyDown())
                    newReceive = tile.getMaxReceive() - 500;
                else if (isCtrlKeyDown())
                    newReceive = tile.getMaxReceive() - 5;
                else
                    newReceive = tile.getMaxReceive() - 50;
                if (newReceive < 0)
                    newReceive = tile.getMaxDefaultReceive();
            }
        }
        if (button == this.buttonRecievePlus) {
            if (tile.getType() == BatteryType.CREATIVE.getType()) return;
            if (!(tile.getMaxReceive() == tile.getMaxDefaultReceive())) {
                if (isShiftKeyDown())
                    newReceive = tile.getMaxReceive() + 500;
                else if (isCtrlKeyDown())
                    newReceive = tile.getMaxReceive() + 5;
                else
                    newReceive = tile.getMaxReceive() + 50;
                if (newReceive > tile.getMaxDefaultReceive())
                    newReceive = tile.getMaxDefaultReceive();
            }
        }
        tile.setMaxExtract(newExtract);
        tile.setMaxReceive(newReceive);
        playSound("random.click", 1.0F, 50);
        BatteryBox.proxy.sendPacketNewExtract(tile, newExtract);
        BatteryBox.proxy.sendPacketNewReceive(tile, newReceive);
        // Update client-side tile entity + send packet from *ONLY* client (using proxy) to update the server
    }

}

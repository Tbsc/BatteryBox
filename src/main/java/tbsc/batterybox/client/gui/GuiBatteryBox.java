package tbsc.batterybox.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import tbsc.batterybox.BatteryBox;
import tbsc.batterybox.battery.TileBatteryBox;
import tbsc.batterybox.reference.Reference;

/**
 * @author Tbsc on 6/11/2015, 8:58
 */
public class GuiBatteryBox extends GuiScreen {

    // Buttons
    public GuiButton buttonExtractMinus;
    public GuiButton buttonExtractPlus;
    public GuiButton buttonRecieveMinus;
    public GuiButton buttonRecievePlus;

    private TileBatteryBox tile; // The tile entity

    public GuiBatteryBox(TileBatteryBox tile) {
        this.tile = tile;
    } // Initiate and get tile entity

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground(); // Background, like normal
        super.drawScreen(mouseX, mouseY, partialTicks);

        int k = (this.width - 176) / 2; // Start of my part of gui, by width
        int l = (this.height - 166) / 2; // Same, just height

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // This allows for dynamic color switching, I just don't want it
        GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.TEXTURE_PREFIX, "textures/gui/batteryBox.png")); // Set texture
        this.drawTexturedModalRect(k, l, 0, 0, 176, 166); // Draw the gui

        this.drawCenteredString(mc.fontRenderer, "Out: " + tile.getMaxExtract(), k + 47, l + 77, 0xFFFFFF); // Draw the out string
        this.drawCenteredString(mc.fontRenderer, "In: " + tile.getMaxReceive(), k + 128, l + 77, 0xFFFFFF); // Draw the in string
        this.drawCenteredString(mc.fontRenderer, tile.getEnergyStored() + " RF", k + 89, l + 48, 0xFFFFFF); // Draw the energy stored

        double energyPercentage = (double) (tile.getEnergyStored(ForgeDirection.NORTH) * 100) / tile.getMaxEnergyStored(ForgeDirection.SOUTH); // Calculate energy percentage

        int widthPixelDrawing = (int) (energyPercentage / 2.22); // Calculate
        if (widthPixelDrawing >= 45) widthPixelDrawing = 45; // If, for some reason, is HIGHER then 45, then set it to 45
        else widthPixelDrawing = (int) Math.floor(widthPixelDrawing); // Round it *down*

        this.drawTexturedModalRect(k + 67, l + 59, 176, 0, widthPixelDrawing, 12); // Draw the energy bar, length calculated with percentages
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    } // Opening this gui will *not* pause the game, unlike the esc screen, on single-player.

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
        if (button == this.buttonExtractMinus) {
            if (!(tile.getMaxExtract() == 0)) {
                int newMaxExtract = tile.getMaxExtract() - 5;
                tile.setMaxExtract(newMaxExtract);
                BatteryBox.proxy.sendPacketNewExtract(tile, newMaxExtract);
                // Update client-side tile entity + send packet from *ONLY* client (using proxy) to proxy to update the server
            }
        }
        if (button == this.buttonExtractPlus) {
            if (!(tile.getMaxExtract() == tile.getMaxDefaultExtract())) {
                int newMaxExtract = tile.getMaxExtract() + 5;
                tile.setMaxExtract(newMaxExtract);
                BatteryBox.proxy.sendPacketNewExtract(tile, newMaxExtract);
                // Update client-side tile entity + send packet from *ONLY* client (using proxy) to proxy to update the server
            }
        }
        if (button == this.buttonRecieveMinus) {
            if (!(tile.getMaxReceive() == 0)) {
                int newMaxReceive = tile.getMaxReceive() - 5;
                tile.setMaxReceive(newMaxReceive);
                BatteryBox.proxy.sendPacketNewReceive(tile, newMaxReceive);
                // Update client-side tile entity + send packet from *ONLY* client (using proxy) to proxy to update the server
            }
        }
        if (button == this.buttonRecievePlus) {
            if (!(tile.getMaxReceive() == tile.getMaxDefaultReceive())) {
                int newMaxReceive = tile.getMaxReceive() + 5;
                tile.setMaxReceive(newMaxReceive);
                BatteryBox.proxy.sendPacketNewReceive(tile, newMaxReceive);
                // Update client-side tile entity + send packet from *ONLY* client (using proxy) to proxy to update the server
            }
        }
    }

}

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

    public GuiButton buttonExtractMinus;
    public GuiButton buttonExtractPlus;
    public GuiButton buttonRecieveMinus;
    public GuiButton buttonRecievePlus;

    private TileBatteryBox tile;

    public GuiBatteryBox(TileBatteryBox tile) {
        this.tile = tile;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        int k = (this.width - 176) / 2;
        int l = (this.height - 166) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.TEXTURE_PREFIX, "textures/gui/batteryBox.png"));
        this.drawTexturedModalRect(k, l, 0, 0, 176, 166);

        this.drawCenteredString(mc.fontRenderer, "Out: " + tile.getMaxExtract(), k + 47, l + 77, 0xFFFFFF);
        this.drawCenteredString(mc.fontRenderer, "In: " + tile.getMaxReceive(), k + 128, l + 77, 0xFFFFFF);
        this.drawCenteredString(mc.fontRenderer, tile.getEnergyStored() + " RF", k + 89, l + 48, 0xFFFFFF);

        double energyPercentage = (double) (tile.getEnergyStored(ForgeDirection.NORTH) * 100) / tile.getMaxEnergyStored(ForgeDirection.SOUTH);

        int heightPixelDrawing = (int) (energyPercentage / 2.22);
        if (heightPixelDrawing >= 45) heightPixelDrawing = 45;
        else heightPixelDrawing = (int) Math.floor(heightPixelDrawing);

        this.drawTexturedModalRect(k + 67, l + 59, 176, 0, heightPixelDrawing, 12);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
        int k = (this.width - 176) / 2;
        int l = (this.height - 166) / 2;

        this.buttonList.add(this.buttonExtractMinus = new GuiButton(0, k + 25, l + 87, 13, 14, ""));
        this.buttonList.add(this.buttonExtractPlus = new GuiButton(0, k + 55, l + 87, 13, 14, ""));
        this.buttonList.add(this.buttonRecieveMinus = new GuiButton(0, k + 107, l + 87, 13, 14, ""));
        this.buttonList.add(this.buttonRecievePlus = new GuiButton(0, k + 136, l + 87, 13, 14, ""));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonExtractMinus) {
            if (!(tile.getMaxExtract() == 0)) {
                int newMaxExtract = tile.getMaxExtract() - 5;
                tile.setMaxExtract(newMaxExtract);
                BatteryBox.proxy.sendPacketNewExtract(tile, newMaxExtract);
            }
        }
        if (button == this.buttonExtractPlus) {
            if (!(tile.getMaxExtract() == tile.getMaxDefaultExtract())) {
                int newMaxExtract = tile.getMaxExtract() + 5;
                tile.setMaxExtract(newMaxExtract);
                BatteryBox.proxy.sendPacketNewExtract(tile, newMaxExtract);
            }
        }
        if (button == this.buttonRecieveMinus) {
            if (!(tile.getMaxReceive() == 0)) {
                int newMaxReceive = tile.getMaxReceive() - 5;
                tile.setMaxReceive(newMaxReceive);
                BatteryBox.proxy.sendPacketNewReceive(tile, newMaxReceive);
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

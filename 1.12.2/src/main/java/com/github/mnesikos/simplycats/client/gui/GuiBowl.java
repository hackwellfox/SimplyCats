package com.github.mnesikos.simplycats.client.gui;

import com.github.mnesikos.simplycats.Ref;
import com.github.mnesikos.simplycats.tileentity.TileEntityBowl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
// TODO
public class GuiBowl extends GuiContainer {
    private ResourceLocation texture;

    //private InventoryPlayer inventory;
    private TileEntityBowl te;

    public GuiBowl(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        texture = new ResourceLocation(Ref.MODID + ":textures/gui/bowl_gui.png");
        //inventory = player.inventory;
        //this.te = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //fontRendererObj.drawString(I18n.format(te.getInventoryName()), (xSize / 2) - (fontRendererObj.getStringWidth(I18n.format(te.getInventoryName())) / 2), 6, 4210752, false);
        //fontRendererObj.drawString(I18n.format(inventory.getInventoryName()), 8, ySize - 96 + 2, 4210752);
    }
}

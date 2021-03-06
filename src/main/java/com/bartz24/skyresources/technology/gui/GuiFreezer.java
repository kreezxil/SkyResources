package com.bartz24.skyresources.technology.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.bartz24.skyresources.GuiHelper;
import com.bartz24.skyresources.References;
import com.bartz24.skyresources.technology.freezer.FreezerRecipes;
import com.bartz24.skyresources.technology.gui.container.ContainerFreezer;
import com.bartz24.skyresources.technology.tile.FreezerTile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class GuiFreezer extends GuiContainer
{

	private IInventory playerInv;
	private FreezerTile tile;

	public GuiFreezer(IInventory playerInv, FreezerTile te)
	{
		super(new ContainerFreezer(playerInv, te));

		this.playerInv = playerInv;
		this.tile = te;

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(
				References.ModID, "textures/gui/blankInventory.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize,
				this.ySize);

		for (int y = 0; y < Math.floor((float) tile.getSizeInventory() / 5f)
				+ 1; ++y)
		{
			for (int x = 0; x < Math.min(5,
					tile.getSizeInventory() - y * 5); ++x)
			{
				this.drawTexturedModalRect(this.guiLeft + 52 + x * 18,
						this.guiTop + 21 + y * 18, 7, 83, 18, 18);
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = tile.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s,
				88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString("Speed: x" + tile.getFreezerSpeed(),
				110, 56, 4210752);
		this.fontRendererObj.drawString(
				this.playerInv.getDisplayName().getUnformattedText(), 8, 72,
				4210752);
		drawProgress();
	}

	void drawProgress()
	{
		GL11.glEnable(GL11.GL_BLEND);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 0.5f);
		this.mc.getTextureManager().bindTexture(
				new ResourceLocation("textures/blocks/ice_packed.png"));

		for (int y = 0; y < Math.floor((float) tile.getSizeInventory() / 5f)
				+ 1; ++y)
		{
			for (int x = 0; x < Math.min(5,
					tile.getSizeInventory() - y * 5); ++x)
			{
				if (tile.getField(y * 5 + x) <= 0 || FreezerRecipes
						.getRecipe(tile.getStackInSlot(y * 5 + x)) == null)
					continue;

				int height = (int) ((float) tile.getField(y * 5 + x) * 16F
						/ (float) FreezerRecipes
								.getRecipe(tile.getStackInSlot(y * 5 + x))
								.getTimeReq());
				this.drawTexturedModalRect(53 + x * 18,
						+22 + y * 18 + 16 - height, 0, 16 - height, 16, height);
			}
		}
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}
}

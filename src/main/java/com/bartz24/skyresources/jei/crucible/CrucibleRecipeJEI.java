package com.bartz24.skyresources.jei.crucible;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CrucibleRecipeJEI extends BlankRecipeWrapper
{
	private final ItemStack input;

	private final FluidStack output;

	public CrucibleRecipeJEI(ItemStack input, FluidStack output)
	{
		this.input = input;
		this.output = output;
	}

	@Override
	public List getInputs()
	{
		return Collections.singletonList(input);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight,
			int mouseX, int mouseY)
	{
	}

	@Override
	public List getFluidOutputs()
	{
		return Collections.singletonList(output);
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY)
	{
		return null;
	}
}

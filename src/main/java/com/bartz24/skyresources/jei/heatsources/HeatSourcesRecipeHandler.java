package com.bartz24.skyresources.jei.heatsources;

import com.bartz24.skyresources.References;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HeatSourcesRecipeHandler
		implements IRecipeHandler<HeatSourcesRecipeJEI>
{

	@Override
	public String getRecipeCategoryUid()
	{
		return References.ModID + ":heatsources";
	}

	@Override
	public Class<HeatSourcesRecipeJEI> getRecipeClass()
	{
		return HeatSourcesRecipeJEI.class;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(HeatSourcesRecipeJEI recipe)
	{
		return recipe;
	}

	@Override
	public boolean isRecipeValid(HeatSourcesRecipeJEI recipe)
	{
		return true;
	}

}

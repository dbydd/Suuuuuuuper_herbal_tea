package com.dbydd.suuuuuper_herbal_tea.jei;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "main");
    }
    //todo description for earth stove top


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
    }
}

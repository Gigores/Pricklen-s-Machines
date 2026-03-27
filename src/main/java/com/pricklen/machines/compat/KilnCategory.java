package com.pricklen.machines.compat;

import com.pricklen.machines.PMachines;
import com.pricklen.machines.block.ModBlocks;
import com.pricklen.machines.recipe.KilnRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageTranscoder;

public class KilnCategory implements IRecipeCategory<KilnRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(PMachines.MODID, "kiln_smelting");
    public static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
    public static final RecipeType<KilnRecipe> KILN_TYPE =
            new RecipeType<>(UID, KilnRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public KilnCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 52, 13, 88, 60);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.KILN.get()));
    }

    @Override
    public RecipeType<KilnRecipe> getRecipeType() {
        return KILN_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.pricklensmachines.kiln");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KilnRecipe kilnRecipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 4, 4).addIngredients(kilnRecipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 22).addItemStack(kilnRecipe.getResultItem(null));
    }
}

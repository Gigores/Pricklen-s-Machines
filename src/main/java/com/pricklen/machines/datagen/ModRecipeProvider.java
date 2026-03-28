package com.pricklen.machines.datagen;

import com.pricklen.machines.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        stairBuilder(ModBlocks.FIRECLAY_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.FIRECLAY_BRICKS.get())).unlockedBy("has_fireclay_bricks", has(ModBlocks.FIRECLAY_BRICKS.get())).save(pWriter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FIRECLAY_BRICK_SLAB.get(), Ingredient.of(ModBlocks.FIRECLAY_BRICKS.get())).unlockedBy("has_fireclay_bricks", has(ModBlocks.FIRECLAY_BRICKS.get())).save(pWriter);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FIRECLAY_BRICK_WALL.get(), Ingredient.of(ModBlocks.FIRECLAY_BRICKS.get())).unlockedBy("has_fireclay_bricks", has(ModBlocks.FIRECLAY_BRICKS.get())).save(pWriter);
        stonecutterResultFromBase(
                pWriter,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.FIRECLAY_BRICK_STAIRS.get(),
                ModBlocks.FIRECLAY_BRICKS.get()
        );
        stonecutterResultFromBase(
                pWriter,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.FIRECLAY_BRICK_SLAB.get(),
                ModBlocks.FIRECLAY_BRICKS.get(),
                2
        );
        stonecutterResultFromBase(
                pWriter,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.FIRECLAY_BRICK_WALL.get(),
                ModBlocks.FIRECLAY_BRICKS.get()
        );
    }
}

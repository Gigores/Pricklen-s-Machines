package com.pricklen.machines.datagen;

import com.pricklen.machines.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        blockSet(
                pWriter,
                ModBlocks.FIRECLAY_BRICKS.get(),
                ModBlocks.FIRECLAY_BRICK_SLAB.get(),
                ModBlocks.FIRECLAY_BRICK_WALL.get(),
                ModBlocks.FIRECLAY_BRICK_STAIRS.get(),
                "fireclay_bricks"
        );
        blockSet(
                pWriter,
                ModBlocks.CRACKED_FIRECLAY_BRICKS.get(),
                ModBlocks.CRACKED_FIRECLAY_BRICK_SLAB.get(),
                ModBlocks.CRACKED_FIRECLAY_BRICK_WALL.get(),
                ModBlocks.CRACKED_FIRECLAY_BRICK_STAIRS.get(),
                "cracked_fireclay_bricks"
        );
        blockSet(
                pWriter,
                ModBlocks.MOSSY_FIRECLAY_BRICKS.get(),
                ModBlocks.MOSSY_FIRECLAY_BRICK_SLAB.get(),
                ModBlocks.MOSSY_FIRECLAY_BRICK_WALL.get(),
                ModBlocks.MOSSY_FIRECLAY_BRICK_STAIRS.get(),
                "mossy_fireclay_bricks"
        );
    }
    protected void blockSet(Consumer<FinishedRecipe> pWriter, Block full, Block slab, Block wall, Block stairs, String name) {
        stairBuilder(stairs, Ingredient.of(full)).unlockedBy("has_" + name, has(full)).save(pWriter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(full)).unlockedBy("has_" + name, has(full)).save(pWriter);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall, Ingredient.of(full)).unlockedBy("has_" + name, has(full)).save(pWriter);
        stonecutterResultFromBase(
                pWriter,
                RecipeCategory.BUILDING_BLOCKS,
                stairs,
                full
        );
        stonecutterResultFromBase(
                pWriter,
                RecipeCategory.BUILDING_BLOCKS,
                slab,
                full,
                2
        );
        stonecutterResultFromBase(
                pWriter,
                RecipeCategory.BUILDING_BLOCKS,
                wall,
                full
        );
    }
}

package com.pricklen.machines.compat.kubejs.recipes;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface KilnRecipeSchema {
    RecipeKey<OutputItem> RESULTS = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputItem[]> INGREDIENTS = ItemComponents.INPUT_ARRAY.key("ingredients");
    RecipeKey<Long> TIME = TimeComponent.TICKS.key("time");

    RecipeSchema DEFAULT = new RecipeSchema(RESULTS, INGREDIENTS, TIME);
}

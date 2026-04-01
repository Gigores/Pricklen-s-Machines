package com.pricklen.machines.compat.kubejs;

import com.pricklen.machines.compat.kubejs.recipes.KilnRecipeSchema;
import com.pricklen.machines.recipe.ModRecipes;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;

public class KubeJSPMachinesPlugin extends KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(ModRecipes.KILN_SERIALIZER.getId(), KilnRecipeSchema.DEFAULT);
    }
}

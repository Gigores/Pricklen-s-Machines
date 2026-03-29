package com.pricklen.machines;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig_ {
    public static final ForgeConfigSpec SPEC;
    public static final ModConfig_ CONFIG;

    public final ForgeConfigSpec.BooleanValue loadFurnace;
    public final ForgeConfigSpec.BooleanValue loadBlastFurnace;

    static {
        Pair<ModConfig_, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder()
                .configure(ModConfig_::new);

        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }
    public ModConfig_(ForgeConfigSpec.Builder builder) {
        builder.push("general");

        loadFurnace = builder.define("useFurnaceRecipes", true);
        loadBlastFurnace = builder.define("useBlastFurnaceRecipes", true);

        builder.pop();
    }
}

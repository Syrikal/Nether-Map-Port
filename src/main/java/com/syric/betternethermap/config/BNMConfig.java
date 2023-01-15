package com.syric.betternethermap.config;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.*;

public class BNMConfig {

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<MapBehaviorType> defaultBehavior;

    public static final ForgeConfigSpec.ConfigValue<Boolean> addFixedMaps;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> dimensionsFixed;

    public static final ForgeConfigSpec.ConfigValue<Boolean> addSnapMaps;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> dimensionsSnap;

    public static final ForgeConfigSpec.ConfigValue<Boolean> addVariableMaps;
//    public static final ForgeConfigSpec.ConfigValue<Integer> minimumMapHeight;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> dimensionMinima;
    public static final ForgeConfigSpec.ConfigValue<Integer> variableModifier;

    public static final ForgeConfigSpec.ConfigValue<Boolean> disableSpinningIndicator;

    static {
        COMMON_BUILDER.push("Default Map Behavior");
        defaultBehavior = COMMON_BUILDER.comment("Decides what behavior vanilla maps follow in cave dimensions." +
                "\nFIXED: map scans at a fixed configurable y-value" +
                "\nSNAP: map scans at the nearest of several configurable y-values" +
                "\nVARIABLE: map scans at the y-value it was created at" +
                "\nDefault: FIXED")
                        .defineEnum("Default Behavior", MapBehaviorType.FIXED);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Fixed-Height Map Settings");
        addFixedMaps = COMMON_BUILDER.comment("If true, will add fixed-height maps to the game, craftable with a normal map and an iron nugget.\n" +
                "These maps always use the configurable fixed heights.\n" +
                "If false, the maps will still be craftable, but will not work. Default: false").define("Enable Fixed Maps", false);
        dimensionsFixed = COMMON_BUILDER.comment("Add dimensions' fixed scan heights." +
                        "\nNote that in the Nether, values under 22 allow for Ancient Debris cheese." +
                        "\nExample: [\"minecraft:the_nether,70\",\"undergarden:undergarden,120\"]")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,70"), (s) -> DimensionEntry.validateFixed((String) s));
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Snap-Height Map Settings");
        addSnapMaps = COMMON_BUILDER.comment("If true, will add snap-height maps to the game, craftable with a normal map and redstone.\n" +
                "These maps always use the configurable snap heights.\n" +
                "If false, the maps will still be craftable, but will not work. Default: false").define("Enable Snap Maps", false);
        dimensionsSnap = COMMON_BUILDER.comment("Add dimensions' snap scan heights." +
                        "\nYou can specify multiple heights per dimension." +
                        "\nThis is good for mapping a dimension in multiple fixed layers." +
                        "\nNote that in the Nether, values under 22 allow for Ancient Debris cheese." +
                        "\nExample: [\"minecraft:the_nether,40,70,100\",\"undergarden:undergarden,60,120,180\"]")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,40,70,100"), (s) -> DimensionEntry.validateSnap((String) s));

        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Variable-Height Map Settings");
        addVariableMaps = COMMON_BUILDER.comment("If true, will add variable-height maps to the game, craftable with a normal map and lapis.\n" +
                "These maps always use the height they are created at.\n" +
                "If false, the maps will still be craftable, but will not work. Default: false").define("Enable Variable Maps", false);
        dimensionMinima = COMMON_BUILDER.comment("Add minimum allowed y-values for variable-height maps." +
                        "\nIt is recommended to set this to at least 22 in the Nether to avoid Ancient Debris cheese." +
                        "\nExample: [\"minecraft:the_nether,22\"]")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,22"), (s) -> DimensionEntry.validateFixed((String) s));
        variableModifier = COMMON_BUILDER.comment("Modifier to place on variable-height maps' y-value. 0 produces a map at your feet's y-value, 2 at your head. Default: 2").defineInRange("Variable Height Modifier", 2, -10, 10);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Disable Spinning Indicator");
        disableSpinningIndicator = COMMON_BUILDER.comment("If true, the location indicator on Nether maps will be accurate rather than spinning. Default: true").define("Disable Spin", true);
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
    }



    //When given a dimension, returns the fixed height assigned to that dimension.
    public static int getFixedHeight(World world) {
        for (String entry : dimensionsFixed.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                return dimensionEntry.heights.get(0);
            }
        }
        return 256;
    }
    public static int getSnapHeight(World world, int y) {
        for (String entry : dimensionsSnap.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                return dimensionEntry.getClosestValue(y);
            }
        }
        return 256;
    }
    public static int getMinHeight(World world) {
        for (String entry : dimensionMinima.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                return dimensionEntry.heights.get(0);
            }
        }
        return 0;
    }



}






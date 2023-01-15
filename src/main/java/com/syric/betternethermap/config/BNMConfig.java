package com.syric.betternethermap.config;

import net.minecraft.world.level.Level;
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

    public static final ForgeConfigSpec.ConfigValue<Boolean> enableOverworldMapping;

    public static final ForgeConfigSpec.ConfigValue<Boolean> disableSpinningIndicator;
    public static final ForgeConfigSpec.ConfigValue<Boolean> debugMessages;

    static {
        COMMON_BUILDER.push("Default Map Behavior");
        defaultBehavior = COMMON_BUILDER.comment("""
                Decides what behavior vanilla maps follow in cave dimensions.
                FIXED: map scans at a fixed configurable y-value
                SNAP: map scans at the nearest of several configurable y-values
                VARIABLE: map scans at the y-value it was created at
                Default: FIXED""")
                .defineEnum("Default Behavior", MapBehaviorType.FIXED);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Fixed-Height Map Settings");
        addFixedMaps = COMMON_BUILDER.comment("""
                If true, will add fixed-height maps to the game, craftable with a normal map and an iron nugget.
                These maps always use the configurable fixed heights.
                If false, the maps will still be craftable, but will not work. Default: false""").define("Enable Fixed Maps", false);
        dimensionsFixed = COMMON_BUILDER.comment("""
                Add dimensions' fixed scan heights.
                Note that in the Nether, values under 22 allow for Ancient Debris cheese.
                Example: ["minecraft:the_nether,70","undergarden:undergarden,120"]""")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,70"), (s) -> DimensionEntry.validateFixed((String) s));
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Snap-Height Map Settings");
        addSnapMaps = COMMON_BUILDER.comment("""
                If true, will add snap-height maps to the game, craftable with a normal map and redstone.
                These maps always use the configurable snap heights.
                If false, the maps will still be craftable, but will not work. Default: false""").define("Enable Snap Maps", false);
        dimensionsSnap = COMMON_BUILDER.comment("""
                Add dimensions' snap scan heights.
                You can specify multiple heights per dimension.
                This is good for mapping a dimension in multiple fixed layers.
                Note that in the Nether, values under 22 allow for Ancient Debris cheese.
                Example: ["minecraft:the_nether,40,70,100","undergarden:undergarden,60,120,180"]""")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,40,70,100"), (s) -> DimensionEntry.validateSnap((String) s));
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Variable-Height Map Settings");
        addVariableMaps = COMMON_BUILDER.comment("""
                If true, will add variable-height maps to the game, craftable with a normal map and lapis.
                These maps always use the height they are created at.
                If false, the maps will still be craftable, but will not work. Default: false""").define("Enable Variable Maps", false);
        dimensionMinima = COMMON_BUILDER.comment("""
                Add minimum allowed y-values for variable-height maps.
                It is recommended to set this to at least 22 in the Nether to avoid Ancient Debris cheese.
                Minima can never be less than -64.
                Example: ["minecraft:the_nether,22"]""")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,22"), (s) -> DimensionEntry.validateFixed((String) s));
        variableModifier = COMMON_BUILDER.comment("Modifier to place on variable-height maps' y-value. 0 produces a map at your feet's y-value, 2 at your head. Default: 2").defineInRange("Variable Height Modifier", 2, -100, 100);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Enable Overworld Cave Mapping");
        enableOverworldMapping = COMMON_BUILDER.comment("""
                If true, the alternate map types will use their cave functionality in the Overworld and other non-cave dimensions. Vanilla maps will still use vanilla behavior.
                This therefore requires alternate maps to be enabled.
                This requires entering the overworld into the above config settings, e.g. "minecraft:overworld,-30,0,30".
                """).define("Enable Overworld Maps", false);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Disable Spinning Indicator");
        disableSpinningIndicator = COMMON_BUILDER.comment("If true, the location indicator on Nether maps will be accurate rather than spinning. Default: true").define("Disable Spin", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Enable Debug Messages");
        debugMessages = COMMON_BUILDER.comment("Displays the Y-level a map is linked to in the chat when you create it. Default: false").define("Enable Debug", false);
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
    }



    //When given a dimension, returns the fixed height assigned to that dimension.
    public static int getFixedHeight(Level world) {
        for (String entry : dimensionsFixed.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                return dimensionEntry.heights.get(0);
            }
        }
        return -1000;
    }
    public static int getSnapHeight(Level world, int y) {
        for (String entry : dimensionsSnap.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                return dimensionEntry.getClosestValue(y);
            }
        }
        return -1000;
    }
    public static int getMinHeight(Level world) {
        int candidate = -64;
        for (String entry : dimensionMinima.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                candidate = dimensionEntry.heights.get(0);
            }
        }
        return Math.max(candidate, -64);
    }



}






package com.syric.betternethermap;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collections;
import java.util.List;

public class BNMConfig {

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> useFixedHeight;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> dimensions;
    public static final ForgeConfigSpec.ConfigValue<Boolean> allowBothTypes;
    public static final ForgeConfigSpec.ConfigValue<Integer> minimumMapHeight;
    public static final ForgeConfigSpec.ConfigValue<Boolean> disableSpinningIndicator;

    static {
        COMMON_BUILDER.push("Use Fixed Heights");
        useFixedHeight = COMMON_BUILDER.comment("If true, default maps use a configurable fixed height.\nIf false, default maps use the height they were created at.\nDefault: true").define("Use Fixed Heights", true);
        dimensions = COMMON_BUILDER.comment("Add dimensions and their fixed scan heights.\nExample: \"minecraft:the_nether,70\", \"undergarden:the_undergarden,60\"")
                .defineListAllowEmpty(Collections.singletonList("dimension list"), () -> Collections.singletonList("minecraft:the_nether,70"), (s) -> DimensionEntry.validate((String) s));
//                .defineListAllowEmpty("dimensions", Arrays.asList(new DimensionEntry("minecraft:nether", 40)));
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Allow Both Types");
        allowBothTypes = COMMON_BUILDER.comment("If true, will add variable-height maps to the game, craftable with a normal map and lapis.\n" +
                "These maps use the height they are created at.\n" +
                "If false, the maps will still be craftable, but will not work. Default: false").define("Add Both Types", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Minimum Variable-Height Altitude");
        minimumMapHeight = COMMON_BUILDER.comment("Minimum height at which a variable-height map will scan. Setting this to at least 22 minimizes Ancient Debris cheese. Set to 0 to disable. Default: 22").defineInRange("Minimum Height", 22, 0, 256);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Disable Spinning Indicator");
        disableSpinningIndicator = COMMON_BUILDER.comment("If true, the location indicator on Nether maps will be accurate rather than spinning. Default: true").define("Disable Spin", true);
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
    }



    //When given a dimension, returns the fixed height assigned to that dimension.
    public static int getFixedHeight(World world) {
        for (String entry : dimensions.get()) {
            DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
            String id = String.valueOf(world.dimension().location());
            if (dimensionEntry.dimension.equals(id)) {
                return dimensionEntry.scanHeight;
            }
        }
        return 256;
    }

}



class DimensionEntry {

    protected final String dimension;
    protected final int scanHeight;

    public DimensionEntry(String s) {
        String tempDim = "";
        int tempScan = 100;

        try {
            String[] split = s.split(",");
            tempDim = split[0];
            tempScan = Integer.parseInt(split[1]);
        } catch (IndexOutOfBoundsException i) {
            BetterNetherMap.LOGGER.info("IndexOutOfBoundsException while parsing dimension entry");
        } catch (NumberFormatException n) {
            BetterNetherMap.LOGGER.info("NumberFormatException while parsing dimension entry");
        }

        dimension = tempDim;
        scanHeight = tempScan;
    }

    public static DimensionEntry deserialize(String s) {
        return new DimensionEntry(s);
    }

    public static boolean validate(String s) {
        String[] split = s.split(",");
        if (split.length != 2) {
            return false;
        } else {
            try {
                Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

}

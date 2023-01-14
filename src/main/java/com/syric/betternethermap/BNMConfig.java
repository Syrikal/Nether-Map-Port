package com.syric.betternethermap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.MapItem;

public class BNMConfig {

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> useMapCreationHeight;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> dimensions;


    public static int getDimensionScanHeight(World world, Entity entity, MapData state) {
        if (useMapCreationHeight.get()) {
            ServerPlayerEntity player = (ServerPlayerEntity)(entity);
            for (int slot = 0; slot < player.inventory.items.size(); slot++) {
                ItemStack item = player.inventory.getItem(slot);
                if (item.getItem() instanceof FilledMapItem && FilledMapItem.getOrCreateSavedData(item, entity.level) == state) {
                    return item.getOrCreateTag().getInt("yLevel");
                }
            }
        } else {
            for (String entry : dimensions.get()) {
                DimensionEntry dimensionEntry = DimensionEntry.deserialize(entry);
                String id = String.valueOf(world.dimension().location());
                if (dimensionEntry.dimension.equals(id)) {
                    return dimensionEntry.scanHeight;
                } else {
                    BetterNetherMap.LOGGER.info("Config entry does not match current dimension. Config entry: " + dimensionEntry.dimension + ", dimension: " + id);
                }
            }
        }
        return 200;
    }


    static {
        COMMON_BUILDER.push("Use Map Creation Height");
        useMapCreationHeight = COMMON_BUILDER.comment("If true, uses the height a map was created at rather than a fixed height. Default: false").define("Use Creation Height", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Dimension List");
        dimensions = COMMON_BUILDER.comment("Add dimensions and their scan heights. Default: minecraft:nether at 40")
                .comment("Example: \"minecraft:nether,40\", \"undergarden:the_undergarden,60\"")
                .defineListAllowEmpty(Arrays.asList("dimension list"), () -> Arrays.asList("minecraft:nether,40"), (s) -> DimensionEntry.validate((String) s));


//                .defineListAllowEmpty("dimensions", Arrays.asList(new DimensionEntry("minecraft:nether", 40)));
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
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

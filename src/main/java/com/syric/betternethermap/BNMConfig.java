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
    public static final ForgeConfigSpec.ConfigValue<List<DimensionEntry>> dimensions;


    public int getDimensionScanHeight(World world, Entity entity, MapData state) {
        if (useMapCreationHeight.get()) {
            ServerPlayerEntity player = (ServerPlayerEntity)(entity);
            for (int slot = 0; slot < player.inventory.items.size(); slot++) {
                ItemStack item = player.inventory.getItem(slot);
                if (item.getItem() instanceof FilledMapItem && FilledMapItem.getOrCreateSavedData(item, entity.level) == state) {
                    return item.getTag().getInt("yLevel");
                }
            }
        } else {
            for (DimensionEntry dimensionEntry : dimensions.get()) {
                String id = world.dimension().getRegistryName() + ":" + world.dimension().location();
                if (dimensionEntry.dimension.equals(id)) {
                    return dimensionEntry.scanHeight;
                }
            }
        }
        return 100;
    }

    static {
        COMMON_BUILDER.push("Use Map Creation Height");
        useMapCreationHeight = COMMON_BUILDER.comment("If true, uses the height a map was created at rather than a fixed height. Default: false").define("Use Creation Height", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Dimension List");
        dimensions = COMMON_BUILDER.comment("Add dimensions and their scan heights. Default: minecraft:nether at 40")
                .define("dimensions", Arrays.asList(new DimensionEntry("minecraft:nether", 40)));
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
    }

}

class DimensionEntry {

    String dimension;
    int scanHeight;

    public DimensionEntry() {
        dimension = "";
        scanHeight = 100;
    }

    public DimensionEntry(String dimension, int scanHeight) {
        this.dimension = dimension;
        this.scanHeight = scanHeight;
    }
}

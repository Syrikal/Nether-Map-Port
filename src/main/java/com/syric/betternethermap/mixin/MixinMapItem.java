package com.syric.betternethermap.mixin;

import com.syric.betternethermap.config.BNMConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(MapItem.class)
public class MixinMapItem {

    @Redirect(method = "update", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/dimension/DimensionType;hasCeiling()Z"))
    /**
     * Make every dimension have a sky, which makes maps show the surface.
     *
     * @see FilledMapItem#update(World world, Entity entity, MapData data)
     */
    public boolean hasCeiling(DimensionType type) {
        return false;
    }

    @Redirect(method = "update", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/chunk/LevelChunk;getHeight(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I"))
    /**
     * Change the height at which the map starts to scan for blocks.
     * If the dimension type has no ceiling, returns the surface.
     * Otherwise, checks the player's inventory for the map item and reads its stored yLevel.
     */
    public int getHeight(LevelChunk chunk, Heightmap.Types type, int x, int z, Level world, Entity entity, MapItemSavedData data) {
        boolean caveMode = false;
        int foundLevel = -1000;

        //If cave dimension, find the level. Cave mode is true.
        if (world.dimensionType().hasCeiling()) {
            if (entity instanceof ServerPlayer player) {
                for (int slot = 0; slot < player.getInventory().items.size(); slot++) {
                    ItemStack item = player.getInventory().getItem(slot);
                    //If the map is found, return its yLevel value!
                    if (item.getItem() instanceof MapItem && Objects.equals(MapItem.getSavedData(item, entity.level), data)) {
                        //This might damage preexisting maps! Probably only of the nether though.
//                    BetterNetherMap.LOGGER.info("Scanning at y-level " + item.getOrCreateTag().getInt("yLevel"));
                        foundLevel = item.getOrCreateTag().getInt("yLevel");
                        caveMode = true;
                    }
                }
                //Also check the offhand!
                ItemStack offhandItem = player.getOffhandItem();
                if (offhandItem.getItem() instanceof MapItem && Objects.equals(MapItem.getSavedData(offhandItem, entity.level), data)) {
                    //This might damage preexisting maps! Probably only of the nether though.
//                BetterNetherMap.LOGGER.info("Scanning at y-level " + offhandItem.getOrCreateTag().getInt("yLevel"));
                    foundLevel = offhandItem.getOrCreateTag().getInt("yLevel");
                    caveMode = true;
                }
            }
        }
        //Otherwise, check cave mode and y level.
        else {
            if (entity instanceof ServerPlayer player) {
                for (int slot = 0; slot < player.getInventory().items.size(); slot++) {
                    ItemStack item = player.getInventory().getItem(slot);
                    //If the map is found, return its yLevel value!
                    if (item.getItem() instanceof MapItem && Objects.equals(MapItem.getSavedData(item, entity.level), data)) {
                        //This might damage preexisting maps! Probably only of the nether though.
//                    BetterNetherMap.LOGGER.info("Scanning at y-level " + item.getOrCreateTag().getInt("yLevel"));
                        foundLevel = item.getOrCreateTag().getInt("yLevel");
                        caveMode = item.getOrCreateTag().getBoolean("forceCave") && BNMConfig.enableOverworldMapping.get();
                    }
                }
                //Also check the offhand!
                ItemStack offhandItem = player.getOffhandItem();
                if (offhandItem.getItem() instanceof MapItem && Objects.equals(MapItem.getSavedData(offhandItem, entity.level), data)) {
                    //This might damage preexisting maps! Probably only of the nether though.
//                BetterNetherMap.LOGGER.info("Scanning at y-level " + offhandItem.getOrCreateTag().getInt("yLevel"));
                    foundLevel = offhandItem.getOrCreateTag().getInt("yLevel");
                    caveMode = offhandItem.getOrCreateTag().getBoolean("forceCave") && BNMConfig.enableOverworldMapping.get();
                }
            }
        }

        //Return the found level if it's cave mode (and the found level wasn't the default, -1000), or the chunk height if it's not.
        if (caveMode & foundLevel != -1000) {
            return foundLevel;
        } else {
            return chunk.getHeight(Heightmap.Types.WORLD_SURFACE, x, z) + 1;
        }
    }
}

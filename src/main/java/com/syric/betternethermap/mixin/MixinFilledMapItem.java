package com.syric.betternethermap.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.FilledMapItem;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(FilledMapItem.class)
public class MixinFilledMapItem {

    @Redirect(method = "update", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/DimensionType;hasCeiling()Z"))
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
            target = "Lnet/minecraft/world/chunk/Chunk;getHeight(Lnet/minecraft/world/gen/Heightmap$Type;II)I"))
    /**
     * Change the height at which the map starts to scan for blocks.
     * If the dimension type has no ceiling, returns the surface.
     * Otherwise, checks the player's inventory for the map item and reads its stored yLevel.
     */
    public int getHeight(Chunk chunk, Heightmap.Type type, int x, int z, World world, Entity entity, MapData data) {
        int candidateLevel = -1;

        if (world.dimensionType().hasCeiling()) {
            ServerPlayerEntity player = (ServerPlayerEntity) (entity);
            for (int slot = 0; slot < player.inventory.items.size(); slot++) {
                ItemStack item = player.inventory.getItem(slot);
                //If the map is found, return its yLevel value!
                if (item.getItem() instanceof FilledMapItem && Objects.equals(FilledMapItem.getOrCreateSavedData(item, entity.level), data)) {
                    //This might damage preexisting maps! Probably only of the nether though.
//                    BetterNetherMap.LOGGER.info("Scanning at y-level " + item.getOrCreateTag().getInt("yLevel"));
                    candidateLevel = item.getOrCreateTag().getInt("yLevel");
                }
            }
            //Also check the offhand!
            ItemStack offhandItem = player.getOffhandItem();
            if (offhandItem.getItem() instanceof FilledMapItem && Objects.equals(FilledMapItem.getOrCreateSavedData(offhandItem, entity.level), data)) {
                //This might damage preexisting maps! Probably only of the nether though.
//                BetterNetherMap.LOGGER.info("Scanning at y-level " + offhandItem.getOrCreateTag().getInt("yLevel"));
                candidateLevel = offhandItem.getOrCreateTag().getInt("yLevel");
            }
            //If no map is found for some reason, return 256.
        }

        if (candidateLevel != -1) {
            return candidateLevel;
        } else {
            return chunk.getHeight(Heightmap.Type.WORLD_SURFACE, x, z) + 1;
        }
    }

}

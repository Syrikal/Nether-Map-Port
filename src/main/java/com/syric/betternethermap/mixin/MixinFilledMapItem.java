package com.syric.betternethermap.mixin;

import com.syric.betternethermap.BNMConfig;
import net.minecraft.entity.Entity;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.FilledMapItem;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

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
     */
    public int getHeight(Chunk chunk, Heightmap.Type type, int x, int z, World world, Entity entity, MapData data) {
        int scanHeight = BNMConfig.getDimensionScanHeight(world, entity, data);
        if (world.dimensionType().hasCeiling()) {
            return scanHeight;
        } else {
            return chunk.getHeight(Heightmap.Type.WORLD_SURFACE, x, z) + 1;
        }
    }

}

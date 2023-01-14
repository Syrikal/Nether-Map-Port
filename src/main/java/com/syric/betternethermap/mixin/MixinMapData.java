package com.syric.betternethermap.mixin;

import com.syric.betternethermap.config.BNMConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MapData.class)
public class MixinMapData {

    @Shadow @Final public Map<String, MapDecoration> decorations;

    @Shadow public RegistryKey<World> dimension;

    @Redirect(method = "addDecoration", at = @At(value = "FIELD", target = "Lnet/minecraft/world/storage/MapData;dimension:Lnet/minecraft/util/RegistryKey;"))
    //Disables the spinning indicator in the Nether.
    public RegistryKey<World> addDecoration(MapData instance) {

        if (BNMConfig.disableSpinningIndicator.get()) {
            return World.OVERWORLD;
        } else {
            return instance.dimension;
        }
    }

    @Inject(method= "tickCarriedBy", at = @At(value = "TAIL"))
    //Removes player location icon when in a different dimension.
    public void removeOtherDimensions(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (player.level.dimension() != this.dimension) {
            this.decorations.remove(player.getName().getString());
        }
    }

}

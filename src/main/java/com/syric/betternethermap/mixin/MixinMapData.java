package com.syric.betternethermap.mixin;

import com.syric.betternethermap.BNMConfig;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MapData.class)
public class MixinMapData {

    @Redirect(method = "addDecoration", at = @At(value = "FIELD", target = "Lnet/minecraft/world/storage/MapData;dimension:Lnet/minecraft/util/RegistryKey;"))
    public RegistryKey<World> addDecoration(MapData instance) {

        if (BNMConfig.disableSpinningIndicator.get()) {
            return World.OVERWORLD;
        } else {
            return instance.dimension;
        }
    }

    //Disable cursor if player isn't in the dimension!

}

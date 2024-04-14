package com.syric.betternethermap.mixin;

import com.syric.betternethermap.config.BNMConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MapItemSavedData.class)
public class MixinMapItemSavedData {

    @Shadow @Final
    Map<String, MapDecoration> decorations;

    @Final
    @Shadow public ResourceKey<Level> dimension;

    @Redirect(method = "addDecoration", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData;dimension:Lnet/minecraft/resources/ResourceKey;"))
    //Disables the spinning indicator in the Nether.
    public ResourceKey<Level> addDecoration(MapItemSavedData instance) {

        if (BNMConfig.disableSpinningIndicator.get()) {
            return Level.OVERWORLD;
        } else {
            return instance.dimension;
        }
    }

    @Inject(method= "tickCarriedBy", at = @At(value = "TAIL"))
    //Removes player location icon when in a different dimension.
    public void removeOtherDimensions(Player player, ItemStack stack, CallbackInfo ci) {
        if (player.level().dimension() != this.dimension) {
            this.decorations.remove(player.getName().getString());
        }
    }

}

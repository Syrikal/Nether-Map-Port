package com.syric.betternethermap.mixin;

import com.syric.betternethermap.config.BNMConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.syric.betternethermap.getMapHeight.getVanillaMapHeight;

@Mixin(EmptyMapItem.class)
public class MixinEmptyMapItem {

    @Redirect(method = "use", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;copy()Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack copy(ItemStack instance, Level world, Player player) {
        ItemStack output = instance.copy();
        //Add the NBT
        CompoundTag tag = output.getOrCreateTag();
        tag.putInt("yLevel", getVanillaMapHeight(player, world));
        if (BNMConfig.debugMessages.get() && world.isClientSide) {
            player.displayClientMessage(Component.nullToEmpty("Set map y-level to " + getVanillaMapHeight(player, world)), false);
            player.displayClientMessage(Component.nullToEmpty("Tag: " + tag), false);
        }
        output.setTag(tag);
        return output;
    }


}

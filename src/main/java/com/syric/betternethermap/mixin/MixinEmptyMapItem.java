package com.syric.betternethermap.mixin;

import com.syric.betternethermap.config.BNMConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.syric.betternethermap.getMapHeight.getVanillaMapHeight;

@Mixin(EmptyMapItem.class)
public class MixinEmptyMapItem {

    @ModifyVariable(method = "use", at = @At("STORE"), ordinal = 0)
    private ItemStack filledMap(ItemStack filledMapStack, Level world, Player player, InteractionHand hand) {

        //Add the NBT
        CompoundTag tag = filledMapStack.getOrCreateTag();
        tag.putInt("yLevel", getVanillaMapHeight(player, world));
        if (world.isClientSide && BNMConfig.debugMessages.get()) {
            player.displayClientMessage(Component.nullToEmpty("Set map y-level to " + getVanillaMapHeight(player, world)), false);
        }
        filledMapStack.setTag(tag);
        return filledMapStack;
    }

}

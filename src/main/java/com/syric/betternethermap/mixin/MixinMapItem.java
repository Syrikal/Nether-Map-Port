package com.syric.betternethermap.mixin;

import com.syric.betternethermap.config.BNMConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MapItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.syric.betternethermap.getMapHeight.getNewMapHeight;

@Mixin(MapItem.class)
public class MixinMapItem {

    @ModifyVariable(method = "use", at = @At("STORE"), ordinal = 0)
    private ItemStack filledMap(ItemStack filledMapStack, World world, PlayerEntity player, Hand hand) {

        //Add the NBT
        CompoundNBT tag = filledMapStack.getOrCreateTag();
        tag.putInt("yLevel", getNewMapHeight(player, world));
        if (world.isClientSide && BNMConfig.debugMessages.get()) {
            player.displayClientMessage(ITextComponent.nullToEmpty("Set map y-level to " + getNewMapHeight(player, world)), false);
        }
        filledMapStack.setTag(tag);
        return filledMapStack;
    }

}

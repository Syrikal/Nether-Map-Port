package com.syric.betternethermap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MapItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public class BNMEvents {

    public static void makeCaveMap(LivingEntityUseItemEvent.Finish event) {
        World world = event.getEntity().level;
        ItemStack used = event.getItem();
        if (used.getItem() instanceof MapItem && event.getEntity() instanceof PlayerEntity) {
            if(!world.isClientSide) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                ItemStack filledMap = FilledMapItem.create(world, MathHelper.floor(player.getX()), MathHelper.floor(player.getZ()), (byte)0, true, false);
                CompoundNBT tag = filledMap.getOrCreateTag();
                tag.putInt("yLevel", (int) player.getY());
                filledMap.setTag(tag);
            } else {
                event.setCanceled(true);
            }
        }
    }

//    ItemStack used = player.getStackInHand(hand);
//
//        if (used.getItem() instanceof EmptyMapItem) {
//        if (world.isClient) {
//            return TypedActionResult.success(used);
//        } else {
//            if (!player.abilities.creativeMode) {
//                used.decrement(1);
//            }
//
//            player.incrementStat(Stats.USED.getOrCreateStat(used.getItem()));
//            player.world.playSoundFromEntity((PlayerEntity)null, player, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, player.getSoundCategory(), 1.0F, 1.0F);
//            ItemStack filledMap = FilledMapItem.createMap(world, MathHelper.floor(player.getX()), MathHelper.floor(player.getZ()), (byte)0, true, false);
//            NbtCompound tag = filledMap.getTag();
//            tag.putInt("yLevel", (int) player.getY());
//            filledMap.setTag(tag);
//            if (used.isEmpty()) {
//                // For some reason fabric's mixin breaks consuming items, so doing it manually here.
//                player.setStackInHand(hand, filledMap);
//                return TypedActionResult.consume(filledMap);
//            } else {
//                if (!player.inventory.insertStack(filledMap.copy())) {
//                    player.dropItem(filledMap, false);
//                }
//
//                return TypedActionResult.consume(used);
//            }
//        }
//    }
//        return TypedActionResult.pass(used);


}

package com.syric.betternethermap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MapItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;

public class BNMEvents {

    //When a new map is created, add a y-level to its NBT data.
    public static void setMapHeight(PlayerInteractEvent.RightClickItem event) {
        World world = event.getEntity().level;
        ItemStack used = event.getItemStack();
        if (used.getItem() instanceof MapItem && event.getEntity() instanceof PlayerEntity) {
            event.setCanceled(true);
            PlayerEntity player = (PlayerEntity) event.getEntity();

            ItemStack filledMapStack = FilledMapItem.create(world, MathHelper.floor(player.getX()), MathHelper.floor(player.getZ()), (byte)0, true, false);
            ItemStack emptyMapStack = player.getItemInHand(event.getHand());

            //Add the NBT
            CompoundNBT tag = filledMapStack.getOrCreateTag();
            tag.putInt("yLevel", getNewMapHeight(used, player, world));
            filledMapStack.setTag(tag);

            if (!player.abilities.instabuild) {
                emptyMapStack.shrink(1);
            }

            player.awardStat(Stats.ITEM_USED.get(used.getItem()));
            player.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F, 1.0F);
            if (emptyMapStack.isEmpty()) {
                event.setResult(Event.Result.DEFAULT);
            } else {
                if (!player.inventory.add(filledMapStack.copy())) {
                    player.drop(filledMapStack, false);
                }
                event.setResult(Event.Result.DEFAULT);
            }
        }
    }

    //A new map's height is its creation value by default.
    //If useFixedHeight is enabled and the map is not a VariableHeightMap, it uses the fixed height (from the config) instead.
    private static int getNewMapHeight(ItemStack item, PlayerEntity player, World world) {
        if (BNMConfig.useFixedHeight.get() && !(item.getItem() instanceof VariableHeightMapItem)) {
            return BNMConfig.getFixedHeight(world);
        } else {
            //Don't return a variable height lower than the minimum.
            return (int) Math.max(player.getY(), BNMConfig.minimumMapHeight.get());
        }
    }

}

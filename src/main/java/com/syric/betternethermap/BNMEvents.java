package com.syric.betternethermap;

import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.config.MapBehaviorType;
import com.syric.betternethermap.items.AlternateMapItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;

public class BNMEvents {

    //When a new map is created, add a y-level to its NBT data.
    public static void setMapHeight(PlayerInteractEvent.RightClickItem event) {
        Level world = event.getEntity().level;
        ItemStack used = event.getItemStack();
        if (used.getItem() instanceof EmptyMapItem) {
            event.setCanceled(true);
            Player player = event.getEntity();

            if (used.getItem() instanceof AlternateMapItem alt) {
//                AlternateMapItem alt = (AlternateMapItem) used.getItem();
                if (!alt.enabled()) {
                    if (world.isClientSide) {
                        player.displayClientMessage(Component.translatable("betternethermap.failmessage"), false);
                    }
                    event.setResult(Event.Result.DENY);
                    return;
                }
            }

            ItemStack filledMapStack = MapItem.create(world, Mth.floor(player.getX()), Mth.floor(player.getZ()), (byte)0, true, false);
            ItemStack emptyMapStack = player.getItemInHand(event.getHand());

            //Add the NBT
            CompoundTag tag = filledMapStack.getOrCreateTag();
            tag.putInt("yLevel", getNewMapHeight(used, player, world));
            if (used.getItem() instanceof AlternateMapItem && BNMConfig.enableOverworldMapping.get()) {
                tag.putBoolean("forceCave", true);
            }
            if (world.isClientSide && BNMConfig.debugMessages.get()) {
                player.displayClientMessage(Component.nullToEmpty("Set map y-level to " + getNewMapHeight(used, player, world)), false);
            }
            filledMapStack.setTag(tag);

            if (!player.isCreative()) {
                emptyMapStack.shrink(1);
            }

            player.awardStat(Stats.ITEM_USED.get(used.getItem()));
            player.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F, 1.0F);
            if (emptyMapStack.isEmpty()) {
                event.setResult(Event.Result.DEFAULT);
            } else {
                if (!player.getInventory().add(filledMapStack.copy())) {
                    player.drop(filledMapStack, false);
                }
                event.setResult(Event.Result.DEFAULT);
            }
        }
    }

    //A new map's height is its creation value by default.
    //If useFixedHeight is enabled and the map is not a VariableHeightMap, it uses the fixed height (from the config) instead.
    private static int getNewMapHeight(ItemStack item, Player player, Level world) {

        //Decide what behavior type it is
        MapBehaviorType category;

        if (item.getItem() instanceof AlternateMapItem alt) {
            category = alt.type;
        } else {
            category = BNMConfig.defaultBehavior.get();
        }

        //Return the appropriate height
        if (category.equals(MapBehaviorType.FIXED)) {
            return BNMConfig.getFixedHeight(world);
        } else if (category.equals(MapBehaviorType.SNAP)) {
            return BNMConfig.getSnapHeight(world, (int) player.getY());
        } else {
            return (int) Math.max((player.getY() + (double) BNMConfig.variableModifier.get()), BNMConfig.getMinHeight(world));
        }
    }

}

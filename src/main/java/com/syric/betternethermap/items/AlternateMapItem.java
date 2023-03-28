package com.syric.betternethermap.items;

import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.config.MapBehaviorType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;

import static com.syric.betternethermap.getMapHeight.getAltMapHeight;

public class AlternateMapItem extends EmptyMapItem {

    public final MapBehaviorType type;

    public AlternateMapItem(Properties properties, MapBehaviorType type) {
        super(properties);
        this.type = type;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
//        BetterNetherMap.LOGGER.info("Detected an alternate map");
        ItemStack blankMapStack = player.getItemInHand(hand);

        //If the map type is not enabled, display a fail message and fail.
        if (!enabled()) {
            if (world.isClientSide) {
                player.displayClientMessage(Component.translatable("betternethermap.failmessage"), false);
            }
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }

        //If clientside, succeed. Print debug statement if enabled.
        if (world.isClientSide) {
            if (BNMConfig.debugMessages.get()) {
                player.displayClientMessage(Component.nullToEmpty("Set map y-level to " + getAltMapHeight(blankMapStack, player, world)), false);
            }
            return InteractionResultHolder.success(blankMapStack);
        }

        //Create and tag the new map.
        ItemStack filledMapStack = MapItem.create(world, player.getBlockX(), player.getBlockZ(), (byte)0, true, false);
        CompoundTag tag = filledMapStack.getOrCreateTag();
        tag.putInt("yLevel", getAltMapHeight(blankMapStack, player, world));
        if (blankMapStack.getItem() instanceof AlternateMapItem && BNMConfig.enableOverworldMapping.get()) {
            tag.putBoolean("forceCave", true);
        }
        filledMapStack.setTag(tag);

        //Remove blank map.
        if (!player.getAbilities().instabuild) {
            blankMapStack.shrink(1);
        }

        //Add new map
        player.awardStat(Stats.ITEM_USED.get(this));
        player.level.playSound(null, player, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, player.getSoundSource(), 1.0F, 1.0F);
        if (blankMapStack.isEmpty()) {
            return InteractionResultHolder.consume(filledMapStack);
        } else {
            if (!player.getInventory().add(filledMapStack.copy())) {
                player.drop(filledMapStack, false);
            }
            return InteractionResultHolder.consume(blankMapStack);
        }

    }

    public boolean enabled() {
        if (this.type == MapBehaviorType.FIXED) {
//            BetterNetherMap.LOGGER.info("Detected a fixed map, enabled: " + BNMConfig.addFixedMaps.get());
            return BNMConfig.addFixedMaps.get();
        } else if (this.type == MapBehaviorType.SNAP) {
//            BetterNetherMap.LOGGER.info("Detected a snap map, enabled: " + BNMConfig.addSnapMaps.get());
            return BNMConfig.addSnapMaps.get();
        } else {
//            BetterNetherMap.LOGGER.info("Detected a variable map, enabled: " + BNMConfig.addVariableMaps.get());
            return BNMConfig.addVariableMaps.get();
        }
    }

}

package com.syric.betternethermap.items;

import com.syric.betternethermap.BetterNetherMap;
import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.config.MapBehaviorType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MapItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AlternateMapItem extends MapItem {

    public final MapBehaviorType type;

    public AlternateMapItem(Properties properties, MapBehaviorType type) {
        super(properties);
        this.type = type;
    }

//    @Override
//    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
//        BetterNetherMap.LOGGER.info("Detected an alternate map");
//        if (enabled()) {
//            ItemStack itemstack = FilledMapItem.create(world, MathHelper.floor(player.getX()), MathHelper.floor(player.getZ()), (byte)0, true, false);
//            ItemStack itemstack1 = player.getItemInHand(hand);
//            if (!player.isCreative()) {
//                itemstack1.shrink(1);
//            }
//
//            player.awardStat(Stats.ITEM_USED.get(this));
//            player.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F, 1.0F);
//            if (itemstack1.isEmpty()) {
//                return ActionResult.sidedSuccess(itemstack, world.isClientSide());
//            } else {
//                if (!player.inventory.add(itemstack.copy())) {
//                    player.drop(itemstack, false);
//                }
//                return ActionResult.sidedSuccess(itemstack1, world.isClientSide());
//            }
//        } else {
//            if (world.isClientSide) {
//                player.displayClientMessage(new TranslationTextComponent("betternethermap.failmessage"), false);
//            }
//            return ActionResult.fail(player.getItemInHand(hand));
//        }
//    }

    public boolean enabled() {
        if (this.type == MapBehaviorType.FIXED) {
            BetterNetherMap.LOGGER.info("Detected a fixed map, enabled: " + BNMConfig.addFixedMaps.get());
            return BNMConfig.addFixedMaps.get();
        } else if (this.type == MapBehaviorType.SNAP) {
            BetterNetherMap.LOGGER.info("Detected a snap map, enabled: " + BNMConfig.addSnapMaps.get());
            return BNMConfig.addSnapMaps.get();
        } else {
            BetterNetherMap.LOGGER.info("Detected a variable map, enabled: " + BNMConfig.addVariableMaps.get());
            return BNMConfig.addVariableMaps.get();
        }
    }

}

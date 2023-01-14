package com.syric.betternethermap.items;

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

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (enabled()) {
            ItemStack itemstack = FilledMapItem.create(world, MathHelper.floor(player.getX()), MathHelper.floor(player.getZ()), (byte)0, true, false);
            ItemStack itemstack1 = player.getItemInHand(hand);
            if (!player.abilities.instabuild) {
                itemstack1.shrink(1);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F, 1.0F);
            if (itemstack1.isEmpty()) {
                return ActionResult.sidedSuccess(itemstack, world.isClientSide());
            } else {
                if (!player.inventory.add(itemstack.copy())) {
                    player.drop(itemstack, false);
                }

                return ActionResult.sidedSuccess(itemstack1, world.isClientSide());
            }
        } else {
            if (world.isClientSide) {
                player.displayClientMessage(new TranslationTextComponent("betternethermap.failmessage"), false);
            }
            return ActionResult.fail(player.getItemInHand(hand));
        }
    }

    private boolean enabled() {
        if (type == MapBehaviorType.FIXED) {
            return BNMConfig.addFixedMaps.get();
        } else if (type == MapBehaviorType.SNAP) {
            return BNMConfig.addSnapMaps.get();
        } else {
            return BNMConfig.addVariableMaps.get();
        }
    }

}
package com.syric.betternethermap.items;

import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.config.MapBehaviorType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MapItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import static com.syric.betternethermap.getMapHeight.getNewMapHeight;

public class AlternateMapItem extends MapItem {

    public final MapBehaviorType type;

    public AlternateMapItem(Properties properties, MapBehaviorType type) {
        super(properties);
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack filledMapStack = FilledMapItem.create(world, MathHelper.floor(player.getX()), MathHelper.floor(player.getZ()), (byte)0, true, false);
        ItemStack blankMapStack = player.getItemInHand(hand);

        //Disallow if type not enabled
        if (!enabled()) {
            if (world.isClientSide) {
                player.displayClientMessage(new TranslationTextComponent("betternethermap.failmessage"), false);
            }
            return ActionResult.fail(player.getItemInHand(hand));
        }

        //Set yLevel and forceCave
        CompoundNBT tag = filledMapStack.getOrCreateTag();
        tag.putInt("yLevel", getNewMapHeight(blankMapStack, player, world));
        if (BNMConfig.enableOverworldMapping.get()) {
            tag.putBoolean("forceCave", true);
        }
        filledMapStack.setTag(tag);

        //Announce height if debug mode is on
        if (world.isClientSide && BNMConfig.debugMessages.get()) {
            player.displayClientMessage(ITextComponent.nullToEmpty("Set map y-level to " + getNewMapHeight(blankMapStack, player, world)), false);
        }

        //Remove blank map
        if (!player.abilities.instabuild) {
            blankMapStack.shrink(1);
        }

        //Add new map
        player.awardStat(Stats.ITEM_USED.get(this));
        player.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F, 1.0F);
        if (blankMapStack.isEmpty()) {
            return ActionResult.sidedSuccess(filledMapStack, world.isClientSide());
        } else {
            if (!player.inventory.add(filledMapStack.copy())) {
                player.drop(filledMapStack, false);
            }

            return ActionResult.sidedSuccess(blankMapStack, world.isClientSide());
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

package com.syric.betternethermap;

import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.config.MapBehaviorType;
import com.syric.betternethermap.items.AlternateMapItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class getMapHeight {

    //A new map's height is its creation value by default.
    //If useFixedHeight is enabled and the map is not a VariableHeightMap, it uses the fixed height (from the config) instead.
    public static int getNewMapHeight(ItemStack item, PlayerEntity player, World world) {

        //Decide what behavior type it is
        MapBehaviorType category;

        if (item.getItem() instanceof AlternateMapItem) {
            AlternateMapItem alt = (AlternateMapItem) item.getItem();
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

    public static int getNewMapHeight(PlayerEntity player, World world) {
        //Decide what behavior type it is
        MapBehaviorType category = BNMConfig.defaultBehavior.get();

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

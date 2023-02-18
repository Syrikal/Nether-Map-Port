package com.syric.betternethermap;

import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.config.MapBehaviorType;
import com.syric.betternethermap.items.AlternateMapItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class getMapHeight {

    //A new map's height is its creation value by default.
    //If useFixedHeight is enabled and the map is not a VariableHeightMap, it uses the fixed height (from the config) instead.
    public static int getAltMapHeight(ItemStack item, Player player, Level world) {

        //Decide what behavior type it is
        MapBehaviorType category;

        //Should always be an AlternateMapItem!
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

    //Other version for use in vanilla maps
    //If useFixedHeight is enabled and the map is not a VariableHeightMap, it uses the fixed height (from the config) instead.
    public static int getVanillaMapHeight(Player player, Level world) {

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

package com.syric.betternethermap.items;

import com.syric.betternethermap.BetterNetherMap;
import com.syric.betternethermap.config.MapBehaviorType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BNMItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterNetherMap.MODID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }


    // register items
    public static final RegistryObject<Item> FIXED_HEIGHT_MAP = ITEMS.register("fixedmap",
            () -> new AlternateMapItem(new Item.Properties().tab(ItemGroup.TAB_MISC), MapBehaviorType.FIXED));
    public static final RegistryObject<Item> SNAP_HEIGHT_MAP = ITEMS.register("snapmap",
            () -> new AlternateMapItem(new Item.Properties().tab(ItemGroup.TAB_MISC), MapBehaviorType.SNAP));
    public static final RegistryObject<Item> VARIABLE_HEIGHT_MAP = ITEMS.register("variablemap",
            () -> new AlternateMapItem(new Item.Properties().tab(ItemGroup.TAB_MISC), MapBehaviorType.VARIABLE));


}

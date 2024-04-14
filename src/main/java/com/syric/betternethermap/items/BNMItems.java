package com.syric.betternethermap.items;

import com.syric.betternethermap.BetterNetherMap;
import com.syric.betternethermap.config.MapBehaviorType;
import net.minecraft.world.item.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BNMItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterNetherMap.MODID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }


    // register items
    public static final RegistryObject<Item> FIXED_HEIGHT_MAP = ITEMS.register("fixedmap",
            () -> new AlternateMapItem(new Item.Properties(), MapBehaviorType.FIXED));
    public static final RegistryObject<Item> SNAP_HEIGHT_MAP = ITEMS.register("snapmap",
            () -> new AlternateMapItem(new Item.Properties(), MapBehaviorType.SNAP));
    public static final RegistryObject<Item> VARIABLE_HEIGHT_MAP = ITEMS.register("variablemap",
            () -> new AlternateMapItem(new Item.Properties(), MapBehaviorType.VARIABLE));

    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.getEntries().putAfter(Items.MAP.getDefaultInstance(), new ItemStack(VARIABLE_HEIGHT_MAP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.MAP.getDefaultInstance(), new ItemStack(SNAP_HEIGHT_MAP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.MAP.getDefaultInstance(), new ItemStack(FIXED_HEIGHT_MAP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

}

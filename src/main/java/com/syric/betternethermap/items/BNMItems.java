package com.syric.betternethermap.items;

import com.syric.betternethermap.BetterNetherMap;
import com.syric.betternethermap.config.MapBehaviorType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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
            () -> new AlternateMapItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), MapBehaviorType.FIXED));
    public static final RegistryObject<Item> SNAP_HEIGHT_MAP = ITEMS.register("snapmap",
            () -> new AlternateMapItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), MapBehaviorType.SNAP));
    public static final RegistryObject<Item> VARIABLE_HEIGHT_MAP = ITEMS.register("variablemap",
            () -> new AlternateMapItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), MapBehaviorType.VARIABLE));


//    public static void addCreative(CreativeModeTabEvent.BuildContents event) {
//        if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
//            event.accept(FIXED_HEIGHT_MAP);
//            event.accept(SNAP_HEIGHT_MAP);
//            event.accept(VARIABLE_HEIGHT_MAP);
//        }
//    }

}

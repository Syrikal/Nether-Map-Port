package com.syric.betternethermap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BNMItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterNetherMap.MODID);

    static void register(IEventBus bus) {
        ITEMS.register(bus);
    }


    // register items
    public static final RegistryObject<Item> VARIABLE_HEIGHT_MAP = ITEMS.register("variableheightmap",
            () -> new VariableHeightMapItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));


}

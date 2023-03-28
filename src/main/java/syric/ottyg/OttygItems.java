package syric.ottyg;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OttygItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OhTheTreesYoullGrow.MODID);

    static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static final RegistryObject<Item> DECIDUOUS = ITEMS.register("deciduous_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> ANCIENT = ITEMS.register("ancient_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BLUFF = ITEMS.register("bluff_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> MUTATED = ITEMS.register("mutated_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> OAK = ITEMS.register("oak_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SPRUCE = ITEMS.register("spruce_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BIRCH = ITEMS.register("birch_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BOREAL = ITEMS.register("boreal_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BAYOU = ITEMS.register("bayou_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> WOODLANDS = ITEMS.register("woodlands_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> MEADOW = ITEMS.register("meadow_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> GROVE = ITEMS.register("grove_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SHRUB = ITEMS.register("shrub_bone_meal",
            () -> new EnhancedBonemealItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));


}

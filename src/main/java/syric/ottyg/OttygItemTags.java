package syric.ottyg;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class OttygItemTags {
    public static final Tags.IOptionalNamedTag<Item> BONE_MEALS = register("bone_meals");

    public OttygItemTags() {
    }

    public static void init() {
    }

    private static Tags.IOptionalNamedTag<Item> register(String path) {
        return ItemTags.createOptional((new ResourceLocation(OhTheTreesYoullGrow.MODID, path)));
    }

}

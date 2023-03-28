package syric.ottyg;

import corgiaoc.byg.common.properties.blocks.BYGBeachGrassBlock;
import corgiaoc.byg.common.world.feature.config.BYGTreeConfig;
import corgiaoc.byg.common.world.feature.overworld.trees.util.HugeTreeSpawner;
import corgiaoc.byg.common.world.feature.overworld.trees.util.TreeSpawner;
import corgiaoc.byg.core.BYGBlocks;
import corgiaoc.byg.core.world.BYGConfiguredFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class TreeSpawners {

    public static final TreeSpawner DECIDUOUS_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.25) {
                return BYGConfiguredFeatures.DECIDUOUS_TREE1;
            } else if (d < 0.5) {
                return BYGConfiguredFeatures.DECIDUOUS_TREE2;
            } else if (d < 0.75) {
                return BYGConfiguredFeatures.DECIDUOUS_TREE3;
            } else {
                return BYGConfiguredFeatures.DECIDUOUS_TREE4;
            }
        }
    };
    public static final TreeSpawner DECIDUOUS_RED_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.25) {
                return BYGConfiguredFeatures.DECIDUOUS_RED_TREE1;
            } else if (d < 0.5) {
                return BYGConfiguredFeatures.DECIDUOUS_RED_TREE2;
            } else if (d < 0.75) {
                return BYGConfiguredFeatures.DECIDUOUS_RED_TREE3;
            } else {
                return BYGConfiguredFeatures.DECIDUOUS_RED_TREE4;
            }
        }
    };
    public static final TreeSpawner DECIDUOUS_BROWN_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.25) {
                return BYGConfiguredFeatures.DECIDUOUS_BROWN_TREE1;
            } else if (d < 0.5) {
                return BYGConfiguredFeatures.DECIDUOUS_BROWN_TREE2;
            } else if (d < 0.75) {
                return BYGConfiguredFeatures.DECIDUOUS_BROWN_TREE3;
            } else {
                return BYGConfiguredFeatures.DECIDUOUS_BROWN_TREE4;
            }
        }
    };
    public static final TreeSpawner DECIDUOUS_ORANGE_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.25) {
                return BYGConfiguredFeatures.DECIDUOUS_ORANGE_TREE1;
            } else if (d < 0.5) {
                return BYGConfiguredFeatures.DECIDUOUS_ORANGE_TREE2;
            } else if (d < 0.75) {
                return BYGConfiguredFeatures.DECIDUOUS_ORANGE_TREE3;
            } else {
                return BYGConfiguredFeatures.DECIDUOUS_ORANGE_TREE4;
            }
        }
    };

    public static final TreeSpawner ANCIENT_DARK_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.ANCIENT_TREE1;
        }
    };
    public static final TreeSpawner ANCIENT_EBONY = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.ANCIENT_TREE2;
        }
    };
    public static final TreeSpawner ANCIENT_MAPLE = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.ANCIENT_TREE3;
        }
    };

    public static final TreeSpawner BLUFF_SPRUCE = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.35) {
                return BYGConfiguredFeatures.BLUFF_TREE1;
            } else if (d < 0.7) {
                return BYGConfiguredFeatures.BLUFF_TREE2;
            } else {
                return BYGConfiguredFeatures.BLUFF_TREE3;
            }
        }
    };

    public static final TreeSpawner MUTATED_WILLOW = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.5) {
                return BYGConfiguredFeatures.WILLOW_M_TREE1;
            } else {
                return BYGConfiguredFeatures.WILLOW_M_TREE2;
            }
        }
    };

    public static final TreeSpawner BOREAL_BIRCH = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.BIRCH_BOREAL_TREE1;
        }
    };
    public static final TreeSpawner BOREAL_YELLOW_BIRCH = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.BIRCH_YELLOW_BOREAL_TREE1;
        }
    };
    public static final TreeSpawner BOREAL_SPRUCE = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.BOREAL_TREE2;
        }
    };

    public static final TreeSpawner DEAD_WILLOW = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.WILLOW_DEAD_TREE1;
        }
    };

    public static final TreeSpawner WOODLANDS_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.WOODLANDS_TREE1;
        }
    };

    public static final TreeSpawner MEADOW_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.MEADOW_TREE1;
        }
    };

    public static final TreeSpawner MEADOW_DARK_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.5) {
                return BYGConfiguredFeatures.MEADOW_TREE2;
            } else {
                return BYGConfiguredFeatures.MEADOW_TREE3;
            }
        }
    };

    public static final TreeSpawner GROVE_BLUE = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.ENCHANTED_BLUE_GROVE_TREE1;
        }
    };
    public static final TreeSpawner GROVE_GREEN = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.ENCHANTED_GREEN_GROVE_TREE1;
        }
    };

    public static final TreeSpawner SHRUB_ASPEN = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.5) {
                return BYGConfiguredFeatures.ASPEN_SHRUB1;
            } else {
                return BYGConfiguredFeatures.ASPEN_SHRUB2;
            }
        }
    };

    public static final TreeSpawner SHRUB_DARK_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.5) {
                return BYGConfiguredFeatures.SHRUB_MEADOW;
            } else {
                return BYGConfiguredFeatures.SHRUB_MEADOW2;
            }
        }
    };

    public static final TreeSpawner SHRUB_RED_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.DECIDUOUS_SEASONAL_SHRUB;
        }
    };
    public static final TreeSpawner SHRUB_MAHOGANY = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            return BYGConfiguredFeatures.TROPICAL_SHRUB1;
        }
    };


    public static final TreeSpawner SHRUB_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.35) {
                return BYGConfiguredFeatures.SHRUB_PRAIRIE1;
            } else if (d < 0.7) {
                return BYGConfiguredFeatures.SHRUB_PRAIRIE2;
            } else {
                return BYGConfiguredFeatures.SHRUB;
            }
        }
    };

    public static final TreeSpawner BIRCH_BIRCH = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.25) {
                return BYGConfiguredFeatures.BIRCH_TREE1;
            } else if (d < 0.5) {
                return BYGConfiguredFeatures.BIRCH_TREE2;
            } else if (d < 0.75) {
                return BYGConfiguredFeatures.BIRCH_TREE3;
            } else {
                return BYGConfiguredFeatures.BIRCH_TREE4;
            }
        }
    };

    public static final TreeSpawner OAK_OAK = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.3) {
                return BYGConfiguredFeatures.OAK_TREE1;
            } else if (d < 0.5) {
                return BYGConfiguredFeatures.OAK_TREE2;
            } else if (d < 0.7) {
                return BYGConfiguredFeatures.OAK_TREE3;
            } else if (d < 0.8) {
                return BYGConfiguredFeatures.OAK_TREE_LARGE1;
            } else if (d < 0.9) {
                return BYGConfiguredFeatures.OAK_TREE_LARGE2;
            } else {
                return BYGConfiguredFeatures.OAK_TREE_LARGE3;
            }
        }
    };


    public static final TreeSpawner SPRUCE_SPRUCE = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<BYGTreeConfig, ?> getTreeFeature(Random random) {
            double d = random.nextDouble();
            if (d < 0.04) {
                return BYGConfiguredFeatures.SPRUCE_TREE1;
            } else if (d < 0.37) {
                return BYGConfiguredFeatures.SPRUCE_TREE2;
            } else if (d < 0.4) {
                return BYGConfiguredFeatures.SPRUCE_TREE3;
            } else if (d < 0.53) {
                return BYGConfiguredFeatures.SPRUCE_TREE4;
            } else if (d < 0.67) {
                return BYGConfiguredFeatures.SPRUCE_TREE_MEDIUM1;
            } else if (d < 0.8) {
                return BYGConfiguredFeatures.SPRUCE_TREE_MEDIUM2;
            } else if (d < 0.93) {
                return BYGConfiguredFeatures.SPRUCE_TREE_MEDIUM3;
            } else {
                return BYGConfiguredFeatures.SPRUCE_TREE_MEDIUM4;
            }
        }
    };


    public static TreeSpawner getSpawner(Item item, Block block) {
        if (item.equals(OttygItems.DECIDUOUS.get())) {
            if (block.is(Blocks.OAK_SAPLING)) {
                return DECIDUOUS_OAK;
            } else if (block.is(BYGBlocks.BROWN_OAK_SAPLING)) {
                return DECIDUOUS_BROWN_OAK;
            } else if (block.is(BYGBlocks.RED_OAK_SAPLING)) {
                return DECIDUOUS_RED_OAK;
            } else if (block.is(BYGBlocks.ORANGE_OAK_SAPLING)) {
                return DECIDUOUS_ORANGE_OAK;
            }
        }
        else if (item.equals(OttygItems.ANCIENT.get())) {
            if (block.is(Blocks.DARK_OAK_SAPLING)) {
                return ANCIENT_DARK_OAK;
            } else if (block.is(BYGBlocks.EBONY_SAPLING)) {
                return ANCIENT_EBONY;
            } else if (block.is(BYGBlocks.MAPLE_SAPLING)) {
                return ANCIENT_MAPLE;
            }
        }
        else if (item.equals(OttygItems.BLUFF.get())) {
            if (block.is(Blocks.SPRUCE_SAPLING)) {
                return BLUFF_SPRUCE;
            }
        }
        else if (item.equals(OttygItems.MUTATED.get())) {
            if (block.is(BYGBlocks.WILLOW_SAPLING)) {
                return MUTATED_WILLOW;
            }
        }
        else if (item.equals(OttygItems.OAK.get())) {
            if (block.is(Blocks.OAK_SAPLING)) {
                return OAK_OAK;
            }
        }
        else if (item.equals(OttygItems.SPRUCE.get())) {
            if (block.is(Blocks.SPRUCE_SAPLING)) {
                return SPRUCE_SPRUCE;
            }
        }
        else if (item.equals(OttygItems.BOREAL.get())) {
            //The colors are swapped on BYG's end
            if (block.is(Blocks.BIRCH_SAPLING)) {
                return BOREAL_YELLOW_BIRCH;
            } else if (block.is(BYGBlocks.YELLOW_BIRCH_SAPLING)) {
                return BOREAL_BIRCH;
            } else if (block.is(Blocks.SPRUCE_SAPLING)) {
                return BOREAL_SPRUCE;
            }
        }
        else if (item.equals(OttygItems.BIRCH.get())) {
            if (block.is(Blocks.BIRCH_SAPLING)) {
                return BIRCH_BIRCH;
            }
        }
        else if (item.equals(OttygItems.BAYOU.get())) {
            if (block.is(BYGBlocks.WILLOW_SAPLING)) {
                return DEAD_WILLOW;
            }
        }
        else if (item.equals(OttygItems.WOODLANDS.get())) {
            if (block.is(Blocks.OAK_SAPLING)) {
                return WOODLANDS_OAK;
            }
        }
        else if (item.equals(OttygItems.MEADOW.get())) {
            if (block.is(Blocks.OAK_SAPLING)) {
                return MEADOW_OAK;
            } else if (block.is(Blocks.DARK_OAK_SAPLING)) {
                return MEADOW_DARK_OAK;
            }
        }
        else if (item.equals(OttygItems.GROVE.get())) {
            if (block.is(BYGBlocks.BLUE_ENCHANTED_SAPLING)) {
                return GROVE_BLUE;
            } else if (block.is(BYGBlocks.GREEN_ENCHANTED_SAPLING)) {
                return GROVE_GREEN;
            }
        }
        else if (item.equals(OttygItems.SHRUB.get())) {
            if (block.is(BYGBlocks.ASPEN_SAPLING)) {
                return SHRUB_ASPEN;
            } else if (block.is(BYGBlocks.RED_OAK_SAPLING)) {
                return SHRUB_RED_OAK;
            } else if (block.is(Blocks.DARK_OAK_SAPLING)) {
                return SHRUB_DARK_OAK;
            } else if (block.is(Blocks.OAK_SAPLING)) {
                return SHRUB_OAK;
            } else if (block.is(BYGBlocks.MAHOGANY_SAPLING)) {
                return SHRUB_MAHOGANY;
            }
        }
        return null;
    }


}

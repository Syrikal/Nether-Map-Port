package syric.ottyg;

import corgiaoc.byg.common.world.feature.overworld.trees.util.TreeSpawner;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import static net.minecraft.block.SaplingBlock.STAGE;

public class EnhancedBonemealItem extends Item {
    public EnhancedBonemealItem(Item.Properties prop) {
        super(prop);
    }

    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        if (applyBonemeal(context.getItemInHand(), world, blockpos, context.getPlayer())) {
            if (!world.isClientSide) {
                world.levelEvent(2005, blockpos, 0);
            }

            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            return ActionResultType.PASS;
        }
    }


    public static boolean applyBonemeal(ItemStack stack, World world, BlockPos pos, net.minecraft.entity.player.PlayerEntity player) {
        BlockState blockstate = world.getBlockState(pos);
        TreeSpawner tree = TreeSpawners.getSpawner(stack.getItem(), blockstate.getBlock());
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, world, pos, blockstate, stack);
        if (hook != 0) return hook > 0;
        if (tree != null) {
            SaplingBlock saplingBlock = (SaplingBlock)blockstate.getBlock();
            if (saplingBlock.isValidBonemealTarget(world, pos, blockstate, world.isClientSide)) {
                if (world instanceof ServerWorld) {
                    ServerWorld server = (ServerWorld) world;
                    Random rand = new Random();
                    if (saplingBlock.isBonemealSuccess(world, world.random, pos, blockstate)) {
                        if ((Integer)blockstate.getValue(STAGE) == 0) {
                            world.setBlock(pos, (BlockState)blockstate.cycle(STAGE), 4);
                        } else {
                            tree.spawn(server, server.getChunkSource().getGenerator(), pos, blockstate, rand);
                        }
                    }
                    stack.shrink(1);
                }
                return true;
            }
        }

        return false;
    }


    @OnlyIn(Dist.CLIENT)
    public static void addGrowthParticles(IWorld p_195965_0_, BlockPos p_195965_1_, int p_195965_2_) {
        if (p_195965_2_ == 0) {
            p_195965_2_ = 15;
        }

        BlockState blockstate = p_195965_0_.getBlockState(p_195965_1_);
        if (!blockstate.isAir(p_195965_0_, p_195965_1_)) {
            double d0 = 0.5D;
            double d1;
            if (blockstate.is(Blocks.WATER)) {
                p_195965_2_ *= 3;
                d1 = 1.0D;
                d0 = 3.0D;
            } else if (blockstate.isSolidRender(p_195965_0_, p_195965_1_)) {
                p_195965_1_ = p_195965_1_.above();
                p_195965_2_ *= 3;
                d0 = 3.0D;
                d1 = 1.0D;
            } else {
                d1 = blockstate.getShape(p_195965_0_, p_195965_1_).max(Direction.Axis.Y);
            }

            p_195965_0_.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)p_195965_1_.getX() + 0.5D, (double)p_195965_1_.getY() + 0.5D, (double)p_195965_1_.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

            for(int i = 0; i < p_195965_2_; ++i) {
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextGaussian() * 0.02D;
                double d4 = random.nextGaussian() * 0.02D;
                double d5 = 0.5D - d0;
                double d6 = (double)p_195965_1_.getX() + d5 + random.nextDouble() * d0 * 2.0D;
                double d7 = (double)p_195965_1_.getY() + random.nextDouble() * d1;
                double d8 = (double)p_195965_1_.getZ() + d5 + random.nextDouble() * d0 * 2.0D;
                if (!p_195965_0_.getBlockState((new BlockPos(d6, d7, d8)).below()).isAir()) {
                    p_195965_0_.addParticle(ParticleTypes.HAPPY_VILLAGER, d6, d7, d8, d2, d3, d4);
                }
            }

        }
    }


}

package com.syric.betternethermap;

import com.syric.betternethermap.config.BNMConfig;
import com.syric.betternethermap.items.BNMItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("betternethermap")
public class BetterNetherMap
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "betternethermap";


    public BetterNetherMap() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BNMItems.register(modEventBus);
        modEventBus.addListener(BNMItems::buildCreativeModeTabs);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BNMConfig.COMMON_SPEC, "betternethermap-common.toml");
    }

}

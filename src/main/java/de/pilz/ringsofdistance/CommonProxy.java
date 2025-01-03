package de.pilz.ringsofdistance;

import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public RingsOfDistanceController controller = new RingsOfDistanceController();

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        RingsOfDistanceMod.LOG.info("I am Rings of distance at version " + Tags.VERSION);

        // Load config
        Config.initialize();

        // Register items
        ModItems.registerItems();

        // Register Bauble slot
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.ringType, 3);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        // Initialize controller
        FMLCommonHandler.instance().bus().register(controller);
        MinecraftForge.EVENT_BUS.register(controller);

        // Register recipes
        ModRecipes.registerRecipes();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}

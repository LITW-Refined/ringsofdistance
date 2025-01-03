package de.pilz.ringsofdistance;

import cpw.mods.fml.common.registry.GameRegistry;
import de.pilz.ringsofdistance.enums.ItemRingOfDistanceVariants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

// spotless off

public class ModRecipes {

    public static void registerRecipes() {
        ItemStack rodL1 = new ItemStack(ModItems.ITEM_RINGOFDISTANCE, 1, ItemRingOfDistanceVariants.LEVEL_1.getMeta());
        ItemStack rodL2 = new ItemStack(ModItems.ITEM_RINGOFDISTANCE, 1, ItemRingOfDistanceVariants.LEVEL_2.getMeta());
        ItemStack rodL3 = new ItemStack(ModItems.ITEM_RINGOFDISTANCE, 1, ItemRingOfDistanceVariants.LEVEL_3.getMeta());
        GameRegistry.addRecipe(new ShapedOreRecipe(rodL1, "ACA", "B B", "ABA", 'A', "ingotGold", 'B', "dyePurple", 'C', "dyeLime"));
        GameRegistry.addRecipe(new ShapedOreRecipe(rodL2, "ACA", "BDB", "ABA", 'A', "gemDiamond", 'B', "dyeRed", 'C', "gemEmerald", 'D', rodL1));
        GameRegistry.addRecipe(new ShapedOreRecipe(rodL3, "ACA", "BDB", "ABA", 'A', Blocks.obsidian, 'B', "dyeBrown", 'C', Items.nether_star, 'D', rodL2));
    }
}

// spotless on
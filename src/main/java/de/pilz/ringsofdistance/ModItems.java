package de.pilz.ringsofdistance;

import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;
import de.pilz.ringsofdistance.items.ItemRingOfDistance;
import de.pilz.ringsofdistance.strings.ItemNames;

public class ModItems {

    public static Item ITEM_RINGOFDISTANCE = new ItemRingOfDistance();

    public static void registerItems() {
        GameRegistry.registerItem(ITEM_RINGOFDISTANCE, ItemNames.RINGOFDISTANCE);
    }
}

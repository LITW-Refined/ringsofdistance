package de.pilz.ringsofdistance.items;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import de.pilz.ringsofdistance.RingsOfDistanceMod;
import de.pilz.ringsofdistance.enums.ItemRingOfDistanceVariants;
import de.pilz.ringsofdistance.strings.ItemNames;
import de.pilz.ringsofdistance.strings.OtherStrings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemRingOfDistance extends Item implements IBaubleExpanded {

    private IIcon[] myIcons;

    public ItemRingOfDistance() {
        this.setMaxDamage(0);
        this.hasSubtypes = true;
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        var variation = ItemRingOfDistanceVariants.values()[stack.getItemDamage()];
        return OtherStrings.ITEM_PREFIX + ItemNames.RINGOFDISTANCE + "." + variation.getName();
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (ItemRingOfDistanceVariants pt : ItemRingOfDistanceVariants.values()) {
            list.add(new ItemStack(item, 1, pt.getMeta()));
        }
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        myIcons = new IIcon[ItemRingOfDistanceVariants.values().length];

        for (ItemRingOfDistanceVariants variation : ItemRingOfDistanceVariants.values()) {
            myIcons[variation.getMeta()] = reg.registerIcon(OtherStrings.MOD_PREFIX + ItemNames.RINGOFDISTANCE + "_" + variation.getName());
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta >= 0 && meta < ItemRingOfDistanceVariants.values().length) {
            return myIcons[meta];
        }
        return null;
    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase player) {
        return true;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase player) {
        RingsOfDistanceMod.proxy.controller.processEquippedChange(itemStack, player, false);
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase player) {
        RingsOfDistanceMod.proxy.controller.processEquippedChange(itemStack, player, true);
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase player) {
        // Nothing todo here
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[] { BaubleExpandedSlots.ringType };
    }
}

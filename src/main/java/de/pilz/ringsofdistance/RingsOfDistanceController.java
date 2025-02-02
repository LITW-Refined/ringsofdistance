package de.pilz.ringsofdistance;

import java.util.HashMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.ItemInWorldManager;

import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import de.pilz.ringsofdistance.enums.ItemRingOfDistanceVariants;
import de.pilz.ringsofdistance.items.ItemRingOfDistance;

public class RingsOfDistanceController {

    public HashMap<EntityPlayerMP, Double> moddedPlayers = new HashMap<EntityPlayerMP, Double>();

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        processEquippedChange(event.player, false);
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
        processEquippedChange(event.player, true);
    }

    public ItemStack getRingFromBaubles(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer)) {
            return null;
        }

        int[] ringSlotIds = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.ringType);
        for (int slotIndex : ringSlotIds) {
            ItemStack ring = BaublesApi.getBaubles((EntityPlayer) entity)
                .getStackInSlot(slotIndex);
            if (ring != null && ring.getItem() instanceof ItemRingOfDistance) {
                return ring;
            }
        }

        return null;
    }

    public ItemRingOfDistanceVariants getRingVariantFromBaubles(EntityLivingBase entity) {
        ItemStack itemStack = getRingFromBaubles(entity);

        if (itemStack != null) {
            return getRingVariant(itemStack);
        }

        return null;
    }

    public ItemRingOfDistanceVariants getRingVariant(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemRingOfDistance) {
            return ItemRingOfDistanceVariants.values()[itemStack.getItemDamage()];
        }
        return null;
    }

    public void processEquippedChange(EntityLivingBase player, boolean removed) {
        ItemStack itemStack = getRingFromBaubles(player);

        if (itemStack == null) {
            removed = true;
        }

        processEquippedChange(itemStack, player, removed);
    }

    public void processEquippedChange(ItemStack itemStack, EntityLivingBase player, boolean removed) {
        boolean isModded = RingsOfDistanceMod.proxy.controller.moddedPlayers.containsKey(player);

        if ((removed && !isModded) || (!removed && isModded)) {
            return;
        }

        if (player instanceof EntityPlayerMP) {
            ItemInWorldManager itemInWorldManager = ((EntityPlayerMP) player).theItemInWorldManager;
            double distance = itemInWorldManager.getBlockReachDistance();
            double modification;

            if (itemStack == null || removed) {
                modification = RingsOfDistanceMod.proxy.controller.moddedPlayers.remove(player);
                distance -= modification;
            } else {
                modification = getRingVariant(itemStack).getModification();
                double oldModification = RingsOfDistanceMod.proxy.controller.moddedPlayers.getOrDefault(player, 0.0D);
                distance += modification - oldModification;
                RingsOfDistanceMod.proxy.controller.moddedPlayers.put((EntityPlayerMP) player, modification);
            }

            if (modification != 0) {
                itemInWorldManager.setBlockReachDistance(distance);
            }
        }
    }
}

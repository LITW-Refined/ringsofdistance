package de.pilz.ringsofdistance.mixins.early;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import de.pilz.ringsofdistance.RingsOfDistanceMod;
import de.pilz.ringsofdistance.enums.ItemRingOfDistanceVariants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {

    @Shadow
    private Minecraft mc;

    @ModifyReturnValue(method = "getBlockReachDistance", at = @At("RETURN"))
    private float pilz$getBlockReachDistance$bumpDistance(float original) {
        ItemRingOfDistanceVariants ring = RingsOfDistanceMod.proxy.controller.getRingVariantFromBaubles(mc.thePlayer);
        if (ring != null) {
            original += ring.getModification();
        }
        return original;
    }
}

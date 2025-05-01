package net.adam.tutorialmod.effect;

import net.adam.tutorialmod.TutorialMod;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> SLIMEY = registerStatusEffect("slimey",
            new SlimeyEffect(StatusEffectCategory.BENEFICIAL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(TutorialMod.MOD_ID, "slimey"), +0.1f,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> LESSER_SATURATION = registerStatusEffect("lesser_saturation",
            new LesserSaturationEffect(StatusEffectCategory.BENEFICIAL, 0xedc001));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(TutorialMod.MOD_ID, name), statusEffect);
    }


    public static void registerEffects() {
        TutorialMod.LOGGER.info("Registering Mod Effects for " + TutorialMod.MOD_ID);
    }
}

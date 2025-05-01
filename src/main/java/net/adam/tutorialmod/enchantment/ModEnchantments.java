package net.adam.tutorialmod.enchantment;

import net.adam.tutorialmod.TutorialMod;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> LIGHTNING_STRIKER =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TutorialMod.MOD_ID, "lightning_striker"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, LIGHTNING_STRIKER, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                        5,
                        2,
                        Enchantment.leveledCost(5, 7),
                        Enchantment.leveledCost(25, 9),
                        2,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.CROSSBOW_EXCLUSIVE_SET))
                .addEffect(
                        EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,
                        EnchantmentEffectTarget.VICTIM,

                        new LightningStrikerEnchantmentEffect()
                ));
    }



    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}

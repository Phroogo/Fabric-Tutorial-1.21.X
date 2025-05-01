package net.adam.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

    public static final FoodComponent HONEY_BERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.15f).snack().build();

    public static final FoodComponent CANCEROUS_REACTOR = new FoodComponent.Builder().nutrition(-4).saturationModifier(-1.0f).alwaysEdible().
            statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 255), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0), 1.0f)
            .build();

}



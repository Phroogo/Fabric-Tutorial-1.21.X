package net.adam.tutorialmod.entity.client;

import com.google.common.collect.Maps;
import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.entity.custom.MantisEntity;
import net.adam.tutorialmod.entity.custom.MantisVariant;
import net.adam.tutorialmod.entity.custom.PhrogEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PhrogRenderer extends MobEntityRenderer<PhrogEntity, PhrogModel<PhrogEntity>> {
    public PhrogRenderer(EntityRendererFactory.Context context) {
        super(context, new PhrogModel<>(context.getPart(PhrogModel.PHROG)), 0.26f);
    }

    @Override
    public Identifier getTexture(PhrogEntity entity) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/entity/phrog/phrog.png");
    }

    @Override
    public void render(PhrogEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.7f, 0.7f, 0.7f);
        } else {
            matrixStack.scale(1.5f, 1.5f, 1.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
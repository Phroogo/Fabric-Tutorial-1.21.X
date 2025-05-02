package net.adam.tutorialmod.entity.client;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.entity.custom.BlackEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class BlackRenderer extends MobEntityRenderer<BlackEntity, BlackModel<BlackEntity>> {

    public BlackRenderer(EntityRendererFactory.Context context) {
        super(context, new BlackModel<>(context.getPart(BlackModel.BLACK)), 0.5f);
    }

    @Override
    public Identifier getTexture(BlackEntity entity) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/entity/black/black.png");
    }

    @Override
    public void render(BlackEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}

package net.adam.tutorialmod.entity.client;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.entity.custom.PhrogEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CamelEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class PhrogModel<T extends PhrogEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer PHROG = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "phrog"), "main");
    private final ModelPart all;
    private final ModelPart body;
    private final ModelPart legs;
    private final ModelPart eyes;
    private final ModelPart tail;

    public PhrogModel(ModelPart root) {
        this.all = root.getChild("all");
        this.body = this.all.getChild("body");
        this.legs = this.all.getChild("legs");
        this.eyes = this.all.getChild("eyes");
        this.tail = this.all.getChild("tail");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData body = all.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -1.0F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData legs = all.addChild("legs", ModelPartBuilder.create().uv(10, 8).cuboid(3.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 10).cuboid(-4.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 10).cuboid(-4.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData eyes = all.addChild("eyes", ModelPartBuilder.create().uv(0, 10).cuboid(1.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 8).cuboid(-2.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = all.addChild("tail", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, 1.0F, 2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(PhrogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setEyeAngles(headYaw, headPitch);

        this.animateMovement(PhrogAnimations.ANIM_PHROG_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.longJumpAnimationState, PhrogAnimations.ANIM_PHROG_LONG_JUMP, ageInTicks);
        this.updateAnimation(entity.idleAnimationState, PhrogAnimations.ANIM_PHROG_IDLE, ageInTicks, 1f);
    }

    private void setEyeAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -20.0F, 20.0F);
        headPitch = MathHelper.clamp(headPitch, -15.0F, 15.0F);

        this.eyes.yaw = headYaw * 0.017453292F;
        this.eyes.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        all.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return all;
    }
}

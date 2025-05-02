package net.adam.tutorialmod.entity.client;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.entity.custom.BlackEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class BlackModel<T extends BlackEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer BLACK = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "black"), "main");
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart arms;
    private final ModelPart lhand;
    private final ModelPart rhand;
    private final ModelPart legs;
    private final ModelPart lleg;
    private final ModelPart rleg;
    public BlackModel(ModelPart root) {
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.body = this.all.getChild("body");
        this.arms = this.all.getChild("arms");
        this.lhand = this.arms.getChild("lhand");
        this.rhand = this.arms.getChild("rhand");
        this.legs = this.all.getChild("legs");
        this.lleg = this.legs.getChild("lleg");
        this.rleg = this.legs.getChild("rleg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData head = all.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -32.0F, 1.0F));

        ModelPartData body = all.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 1.0F));

        ModelPartData arms = all.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -24.0F, 1.0F));

        ModelPartData lhand = arms.addChild("lhand", ModelPartBuilder.create().uv(16, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 0.0F, 0.0F));

        ModelPartData rhand = arms.addChild("rhand", ModelPartBuilder.create().uv(24, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, 0.0F, 0.0F));

        ModelPartData legs = all.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData lleg = legs.addChild("lleg", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -12.0F, 0.0F));

        ModelPartData rleg = legs.addChild("rleg", ModelPartBuilder.create().uv(32, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -11.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(BlackEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(BlackAnimations.ANIM_BLACK_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, BlackAnimations.ANIM_BLACK_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.punchAnimationState, BlackAnimations.ANIM_BLACK_PUNCH, ageInTicks, 1.0f);

        this.updateAnimation(entity.sittingTransitionAnimationState, BlackAnimations.ANIM_BLACK_SIT, ageInTicks, 1.0f);
        this.updateAnimation(entity.sittingAnimationState, BlackAnimations.ANIM_BLACK_IDLE_SIT, ageInTicks, 1.0f);
        this.updateAnimation(entity.standingTransitionAnimationState, BlackAnimations.ANIM_BLACK_STAND, ageInTicks, 1.0f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, 0F, 0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
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
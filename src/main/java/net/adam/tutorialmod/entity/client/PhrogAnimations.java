package net.adam.tutorialmod.entity.client;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class PhrogAnimations {

    public static final Animation ANIM_PHROG_WALK = Animation.Builder.create(2f).looping()
            .addBoneAnimation("legs",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("tail",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(-2.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation ANIM_PHROG_IDLE = Animation.Builder.create(1f).looping()
            .addBoneAnimation("eyes",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(-2.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation ANIM_PHROG_LONG_JUMP = Animation.Builder.create(1.25f)
            .addBoneAnimation("all",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 3f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 1f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("all",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(-12.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
}

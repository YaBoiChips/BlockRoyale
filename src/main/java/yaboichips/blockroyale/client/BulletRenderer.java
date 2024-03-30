package yaboichips.blockroyale.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import yaboichips.blockroyale.common.entity.AbstractBulletEntity;

public class BulletRenderer extends GeoEntityRenderer<AbstractBulletEntity> {
    public BulletRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BulletModel());
    }

    @Override
    protected void applyRotations(AbstractBulletEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        poseStack.mulPose(Axis.XP.rotationDegrees(animatable.getXRot()));
        poseStack.mulPose(Axis.YP.rotationDegrees(animatable.getYRot()));
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
    }
}

package yaboichips.blockroyale.client;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import yaboichips.blockroyale.common.entity.AbstractBulletEntity;

import static yaboichips.blockroyale.BlockRoyale.MOD_ID;

public class BulletModel extends GeoModel<AbstractBulletEntity> {
    @Override
    public ResourceLocation getModelResource(AbstractBulletEntity animatable) {
        return new ResourceLocation(MOD_ID, "geo/bullet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AbstractBulletEntity animatable) {
        return new ResourceLocation(MOD_ID, "textures/bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AbstractBulletEntity animatable) {
        return new ResourceLocation(MOD_ID, "animations/bullet.animation.json");
    }
}

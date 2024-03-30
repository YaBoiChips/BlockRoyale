package yaboichips.blockroyale.core;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import yaboichips.blockroyale.common.entity.AbstractBulletEntity;
import yaboichips.blockroyale.common.item.AbstractGun;

import static yaboichips.blockroyale.BlockRoyale.MOD_ID;

public class BREntities {

    public static final EntityType<AbstractBulletEntity> ABSTRACT_BULLET = register("abstract_bullet", EntityType.Builder.<AbstractBulletEntity>of(AbstractBulletEntity::new, MobCategory.MISC).sized(0.2F, 0.2F).clientTrackingRange(4).updateInterval(10).build("abstract_bullet"));

    public static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MOD_ID, id), entityType);
    }

    public static void init() {
    }
}

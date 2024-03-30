package yaboichips.blockroyale.common.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import yaboichips.blockroyale.core.BREntities;
import yaboichips.blockroyale.core.BRItems;


public class AbstractBulletEntity extends AbstractArrow implements GeoEntity {
    private AnimatableInstanceCache geoCache = new SingletonAnimatableInstanceCache(this);
    public Player owner;
    public AbstractBulletEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level, new ItemStack(BRItems.GENERIC_BULLET));
    }

    public AbstractBulletEntity(Level world, Player player, ItemStack stack) {
        super(BREntities.ABSTRACT_BULLET, world, stack);
        this.owner = player;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entity != owner) {
            entity.hurt(this.damageSources().generic(), 2f);
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return BRItems.GENERIC_BULLET.getDefaultInstance();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));

    }

    private <E extends AbstractBulletEntity> PlayState predicate(final AnimationState<E> event) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.bullet.spin"));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}

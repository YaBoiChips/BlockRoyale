package yaboichips.blockroyale.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import yaboichips.blockroyale.common.entity.AbstractBulletEntity;

import java.util.concurrent.atomic.AtomicReference;

public class AbstractGun extends Item implements Gun {

    private static final int TRANSITION_STEPS = 7; // Number of steps for smooth transition
    private static final int TRANSITION_INTERVAL = 3; // Interval between each step in milliseconds

    public AbstractGun(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        AtomicReference<Float> originalPitch = new AtomicReference<>(player.getXRot());
        float targetPitch = originalPitch.get() - (recoilAmplifier() - player.getRandom().nextFloat()); // Adjust the pitch angle upwards (camera look)
        level.playSound(null, player.blockPosition(), SoundEvents.IRON_GOLEM_DAMAGE, SoundSource.PLAYERS, 0.5f, 1);
        // Calculate the step size for each transition
        float stepSize = (targetPitch - originalPitch.get()) / TRANSITION_STEPS;

        if (!level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            if (player.getMainHandItem() == stack) {
                AbstractBulletEntity bullet = new AbstractBulletEntity(level, player, stack);
                bullet.setPos(player.getEyePosition());
                bullet.setXRot(player.getXRot());
                bullet.setYRot(player.getYRot());
                bullet.setSoundEvent(SoundEvents.BUBBLE_COLUMN_BUBBLE_POP);
                bullet.setBaseDamage(0.3D);
                bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3F + (float) 3 * 0.5F, 1.0F);
                level.addFreshEntity(bullet);
            }
        }
        // Start the gradual transition
        new Thread(() -> {
            try {
                for (int i = 0; i < TRANSITION_STEPS; i++) {
                    Thread.sleep(TRANSITION_INTERVAL);
                    originalPitch.updateAndGet(v -> v + stepSize);
                    player.setXRot(originalPitch.get());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        return super.use(level, player, hand);
    }

    @Override
    public float gunCooldown() {
        return 0;
    }

    @Override
    public float recoilAmplifier() {
        return 5f;
    }
}

package yaboichips.blockroyale.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import yaboichips.blockroyale.core.BREntities;

public class BlockRoyaleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BREntities.ABSTRACT_BULLET, BulletRenderer::new);
    }
}

package yaboichips.blockroyale;

import net.fabricmc.api.ModInitializer;
import software.bernie.geckolib.GeckoLib;

public class BlockRoyale implements ModInitializer {
    public static final String MOD_ID = "blockroyale";
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
    }
}

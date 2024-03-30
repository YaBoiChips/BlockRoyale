package yaboichips.blockroyale.core;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import yaboichips.blockroyale.common.entity.AbstractBullet;
import yaboichips.blockroyale.common.item.AbstractGun;

import static yaboichips.blockroyale.BlockRoyale.MOD_ID;

public class BRItems {

    public static Item registerItem(String id, Item item){
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, id), item);
    }

    public static final Item TEST_GUN = registerItem("test_gun", new AbstractGun(new Item.Properties()));
    public static final Item GENERIC_BULLET = registerItem("generic_bullet", new Item(new Item.Properties()));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> {
            content.accept(TEST_GUN);
        });
    }
}

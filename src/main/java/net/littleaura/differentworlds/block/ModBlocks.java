package net.littleaura.differentworlds.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.littleaura.differentworlds.DifferentWorlds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HeavyCoreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ENERGY_VESSEL = registerBlock("energy_vessel",
            new HeavyCoreBlock(AbstractBlock.Settings.create()
                    .strength(5f)
                    .requiresTool()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DifferentWorlds.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(DifferentWorlds.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        DifferentWorlds.LOGGER.info("Registering Mod Blocks for " + DifferentWorlds.MOD_ID);

        //Vanilla Groups
        /*
        Natural
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {

        });
        */
    }
}

package net.littleaura.differentworlds.block;

import net.littleaura.differentworlds.DifferentWorlds;
import net.littleaura.differentworlds.block.custom.EnergyVesselBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ENERGY_VESSEL_BLOCK = registerBlock("energy_vessel",
            new EnergyVesselBlock(AbstractBlock.Settings.create()
                    .strength(5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block FRACTURED_ENERGY_VESSEL_BLOCK = registerBlock("fractured_energy_vessel",
            new EnergyVesselBlock(AbstractBlock.Settings.create()
                    .strength(5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)));

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

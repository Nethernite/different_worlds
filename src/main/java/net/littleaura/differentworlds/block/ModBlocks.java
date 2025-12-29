package net.littleaura.differentworlds.block;

import net.littleaura.differentworlds.DifferentWorlds;
import net.littleaura.differentworlds.block.custom.EnergyVesselBlock;
import net.littleaura.differentworlds.component.EnergyStorage;
import net.littleaura.differentworlds.component.ModDataComponentTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ENERGY_VESSEL_BLOCK = registerEnergyVesselBlock("energy_vessel",
            new EnergyVesselBlock(AbstractBlock.Settings.create()
                    .strength(5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)), 1000);

    /*public static final Block FRACTURED_ENERGY_VESSEL_BLOCK = registerEnergyVesselBlock("fractured_energy_vessel",
            new EnergyVesselBlock(AbstractBlock.Settings.create()
                    .strength(5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)), 250);*/

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DifferentWorlds.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
            Registry.register(Registries.ITEM, Identifier.of(DifferentWorlds.MOD_ID, name),
                    new BlockItem(block, new Item.Settings()));
    }

    //EnergyVesselBlock
    private static Block registerEnergyVesselBlock(String name, Block block, int defaultMaxEnergy) {
        registerEnergyVesselBlockItem(name, block, defaultMaxEnergy);
        return Registry.register(Registries.BLOCK, Identifier.of(DifferentWorlds.MOD_ID, name), block);
    }

    private static void registerEnergyVesselBlockItem(String name, Block block, int defaultMaxEnergy) {
        Registry.register(Registries.ITEM, Identifier.of(DifferentWorlds.MOD_ID, name),
                new BlockItem(block, new Item.Settings()
                        .component(ModDataComponentTypes.ENERGY_STORAGE, new EnergyStorage(0, defaultMaxEnergy, ""))
                        .component(DataComponentTypes.MAX_STACK_SIZE, 1)));
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

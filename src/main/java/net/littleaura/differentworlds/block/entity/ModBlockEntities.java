package net.littleaura.differentworlds.block.entity;

import net.littleaura.differentworlds.DifferentWorlds;
import net.littleaura.differentworlds.block.ModBlocks;
import net.littleaura.differentworlds.block.entity.custom.EnergyVesselBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<EnergyVesselBlockEntity> ENERGY_VESSEL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(DifferentWorlds.MOD_ID, "energy_vessel_be"),
                    BlockEntityType.Builder.create(EnergyVesselBlockEntity::new, ModBlocks.ENERGY_VESSEL).build(null));

    public static void registerBlockEntites() {
        DifferentWorlds.LOGGER.info("Registering Block Entities for " + DifferentWorlds.MOD_ID);
    }
}

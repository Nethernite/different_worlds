package net.littleaura.differentworlds.component;

import com.mojang.serialization.Codec;
import net.littleaura.differentworlds.DifferentWorlds;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<EnergyStorage> ENERGY_STORAGE =
            register("energy_storage", builder -> builder
                    .codec(EnergyStorage.CODEC));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(DifferentWorlds.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        DifferentWorlds.LOGGER.info("Registering Mod Data Components for " + DifferentWorlds.MOD_ID);
    }

}

package net.littleaura.differentworlds.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record EnergyStorage(int energy, int max_energy, String energy_type) {

    public static final Codec<EnergyStorage> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.INT.fieldOf("stored_energy").forGetter(EnergyStorage::energy),
                Codec.INT.fieldOf("max_energy").forGetter(EnergyStorage::max_energy),
                Codec.STRING.optionalFieldOf("energy_type","").forGetter(EnergyStorage::energy_type)
        ).apply(builder, EnergyStorage::new);
    });

}

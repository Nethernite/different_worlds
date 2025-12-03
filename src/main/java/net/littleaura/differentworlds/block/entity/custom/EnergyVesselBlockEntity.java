package net.littleaura.differentworlds.block.entity.custom;

import net.littleaura.differentworlds.block.entity.ModBlockEntities;
import net.littleaura.differentworlds.component.ModDataComponentTypes;
import net.littleaura.differentworlds.block.custom.energy.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class EnergyVesselBlockEntity extends BlockEntity {

    private EnergyStorage energyStorage = new EnergyStorage(100, 0, "hello");

    public EnergyVesselBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_VESSEL_BE, pos, state);
    }

    public void modifyEnergy(int energy, String energyType) {
        energyStorage.addEnergy(energy, energyType);

    }

//how do i make these 2 work with a complex component?
    @Override
    protected void readComponents(ComponentsAccess components) {
        super.readComponents(components);
        components.get(ModDataComponentTypes.ENERGY_STORAGE);
        /*energyStorage.setCurrentEnergy(components.get(ModDataComponentTypes.ENERGY_STORAGE).energy());
        energyStorage.setMAX_ENERGY(components.get(ModDataComponentTypes.ENERGY_STORAGE).max_energy());
        energyStorage.setEnergyType(components.get(ModDataComponentTypes.ENERGY_STORAGE).energy_type());*/
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
        //componentMapBuilder.add(ModDataComponentTypes.ENERGY_STORAGE, );
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        energyStorage.setMAX_ENERGY(nbt.getInt("max_energy"));
        energyStorage.setCurrentEnergy(nbt.getInt("current_energy"));
        energyStorage.setEnergyType(nbt.getString("energy_type"));
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("max_energy", energyStorage.getMAX_ENERGY());
        nbt.putInt("current_energy", energyStorage.getCurrentEnergy());
        nbt.putString("energy_type", energyStorage.getEnergyType());
    }

    public EnergyStorage getEnergyStorage() {
        return energyStorage;
    }
}

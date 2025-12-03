package net.littleaura.differentworlds.block.custom.energy;

public class EnergyStorage {

    private int MAX_ENERGY;
    private int currentEnergy;
    private String energyType;

    public EnergyStorage(int MAX_ENERGY, int currentEnergy, String energyType) {
        this.MAX_ENERGY = MAX_ENERGY;
        this.currentEnergy = currentEnergy;
        this.energyType = energyType;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getMAX_ENERGY() {
        return MAX_ENERGY;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void addEnergy(int energy, String energyType) {
        if (energyType.equals(this.energyType) && (currentEnergy + energy < MAX_ENERGY)) {
            if (currentEnergy > energy) {
                currentEnergy += energy;
            } else {
                currentEnergy = 0;
                resetEnergyType();
            }
        }
    }
    public void addMAX_ENERGY(int MAX_ENERGY) {
        this.MAX_ENERGY += MAX_ENERGY;
    }

    public void setMAX_ENERGY(int MAX_ENERGY) {
        this.MAX_ENERGY = MAX_ENERGY;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    private void resetEnergyType() {
        energyType = null;
    }
}

package net.littleaura.differentworlds.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.littleaura.differentworlds.DifferentWorlds;
import net.littleaura.differentworlds.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup ENERGY_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(DifferentWorlds.MOD_ID, "energy_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.ENERGY_VESSEL))
                    .displayName(Text.translatable("itemgroup.differentworlds.energy_items"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModBlocks.ENERGY_VESSEL);

                    }).build());

    public static void registerItemGroups() {
        DifferentWorlds.LOGGER.info("Registering Item Groups for " + DifferentWorlds.MOD_ID);
    }
}
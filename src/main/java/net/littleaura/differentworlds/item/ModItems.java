package net.littleaura.differentworlds.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.littleaura.differentworlds.DifferentWorlds;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DifferentWorlds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DifferentWorlds.LOGGER.info("Registering Mod Items for " + DifferentWorlds.MOD_ID);

        //Vanilla Groups
        /*
        Ingredients
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
        */
    }

}

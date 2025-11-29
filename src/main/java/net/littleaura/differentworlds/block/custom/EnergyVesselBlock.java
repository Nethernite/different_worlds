package net.littleaura.differentworlds.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.HeavyCoreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyVesselBlock extends HeavyCoreBlock {

    public EnergyVesselBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.getInventory().getMainHandStack().isEmpty()) {
            if (!world.isClient) {
                player.getInventory().setStack(player.getInventory().selectedSlot, new ItemStack(RegistryEntry.of(state.getBlock().asItem())));
                world.removeBlock(pos, false);
            }
        }
        return ActionResult.SUCCESS;
    }
}

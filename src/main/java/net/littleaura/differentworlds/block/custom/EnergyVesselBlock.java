package net.littleaura.differentworlds.block.custom;

import com.mojang.serialization.MapCodec;
import net.littleaura.differentworlds.block.entity.custom.EnergyVesselBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergyVesselBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    public static final MapCodec<EnergyVesselBlock> CODEC = EnergyVesselBlock.createCodec(EnergyVesselBlock::new);

    public EnergyVesselBlock(Settings settings) {
        super(settings);
    }

    //Remember to change this as it will not work once blockentity is added
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

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.differentworlds.energy_vessel.tooltip")
                .append(Text.literal(": "
                        /*+ String.valueOf(EnergyVesselBlockEntity.currentEnergy)
                        + "/"
                        + String.valueOf(EnergyVesselBlockEntity.MAX_ENERGY)*/)));
        super.appendTooltip(stack, context, tooltip, options);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnergyVesselBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}

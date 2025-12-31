package net.littleaura.differentworlds.block.custom;

import com.mojang.serialization.MapCodec;
import net.littleaura.differentworlds.block.entity.custom.EnergyVesselBlockEntity;
import net.littleaura.differentworlds.component.EnergyStorage;
import net.littleaura.differentworlds.component.ModDataComponentTypes;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class EnergyVesselBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    public static final MapCodec<EnergyVesselBlock> CODEC = EnergyVesselBlock.createCodec(EnergyVesselBlock::new);

    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    public EnergyVesselBlock(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        if (!world.isClient) {

            final BlockEntity blockEntity = world.getBlockEntity(pos);

            if (player.getInventory().getMainHandStack().isEmpty() && player.isSneaking()) {

                final ItemStack pickStack = getPickStack(world, pos, state);

                if (blockEntity instanceof EnergyVesselBlockEntity energyVesselBlockEntity) {
                    pickStack.applyComponentsFrom(energyVesselBlockEntity.createComponentMap());
                }
                player.getInventory().setStack(player.getInventory().selectedSlot, pickStack);
                world.removeBlock(pos, false);

            } else if (blockEntity instanceof EnergyVesselBlockEntity energyVesselBlockEntity) {

                boolean activated = state.get(ACTIVATED);

                //check for energy if activating
                if (!activated) {
                    if (Objects.requireNonNull(blockEntity.getComponents().get(ModDataComponentTypes.ENERGY_STORAGE)).stored_energy() > 0) {
                        player.sendMessage(Text.literal("activating"));
                        world.setBlockState(pos, state.with(ACTIVATED, true));
                        world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    } else {
                        //placeholder for message
                        player.sendMessage(Text.literal("Not enough stored energy."));
                    }
                } else {
                    player.sendMessage(Text.literal("deactivating"));
                    world.setBlockState(pos, state.with(ACTIVATED, false));
                    world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        if (stack.getComponents().contains(ModDataComponentTypes.ENERGY_STORAGE)) {
            tooltip.add(Text.translatable("tooltip.differentworlds.energy_vessel.tooltip")
                    .append(Text.literal(": "
                            + stack.getOrDefault(ModDataComponentTypes.ENERGY_STORAGE, new EnergyStorage(0,0,"")).stored_energy()
                            + "/"
                            + stack.getOrDefault(ModDataComponentTypes.ENERGY_STORAGE, new EnergyStorage(0,0,"")).max_energy()
                            + " " + stack.getOrDefault(ModDataComponentTypes.ENERGY_STORAGE, new EnergyStorage(0,0,"")).energy_type())));
        } else {
            tooltip.add(Text.literal("§4Missing Component"));
        }
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

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        //super.appendProperties(builder);
        builder.add(ACTIVATED);
    }
}

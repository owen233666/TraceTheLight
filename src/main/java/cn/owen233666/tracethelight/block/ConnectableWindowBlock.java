package cn.owen233666.tracethelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ConnectableWindowBlock extends Block {
    protected static final VoxelShape EE = box(0.0d, 0.0d, 7.0d, 16.0d, 16.0d, 9.0d);
    protected static final VoxelShape NN = box(7.0d, 0.0d, 0.0d, 9.0d, 16.0d, 16.0d);
    protected static final VoxelShape CORNER = Shapes.or(box(7.0d, 0.0d, 0.0d, 9.0d, 16.0d, 7.0d),
            new VoxelShape[]{
                    box(0.0d, 0.0d, 7.0d, 7.0d, 16.0d, 9.0d)
            }
    );

//    public static final EnumProperty<ConnectionStatus> PART = EnumProperty.create("part", ConnectionStatus.class);

    public static final EnumProperty<CornerType> CORNER_TYPE = EnumProperty.create("corner_type", CornerType.class);
    public static final DirectionProperty FACING  =     DirectionProperty.create("facing", new Direction[]{Direction.NORTH, Direction.EAST});
    public static final BooleanProperty ABOVE     =     BooleanProperty.create("above");
    public static final BooleanProperty BELOW     =     BooleanProperty.create("below");
    public static final BooleanProperty NORTH     =     BooleanProperty.create("north");
    public static final BooleanProperty SOUTH     =     BooleanProperty.create("south");
    public static final BooleanProperty EAST      =     BooleanProperty.create("east");
    public static final BooleanProperty WEST      =     BooleanProperty.create("west");
    public static final BooleanProperty IS_CORNER =     BooleanProperty.create("is_corner");

    private static boolean wasInteractedWith;

    protected ConnectableWindowBlock(Properties properties) {
        super(properties);
        wasInteractedWith = false;
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(IS_CORNER, false)
                .setValue(CORNER_TYPE, CornerType.RIGHT)
                .setValue(ABOVE, false)
                .setValue(BELOW, false)
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
        );
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter reader, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? NN : EE;
    }

    protected BlockState WindowState(BlockState state, LevelAccessor level, BlockPos pos) {
        boolean above = level.getBlockState(pos.above()).getBlock() == this;
        boolean below = level.getBlockState(pos.below()).getBlock() == this;
        boolean north = level.getBlockState(pos.north()).getBlock() == this;
        boolean east  = level.getBlockState(pos.east()) .getBlock() == this;
        boolean south = level.getBlockState(pos.south()).getBlock() == this;
        boolean west  = level.getBlockState(pos.west()) .getBlock() == this;
        boolean is_corner = isCorner(north, south, east, west);
        Direction facing = state.getValue(FACING);

        state.setValue(ABOVE, above && level.getBlockState(pos.above()).getValue(FACING).equals(facing));
        state.setValue(BELOW, below && level.getBlockState(pos.below()).getValue(FACING).equals(facing));

        if(is_corner){
            //TODO
            state.setValue(IS_CORNER, true).setValue(CORNER_TYPE, CornerType.RIGHT);
            return state;
        }else {
            if(facing == Direction.NORTH){
                state.setValue(NORTH, false).setValue(SOUTH, false);
                state.setValue(EAST, east && level.getBlockState(pos.east()).getValue(FACING).equals(facing));
                state.setValue(WEST, west && level.getBlockState(pos.west()).getValue(FACING).equals(facing));
            }else {
                state.setValue(EAST, false).setValue(WEST, false);
                state.setValue(NORTH, east && level.getBlockState(pos.east()).getValue(FACING).equals(facing));
                state.setValue(SOUTH, west && level.getBlockState(pos.west()).getValue(FACING).equals(facing));
            }
        }

        return state;
    }

    public void setWasInteractedWith(boolean interacted, Level level, BlockPos pos) {
        wasInteractedWith = interacted;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState statetwo, boolean bool) {
        if (!statetwo.is(state.getBlock())) {
            level.setBlock(pos, this.WindowState(state, level, pos), 2);
            wasInteractedWith = false;
        }

    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction dir, BlockState statetwo, LevelAccessor access, BlockPos pos, BlockPos postwo) {
        return wasInteractedWith ? state : this.WindowState(state, access, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{IS_CORNER, CORNER_TYPE, FACING, ABOVE, BELOW, NORTH, SOUTH, EAST, WEST});
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext contx) {
        Direction facingDirection = contx.getHorizontalDirection();
        LevelAccessor world = contx.getLevel();
        if (facingDirection == Direction.WEST) {
            facingDirection = Direction.EAST;
        } else if (facingDirection == Direction.SOUTH) {
            facingDirection = Direction.NORTH;
        }

        // 这里有问题：先调用WindowState，再设置FACING，但WindowState依赖于FACING
        return this.WindowState(super.getStateForPlacement(contx), world, contx.getClickedPos())
                .setValue(FACING, facingDirection);  // FACING在这里才设置，但WindowState已经在前面用过了
    }

    public enum CornerType implements StringRepresentable{
        RIGHT("right"),
        ROUNDED("rounded");

        private final String name;
        CornerType(String name){this.name = name;}
        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    private boolean isCorner(boolean north, boolean south, boolean east, boolean west){
        if((north ? 1 : 0) + (south ? 1 : 0) + (east ? 1 :0 ) + (west ? 1 : 0) == 2){
            if(north && west) return true;
            if(north && east) return true;
            if(south && west) return true;
            return south && east;
        }else {
            return false;
        }
    }
}

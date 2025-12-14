package cn.owen233666.tracethelight.block;

import cn.owen233666.tracethelight.TracetheLight;
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

    public ConnectableWindowBlock(Properties properties) {
        super(properties);
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
        Direction facing = state.getValue(FACING);
        boolean is_corner = false;
        int cornerAmount = (north ? 1 : 0) + (south ? 1 : 0) + (east ? 1 :0 ) + (west ? 1 : 0);
        if(cornerAmount == 2){
            if(facing.equals(Direction.NORTH)){
                if((north && level.getBlockState(pos.north()).getValue(FACING).equals(Direction.EAST)) && (east && level.getBlockState(pos.east()).getValue(FACING).equals(facing))) is_corner=true;
                if((north && level.getBlockState(pos.north()).getValue(FACING).equals(Direction.EAST)) && (west && level.getBlockState(pos.west()).getValue(FACING).equals(facing))) is_corner=true;
                if((south && level.getBlockState(pos.south()).getValue(FACING).equals(Direction.EAST)) && (east && level.getBlockState(pos.east()).getValue(FACING).equals(facing))) is_corner=true;
                if((south && level.getBlockState(pos.south()).getValue(FACING).equals(Direction.EAST)) && (west && level.getBlockState(pos.west()).getValue(FACING).equals(facing))) is_corner=true;
            }else {
                is_corner = false;
            }
        }
        // 每次setValue都需要重新赋值给state变量
        state = state.setValue(ABOVE, above && level.getBlockState(pos.above()).getValue(FACING).equals(facing));
        state = state.setValue(BELOW, below && level.getBlockState(pos.below()).getValue(FACING).equals(facing));

        if(is_corner){
            state = state.setValue(IS_CORNER, true)
                    .setValue(CORNER_TYPE, CornerType.RIGHT);
            // 清除其他方向属性
            state = state.setValue(NORTH, false)
                    .setValue(SOUTH, false)
                    .setValue(EAST, false)
                    .setValue(WEST, false);
            return state;
        } else {
            state = state.setValue(IS_CORNER, false);

            if(facing == Direction.NORTH){
                state = state.setValue(NORTH, false)
                        .setValue(SOUTH, false);
                state = state.setValue(WEST, east && level.getBlockState(pos.east()).getValue(FACING).equals(facing));
                state = state.setValue(EAST, west && level.getBlockState(pos.west()).getValue(FACING).equals(facing));
            } else {
                state = state.setValue(EAST, false)
                        .setValue(WEST, false);
                // 注意：这里检查的是north和south方向的方块
                state = state.setValue(SOUTH, north && level.getBlockState(pos.north()).getValue(FACING).equals(facing));
                state = state.setValue(NORTH, south && level.getBlockState(pos.south()).getValue(FACING).equals(facing));
            }
        }

        return state;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState statetwo, boolean bool) {
        TracetheLight.LOGGER.info("onPlace() called");
        if (!statetwo.is(state.getBlock())) {
            TracetheLight.LOGGER.info("onPlace().if() called");
            level.setBlock(pos, WindowState(state, level, pos), 2);

            // 更新相邻方块
            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = pos.relative(direction);
                BlockState neighborState = level.getBlockState(neighborPos);
                if (neighborState.getBlock() instanceof ConnectableWindowBlock) {
                    level.setBlock(neighborPos, WindowState(neighborState, level, neighborPos), 3);
                }
            }
        }
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction dir, BlockState statetwo, LevelAccessor access, BlockPos pos, BlockPos postwo) {
        return WindowState(state, access, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{IS_CORNER, CORNER_TYPE, FACING, ABOVE, BELOW, NORTH, SOUTH, EAST, WEST});
    }

//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext contex) {
//        Direction facingDirection = contex.getHorizontalDirection();
//        LevelAccessor world = contex.getLevel();
//        if (facingDirection == Direction.WEST) {
//            facingDirection = Direction.EAST;
//        } else if (facingDirection == Direction.SOUTH) {
//            facingDirection = Direction.NORTH;
//        }
//
//        // 这里有问题：先调用WindowState，再设置FACING，但WindowState依赖于FACING
//        return this.WindowState(super.getStateForPlacement(contex), world, contex.getClickedPos())
//                .setValue(FACING, facingDirection);  // FACING在这里才设置，但WindowState已经在前面用过了
//    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext contx) {
        Direction facingDirection = contx.getHorizontalDirection();
        LevelAccessor world = contx.getLevel();
        BlockPos pos = contx.getClickedPos();

        // 修正朝向：只允许 NORTH 或 EAST
        if (facingDirection == Direction.WEST) {
            facingDirection = Direction.EAST;
        } else if (facingDirection == Direction.SOUTH) {
            facingDirection = Direction.NORTH;
        }

        // 先获取基础状态并设置朝向
        BlockState baseState = super.getStateForPlacement(contx);
        if (baseState == null) {
            baseState = defaultBlockState();
        }

        BlockState stateWithFacing = baseState.setValue(FACING, facingDirection);

        // 然后基于正确的朝向计算连接状态
        return WindowState(stateWithFacing, world, pos);
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

    private boolean isCorner(Direction facing1, Direction facing2, boolean north, boolean south, boolean east, boolean west){
        if(!(facing1 == facing2)){
            return false;
        }
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

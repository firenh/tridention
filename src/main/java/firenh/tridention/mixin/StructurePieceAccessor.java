package firenh.tridention.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.BlockState;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor {
	@Invoker("fillWithOutline")
	public void fillWithOutlineInvoker(StructureWorldAccess world, BlockBox box, int minX, int minY, int minZ, int maxX,
			int maxY, int maxZ, BlockState outline, BlockState inside, boolean cantReplaceAir);

	@Invoker("fill")
	public void fillInvoker(StructureWorldAccess world, BlockBox bounds, int minX, int minY, int minZ, int maxX,
			int maxY, int maxZ);

	@Invoker("offsetPos")
	public BlockPos.Mutable offsetPosInvoker(int x, int y, int z);
}

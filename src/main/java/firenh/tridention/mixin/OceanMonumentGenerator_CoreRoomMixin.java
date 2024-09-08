package firenh.tridention.mixin;

import java.util.logging.Logger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import firenh.tridention.Tridention;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.structure.OceanMonumentGenerator.CoreRoom;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Mixin(CoreRoom.class)
public class OceanMonumentGenerator_CoreRoomMixin {
	@Inject(at = @At("TAIL"), method = "generate")
	private void generate(
			StructureWorldAccess world,
			StructureAccessor structureAccessor,
			ChunkGenerator chunkGenerator,
			Random random,
			BlockBox chunkBox,
			ChunkPos chunkPos,
			BlockPos pivot,
			CallbackInfo info) {
		StructurePieceAccessor room = ((StructurePieceAccessor) (CoreRoom) (Object) this);

		BlockPos blockPos = room.offsetPosInvoker(8, 5, 8);
		TridentEntity trident = (TridentEntity) EntityType.TRIDENT.create(world.toServerWorld());

		if (trident != null && world.getBlockState(blockPos).isOf(Blocks.GOLD_BLOCK)) {
			((TridentEntityAccessor) trident).setGeneratedInMonument(true);
			trident.pickupType = PickupPermission.ALLOWED;
			trident.refreshPositionAndAngles(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0.0F, 0.0F);
			world.spawnEntityAndPassengers(trident);
		}

		room.fillWithOutlineInvoker(world, chunkBox, 7, 4, 7, 8, 5, 8, Blocks.WATER.getDefaultState(),
				Blocks.WATER.getDefaultState(), false);

		Tridention.LOGGER.info("Generating Core Room!");
	}
}
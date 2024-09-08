package firenh.tridention.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.nbt.NbtCompound;

@Mixin(TridentEntity.class)
public class TridentEntityMixin {
    boolean generatedInMonument = false;

    @Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        generatedInMonument = nbt.getBoolean("Tridention_generatedInMonument");
    }

    @Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putBoolean("Tridention_generatedInMonument", generatedInMonument);
    }

    @Inject(at = @At("HEAD"), method = "age", cancellable = true) 
    private void age(CallbackInfo info) {
        if (generatedInMonument) info.cancel();
    }
}

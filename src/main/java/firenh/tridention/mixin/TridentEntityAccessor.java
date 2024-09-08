package firenh.tridention.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.projectile.TridentEntity;

@Mixin(TridentEntity.class)
public interface TridentEntityAccessor {
    @Accessor("generatedInMonument")
    public void setGeneratedInMonument(boolean b);
}

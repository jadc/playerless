package red.jad.playerless;

import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class Playerless {
    @Mixin(LivingEntity.class)
    static class LivingEntityMixin {
        @Inject(at = @At("HEAD"), method = "shouldAlwaysDropXp", cancellable = true)
        void dropXP(CallbackInfoReturnable<Boolean> cir){
            cir.setReturnValue(true);
        }
    }

    @Mixin(KilledByPlayerLootCondition.class)
    static class KilledByPlayerLootConditionMixin {
        @Inject(at = @At("HEAD"), method = "test", cancellable = true)
        void dropPlayerLoot(LootContext lootContext, CallbackInfoReturnable<Boolean> cir){
            cir.setReturnValue(true);
        }
    }
}

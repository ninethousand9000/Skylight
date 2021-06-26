package club.astro.base.mixins.mixins;

import club.astro.Astro;
import club.astro.base.mixins.accessors.IGuiMainMenu;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu implements IGuiMainMenu {
    @Shadow
    private String splashText;

    @Inject(method={"initGui"}, at={@At(value="TAIL")})
    private void initGui(CallbackInfo callbackInfo) {
        setSplashText(Astro.MOD_VERSION);
    }

    @Override
    public void setSplashText(String splashText) {
        this.splashText = splashText;
    }
}

package com.skylight.base.mixins.mixins;

import com.skylight.base.mixins.accessors.IMinecraft;
import com.skylight.base.ui.bettermenu.BetterMainMenuLogin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public final class MixinMinecraft implements IMinecraft {
    @Inject(method={"runTick()V"}, at={@At(value="RETURN")})
    private void runTick(CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu && !BetterMainMenuLogin.loggedIn) {
            Minecraft.getMinecraft().displayGuiScreen(new BetterMainMenuLogin());
        }
    }

    @Inject(method={"displayGuiScreen"}, at={@At(value="HEAD")})
    private void displayGuiScreen(GuiScreen screen, CallbackInfo ci) {
        if (screen instanceof GuiMainMenu && !BetterMainMenuLogin.loggedIn) {
            this.displayGuiScreen(new BetterMainMenuLogin());
        }
    }

    private void displayGuiScreen(BetterMainMenuLogin customMainMenu) {
        customMainMenu.initGui();
    }

    @Shadow
    @Final
    private Timer timer;

    @Override
    public Timer getTimer() {
        return timer;
    }
}

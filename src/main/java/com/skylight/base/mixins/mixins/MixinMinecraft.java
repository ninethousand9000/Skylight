package com.skylight.base.mixins.mixins;

import com.skylight.base.ui.bettermenu.BetterMainMenuLogin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public final class MixinMinecraft {
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
}

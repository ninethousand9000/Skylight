package club.astro.base.mixins.accessors;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiMainMenu.class)
public interface IGuiMainMenu {
    @Accessor("splashText")
    void setSplashText(String splashText);
}

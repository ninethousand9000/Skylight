package club.astro.client.modules.visual;

import club.astro.base.events.events.AspectEvent;
import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.NumberSetting;
import club.astro.base.utils.game.Game;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class AspectRatio extends Module implements Game {
    public static final NumberSetting<Double> aspectRatio = new NumberSetting<>("AspectRatio", 0.0, mc.displayWidth / mc.displayHeight + 0d, 3.0, 1);

    public AspectRatio() {
        registerSettings(aspectRatio);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onAspectEvent(AspectEvent event){
        event.setAspectRatio(aspectRatio.getValue());
    }

}

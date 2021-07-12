package com.skylight.client.modules.visual;

import com.skylight.base.events.events.AspectEvent;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.utils.game.Game;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class AspectRatio extends Module implements Game {
    public static final NumberSetting<Double> aspectRatio = new NumberSetting<>("AspectRatio", 0.0, mc.displayWidth / mc.displayHeight + 0d, 3.0, 1);

    public AspectRatio() {
        registerParents(new ParentSetting("Settings", true, aspectRatio));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onAspectEvent(AspectEvent event){
        event.setAspectRatio(aspectRatio.getValue());
    }

}

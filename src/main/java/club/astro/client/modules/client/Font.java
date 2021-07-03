package club.astro.client.modules.client;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.Setting;
import club.astro.base.utils.render.font.FontMode;

@ModuleAnnotation(category = ModuleCategory.Client, enabledByDefault = true)
public class Font extends Module {
    public static final Setting<FontMode> customFont = new Setting<>("Font", FontMode.VERDANA);

    public Font() {
        registerSettings(customFont);
    }
}

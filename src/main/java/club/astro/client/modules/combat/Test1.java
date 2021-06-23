package club.astro.client.modules.combat;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.Setting;

@ModuleAnnotation(category = ModuleCategory.Combat)
public class Test1 extends Module {
    public static final Setting<String> setting = new Setting<>("Name", "Yes");

    public Test1() {
        registerSettings(setting);
    }
}

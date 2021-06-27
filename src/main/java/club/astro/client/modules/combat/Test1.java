package club.astro.client.modules.combat;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.NumberSetting;
import club.astro.base.settings.Setting;

@ModuleAnnotation(category = ModuleCategory.Combat)
public class Test1 extends Module {
    public static final Setting<String> setting = new Setting<>("Str", "Yes");
    public static final Setting<Boolean> setting1 = new Setting<>("Bol", true);
    public static final Setting<Pop> setting2 = new Setting<>("En", Pop.Other);
    public static final NumberSetting<Integer> setting3 = new NumberSetting<>("int", 0, 10, 100);
    public static final NumberSetting<Float> setting4 = new NumberSetting<>("flot", 0f, 10f, 100f);
    public static final NumberSetting<Double> setting5 = new NumberSetting<>("dov", 0d, 10d, 100d);


    public Test1() {
        registerSettings(setting,setting1, setting2, setting3, setting4, setting5);
    }

    public enum Pop {
        Value,
        Other,
        Test
    }
}

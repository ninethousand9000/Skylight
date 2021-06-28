package club.astro.client.modules.client;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.Setting;

@ModuleAnnotation(category = ModuleCategory.Client, alwaysEnabled = true)
public class Client extends Module {
    public static final Setting<String> name = new Setting<>("Name", "Astro");
    public static final Setting<Boolean> limitValues = new Setting<>("Limit Values", true);

    public Client() {
        registerSettings(
                name,
                limitValues
        );
    }
}

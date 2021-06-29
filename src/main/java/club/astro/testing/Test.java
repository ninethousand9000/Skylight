package club.astro.testing;

import club.astro.Astro;
import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.Setting;
import club.astro.base.ui.clickgui.components.Modules;
import club.astro.base.utils.login.HashingUtil;
import club.astro.base.utils.login.LoginUtil;
import club.astro.base.utils.math.PointUtil;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String name = "TEST";
        System.out.println("\"" + name + "\" is a valid module");
    }

    public static void drawSettings(Setting<?> setting) {
        System.out.println(setting.getValue());
        if (setting.getSubSettings().size() > 0) {
            for (Setting<?> sub : setting.getSubSettings()) {
                drawSettings(sub);
            }
        }
    }
}

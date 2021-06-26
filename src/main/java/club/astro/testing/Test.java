package club.astro.testing;

import club.astro.base.settings.Setting;
import club.astro.base.utils.login.HashingUtil;
import club.astro.base.utils.login.LoginUtil;
import club.astro.base.utils.math.PointUtil;

import java.awt.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        System.out.println(LoginUtil.login("ADMIN2", "PASSWORD"));
        System.out.println(LoginUtil.getNewLogin("TEST", "TEST"));
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

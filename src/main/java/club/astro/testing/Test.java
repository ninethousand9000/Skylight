package club.astro.testing;

import club.astro.base.settings.Setting;
import club.astro.base.utils.math.PointUtil;

import java.awt.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Point> points = PointUtil.regPolyPoints(4,10, new Point(0, 0));
        for (Point point : points) {
            System.out.println("(" + point.x + "," + point.y + ")");
        }
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

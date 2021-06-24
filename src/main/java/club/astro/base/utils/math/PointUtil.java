package club.astro.base.utils.math;

import java.awt.*;
import java.util.ArrayList;

public class PointUtil {
    public static ArrayList<Point> regPolyPoints(int sides, int radius, Point origin) {
        ArrayList<Point> points = new ArrayList<>();

        float angle = 360 / sides;
        int currentAngle = 0;

        for (int i = 0; i < sides; i++) {
            int x = (int) (radius * Math.cos(currentAngle * Math.PI / 180f) + origin.x);
            int y = (int) (radius * Math.sin(currentAngle * Math.PI / 180f) + origin.y);
            points.add(new Point(x, y));
            currentAngle += angle;
        }

        return points;
    }
}

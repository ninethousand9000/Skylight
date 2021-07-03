package com.skylight.base.utils.math;

import java.awt.*;
import java.util.ArrayList;

public class PointUtil {
    public static ArrayList<CoordPoint> regPolyPointsFromOrigin(int sides, int radius, Point origin) {
        ArrayList<CoordPoint> points = new ArrayList<>();

        float angle = 360 / sides;
        float currentAngle = 0;

        for (int i = 0; i < sides; i++) {
            float x = (float) (radius * Math.cos(currentAngle * Math.PI / 180f) + origin.x);
            float y = (float) (radius * Math.sin(currentAngle * Math.PI / 180f) + origin.y);
            points.add(new CoordPoint(x, y));
            currentAngle += angle;
        }

        return points;
    }

    public static class CoordPoint {
        public float x;
        public float y;

        public CoordPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}

package com.ncirl.oop.groupca.thomas.GameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RiverDrawer extends GameObject {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 600;

    private static final Stroke RIVER_STROKE = new BasicStroke(16f);
    private static final Stroke RIVER_OVERLAY_STROKE = new BasicStroke(200f);
    private static ArrayList<Point> riverPoints = new ArrayList<>();

    private static boolean showPlacementOverlay = false;

    public RiverDrawer() {
        super(0, 0, 0);
        generateRiver();
    }

    private static Point generateSourcePoint(Random random, int edgeThreshold) {
        int genX = random.nextInt(edgeThreshold);
        int genY = random.nextInt(edgeThreshold);

        // If they are greater than half of the threshold, move them to the other edge of the screen
        if (genX > (edgeThreshold / 2)) {
            genX += CANVAS_WIDTH / 2;
        }

        if (genY > (edgeThreshold / 2)) {
            genY += CANVAS_HEIGHT / 2;
        }

        return new Point(genX, genY);
    }

    private static void generateRiver() {
        riverPoints.add(new Point(200, 0));
        riverPoints.add(new Point(800, 600));

        // TODO: fancy logic for river generation
//        Random random = new Random();
//
//        // start at random edge of the map
//
//        Point sourcePoint = generateSourcePoint(random, 300);
//        riverPoints.add(sourcePoint);
//
//
//
//        // start going towards middle
//        // away from source
//
//        riverPoints.add(PointUtils.multiply(sourcePoint, -2));
////        riverPoints.add(new Point(random.nextInt(1000), random.nextInt(600)));
////        riverPoints.add(new Point(random.nextInt(1000), random.nextInt(600)));
    }

    @Override
    public void onClicked() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        if (showPlacementOverlay) {
            g2.setColor(new Color(123, 255, 0, 108));
            g2.setStroke(RIVER_OVERLAY_STROKE);

            for (int i = 0; i < riverPoints.size() - 1; i++) {
                Point point = riverPoints.get(i);
                Point nextPoint = riverPoints.get(i + 1);

                g2.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
            }
        }

        g2.setColor(new Color(61, 160, 205));
        g2.setStroke(RIVER_STROKE);

        for (int i = 0; i < riverPoints.size() - 1; i++) {
            Point point = riverPoints.get(i);
            Point nextPoint = riverPoints.get(i + 1);

            g2.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
        }
    }

    @Override
    public void tickLogic() {}

    public static void showPlacementOverlay() {
        RiverDrawer.showPlacementOverlay = true;
    }

    public static void hidePlacementOverlay() {
        RiverDrawer.showPlacementOverlay = false;
    }

    public static boolean isNearRiver(Point point, int range) {
        return false;
    }
}

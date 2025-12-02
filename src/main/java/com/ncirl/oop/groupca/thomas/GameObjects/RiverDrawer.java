package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameValues;

import java.awt.*;
import java.util.ArrayList;

public class RiverDrawer extends GameObject {
    // Can be final as we aren't changing the value directly , just modifying the arraylist
    private static final ArrayList<Point> riverPoints = new ArrayList<>();

    private static final Stroke RIVER_STROKE = new BasicStroke(24f);
    private static final Stroke RIVER_DEEP_STROKE = new BasicStroke(14f);
    private static final Color OVERLAY_COLOR = new Color(123, 255, 0, 108);
    private static final Color RIVER_COLOR = new Color(61, 160, 205);
    private static final Color RIVER_DEEP_COLOR = new Color(8, 96, 128);

    private static Stroke overlayStroke = new BasicStroke(GameValues.getIrrigationDistance());

    private static boolean showPlacementOverlay = false;

    public RiverDrawer() {
        super(0, 0, 0);
        generateRiver();
    }

    public static void updateOverlayStroke() {
        overlayStroke = new BasicStroke(GameValues.getIrrigationDistance());
    }

    private static void generateRiver() {
        riverPoints.add(new Point(200, 0));
        riverPoints.add(new Point(800, 600));

        // maybe river generation
        // riverPoints.add(new Point(random.nextInt(1000), random.nextInt(600)));
        // riverPoints.add(new Point(random.nextInt(1000), random.nextInt(600)));
    }

    @Override
    public void onClicked() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        if (showPlacementOverlay) {
            g2.setColor(OVERLAY_COLOR);
            g2.setStroke(overlayStroke);

            for (int i = 0; i < riverPoints.size() - 1; i++) {
                Point point = riverPoints.get(i);
                Point nextPoint = riverPoints.get(i + 1);

                g2.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
            }
        }

        g2.setColor(RIVER_COLOR);
        g2.setStroke(RIVER_STROKE);

        for (int i = 0; i < riverPoints.size() - 1; i++) {
            Point point = riverPoints.get(i);
            Point nextPoint = riverPoints.get(i + 1);

            g2.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
        }

        g2.setColor(RIVER_DEEP_COLOR);
        g2.setStroke(RIVER_DEEP_STROKE);

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

    public static boolean isNearRiver(Point point) {
        // https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line#Line_defined_by_two_points

        // First, lets jut assume the river only has two points.
        Point p1 = riverPoints.get(0);
        Point p2 = riverPoints.get(1);

        int x1 = p1.x;
        int y1 = p1.y;

        int x2 = p2.x;
        int y2 = p2.y;

        int x0 = point.x;
        int y0 = point.y;

        double distanceToLine =
                Math.abs((y2 - y1) * x0 - (x2 -x1) * y0 + (x2 * y1) - (y2 * x1))
                / Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));

        // Divide by half since the distance is for total, not just one side of the river
        return distanceToLine > 40 && distanceToLine < (GameValues.getIrrigationDistance() * 0.5);
    }
}

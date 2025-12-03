/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Map;

import com.ncirl.oop.groupca.antonio.Items.Items;
import com.ncirl.oop.groupca.antonio.Items.Pickup;
import com.ncirl.oop.groupca.antonio.Items.Delivery;
import com.ncirl.oop.groupca.antonio.Vehicle.Vehicle;
import com.ncirl.oop.groupca.antonio.Vehicle.Air;
import com.ncirl.oop.groupca.antonio.Vehicle.Land;
import com.ncirl.oop.groupca.antonio.Vehicle.Sea;
import com.ncirl.oop.groupca.antonio.AntonioGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author anton
 */
public class Maps {

    private Panel panel;
    private Vehicle vehicle;
    private ArrayList<Pickup> pickups = new ArrayList<>();
    private ArrayList<Delivery> deliveries = new ArrayList<>();
    private ArrayList<Obstacle> obstacle = new ArrayList<>();
    private Random random = new Random();

    private int points = 0;
    private int timeLeft = 120;

    private String last = "";
    private boolean accelerate;
    private boolean decelerate;
    private boolean left;
    private boolean right;

    private Timer loop, countdown;

    private JLabel pointsLabel;
    private JLabel timerLabel;
    private JLabel cargoLabel;

    private int vehicleCargo = 0;

    public Maps(Vehicle vehicle, Items pickupStub, Items deliveryStub) {
        this.vehicle = vehicle;
        generateObstacles();
        generateItems();
    }

    private void generateObstacles() {
        int count = random.nextInt(9) + 12;
        for (int i = 0; i < count; i++) {
            int x;
            int y;
            int w;
            int h;
            int attempts = 0;
            do {
                x = random.nextInt(900);
                y = random.nextInt(500);
                w = random.nextInt(40) + 30;
                h = random.nextInt(40) + 30;
                attempts++;
                if (attempts > 1000) break;
            } while (isColliding());
            obstacle.add(new Obstacle(x, y, w, h));
        }
    }

    private boolean CheckOverlap(int x, int y, int radius) {
        double itemCenterX = x + radius / 2.0;
        double itemCenterY = y + radius / 2.0;
        double itemRadius = radius / 2.0;
        for (Obstacle obstacle : obstacle) {
            double oCenterX = obstacle.getX() + obstacle.getWidth() / 2.0;
            double oCenterY = obstacle.getY() + obstacle.getHeight() / 2.0;
            double oRadius = Math.max(obstacle.getWidth(), obstacle.getHeight()) / 2.0;
            double dist = Math.hypot(itemCenterX - oCenterX, itemCenterY - oCenterY);
            if (dist < (itemRadius + oRadius + 4)) {
                return true;
            }
        }
        return false;
    }

    private void generateItems() {
        pickups.clear();
        deliveries.clear();
        for (int i = 0; i < 3; i++) {
            int px;
            int py;
            int pAmount;
            int radius = random.nextInt(20) + 40;
            do {
                px = random.nextInt(900);
                py = random.nextInt(500);
                pAmount = random.nextInt(16) + 5;
            } while (CheckOverlap(px, py, radius));
            pickups.add(new Pickup(radius, px, py, pAmount));

            int dx;
            int dy;
            int dAmount;
            radius = random.nextInt(20) + 40;
            do {
                dx = random.nextInt(900);
                dy = random.nextInt(500);
                dAmount = random.nextInt(16) + 5;
            } while (CheckOverlap(dx, dy, radius));
            deliveries.add(new Delivery(radius, dx, dy, dAmount));
        }
    }

    public void createWindow() {
        panel = new Panel();
        JFrame frame = new JFrame("Delivery Game");
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pointsLabel = new JLabel("Points: 0");
        timerLabel = new JLabel("Time: 120s");
        cargoLabel = new JLabel("Cargo: 0");
        buttonContainer.add(pointsLabel);
        buttonContainer.add(cargoLabel);
        buttonContainer.add(timerLabel);
        frame.add(buttonContainer, BorderLayout.NORTH);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Return to selection menu?", "Confirm", JOptionPane.YES_NO_OPTION) == 1) {
                    return;
                }
                loop.stop();
                countdown.stop();
                AntonioGUI gui = new AntonioGUI();
                gui.setVisible(true);
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (timeLeft <= 0) return;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        accelerate = true;
                        last = "acc";
                    }
                    case KeyEvent.VK_S -> {
                        decelerate = true;
                        last = "dec";
                    }
                    case KeyEvent.VK_A -> {
                        left = true;
                        last = "l";
                    }
                    case KeyEvent.VK_D -> {
                        right = true;
                        last = "r";
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> accelerate = false;
                    case KeyEvent.VK_S -> decelerate = false;
                    case KeyEvent.VK_A -> left = false;
                    case KeyEvent.VK_D -> right = false;
                }
            }
        });

        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loop = new Timer(33, e -> {
            updateMovement();
            checkPickupDelivery();
            checkCollision();
            panel.repaint();
        });
        loop.start();

        countdown = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + "s");
            pointsLabel.setText("Points: " + points);
            cargoLabel.setText("Cargo: " + vehicleCargo);
            if (timeLeft <= 0) {
                accelerate = decelerate = left = right = false;
                loop.stop();
                countdown.stop();
                JOptionPane.showMessageDialog(panel, "Time's up! Final points: " + points);
                AntonioGUI gui = new AntonioGUI();
                gui.setVisible(true);
                frame.dispose();
            }
        });
        countdown.start();
    }

    private void updateMovement() {
        double acceleration = 1.0;
        double friction = 0.94;

        if (accelerate) vehicle.setyVel(vehicle.getyVel() - acceleration);
        if (decelerate) vehicle.setyVel(vehicle.getyVel() + acceleration);
        if (left) vehicle.setxVel(vehicle.getxVel() - acceleration);
        if (right) vehicle.setxVel(vehicle.getxVel() + acceleration);

        vehicle.setPosX(vehicle.getPosX() + vehicle.getxVel());
        vehicle.setPosY(vehicle.getPosY() + vehicle.getyVel());

        vehicle.setxVel(vehicle.getxVel() * friction);
        vehicle.setyVel(vehicle.getyVel() * friction);
    }

    private boolean isColliding() {
        double vCenterX;
        double vCenterY;

        if (vehicle instanceof Air) {
            vCenterX = vehicle.getPosX();
            vCenterY = vehicle.getPosY();
        } else {
            vCenterX = vehicle.getPosX() + vehicle.getWidth() / 2.5;
            vCenterY = vehicle.getPosY() + vehicle.getHeight() / 2.5;
        }

        double vehicleRadius = Math.max(vehicle.getWidth(), vehicle.getHeight()) / 3;

        for (Obstacle obstacle : obstacle) {
            double oCenterX = obstacle.getX() + obstacle.getWidth() / 2.0;
            double oCenterY = obstacle.getY() + obstacle.getHeight() / 2.0;
            double obstacleRadius = Math.max(obstacle.getWidth(), obstacle.getHeight()) / 2.0;
            double dist = Math.hypot(vCenterX - oCenterX, vCenterY - oCenterY);
            if (dist < (vehicleRadius + obstacleRadius)) {
                return true;
            }
        }
        return false;
    }

    private void checkCollision() {
        if (vehicle.getPosX() > 1000) {
            vehicle.setPosX(990);
            vehicle.setyVel(0);
            vehicle.setxVel(0);
        } else if (vehicle.getPosX() < 0) {
            vehicle.setPosX(10);
            vehicle.setyVel(0);
            vehicle.setxVel(0);
        } else if (vehicle.getPosY() > 600) {
            vehicle.setPosY(590);
            vehicle.setyVel(0);
            vehicle.setxVel(0);
        } else if (vehicle.getPosY() < 0) {
            vehicle.setPosY(10);
            vehicle.setyVel(0);
            vehicle.setxVel(0);
        }
        else if (!isColliding()) {
            return;
        }
        else {
            if (last.equals("acc")) vehicle.setyVel(vehicle.getPosY() * 0.01);
           else if (last.equals("dec")) vehicle.setyVel(vehicle.getPosY() * -0.01);
           else if (last.equals("l")) vehicle.setxVel(vehicle.getPosX() * 0.01);
           else if (last.equals("r")) vehicle.setxVel(vehicle.getPosX() * -0.01);
            if(points>1){
                points -= 1;
            }
            pointsLabel.setText("Points: " + points);
        }
    }

    private int getMaxCargoForVehicle() {
        return (vehicle instanceof Land) ? 20 : Integer.MAX_VALUE;
    }

    private void respawnPickupAt(int index) {
        int radius;
        int px;
        int py;
        int amount;
        int attempts = 0;
        do {
            radius = random.nextInt(20) + 40;
            px = random.nextInt(900 - radius);
            py = random.nextInt(500 - radius);
            amount = random.nextInt(16) + 5;
            attempts++;
            if (attempts > 1000) break;
        } while (CheckOverlap(px, py, radius));
        pickups.set(index, new Pickup(radius, px, py, amount));
    }

    private void respawnDeliveryAt(int index) {
        int radius;
        int dx;
        int dy;
        int amount;
        int attempts = 0;
        do {
            radius = random.nextInt(20) + 40;
            dx = random.nextInt(900 - radius);
            dy = random.nextInt(500 - radius);
            amount = random.nextInt(16) + 5;
            attempts++;
            if (attempts > 1000) break;
        } while (CheckOverlap(dx, dy, radius));
        deliveries.set(index, new Delivery(radius, dx, dy, amount));
    }

    private void checkPickupDelivery() {
        double vCenterX = vehicle.getPosX() + vehicle.getWidth() / 2.0;
        double vCenterY = vehicle.getPosY() + vehicle.getHeight() / 2.0;
        if (vehicle instanceof Air) {
            vCenterX = vehicle.getPosX();
            vCenterY = vehicle.getPosY();
        }

        int maxCargo = getMaxCargoForVehicle();

        for (int i = 0; i < pickups.size(); i++) {
            Pickup p = pickups.get(i);
            double pCenterX = p.getPosX()[0] + p.getRadius() / 2.0;
            double pCenterY = p.getPosY()[0] + p.getRadius() / 2.0;
            double dist = Math.hypot(vCenterX - pCenterX, vCenterY - pCenterY);
            double threshold = (vehicle.getWidth() + p.getRadius()) / 2.0;
            if (dist < threshold) {
                int[] amtArr = p.getAmount();
                if (amtArr != null && amtArr[0] > 0 && vehicleCargo < maxCargo) {
                    amtArr[0] -= 1;
                    vehicleCargo += 1;
                    if (amtArr[0] <= 0) {
                        respawnPickupAt(i);
                    }
                }
            }
        }

        for (int i = 0; i < deliveries.size(); i++) {
            Delivery d = deliveries.get(i);
            double dCenterX = d.getPosX()[0] + d.getRadius() / 2.0;
            double dCenterY = d.getPosY()[0] + d.getRadius() / 2.0;
            double dist = Math.hypot(vCenterX - dCenterX, vCenterY - dCenterY);
            double threshold = (vehicle.getWidth() + d.getRadius()) / 2.0;
            if (dist < threshold) {
                int[] reqArr = d.getRequested();
                if (reqArr != null && reqArr[0] > 0 && vehicleCargo > 0) {
                    reqArr[0] -= 1;
                    vehicleCargo -= 1;
                    points += 1;
                    pointsLabel.setText("Points: " + points);
                    if (reqArr[0] <= 0) {
                        respawnDeliveryAt(i);
                    }
                }
            }
        }
        cargoLabel.setText("Cargo: " + vehicleCargo);
    }

    class Panel extends JPanel {
        public Panel() {
            setPreferredSize(new Dimension(1000, 600));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setBackground(Color.ORANGE);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Obstacle obstacle : obstacle) {
                obstacle.paintObstacle(g);
            }
            for (Pickup pickup : pickups) {
                pickup.paintItem(g);
            }
            for (Delivery delivery : deliveries) {
                delivery.paintItem(g);
            }
            vehicle.paintVehicle(g);
        }
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public boolean isAccelerate() {
        return accelerate;
    }

    public void setAccelerate(boolean accelerate) {
        this.accelerate = accelerate;
    }

    public boolean isDecelerate() {
        return decelerate;
    }

    public void setDecelerate(boolean decelerate) {
        this.decelerate = decelerate;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Timer getLoop() {
        return loop;
    }

    public void setLoop(Timer loop) {
        this.loop = loop;
    }

    public Timer getCountdown() {
        return countdown;
    }

    public void setCountdown(Timer countdown) {
        this.countdown = countdown;
    }

    public JLabel getPointsLabel() {
        return pointsLabel;
    }

    public void setPointsLabel(JLabel pointsLabel) {
        this.pointsLabel = pointsLabel;
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public void setTimerLabel(JLabel timerLabel) {
        this.timerLabel = timerLabel;
    }
}
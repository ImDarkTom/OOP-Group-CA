/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Map;

import com.ncirl.oop.groupca.OOPGroupCAGUI;
import com.ncirl.oop.groupca.antonio.Items.Items;
import com.ncirl.oop.groupca.antonio.Items.Pickup;
import com.ncirl.oop.groupca.antonio.Items.Delivery;
import com.ncirl.oop.groupca.antonio.Vehicle.Sea;
import com.ncirl.oop.groupca.antonio.Vehicle.Vehicle;
import com.ncirl.oop.groupca.antonio.Vehicle.Air;
import com.ncirl.oop.groupca.antonio.Vehicle.Land;
import com.ncirl.oop.groupca.antonio.AntonioGUI;
import com.ncirl.oop.groupca.thomas.shared.ScoreManager;

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
    private final Vehicle vehicle;
    private final ArrayList<Pickup> pickups = new ArrayList<>();
    private final ArrayList<Delivery> deliveries = new ArrayList<>();
    private final ArrayList<Obstacle> obstacle = new ArrayList<>();
    private final Random random = new Random();

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


    public Maps(Vehicle vehicle) {
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

    private void generateItems() {
        pickups.clear();
        deliveries.clear();
        for (int i = 0; i < 3; i++) {
            int[] valuesDelivery = spawnItem();
            deliveries.add(new Delivery(valuesDelivery[0], valuesDelivery[1], valuesDelivery[2], valuesDelivery[3]));
            int[] valuesPickup = spawnItem();
            pickups.add(new Pickup(valuesPickup[0], valuesPickup[1], valuesPickup[2], valuesPickup[3]));
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
                String message = "";
                accelerate = decelerate = left = right = false;
                loop.stop();
                countdown.stop();
                if(points<150){
                    message="you could have made more delivery's with less mistakes";
                    ScoreManager.setAntonioMsg("you could have made more delivery's with less mistakes");
                }
                else if(points<350){
                    message="you made an acceptable amount of delivery's";
                    ScoreManager.setAntonioMsg("you made an acceptable amount of delivery's");
                }
                else if(points>350){
                    message="you made an exceptional amount of delivery's";
                    ScoreManager.setAntonioMsg("you made an exceptional amount of delivery's");
                }
                JOptionPane.showMessageDialog(null, "Well done on finishing the delivery game!\nYou scored "+points+" points!\n"+message, "Final point score", JOptionPane.INFORMATION_MESSAGE);
                ScoreManager.setAntonioScore(points);
                AntonioGUI MyGUI = new AntonioGUI();
                MyGUI.setVisible(true);
                frame.dispose();
            }
        });
        countdown.start();
    }

    private void updateMovement() {
        double acceleration = 1;

        if(vehicle instanceof Air && ((vehicle.getxVel() + vehicle.getyVel())<0.8) && ((vehicle.getxVel() + vehicle.getyVel())>-0.8)){
            acceleration = 0.5;
        }
        double friction = 0.94;

        if (accelerate) vehicle.setyVel(vehicle.getyVel() - acceleration);
        else if (decelerate) vehicle.setyVel(vehicle.getyVel() + acceleration);
        else if (left) vehicle.setxVel(vehicle.getxVel() - acceleration);
        else if (right) vehicle.setxVel(vehicle.getxVel() + acceleration);

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

        double vehicleRadius = (double) Math.max(vehicle.getWidth(), vehicle.getHeight()) / 2;

        return obstacleHitbox(vCenterX, vCenterY, vehicleRadius);
    }
    private boolean obstacleHitbox(double X, double Y, double rad) {
        for (Obstacle obstacle : obstacle) {
            double oX = obstacle.getX() + obstacle.getWidth() / 2.0;
            double oY = obstacle.getY() + obstacle.getHeight() / 2.0;
            double oRad = Math.min(obstacle.getWidth(), obstacle.getHeight()) / 2.0;
            double dist = Math.hypot(X - oX, Y - oY);
            if (dist < (rad + oRad)) {
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
        else if(isColliding() && (vehicle instanceof Sea)){
            switch (last) {
                case "acc":
                    vehicle.setyVel(vehicle.getPosY() * 0.02);
                    break;
                case "dec":
                    vehicle.setyVel(vehicle.getPosY() * -0.02);
                    break;
                case "l":
                    vehicle.setxVel(vehicle.getPosX() * 0.02);
                    break;
                default:
                    vehicle.setxVel(vehicle.getPosX() * -0.02);
                    break;
            }
        }
        else if (isColliding()) {
            switch (last) {
                case "acc":
                    vehicle.setyVel(vehicle.getPosY() * 0.01);
                    break;
                case "dec":
                    vehicle.setyVel(vehicle.getPosY() * -0.01);
                    break;
                case "l":
                    vehicle.setxVel(vehicle.getPosX() * 0.01);
                    break;
                default:
                    vehicle.setxVel(vehicle.getPosX() * -0.01);
                    break;
            }
            if(points>0){
                points -= 1;
            }
            pointsLabel.setText("Points: " + points);
        }

    }

    private int getCapacity() {
        if (vehicle instanceof Land landVehicle) {
            return landVehicle.getCapacity();
        }
        return Integer.MAX_VALUE;
    }

    private void respawnPickupAt(int index) {
        int[] values = spawnItem();
        pickups.set(index, new Pickup(values[0], values[1], values[2], values[3]));
    }

    private int[] spawnItem(){
        int[] values = new int[4];
        int attempts = 0;
        do {
            values[0] = random.nextInt(20) + 40;
            values[1] = random.nextInt(900 - values[0]);
            values[2] = random.nextInt(500 - values[0]);
            values[3] = random.nextInt(16) + 5;
            attempts++;
            if (attempts > 1000) break;
        } while (obstacleHitbox(values[1], values[2], values[0]));
        return values;
    }
    private void respawnDeliveryAt(int index) {
        int[] values = spawnItem();
        deliveries.set(index, new Delivery(values[0], values[1], values[2], values[3]));
    }

    private void checkPickupDelivery() {


        int maxCargo = getCapacity();

        for (int i = 0; i < pickups.size(); i++) {
            Pickup p = pickups.get(i);
            double[] itemAllowance = items(p);
            if (itemAllowance[2] < itemAllowance[3]) {
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
            double[] itemAllowance = items(d);
            if (itemAllowance[2] < itemAllowance[3]) {
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

    private double[] items(Items item) {
        double vCenterX;
        double vCenterY;
        if (vehicle instanceof Air) {
            vCenterX = vehicle.getPosX();
            vCenterY = vehicle.getPosY();
        }
        else{
            vCenterX = vehicle.getPosX() + vehicle.getWidth() / 2.0;
            vCenterY = vehicle.getPosY() + vehicle.getHeight() / 2.0;
        }
        double[] values = new double[4];
        values[0] = item.getPosX()[0] + item.getRadius() / 2.0;
        values[1] = item.getPosY()[0] + item.getRadius() / 2.0;
        values[2] = Math.hypot(vCenterX - values[0], vCenterY - values[1]);
        values[3] = (vehicle.getWidth() + item.getRadius()) / 2.0;

        return values;
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



}
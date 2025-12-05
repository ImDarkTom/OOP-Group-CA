/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Map;

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

    //setting variables
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
        //generating objects on game start
        this.vehicle = vehicle;
        generateObstacles();
        generateItems();
    }

    //start and final obstacle generation
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
                if (attempts > 1000) {
                    break;
                }
            } while (vehicleHitbox(x, y, w, h));
            obstacle.add(new Obstacle(x, y, w, h));
        }
    }

    //start item generation
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

    //creating actual game window
    public void createWindow() {
        panel = new Panel();
        JFrame frame = new JFrame("Delivery Game");
        //adding top bar for values
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
            //on pressing x prompts player to go back to selection menu
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
        //added listener for keyboard inputs W,A,S,D
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (timeLeft <= 0) {
                    return;
                }
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
                    case KeyEvent.VK_W ->
                        accelerate = false;
                    case KeyEvent.VK_S ->
                        decelerate = false;
                    case KeyEvent.VK_A ->
                        left = false;
                    case KeyEvent.VK_D ->
                        right = false;
                }
            }
        });

        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //loop timer works ~30fps to check for collision and move vehicle
        loop = new Timer(33, e -> {
            updateMovement();
            checkPickupDelivery();
            checkCollision();
            panel.repaint();
        });
        loop.start();

        //timer for game duration updating every second
        countdown = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + "s");
            pointsLabel.setText("Points: " + points);
            cargoLabel.setText("Cargo: " + vehicleCargo);
            //stops game when time runs out
            if (timeLeft <= 0) {
                String message = "";
                accelerate = decelerate = left = right = false;
                loop.stop();
                countdown.stop();
                //checking final scored points
                if (points < 150) {
                    message = "you could have made more delivery's with less mistakes";
                    ScoreManager.setAntonioMsg("you could have made more delivery's with less mistakes");
                } else if (points < 350) {
                    message = "you made an acceptable amount of delivery's";
                    ScoreManager.setAntonioMsg("you made an acceptable amount of delivery's");
                } else if (points > 350) {
                    message = "you made an exceptional amount of delivery's";
                    ScoreManager.setAntonioMsg("you made an exceptional amount of delivery's");
                }
                //takes you back to selection menu on game end
                JOptionPane.showMessageDialog(null, "Well done on finishing the delivery game!\nYou scored " + points + " points!\n" + message, "Final point score", JOptionPane.INFORMATION_MESSAGE);
                ScoreManager.setAntonioScore(points);
                AntonioGUI MyGUI = new AntonioGUI();
                MyGUI.setVisible(true);
                frame.dispose();
            }
        });
        countdown.start();
    }

    //movement application
    private void updateMovement() {
        double acceleration = 1;

        //unique movement rules for air vehicles
        if (vehicle instanceof Air && ((vehicle.getxVel() + vehicle.getyVel()) < 0.8) && ((vehicle.getxVel() + vehicle.getyVel()) > -0.8)) {
            acceleration = 0.5;
        }
        double friction = 0.94;

        //checking vehicle direction input and moving coordinates according to velocity and reducing velocity due to friction
        if (accelerate) {
            vehicle.setyVel(vehicle.getyVel() - acceleration);
        } else if (decelerate) {
            vehicle.setyVel(vehicle.getyVel() + acceleration);
        } else if (left) {
            vehicle.setxVel(vehicle.getxVel() - acceleration);
        } else if (right) {
            vehicle.setxVel(vehicle.getxVel() + acceleration);
        }

        vehicle.setPosX(vehicle.getPosX() + vehicle.getxVel());
        vehicle.setPosY(vehicle.getPosY() + vehicle.getyVel());

        vehicle.setxVel(vehicle.getxVel() * friction);
        vehicle.setyVel(vehicle.getyVel() * friction);
    }

    //vehicle to obstacle collision check
    //all collision is checked as if every object is a circle for consistency and ease of use
    private boolean isColliding() {
        double vCenterX = designateVehicle()[0];
        double vCenterY = designateVehicle()[1];

        double vehicleRadius = (double) Math.max(vehicle.getWidth(), vehicle.getHeight()) / 2;

        //calling code that checks if vehicle is actually overlapping hitbox and is colliding
        return obstacleHitbox(vCenterX, vCenterY, vehicleRadius);
    }

    //obstacle hitbox calculation
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

    //vehicle hitbox check used mainly for obstacle generation on game start since you can't check collision without first generating and obstacle otherwise
    private boolean vehicleHitbox(int x, int y, int w, int h) {
        double vCenterX = designateVehicle()[0];
        double vCenterY = designateVehicle()[1];

        double oCenterX = x + w / 2.0;
        double oCenterY = y + h / 2.0;

        double distance = Math.hypot(vCenterX - oCenterX, vCenterY - oCenterY);

        double vehicleRadius = Math.max(vehicle.getWidth(), vehicle.getHeight()) / 2.0;
        double obstacleRadius = Math.min(w, h) / 2.0;

        return distance < (vehicleRadius + obstacleRadius);
    }

    //code that checks for vehicle collision with map edge and calls obstacle collision in order to rebound and lose points or reset velocity
    private void checkCollision() {
        //map edge checks
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
        } //special rule for sea vehicles as if obstacles were waves
        else if (isColliding() && (vehicle instanceof Sea)) {
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
        } //regular collision calculation for other vehicles
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
            //point deduction and update
            if (points > 0) {
                points -= 1;
            }
            pointsLabel.setText("Points: " + points);
        }

    }

    //vehicle pickup point max cargo capacity
    private int getCapacity() {
        //land vehicle special rule application
        if (vehicle instanceof Land landVehicle) {
            return landVehicle.getCapacity();
        }
        return Integer.MAX_VALUE;
    }

    //respawns pickups if pickup point is depleted
    private void respawnPickupAt(int index) {
        //spawn items function plus index for replacing specific depleted pickup
        int[] values = spawnItem();
        pickups.set(index, new Pickup(values[0], values[1], values[2], values[3]));
    }

    //item respawn logic making items variable in size and location and preventing obstacle overlap
    private int[] spawnItem() {
        int[] values = new int[4];
        int attempts = 0;
        do {
            values[0] = random.nextInt(20) + 40;
            values[1] = random.nextInt(900 - values[0]);
            values[2] = random.nextInt(500 - values[0]);
            values[3] = random.nextInt(16) + 5;
            attempts++;
            if (attempts > 1000) {
                break;
            }
        } while (obstacleHitbox(values[1], values[2], values[0]));
        return values;
    }

    //delivery respawn on delivery completed
    private void respawnDeliveryAt(int index) {
        int[] values = spawnItem();
        //spawn items function plus index for replacing specific finished delivery's
        deliveries.set(index, new Delivery(values[0], values[1], values[2], values[3]));
    }

    //checking if player has cargo or can hold more while overlapping item point for variable changes
    private void checkPickupDelivery() {

        //getting cargo cap used for land vehicle special rule
        int maxCargo = getCapacity();

        //pickup
        for (int i = 0; i < pickups.size(); i++) {

            Pickup p = pickups.get(i);
            double[] itemAllowance = items(p);
            //checking distance between item and vehicle
            if (itemAllowance[2] < itemAllowance[3]) {
                int[] amtArr = p.getAmount();
                //checking to see if vehicle can hold cargo and pickup has cargo available
                if (amtArr[0] > 0 && vehicleCargo < maxCargo) {
                    amtArr[0] -= 1;
                    vehicleCargo += 1;
                    //calling respawn to overwrite depleted pickup
                    if (amtArr[0] <= 0) {
                        respawnPickupAt(i);
                    }
                }
            }
        }

        //delivery
        for (int i = 0; i < deliveries.size(); i++) {
            Delivery d = deliveries.get(i);
            double[] itemAllowance = items(d);
            //checking distance between items and vehicle
            if (itemAllowance[2] < itemAllowance[3]) {
                int[] reqArr = d.getRequested();
                //checking to see if vehicle has cargo and delivery is requesting any more cargo
                if (reqArr[0] > 0 && vehicleCargo > 0) {
                    //reducing requested cargo and cargo available while increasing score
                    reqArr[0] -= 1;
                    vehicleCargo -= 1;
                    points += 1;
                    pointsLabel.setText("Points: " + points);
                    //calling respawn to overwrite completed delivery
                    if (reqArr[0] <= 0) {
                        respawnDeliveryAt(i);
                    }
                }
            }
        }
        cargoLabel.setText("Cargo: " + vehicleCargo);
    }

    //assigning vehicle values according to vehicle type
    private double[] designateVehicle() {
        double[] vCenters = new double[2];
        //air drawn using polygon thus different logic
        if (vehicle instanceof Air) {
            vCenters[0] = vehicle.getPosX();
            vCenters[1] = vehicle.getPosY();
        } else {
            vCenters[0] = vehicle.getPosX() + vehicle.getWidth() / 2.0;
            vCenters[1] = vehicle.getPosY() + vehicle.getHeight() / 2.0;
        }
        return vCenters;
    }

    //checks vehicle to item collision/overlap
    private double[] items(Items item) {
        double vCenterX = designateVehicle()[0];
        double vCenterY = designateVehicle()[1];

        double[] values = new double[4];
        values[0] = item.getPosX()[0] + item.getRadius() / 2.0;
        values[1] = item.getPosY()[0] + item.getRadius() / 2.0;
        values[2] = Math.hypot(vCenterX - values[0], vCenterY - values[1]);
        values[3] = (vehicle.getWidth() + item.getRadius()) / 2.0;

        return values;
    }

    //window Panel
    class Panel extends JPanel {

        public Panel() {
            setPreferredSize(new Dimension(1000, 600));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setBackground(Color.ORANGE);
        }

        //paints everything including going through all objects with multiple instances
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

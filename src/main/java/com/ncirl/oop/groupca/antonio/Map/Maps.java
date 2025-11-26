/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Map;

import com.ncirl.oop.groupca.antonio.AntonioGUI;
import com.ncirl.oop.groupca.antonio.Items.Items;
import com.ncirl.oop.groupca.antonio.Vehicle.Vehicle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author anton
 */
public class Maps {

    JButton back;
    private javax.swing.JButton Back;
    private int[] x;
    private int[] y;
    private String map;
    private String obstacle;
    private boolean collision;
    private Vehicle vehicle;
    private Items pickup;
    private Items delivery;

    public Maps(Vehicle vehicle, Items pickup, Items delivery) {
        this.vehicle = vehicle;
        this.pickup = pickup;
        this.delivery = delivery;
    }

    JFrame frame = new JFrame("Map");

    public void createWindow() {

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(new Panel());
        frame.pack();
        frame.setVisible(true);
    }

    public void paintObstacle(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(100, 100, 50, 50);
        g.setColor(Color.black);
        g.drawRect(100, 100, 50, 50);
    }

    // Inner panel for drawing
    class Panel extends JPanel {

        public Panel() {
            setPreferredSize(new Dimension(1000, 600));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setBackground(Color.ORANGE);

            back = new JButton("back");

            back.setBounds(0, 0, 50, 50);

            add(back);
            back.addActionListener(e -> {
                AntonioGUI myGUI = new AntonioGUI();
                myGUI.setVisible(true);
                frame.dispose();
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            vehicle.paintVehicle(g);
            pickup.paintItem(g);
            delivery.paintItem(g);
            paintObstacle(g);
        }
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getObstacle() {
        return obstacle;
    }

    public void setObstacle(String obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}

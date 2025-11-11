package com.ncirl.oop.groupca.thomas.GameObjects;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

class TickHunger extends TimerTask {
    private final Settlement settlement;

    public TickHunger(Settlement settlement) {
        this.settlement = settlement;
    }

    @Override
    public void run() {
        settlement.setHunger(settlement.getHunger() + 1);
    }
}

public class Settlement extends GameObject {
    private float hunger = 0;

    public Settlement(double startX, double startY) {
        super(startX, startY, 50);
    }

    @Override
    public void setup() {
        // use java util timer instead of swing like we do on `GameWindow`
        Timer timer = new Timer();

        // pass in this instance of the settlement as the one to tick hunger for
        TimerTask task = new TickHunger(this);

        timer.schedule(task, 1000, 1000);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x, (int)pos.y, size, size);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("Hunger: " + hunger, (int)pos.x, (int)pos.y);
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }
}

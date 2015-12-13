/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.util.Random;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class StatusScreen extends Screen implements Runnable {

    private Thread thread;
    private boolean isRunning;
    private double netX, netY;
    private int counterDelay;

    public StatusScreen() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_STATUS_HEIGHT));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(StatusScreen.this);
        }
        thread.start();
    }

    @Override
    protected void onDraw(Graphics2D g2D) {
        super.onDraw(g2D);
        drawDisplayTime(g2D);
        drawBatteryStatus(g2D);
        drawNetworkStatus(g2D);
        drawBatteryPercentage(g2D);
        drawNetworkProvider(g2D);
    }

    @Override
    public void run() {
        init();
        while (isRunning) {
            try {
                Thread.sleep(SCREEN_REFRESH);
            } catch (Exception e) {
            }
            updateStatus();
        }
    }

    private void updateStatus() {
        
        if (this.counterDelay < 0) {
            
        this.netX = new Random().nextInt((int) NETDX);
        this.netY = (this.netX * NETDY) / NETDX;
                    

            counterDelay = new Random().nextInt(10);
        }
        
        counterDelay--;
        repaint();
    }

    private void init() {
        isRunning = true;
    }

    private void drawDisplayTime(Graphics2D g2D) {
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, SCREEN_WIDTH, SCREEN_STATUS_HEIGHT);
        g2D.setColor(Color.white);
        g2D.drawString(getDisplayTime(), SCREEN_WIDTH - 50, SCREEN_STATUS_HEIGHT - 10);
    }

    private void drawBatteryStatus(Graphics2D g2D) {
        Polygon backgroundBattery = new Polygon();

        int gap = (int) (SCREEN_STATUS_HEIGHT / 8);

        int spaceFromTxt = SCREEN_WIDTH - 70;
        int batteryWidth = (int) (SCREEN_STATUS_HEIGHT / 1.9);
        int batteryHeight = SCREEN_STATUS_HEIGHT - 5;

        backgroundBattery.addPoint((int) (spaceFromTxt + gap), 5);
        backgroundBattery.addPoint((int) (spaceFromTxt + gap), gap + 5);
        backgroundBattery.addPoint((int) (spaceFromTxt), gap + 5);
        backgroundBattery.addPoint((int) (spaceFromTxt), (int) batteryHeight);
        backgroundBattery.addPoint((int) (spaceFromTxt + batteryWidth), (int) batteryHeight);
        backgroundBattery.addPoint((int) (spaceFromTxt + batteryWidth), gap + 5);
        backgroundBattery.addPoint((int) (spaceFromTxt + batteryWidth - gap), gap + 5);
        backgroundBattery.addPoint((int) (spaceFromTxt + batteryWidth - gap), 5);

        g2D.setColor(Color.WHITE);
        g2D.fill(backgroundBattery);

        Polygon batteryLevel = new Polygon();

        batteryLevel.addPoint((int) (spaceFromTxt), (int) (batteryHeight - 15 + (gap + 5)));
        batteryLevel.addPoint((int) (spaceFromTxt), (int) batteryHeight);
        batteryLevel.addPoint((int) (spaceFromTxt + batteryWidth), (int) batteryHeight);
        batteryLevel.addPoint((int) (spaceFromTxt + batteryWidth), (int) (batteryHeight - 15 + (gap + 5)));

        g2D.setColor(Color.GREEN.brighter());
        g2D.fill(batteryLevel);
    }

    private void drawNetworkStatus(Graphics2D g2D) {

        Polygon backgroundTriangle = new Polygon();
        backgroundTriangle.addPoint(0, (int) NETDY);
        backgroundTriangle.addPoint((int) NETDX, (int) NETDY);
        backgroundTriangle.addPoint((int) NETDX, 0);

        g2D.setColor(Color.LIGHT_GRAY);
        g2D.fill(backgroundTriangle);

        Polygon triangle = new Polygon();
            triangle.addPoint(0, (int) NETDY);
            triangle.addPoint((int) this.netX, (int) NETDY);
            triangle.addPoint((int) this.netX, (int) (NETDY - this.netY));

        
        g2D.setColor(Color.white);
            g2D.fill(triangle);
    }

    private void drawNetworkProvider(Graphics2D g2D) {
        g2D.drawString("EE", 50, 15);
    }

    private void drawBatteryPercentage(Graphics2D g2D) {
        int x = SCREEN_WIDTH - 100;
        g2D.drawString("41%", x, 20);
    }
}

package com.epam.lab.game.model;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.*;
public class RoadBicycle extends Road{
    int way_to_game_over =5000;
    int count, onenew;
    Image imgr = new ImageIcon("res/Roadnewnew.png").getImage();
    Bicycle b = new Bicycle();
    Thread stopControlCreateBicycle = new Thread(this);
    private List<StopBicycle> stopBicycles = new ArrayList<StopBicycle>();
    private class myKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            b.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            b.keyReleased(e);
        }
    }
    public RoadBicycle() {
        addKeyListener(new myKeyAdapter());
        setFocusable(true);
        stopControlCreateBicycle.start();
    }
    @Override
    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(imgr, b.layer1, 0, null);
        g.drawImage(imgr, b.layer2, 0, null);
        Iterator<StopBicycle> i = stopBicycles.iterator();
        StopBicycle e = i.next();
        if (e.x >= 2400 || e.x <= -2400) {
            i.remove();
        } else {
            e.move();
            g.drawImage(e.image, e.x, e.y, null);
        }
        g.drawImage(b.img, b.x, b.y, null);
        g.setColor(Color.WHITE);
        Font font = new Font("Curlz MT",Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Go on the cycle track!Not less 8!", 300, 20);
        g.drawString("Way " + b.s + " km", 100, 40);
        g.drawString("You rode on the  " + count +" cycle tracks.", 100, 60);
        testCollision();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        b.move();
        repaint();
        testLoser();

    }
    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(500));
                stopBicycles.add(new StopBicycle(400, rand.nextInt(300), b.v, this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int testCollision() {
        Iterator<StopBicycle> i = stopBicycles.iterator();
        StopBicycle e = i.next();
        if (b.getRect().intersects(e.getRect())) {
            count++;
            i.remove();
            if (b.s > way_to_game_over ) {
                if(count > 7){
                    b.v = 0;
                    JOptionPane.showMessageDialog(null, "It is a victory!!!");
                    System.exit(1);
                }
            }
        }
        return 0;
    }
    private void testLoser() {
        if (b.s > 5000) {
            if (count < 8) {
                {
                    b.v = 0;
                    JOptionPane.showMessageDialog(null, "You are a loser!");
                    System.exit(2);
                }
            }
        }
    }
}



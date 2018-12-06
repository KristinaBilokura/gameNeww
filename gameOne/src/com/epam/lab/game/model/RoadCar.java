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
public class RoadCar extends Road{
    Image imgr = new ImageIcon("res/Roadnewnew.png").getImage();
    int way_to_game_over= 1000;
    Car car = new Car();
    Thread stopCreate = new Thread(this);
    private List<Stop> stops = new ArrayList<Stop>();
    private List<Stopstop> stopsstops = new ArrayList<Stopstop>();
    private class myKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            car.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            car.keyReleased(e);
        }
    }
    public RoadCar() {
        mainTimer.start();
        addKeyListener(new myKeyAdapter());
        setFocusable(true);
        stopCreate.start();
    }
    @Override
    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(imgr, car.layer1, 0, null);
        g.drawImage(imgr, car.layer2, 0, null);
        Iterator<Stop> i = stops.iterator();
        Stop e = i.next();
        if (e.x >= 2400 || e.x <= -2400) {
            i.remove();
        } else {
            e.move();
            g.drawImage(e.img, e.x, e.y, null);
        }
        Iterator<Stopstop> ii = stopsstops.iterator();
        Stopstop ee = ii.next();
        if (ee.x >= 2400 || ee.x <= -2400) {
            ii.remove();
        } else {
            ee.move();
            g.drawImage(ee.img1, ee.x, ee.y, null);
        }
        g.drawImage(car.img, car.x, car.y, null);
        double v= (200/car.MAX_V)+ car.v;
        g.setColor(Color.WHITE);
        Font font = new Font("Curlz MT",Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Look at the road signs ", 300, 20);
        g.drawString("Average speed " + car.v + " mph", 30, 40);
        g.drawString("Way " + car.s + " km", 30, 60);
        testCollision();
        if ((testCollision() == 1)) {
            i.remove();
            car.v = 0;
            JOptionPane.showMessageDialog(null, "Be a good loser!!Try again");
            System.exit(1);
        }
        testCollision1();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        car.move();
        repaint();
        testWin();
    }
    private void testWin() {
        if(car.s > way_to_game_over){
            car.v=0;
            JOptionPane.showMessageDialog(null, "You won!!!");
            System.exit(1);
        }
    }
    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(3000));
                stops.add(new Stop(900, 0, car.v, this));
                stopsstops.add(new Stopstop(900, 0, car.v, this));
            } catch (InterruptedException e) {
            }
        }
    }
    @Override
    public int testCollision() {
        Iterator<Stop> i = stops.iterator();
        Stop e = i.next();
        if (car.getRect().intersects(e.getRect())) {
            if (car.v > 30)
                return 1;
        }
        return 0;
    }
    private void testCollision1() {
        Iterator<Stop> ii = stops.iterator();
        Stop ee = ii.next();
        if (car.getRect().intersects(ee.getRect())) {
            if (car.v < 30) {
                ii.remove();
                car.v = 0;
                JOptionPane.showMessageDialog(null, "You are loser!!!!!");
                System.exit(1);
            }
        }
    }
}



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
public class RoadTrafficControler extends Road {
    int way_to_game_over=15000;
    int count=0;
    Image imgr = new ImageIcon("res/Roadnewnew.png").getImage();
    Thread stopControlCreate = new Thread(this);
    private List<StopControl> stopControls = new ArrayList<StopControl>();
    TrafficController trafficController =new TrafficController();
    private  class myKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            trafficController.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            trafficController.keyReleased(e);
        }
    }
    public RoadTrafficControler(){
        addKeyListener(new myKeyAdapter());
        setFocusable(true);
        stopControlCreate.start();
    }
    @Override
    public void paint (Graphics g){
        g = (Graphics2D) g;
        g.drawImage(imgr, trafficController.layer1,0,null);
        g.drawImage(imgr, trafficController. layer2,0,null);
        Iterator<StopControl> i = stopControls.iterator();
        StopControl e = i.next();
        if (e.x >= 2400 || e.x <= -2400) {
            i.remove();
        } else {
            e.move();
            g.drawImage(e.img, e.x, e.y, null);
        }
        g.drawImage(trafficController.img, trafficController.x, trafficController.y, null);
        g.setColor(Color.WHITE);
        Font font = new Font("Curlz MT",Font.ITALIC, 24);
        g.setFont(font);
        g.drawString("Stop the cars to give not less than 7 fines", 200, 35);
        g.drawString("You gave " + count + " fines", 300, 55);
        testCollision();
    }
    @Override
    public void actionPerformed (ActionEvent e){
        trafficController.move();
        repaint();
        testLoser();
    }
    private void testLoser() {
        if(trafficController.s >= way_to_game_over ){
            if(count <7) {
                JOptionPane.showMessageDialog(null, "Be a good loser!!Try again");
                System.exit(1);
            }
        }
    }
    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(500));
                stopControls.add(new StopControl(900, rand.nextInt(300), trafficController.v, this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int testCollision() {
        Iterator<StopControl> i = stopControls.iterator();
        StopControl e = i.next();
        if (trafficController.getRect().intersects(e.getRect())) {
                count++;
                i.remove();
                if(count> 6) {
                    trafficController.v=0;
                    JOptionPane.showMessageDialog(null, "It's a victory.");
                    System.exit(1);
                }
        }
        return 0;
    }
}


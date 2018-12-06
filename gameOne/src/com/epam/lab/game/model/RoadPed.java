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
public class RoadPed extends Road{
    int way_to_game_over=10000;
    Image imgr = new ImageIcon("res/Roadnewnew.png").getImage();
    Pedestrian ped =new Pedestrian();
    Thread stopPedCreate = new Thread(this);
    private List<StopPedestrian> stopPeds = new ArrayList<StopPedestrian>();
    private  class myKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            ped.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            ped.keyReleased(e);
        }
    }
    public RoadPed(){
        mainTimer.start();
        addKeyListener(new myKeyAdapter());
        setFocusable(true);
        stopPedCreate.start();
    }
    @Override
    public void paint (Graphics g){
        g.drawImage(imgr, ped.layer1,0,null);
        g.drawImage(imgr, ped. layer2,0,null);
        Iterator<StopPedestrian> i = stopPeds.iterator();
        StopPedestrian e = i.next();
        if (e.x >= 1200 || e.x <= -1200) {
            i.remove();
        } else {
            e.move();
            g.drawImage(e.img, e.x, e.y, null);
        }
        g.drawImage(ped.img, ped.x, ped.y,null);
        g.setColor(Color.WHITE);
        Font font = new Font("Curlz MT",Font.ITALIC, 24);
        g.setFont(font);
        g.drawString("Way " + ped.s + "m * Run away from the cars", 200, 35);
        testCollision();
    }
    @Override
    public void actionPerformed (ActionEvent e){
        ped.move();
        repaint();
        testwin();
    }
    private void testwin() {
        if(ped.s > way_to_game_over) {
            JOptionPane.showMessageDialog(null, "You won!!!");
            System.exit(1);
        }
    }
    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(500));
                stopPeds.add(new StopPedestrian(900, rand.nextInt(300), ped.v, this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int testCollision() {
        Iterator<StopPedestrian> i = stopPeds.iterator();
        StopPedestrian e = i.next();
        if (ped.getRect().intersects(e.getRect())){
            i.remove();
            ped.v = 0;
            JOptionPane.showMessageDialog(null, "Be a good loser!!Try again");
            System.exit(1);
        }
        return 0;
    }
}



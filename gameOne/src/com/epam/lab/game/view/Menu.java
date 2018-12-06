package com.epam.lab.game.view;
import com.epam.lab.game.model.RoadBicycle;
import com.epam.lab.game.model.RoadCar;
import com.epam.lab.game.model.RoadPed;
import com.epam.lab.game.model.RoadTrafficControler;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.*;
public class Menu extends JPanel implements ActionListener {
    Image img = new ImageIcon("res/Menu1.png").getImage();
    public Menu(){

        addKeyListener(new Menu.myKeyAdapter());
        setFocusable(true);
    }
    private class myKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            JFrame f = new JFrame("Drive carefully:");
            f.setSize(800, 437);
            if(key==KeyEvent.VK_RIGHT){
                f.add(new RoadTrafficControler());
            }
            if(key==KeyEvent.VK_LEFT){
                f.add(new RoadPed());
            }
            if(key==KeyEvent.VK_DOWN){
                f.add(new RoadBicycle());
            }
            if(key==KeyEvent.VK_UP){
                f.add(new RoadCar());
            }
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }
    public void paint (Graphics g){
        g.drawImage(img, 0,0,null);
    }
    public void actionPerformed (ActionEvent e){
    }
}


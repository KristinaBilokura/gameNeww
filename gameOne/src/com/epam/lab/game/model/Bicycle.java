package com.epam.lab.game.model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class Bicycle extends Player {
    Image img = new ImageIcon("res/Bicycle1.png").getImage();
    @Override
    public void move() {
            s+=v;
            v+=dv;
            if(v<=0){
                v=0;
            }
            if(y<=MAX_TOP){
                y=MAX_TOP;
            }
            if(y>=MAX_BOTTOM){
                y=MAX_BOTTOM;
            }
            y-=dy;
            if(layer2 - v <= 0){
                layer1 = 0;
                layer2 = 400;
            }else {
                layer1 -= v;
                layer2 -= v;
            }
    }
}

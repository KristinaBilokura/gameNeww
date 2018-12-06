package com.epam.lab.game.model;

import javax.swing.*;
import java.awt.*;

public class Pedestrian extends Player {
    Image img = new ImageIcon("res/Pedestrian.png").getImage();
    @Override
    public void move(){
        s+=v;
        v+=dv;
        if(v<=0){
            v=0;
        }
        if(v>=MAX_V){
            v=3;
        }
        if(y<=MAX_TOP_FOR_PEDESRIAN){
            y=MAX_TOP_FOR_PEDESRIAN;
        }
        if(y>=MAX_BOTTOM_FOR_PEDESTRIAN){
            y=MAX_BOTTOM_FOR_PEDESTRIAN;
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

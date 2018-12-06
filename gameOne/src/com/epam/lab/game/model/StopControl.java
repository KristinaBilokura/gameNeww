package com.epam.lab.game.model;
import com.epam.lab.game.consts.Consts;
import javax.swing.*;
import java.awt.*;
public class StopControl extends Consts{
    int x,y,v;
    Image img = new ImageIcon("res/carSmall1.png").getImage();
    RoadTrafficControler roadTrafficControler;
    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH_STOPCONTROL, HEIGHT_STOPCONTROL);
    }
    public StopControl(int x, int y , int v, RoadTrafficControler roadTrafficControler){
        this.x = x;
        this.y = y;
        this.v = v;
        this.roadTrafficControler = roadTrafficControler;
    }
    public void move(){
        x = x - roadTrafficControler.trafficController.v +v;
    }
}

package com.epam.lab.game.model;
import com.epam.lab.game.consts.Consts;
import javax.swing.*;
import java.awt.*;
public class StopPedestrian extends Consts {
    int x,y,v;
    Image img = new ImageIcon("res/carPed.png").getImage();
    RoadPed roadPed;
    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH_STOPCONTROL,HEIGHT_STOPPEDESTRIAN);
    }
    public StopPedestrian(int x, int y , int v, RoadPed roadPed){
        this.x = x;
        this.y = y;
        this.v = v;
        this.roadPed = roadPed;
    }
    public void move(){
        x = x - roadPed.ped.v +v;
    }
}

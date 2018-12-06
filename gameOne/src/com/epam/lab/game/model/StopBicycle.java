package com.epam.lab.game.model;
import com.epam.lab.game.consts.Consts;
import javax.swing.*;
import java.awt.*;
public class StopBicycle extends Consts {
    int x,y,v;
    Image image = new ImageIcon("res/bicycleRoad.gif").getImage();
    RoadBicycle roadBicycle;
    public Rectangle getRect(){
        return new Rectangle(x, y,WIDTH_BICYCLE, HEIGHT_BICYCLE);
    }
    public StopBicycle(int x, int y ,int v,RoadBicycle roadBicycle){
        this.x = x;
        this.y = y;
        this.v = v;
        this.roadBicycle = roadBicycle;
    }
    public void move(){
        x = x - roadBicycle.b.v +v;
    }
}

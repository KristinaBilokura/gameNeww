package com.epam.lab.game.model;
import com.epam.lab.game.consts.Consts;
import java.awt.*;
import javax.swing.ImageIcon;
public class Stop extends Consts{
    int x,y,v;
    Image img = new ImageIcon("res/Stop.png").getImage();
    Image img1 = new ImageIcon("res/Stop50.png").getImage();
    RoadCar roadCar;
    public Rectangle getRect(){
        return new Rectangle(x, y,WIDTH_STOP, HEIGHT_STOP);
    }
    public Stop(int x, int y ,int v,RoadCar roadCar){
        this.x = x;
        this.y = y;
        this.v = v;
        this.roadCar = roadCar;
    }
    public void move(){
        x = x - roadCar.car.v +v;
    }
}

package com.epam.lab.game.model;
import com.epam.lab.game.consts.Consts;
import java.awt.*;
import java.awt.event.KeyEvent;
public class Player extends Consts {
    int v=0;
    int dv=0;
    public int s =0;
    int x=10, y=150 ,dy =0;
    int layer1 = 0;
    int layer2 = 400;
    public Rectangle getRect(){
        return new Rectangle(x,y,170, 69);
    }
    public void move(){
        s+=v;
        v+=dv;
        if(v<=0){
            v=0;
        }
        if(v>=MAX_V){
            v=MAX_V;
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
    public void keyPressed(KeyEvent e){
        int key =e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT){
            dv = 1;
        }
        if(key==KeyEvent.VK_LEFT){
            dv = -1;
        }
        if(key==KeyEvent.VK_UP){
            dy = 30;
        }
        if(key==KeyEvent.VK_DOWN){
            dy = -30;
        }
    }
    public void keyReleased(KeyEvent e){
        int key =e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_LEFT){
            dv = 0;
        }
        if(key==KeyEvent.VK_UP || key==KeyEvent.VK_DOWN){
            dy = 0;
        }
    }
}

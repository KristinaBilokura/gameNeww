package com.epam.lab.game.model;
        import java.awt.Graphics;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import javax.swing.JPanel;
        import javax.swing.Timer;
abstract class Road extends JPanel implements ActionListener ,Runnable{
    Timer mainTimer = new Timer(20,this);
    public Road()
    {
        mainTimer.start();
    }
    public void paint (Graphics g){
    }
    public void actionPerformed (ActionEvent e){
    }
    public int testCollision(){
        return 0;
    }


}





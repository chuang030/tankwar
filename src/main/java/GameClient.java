import object.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent{
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    private boolean stop;
    private boolean[] dirs = new boolean[4];

    GameClient(){
        this(800,600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenWidth = screenHeight;

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        init();
        
        new Thread(new Runnable(){
            public void run(){
                while(!stop){
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        
    }

    public void init(){
        playerTank=new Tank(100,100, Direction.UP);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(playerTank.getImage(),
                playerTank.getX(),playerTank.getY(),null);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                //playerTank.setY(playerTank.getY()-5);
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                //playerTank.setY(playerTank.getY()+5);
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                //playerTank.setX(playerTank.getX()-5);
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                //playerTank.setX(playerTank.getX()+5);
                break;
        }
        playerTank.move();
        //repaint();

    }
}

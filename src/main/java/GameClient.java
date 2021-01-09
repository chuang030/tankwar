import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameClient extends JComponent{
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    //private ArrayList<Tank> enemyTanks = new ArrayList<>();
    //private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Image backGround;
    private boolean stop;


    GameClient(){
        this(800,600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

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

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void init(){
        backGround=Tools.getImage("sand.jpg");
        Image[] brickImage={Tools.getImage("brick.png")};

        Image[] iTankImage=new Image[8];
        Image[] eTankImage=new Image[8];

        String[] sub = {"U","D","L","R","LU","LD","RU","RD"};

        for (int i=0;i<iTankImage.length;i++){
            iTankImage[i] =Tools.getImage("itank"+sub[i]+".png");
            eTankImage[i] =Tools.getImage("etank"+sub[i]+".png");
        }

        playerTank=new Tank(360,100, Direction.UP,iTankImage);
        gameObjects.add(playerTank);

        int space = 110;
        for(int i=0; i<3 ; i++){
            for(int j=0; j<4; j++){
                gameObjects.add(new Tank(240+j*space,280+i*space, Direction.UP,true,eTankImage));
            }
        }
        //objects.addAll(enemyTanks);

        gameObjects.add(new Wall(160,200,true,15,brickImage));
        gameObjects.add(new Wall(70,200,false,16,brickImage));
        gameObjects.add(new Wall(700,200,false,16,brickImage));
        //objects.addAll(walls);

    }

    @Override
    public void paintComponent(Graphics g){
        //super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0,screenWidth,screenHeight);
        g.drawImage(backGround,0,0,null);

        for(GameObject object:gameObjects){
            object.draw(g);
        }

//        playerTank.draw(g);
//        for(Tank tank:enemyTanks){
//            tank.draw(g);
//        }
//
//        for(Wall wall:walls){
//            wall.draw(g);
//        }

    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0]=true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=true;
                break;
        }
        //playerTank.move();
        //repaint();

    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0]=false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=false;
                break;
        }
    }
}

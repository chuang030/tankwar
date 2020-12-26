package object;

import javax.swing.*;
import java.awt.*;

public class Tank {
    //常數
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;
    private int x;
    private int y;
    private int speed;
    private  Direction direction;
    private boolean enemy;
    //0:上 1:下 2:左 3:右
    private boolean[] dirs = new boolean[4];

    public boolean[] getDirs() {
        return dirs;
    }


    public Tank(int x, int y,  Direction direction) {
        this(x,y,direction,false);
    }

    public Tank(int x, int y,  Direction direction, boolean enemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy=enemy;
        speed = 5;
    }

    public void detectDirection(){
        if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]){
            direction = Direction.UP;
        }else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]){
            direction = Direction.DOWN;
        }else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]){
            direction = Direction.LEFT;
        }else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]){
            direction = Direction.RIGHT;
        }else if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]){
            direction = Direction.LEFT_UP;
        }else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]){
            direction = Direction.LEFT_DOWN;
        }else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]){
            direction = Direction.RIGHT_UP;
        }else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]){
            direction = Direction.RIGHT_DOWN;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImage() {
        String name = enemy ? "etank" : "itank";
        if (direction == Direction.UP){
            return new ImageIcon("assets/images/"+name+"U.png").getImage();
        }
        if (direction == Direction.DOWN){
            return new ImageIcon("assets/images/"+name+"D.png").getImage();
        }
        if (direction == Direction.LEFT){
            return new ImageIcon("assets/images/"+name+"L.png").getImage();
        }
        if (direction == Direction.RIGHT){
            return new ImageIcon("assets/images/"+name+"R.png").getImage();
        }
        if (direction == Direction.LEFT_UP){
            return new ImageIcon("assets/images/"+name+"LU.png").getImage();
        }
        if (direction == Direction.LEFT_DOWN){
            return new ImageIcon("assets/images/"+name+"LD.png").getImage();
        }
        if (direction == Direction.RIGHT_UP){
            return new ImageIcon("assets/images/"+name+"RU.png").getImage();
        }
        if (direction == Direction.RIGHT_DOWN){
            return new ImageIcon("assets/images/"+name+"RD.png").getImage();
        }
        return null;
    }
    public void move(){
        switch(direction){
            case UP:
                y-=speed;
                break;
            case DOWN:
                y+=speed;
                break;
            case LEFT:
                x-=speed;
                break;
            case RIGHT:
                x+=speed;
                break;
            case LEFT_UP:
                y-=speed;
                x-=speed;
                break;
            case LEFT_DOWN:
                y+=speed;
                x-=speed;
                break;
            case RIGHT_UP:
                y-=speed;
                x+=speed;
                break;
            case RIGHT_DOWN:
                y+=speed;
                x+=speed;
                break;
        }
    }
    public void draw(Graphics g){
        if(isRunning()){
            detectDirection();
            move();
        }
        g.drawImage(getImage(),x,y,null);
    }

    public boolean isRunning(){
        for (int i = 0; i<dirs.length; i++){
            if (dirs[i]){
                return true;
            }
        }
        return false;
    }

}

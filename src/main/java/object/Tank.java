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


    public Tank(int x, int y,  Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 5;
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
        if (direction == Direction.UP){
            return new ImageIcon("assets/images/itankU.png").getImage();
        }
        if (direction == Direction.DOWN){
            return new ImageIcon("assets/images/itankD.png").getImage();
        }
        if (direction == Direction.LEFT){
            return new ImageIcon("assets/images/itankL.png").getImage();
        }
        if (direction == Direction.RIGHT){
            return new ImageIcon("assets/images/itankR.png").getImage();
        }
        if (direction == Direction.LEFT_UP){
            return new ImageIcon("assets/images/itankLP.png").getImage();
        }
        if (direction == Direction.LEFT_DOWN){
            return new ImageIcon("assets/images/itankLD.png").getImage();
        }
        if (direction == Direction.RIGHT_UP){
            return new ImageIcon("assets/images/itankRP.png").getImage();
        }
        if (direction == Direction.RIGHT_DOWN){
            return new ImageIcon("assets/images/itankRD.png").getImage();
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

}

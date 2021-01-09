import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {
    //常數
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;

    private int speed;
    protected Direction direction;
    protected boolean enemy;
    //0:上 1:下 2:左 3:右
    private boolean[] dirs = new boolean[4];

    public boolean[] getDirs() {
        return dirs;
    }


    public Tank(int x, int y,  Direction direction,Image[] image) {
        this(x,y,direction,false,image);
    }

    public Tank(int x, int y,  Direction direction, boolean enemy,Image[] image) {
        super(x, y,image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy=enemy;
        speed = 15;

        width=(int)(width*0.8);
        height=(int)(height*0.8);
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

    public void fire(){
        Bullet bullet = new Bullet(x+(width-GameClient.bulletImage[0].getWidth(null))/2,
                y+(height-GameClient.bulletImage[0].getHeight(null))/2,direction,enemy,GameClient.bulletImage);
        TankGame.getGameClient().addGameObjects(bullet);
    }

    public void move(){
        oldX = x;
        oldY = y;

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

    public boolean isCollisionBound(){
        boolean isCollision = false;

        if(x<0){
            x=0;
            isCollision=true;
        }else if(x>TankGame.getGameClient().getScreenWidth()-width){
            x=TankGame.getGameClient().getScreenWidth()-width;
        }

        if(y<0){
            y=0;
            isCollision=true;
        }else if(y>TankGame.getGameClient().getScreenHeight()-width){
            y=TankGame.getGameClient().getScreenHeight()-height;
        }

        return isCollision;
    }

    public boolean isCollisionObject(){
        boolean isCollision = false;

        for(GameObject gameObject:TankGame.getGameClient().getGameObjects()){
            if(gameObject instanceof Bullet){
                continue;
            }
            if(gameObject != this && getRectangle().intersects(gameObject.getRectangle())){
                //System.out.println("hit");
                x=oldX;
                y=oldY;
                isCollision=true;
                break;
            }
        }

        return isCollision;
    }

    public boolean collision(){
        boolean isCollision = isCollisionBound();

        if(!isCollision){
            isCollision = isCollisionObject();
        }

        return isCollision;
    }

    public void draw(Graphics g){

        if(!alive){
            return;
        }

        if(isRunning()){
            detectDirection();
            move();
            collision();
        }
        g.drawImage(image[direction.ordinal()],x,y,null);
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

import object.GameObject;

import java.awt.*;

public class Bullet extends Tank{

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 30;
    }


    @Override
    public void draw(Graphics g) {

        if(!alive){
            return;
        }

        move();
        collision();
        g.drawImage(image[direction.ordinal()],x,y,null);
    }

    @Override
    public boolean isCollisionBound() {
        boolean isCollision = super.isCollisionBound();
        if(isCollision){
            alive = false;
        }
        return isCollision;
    }

    @Override
    public boolean isCollisionObject() {
        boolean isCollision = false;
        for(GameObject gameObject:TankGame.getGameClient().getGameObjects()){
            if(gameObject==this){
                continue;
            }
            if(gameObject instanceof Tank){

                if(enemy==((Tank)gameObject).enemy){
                    continue;
                }
            }
            if(getRectangle().intersects(gameObject.getRectangle())){
                if(gameObject instanceof Tank){
                    gameObject.setAlive(false);
                }

                isCollision = true;
                alive = false;
                break;
            }
        }

        return isCollision;
    }
}

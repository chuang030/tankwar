package object;

import java.awt.*;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int oldX;
    protected int oldY;
    protected int width;
    protected int height;
    protected boolean alive;

    protected Image[] image;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        width = image[0].getWidth(null);
        height = image[0].getHeight(null);
        alive = true;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x,y,width,height);
    }

    public abstract void draw(Graphics g);

}

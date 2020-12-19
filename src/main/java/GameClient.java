import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent{
    private int screenWidth;
    private int screenHeight;

    GameClient(){
        this(800,600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenWidth = screenHeight;

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),
                100,100,null);
    }
}

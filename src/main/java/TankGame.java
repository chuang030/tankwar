import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {

    private static GameClient gameClient;

    public static void main(String[] args) {
        gameClient = new GameClient(800,600);
        JFrame frame = new JFrame();
        frame.add(gameClient);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gameClient.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });
    }

    public static GameClient getGameClient() {
        return gameClient;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private final int score = 0;
    private final int totalBricks = 21;
    private final Timer timer;
    private final int delay = 8;
    private int playerX = 310;
    private final int ballposX = 120;
    private final int ballposY = 120;
    private final int ballXdir = 120;
    private final int ballYdir = 120;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
       timer=new Timer(delay,this);
       timer.start();


    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1, 1, 1692, 592);
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);
        g.setColor(Color.green);
        g.fillOval(ballposX, ballposY, 20, 20);
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right");
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left");
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

    }

    private void moveRight() {
        play = true;
        playerX += 20;
    }

    private void moveLeft() {
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

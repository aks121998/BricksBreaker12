import java.util.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);

        timer.start();


    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
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
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 30).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }
            for (int i = 0; i < map.map[0].length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = j * map.BrickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickheight = map.BrickHeight;
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickheight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballposY = -ballYdir;
                            }
                        }
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;

            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
                System.out.println("Right");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
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
    public void keyReleased(KeyEvent keyEvent) {

    }
}

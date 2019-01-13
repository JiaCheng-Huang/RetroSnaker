package hjc.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class SnakeJFrame extends JFrame implements KeyListener {
    private static Snake snake;
    private static Timer timer = new Timer();

    private void init() {
        Queue<Point> bodyPosition = new LinkedBlockingQueue<>();
        bodyPosition.add(new Point(250, 240));
        Point headPosition = new Point(250, 230);
        snake = new Snake(Direction.UP, headPosition, bodyPosition);
        Food.generate(headPosition, bodyPosition);

        this.setTitle("黄嘉成的贪吃蛇");
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(GameSystem.bound + 10, GameSystem.bound + 10);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        GameCanvas gameArea = new GameCanvas(snake);
        this.add(gameArea);
    }

    public static void main(String[] args) {
        SnakeJFrame jFrame = new SnakeJFrame();
        jFrame.init();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!GameSystem.paused) {
                    snake.embark();
                }
            }
        }, 1000, GameSystem.speed);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                GameSystem.paused = !GameSystem.paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

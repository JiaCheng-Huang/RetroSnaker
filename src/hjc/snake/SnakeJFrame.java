package hjc.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeJFrame extends JFrame implements KeyListener {
    private Snake snake;

    public SnakeJFrame() {
        this.setTitle("黄嘉成的贪吃蛇");
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setSize(GameSystem.bound + 10, GameSystem.bound + 10);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setLocationRelativeTo(null);//在屏幕中居中显示;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.changeDirectionOrSpeedUp(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirectionOrSpeedUp(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirectionOrSpeedUp(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirectionOrSpeedUp(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                GameSystem.paused = !GameSystem.paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

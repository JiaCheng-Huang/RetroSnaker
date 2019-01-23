package hjc.snake;

import javax.swing.*;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Jiacheng Huang
 * @date 2019/01/23 18:47
 */
public class Main {
    private static SnakeJFrame jFrame = new SnakeJFrame();
    private static GameCanvas gameArea = new GameCanvas();
    private static Snake snake;
    private static Timer timer;

    private static void start() {
        GameSystem.reset();
        Queue<Coordinate> bodyPosition = new LinkedBlockingQueue<>();
        bodyPosition.add(new Coordinate(250, 240));
        Coordinate headPosition = new Coordinate(250, 230);
        snake = new Snake(Direction.UP, headPosition, bodyPosition);
        Food.generate(headPosition, bodyPosition);
        gameArea.setSnake(snake);
        jFrame.setSnake(snake);
        jFrame.add(gameArea);
        jFrame.setVisible(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!GameSystem.paused) {
                    snake.embark();
                }
            }
        }, 500, GameSystem.speed);
    }

    public static void main(String[] args) {
        start();
    }


    public static void exit() {
        timer.cancel();
        String message = "游戏结束，最终得分：" + GameSystem.score;
        String title = "游戏结束";
        String[] options = new String[]{"确定"};
        int choice = JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
        switch (choice) {
            default:
                start();
                break;
        }
    }
}

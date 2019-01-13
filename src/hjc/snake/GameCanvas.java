package hjc.snake;

import java.awt.*;


/**
 * @author Jiacheng Huang
 * @date 2019/01/13 16:04
 */
public class GameCanvas extends Canvas implements Observer {
    private Snake snake;

    public GameCanvas(Snake snake) {
        this.snake = snake;
        snake.addObserver(this);
    }

    private void drawSnack(Graphics graphics, Snake snake) {
        graphics.setColor(Color.RED);
        graphics.fillRect(snake.getHead().x, snake.getHead().y, GameSystem.size, GameSystem.size);
        graphics.setColor(Color.BLACK);
        for (Point coordinate : snake.getBody()) {
            graphics.fillRect(coordinate.x, coordinate.y, GameSystem.size, GameSystem.size);
        }
    }

    private void drawFood(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(Food.position.x, Food.position.y, GameSystem.size, GameSystem.size);
    }

    @Override
    public void paint(Graphics graphics) {
//        super.paint(graphics);
        drawSnack(graphics, snake);
        drawFood(graphics);
    }

    @Override
    public void update(Object object) {
        this.snake = (Snake) object;
        repaint();
        GameSystem.changeDirect = false;
        System.out.println("食物在(" + Food.position.x + "," + Food.position.y + ")");
        System.out.println("蛇(" + snake.getHead().x + "," + snake.getHead().y + ")");
    }

}

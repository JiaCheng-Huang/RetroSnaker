package hjc.snake;

import java.awt.*;


/**
 * @author Jiacheng Huang
 * @date 2019/01/13 16:04
 */
public class GameCanvas extends Canvas implements Observer {
    private Snake snake;

    public GameCanvas() {
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
        snake.addObserver(this);
    }

    private void drawSnack(Graphics graphics, Snake snake) {
        graphics.setColor(Color.RED);
        graphics.fillRect(snake.getHead().getX(), snake.getHead().getY(), GameSystem.size, GameSystem.size);
        graphics.setColor(Color.BLACK);
        for (Coordinate coordinate : snake.getBody()) {
            graphics.fillRect(coordinate.getX(), coordinate.getY(), GameSystem.size, GameSystem.size);
        }
    }

    private void drawFood(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(Food.position.getX(), Food.position.getY(), GameSystem.size, GameSystem.size);
    }

    private void drawGrid(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        for (int i = 0; i <= GameSystem.bound; i++) {
            graphics.drawLine(0, i * GameSystem.size, GameSystem.bound * GameSystem.size, i * GameSystem.size);
            graphics.drawLine(i * GameSystem.size, 0, i * GameSystem.size, GameSystem.bound * GameSystem.size);
        }
    }

    @Override
    public void paint(Graphics graphics) {
//        super.paint(graphics);
        drawSnack(graphics, snake);
        drawFood(graphics);
        drawGrid(graphics);
    }

    @Override
    public void update(Object object) {
        this.snake = (Snake) object;
        repaint();
        GameSystem.changeDirect = false;
//        System.out.println("食物在(" + Food.position.x + "," + Food.position.y + ")");
        System.out.println("蛇(" + snake.getHead().getX() + "," + snake.getHead().getY() + ")");
    }

}

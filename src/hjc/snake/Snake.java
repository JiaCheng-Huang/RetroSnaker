package hjc.snake;

import java.awt.*;
import java.util.Queue;

import static hjc.snake.GameSystem.score;
import static hjc.snake.GameSystem.step;

/**
 * 贪吃蛇的实体类
 *
 * @author Jiacheng Huang
 * @date 2019/01/13 11:02
 */
public class Snake implements Subject {
    private Direction direction;
    private Point head;
    private Queue<Point> body;
    private Observer observer;

    public Snake(Direction direction, Point head, Queue<Point> body) {
        this.direction = direction;
        this.head = head;
        this.body = body;
    }

    public Point getHead() {
        return head;
    }


    public Queue<Point> getBody() {
        return body;
    }

    public void changeDirection(Direction direction) {
        // 每一次UI刷新只能转向一次
        if (GameSystem.changeDirect) {
            return;
        }
        switch (this.direction) {
            case UP:
                // 垂直方向则转向
                if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
                    this.direction = direction;
                    GameSystem.changeDirect = true;
                    break;
                }
                // 同方向加速
                if (direction.equals(Direction.UP)) {
                    embark();
                    break;
                }
                break;
            case DOWN:
                if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
                    this.direction = direction;
                    GameSystem.changeDirect = true;
                    break;
                }
                if (direction.equals(Direction.DOWN)) {
                    embark();
                    break;
                }
                break;
            case LEFT:
                if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
                    this.direction = direction;
                    GameSystem.changeDirect = true;
                    break;
                }
                if (direction.equals(Direction.LEFT)) {
                    embark();
                    break;
                }
                break;
            case RIGHT:
                if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
                    this.direction = direction;
                    GameSystem.changeDirect = true;
                    break;
                }
                if (direction.equals(Direction.RIGHT)) {
                    embark();
                    break;
                }
                break;
        }
    }

    public void embark() {
        Point nextPosition = null;
        switch (direction) {
            case UP:
                nextPosition = new Point((int) head.getX(), (int) head.getY() - step);
                break;
            case DOWN:
                nextPosition = new Point((int) head.getX(), (int) head.getY() + step);
                break;
            case LEFT:
                nextPosition = new Point((int) head.getX() - step, (int) head.getY());
                break;
            case RIGHT:
                nextPosition = new Point((int) head.getX() + step, (int) head.getY());
                break;
        }
        if (nextPosition.x < 0 || nextPosition.y < 0 || nextPosition.x > GameSystem.bound || nextPosition.y > GameSystem.bound) {
            GameSystem.exit();
        }
        if (nextPosition.equals(Food.position)) {
            eat(nextPosition);
            return;
        }
        move(nextPosition);
    }

    private void move(Point nextPosition) {
        body.add(head);
        head = nextPosition;
        body.remove();
        for (Point point : body) {
            if (head.x == point.x && head.y == point.y) {
                GameSystem.exit();
            }
        }
        this.notifyObservers();
    }

    private void eat(Point nextPosition) {
        Food.generate(head, body);
        body.add(head);
        head = nextPosition;
        score++;
        this.notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }


    @Override
    public void notifyObservers() {
        observer.update(this);
    }
}

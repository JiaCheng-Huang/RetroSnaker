package hjc.snake;

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
    private Coordinate head;
    private Queue<Coordinate> body;
    private Observer observer;

    public Snake(Direction direction, Coordinate head, Queue<Coordinate> body) {
        this.direction = direction;
        this.head = head;
        this.body = body;
    }

    public Coordinate getHead() {
        return head;
    }


    public Queue<Coordinate> getBody() {
        return body;
    }

    public void changeDirectionOrSpeedUp(Direction direction) {
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
        Coordinate nextPosition = null;
        switch (direction) {
            case UP:
                nextPosition = new Coordinate(head.getX(), head.getY() - step);
                break;
            case DOWN:
                nextPosition = new Coordinate(head.getX(), head.getY() + step);
                break;
            case LEFT:
                nextPosition = new Coordinate(head.getX() - step, head.getY());
                break;
            case RIGHT:
                nextPosition = new Coordinate(head.getX() + step, head.getY());
                break;
        }
        if (nextPosition.getX() < 0 || nextPosition.getY() < 0 || nextPosition.getX() > GameSystem.bound || nextPosition.getY() > GameSystem.bound) {
            Main.exit();
            return;
        }
        if (nextPosition.equals(Food.position)) {
            eat(nextPosition);
            return;
        }
        move(nextPosition);
    }

    private void move(Coordinate nextPosition) {
        body.add(head);
        head = nextPosition;
        body.remove();
        for (Coordinate point : body) {
            if (head.getX().equals(point.getX()) && head.getY().equals(point.getY())) {
                Main.exit();
            }
        }
        this.notifyObservers();
    }

    private void eat(Coordinate nextPosition) {
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

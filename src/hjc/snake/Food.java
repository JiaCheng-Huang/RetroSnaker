package hjc.snake;

import java.awt.*;
import java.util.Queue;

/**
 * 食物的实体类
 *
 * @author Jiacheng Huang
 * @date 2019/01/13 11:03
 */
public class Food {
    public static Point position;

    public static void generate(Point head, Queue<Point> body) {
        int x = ((int) (Math.random() * 50) * 10);
        int y = ((int) (Math.random() * 50) * 10);
        if (head.getX() == x && head.getY() == y) {
            generate(head, body);
            return;
        }
        for (Point coordinate : body) {
            if (x == coordinate.getX() && y == coordinate.getY()) {
                generate(head, body);
                return;
            }
        }
        position = new Point(x, y);
    }
}

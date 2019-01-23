package hjc.snake;

/**
 * @author Jiacheng Huang
 * @date 2019/01/13 12:03
 */
public class GameSystem {

    public final static Integer step = 10;
    public final static Integer bound = 500;
    public final static Integer size = 10;
    public static Integer score = 0;
    public static Integer speed = 500;
    public static Boolean changeDirect = false;
    public volatile static Boolean paused = false;

    public static void reset() {
        GameSystem.paused = false;
        GameSystem.score = 0;
        GameSystem.changeDirect = false;
    }
}

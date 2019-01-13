package hjc.snake;

/**
 * @author Jiacheng Huang
 * @date 2019/01/13 12:03
 */
public class GameSystem {

    public final static Integer step = 10;
    public static Boolean changeDirect = false;
    public static Integer speed = 500;
    public static Integer score = 0;
    public static Integer bound = 500;
    public static Integer size = 10;
    public static Boolean paused = false;

    public static void exit() {
        System.out.println("游戏结束");
        System.exit(0);
    }
}

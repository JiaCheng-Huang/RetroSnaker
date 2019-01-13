package hjc.snake;

/**
 * @author Jiacheng Huang
 * @date 2019/01/13 16:45
 */
public interface Subject {
    void addObserver(Observer observer);

    void notifyObservers();

}

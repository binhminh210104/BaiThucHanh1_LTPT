import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class SharedResources {
    public static final int QUEUE_SIZE = 1000;
    public static ArrayBlockingQueue<Integer> A = new ArrayBlockingQueue<>(QUEUE_SIZE);
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore empty = new Semaphore(QUEUE_SIZE);
    public static Semaphore full = new Semaphore(0);
    public static Random random = new Random();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
} 
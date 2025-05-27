import java.time.LocalDateTime;

public class Consumer implements Runnable {
    private int id;

    public Consumer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SharedResources.full.acquire();
                SharedResources.mutex.acquire();

                int value = SharedResources.A.poll();
                int result = value * 2;
                String currentTime = LocalDateTime.now().format(SharedResources.formatter);
                System.out.println("C_" + id + ": " + value + " - " + result + " - " + currentTime);

                SharedResources.mutex.release();
                SharedResources.empty.release();

                Thread.sleep(SharedResources.random.nextInt(2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 
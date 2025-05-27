import java.time.LocalDateTime;

public class Producer implements Runnable {
    private int id;

    public Producer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SharedResources.empty.acquire();
                SharedResources.mutex.acquire();

                int value = SharedResources.random.nextInt(1000);
                SharedResources.A.add(value);
                String currentTime = LocalDateTime.now().format(SharedResources.formatter);
                System.out.println("P_" + id + ": " + value + " - " + currentTime);

                SharedResources.mutex.release();
                SharedResources.full.release();

                Thread.sleep(SharedResources.random.nextInt(2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 
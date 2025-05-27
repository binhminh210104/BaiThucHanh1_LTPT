public class Main {
    public static void main(String[] args) {
        int k = 3;
        int h = 2;

        for (int i = 1; i <= k; i++) {
            new Thread(new Producer(i)).start();
        }

        for (int i = 1; i <= h; i++) {
            new Thread(new Consumer(i)).start();
        }
    }
} 
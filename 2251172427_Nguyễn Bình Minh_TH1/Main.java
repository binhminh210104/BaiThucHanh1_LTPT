import java.util.Random;

class FindMaxThread extends Thread {
    private int[] array;
    private int start, end;
    private int result;

    public FindMaxThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public void run() {
        result = array[start];
        for (int i = start + 1; i < end; i++) {
            if (array[i] > result) {
                result = array[i];
            }
        }
        System.out.println(Thread.currentThread().getName() + ": Gia tri lon nhat doan [" + start + "-" + (end-1) + "] la " + result + " - thoi gian " + System.currentTimeMillis());
    }

    public int getResult() {
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        int N = 1000;
        int K = 4;
        int[] array = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(10000);
        }

        FindMaxThread[] threads = new FindMaxThread[K];
        int chunkSize = N / K;

        for (int i = 0; i < K; i++) {
            int start = i * chunkSize;
            int end = (i == K - 1) ? N : start + chunkSize;
            threads[i] = new FindMaxThread(array, start, end);
            threads[i].setName("T" + (i + 1));
            threads[i].start();
        }

        try {
            for (int i = 0; i < K; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int finalMax = threads[0].getResult();
        for (int i = 1; i < K; i++) {
            if (threads[i].getResult() > finalMax) {
                finalMax = threads[i].getResult();
            }
        }

        System.out.println("Gia tri lon nhat trong toan mang la: " + finalMax);
    }
}


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MultithreadingDemo {
    private static Vector<Integer> vector1, vector2;
    private static int threadPoolSize, vectorSize;

    public static void init() {
        vector1 = new Vector<>();
        vector2 = new Vector<>();
        threadPoolSize = 4;
        vectorSize = 1048576;
        for (int i = 1; i <= vectorSize; i++) {
            vector1.add(40);
            vector2.add(30);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        List<MyThread> myThreadList = new ArrayList<>();
        int eachThreadShare = vectorSize / threadPoolSize;
        if (threadPoolSize * eachThreadShare < vectorSize) {
            eachThreadShare++;
        }
        int threadsSpawned = 0;
        for (int i = 0; i < threadPoolSize; i++) {
            MyThread thread = new MyThread(
                    "Thread-" + i,
                    vector1,
                    vector2,
                    threadsSpawned * eachThreadShare,
                    Math.min((threadsSpawned + 1) * eachThreadShare - 1, vectorSize - 1));
            thread.start();
            threadsSpawned++;
            myThreadList.add(thread);
        }
        int sum = 0;
        for (MyThread thread : myThreadList) {
            thread.join();
            sum += thread.getSum();
        }
        System.out.println("Sum is " + sum);
    }

    public static class MyThread implements Runnable {

        private Thread thread;
        private String threadId;
        private Vector<Integer> vector1, vector2;
        private int beginIndex, endIndex, sum;

        public MyThread(String threadId, Vector<Integer> v1, Vector<Integer> v2, int beginIndex, int endIndex) {
            //System.out.println("Thread " + threadId + " spawned");
            sum = 0;
            this.threadId = threadId;
            thread = new Thread(this);
            this.vector1 = v1;
            this.vector2 = v2;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            //System.out.println("Thread " + threadId + " started execution");
            for (int i = beginIndex; i <= endIndex; i++) {
                sum += vector1.get(i) * vector2.get(i);
            }
            //System.out.println("Thread " + threadId + " finished execution - Sum = " + sum);
        }

        public void start() {
            thread.start();
        }

        public void join() throws InterruptedException {
            thread.join();
        }

        public int getSum() {
            return sum;
        }
    }

}
import mat.java;

public class MatMulConcur {

private final static int NUM_OF_THREAD =1 ;
private static Mat matC;

public static Mat matmul(Mat matA, Mat matB) {
    matC = new Mat(matA.getNRows(),matB.getNColumns());
    return mul(matA,matB);
}

private static Mat mul(Mat matA,Mat matB) {

    int numRowForThread;
    int numRowA = matA.getNRows();
    int startRow = 0;

    Worker[] myWorker = new Worker[NUM_OF_THREAD];

    for (int j = 0; j < NUM_OF_THREAD; j++) {
        if (j<NUM_OF_THREAD-1){
            numRowForThread = (numRowA / NUM_OF_THREAD);
        } else {
            numRowForThread = (numRowA / NUM_OF_THREAD) + (numRowA % NUM_OF_THREAD);
        }
        myWorker[j] = new Worker(startRow, startRow+numRowForThread,matA,matB);
        myWorker[j].start();
        startRow += numRowForThread;
    }

    for (Worker worker : myWorker) {
        try {
            worker.join();
        } catch (InterruptedException e) {

        }
    }
    return matC;
}

private static class Worker extends Thread {

    private int startRow, stopRow;
    private Mat matA, matB;

    public Worker(int startRow, int stopRow, Mat matA, Mat matB) {
        super();
        this.startRow = startRow;
        this.stopRow = stopRow;
        this.matA = matA;
        this.matB = matB;
    }

    @Override
    public void run() {
        for (int i = startRow; i < stopRow; i++) {
            for (int j = 0; j < matB.getNColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < matA.getNColumns(); k++) {
                    sum += matA.get(i, k) * matB.get(k, j);
                }
                matC.set(i, j, sum);
            }
        }
    }
}
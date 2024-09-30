package Question3;
import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q3SecondPart {

    // Single-threaded factorial calculation
    public static BigInteger factorialSingleThreaded(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Multithreaded factorial calculation (same as previous)
    public static BigInteger factorialMultiThreaded(int n, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int chunkSize = n / numThreads;
        Future<BigInteger>[] futures = new Future[numThreads];

        // Assign each thread a portion of the factorial range
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize + 1;
            int end = (i == numThreads - 1) ? n : (i + 1) * chunkSize;
            futures[i] = executor.submit(new Q3SecondPart.FactorialTask(start, end));
        }

        // Collect results and multiply them together
        BigInteger factorial = BigInteger.ONE;
        for (int i = 0; i < numThreads; i++) {
            factorial = factorial.multiply(futures[i].get());
        }

        executor.shutdown();
        return factorial;
    }

    // Task for computing product in a given range (same as previous)
    static class FactorialTask implements Callable<BigInteger> {
        private final int start;
        private final int end;

        public FactorialTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public BigInteger call() {
            BigInteger result = BigInteger.ONE;
            for (int i = start; i <= end; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 10000;  // Set value of n for factorial calculation
        int numThreads = 4;  // Set number of threads for multithreaded version

        // Measure single-threaded execution time
        long startTimeSingle = System.currentTimeMillis();
        BigInteger resultSingle = factorialSingleThreaded(n);
        long endTimeSingle = System.currentTimeMillis();
        long timeSingle = endTimeSingle - startTimeSingle;

        // Measure multi-threaded execution time
        long startTimeMulti = System.currentTimeMillis();
        BigInteger resultMulti = factorialMultiThreaded(n, numThreads);
        long endTimeMulti = System.currentTimeMillis();
        long timeMulti = endTimeMulti - startTimeMulti;

        // Print first 10 digits to verify the result is correct
        System.out.println("Single-threaded result (first 10 digits): " + resultSingle.toString().substring(0, 10));
        System.out.println("Multi-threaded result (first 10 digits): " + resultMulti.toString().substring(0, 10));

        // Print execution times
        System.out.println("Single-threaded execution time: " + timeSingle + " ms");
        System.out.println("Multi-threaded execution time: " + timeMulti + " ms");

        // Compare and determine which one is faster
        if (timeMulti < timeSingle) {
            System.out.println("Multi-threaded version is faster.");
        } else {
            System.out.println("Single-threaded version is faster.");
        }
    }
}







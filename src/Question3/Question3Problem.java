package Question3;
import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Question3Problem {
    // Task for computing product in a given range
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

    // Method to calculate factorial using multiple threads
    public static BigInteger parallelFactorial(int n, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int chunkSize = n / numThreads;
        Future<BigInteger>[] futures = new Future[numThreads];

        // Assign each thread a portion of the factorial range
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize + 1;
            int end = (i == numThreads - 1) ? n : (i + 1) * chunkSize;
            futures[i] = executor.submit(new FactorialTask(start, end));
        }

        // Collect results and multiply them together
        BigInteger factorial = BigInteger.ONE;
        for (int i = 0; i < numThreads; i++) {
            factorial = factorial.multiply(futures[i].get());
        }

        // Shut down the executor
        executor.shutdown();

        return factorial;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 1000;
        int numThreads = 4;

        // Calculate factorial using multiple threads
        BigInteger result = parallelFactorial(n, numThreads);

        // Print first 10 digits of the factorial to verify the result
        String resultStr = result.toString();
        System.out.println("First 10 digits of " + n + "! : " + resultStr.substring(0, 10));
    }
}

package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private static Executor executor = null;

    public ThreadPool(int maxSize, int queueSize) {
        executor = new ThreadPoolExecutor(maxSize, maxSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public static void execute(Runnable task) {
        executor.execute(task);
    }
}

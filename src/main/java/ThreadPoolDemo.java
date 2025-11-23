import java.util.concurrent.*;

public class ThreadPoolDemo {

    /*
    🔍 Giải thích dòng trên:

    2 thread chính (corePoolSize = 2) chạy ngay

    2 task kế tiếp bị đưa vào queue (2 chỗ)

    4 task còn lại phải tạo thêm 2 thread phụ để chạy (vì maxPoolSize = 4)

    nếu còn task nữa → CallerRunsPolicy sẽ bắt main thread chạy task đó luôn 😅
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2)
        );

        Executor executorUsingVirtualThread = Executors.newVirtualThreadPerTaskExecutor();
        executorUsingVirtualThread.execute(() -> {
            System.out.println("Running in virtual thread executor!");
        });

        ThreadFactory threadFactory = Thread.ofVirtual().factory();
        ThreadPoolExecutor executorUsingVirtualThreadPool = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), threadFactory
        );
        System.out.println("Virtual Thread Pool - Core Pool Size: " + executorUsingVirtualThreadPool.getCorePoolSize());
        System.out.println("Virtual Thread Pool - Max Pool Size: " + executorUsingVirtualThreadPool.getMaximumPoolSize());

        Thread virtualThread = Thread.ofVirtual()
                .start(() -> System.out.println("Running in virtual thread!"));

        for (int i = 1; i <= 7; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("▶ Running task " + taskId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            });
        }

        executor.shutdown();
    }
}


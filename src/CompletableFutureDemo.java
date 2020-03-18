import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/27
 * @Content:
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值");
        });
        /**
         * 异步回调
         */
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值");
            int age=10/0;
            return 1024;
        });
        System.out.println(supplyAsync.whenComplete((t, u) -> {
            System.out.println("*****t=" + t);
            System.out.println("*****u=" + u);
        }).exceptionally(f -> {
            System.out.println("******exception" + f.getMessage());
            return 444;
        }).get());

    }
}

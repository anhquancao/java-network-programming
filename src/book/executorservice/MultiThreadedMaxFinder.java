package book.executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by caoquan on 1/20/17.
 */
public class MultiThreadedMaxFinder {
    public static void main(String[] args) {
        int[] data = new int[]{1, 5, 4, 3, 7, 6, 4, 3};

        FindMaxTask t1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask t2 = new FindMaxTask(data, data.length / 2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(t1);
        Future<Integer> future2 = service.submit(t2);

        try {
            System.out.println(Math.max(future1.get(), future2.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

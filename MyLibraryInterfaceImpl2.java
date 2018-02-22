package com.foobar;

import java.util.concurrent.*;

public class MyLibraryInterfaceImpl2 implements MyLibraryInterface {

    final ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public Integer getSomethingFromRemoteServer() throws ExecutionException, InterruptedException, TimeoutException {

//        Callable<Integer> callable = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                // Perform some computation
//                Thread.sleep(2000);
//                return new Integer(777);
//            }
//        };
//
//        Future<Integer> future = executorService.submit(callable);
//        Integer result = null;
//        try {
//            result = future.get(1, TimeUnit.SECONDS);
//        } finally {
//            executorService.shutdown();
//        }
//        return result;


        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
            Thread.sleep(2000L);
            return new Integer(777);
        });
        Thread t = new Thread(() -> task.run());
        t.start();

        Integer result = task.get(3, TimeUnit.SECONDS);
        return result;
    }
}

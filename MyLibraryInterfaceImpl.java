package com.foobar;

import java.util.concurrent.*;

public class MyLibraryInterfaceImpl implements MyLibraryInterface {

    final ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public Integer getSomethingFromRemoteServer () throws ExecutionException, InterruptedException, TimeoutException {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // Perform some computation
                Thread.sleep(2000);
                return new Integer(777);
            }
        };

        Future<Integer> future = executorService.submit(callable);
        System.out.println("Callable has been submitted");
        Integer result = null;
        try {
            result = future.get(1, TimeUnit.SECONDS);
        } finally {
            executorService.shutdown();
        }
        return result;
    }
}

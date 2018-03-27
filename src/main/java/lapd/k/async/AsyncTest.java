package lapd.k.async;//import com.google.common.util.concurrent.Futures;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import java.util.concurrent.Future;

public class AsyncTest {


//    public void testMyPromise() {
//        Thread a = new Thread(() -> {
//
//        });
//        a.start();
//        Thread b = new Thread();
//
//        PromiseThread thread = new PromiseThread();
//
//        Runnable task = () -> {
//            try {
//                Thread.sleep(1000);//handle task
//                System.out.println("finished task");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//
//        Promise promise = thread.submit(task);
//
//        //Promise promise = thread.newPromise();
//        promise.addListener(new Listener() {
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        });
//    }

    static CountDownLatch c = new CountDownLatch(1);
    public static void main(String[] args) {
        AsyncTest t = new AsyncTest();
        t.testFuture();

//
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    public void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        MyListeningExecutorService listeningExecutorService = MyMoreExecutors.listeningDecorator(executorService);



        ListenableFuture f = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": hello");
                return Thread.currentThread().getName() + ": hello";
            }
        });

        Futures.addCallback(f, new Listener() {
            @Override
            public void onComplete(Object value) {
                System.out.println(Thread.currentThread().getName() + ": onComplete with value :" + value);
                c.countDown();
            }
        });

        try {
            c.await();
            listeningExecutorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testPromise() {

    }

}






//package lapd.k.core.async;
//
//import com.google.common.util.concurrent.*;
//import com.google.common.util.concurrent.ListenableFuture;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.concurrent.SuccessCallback;
//import org.springframework.web.client.AsyncRestTemplate;
//
//
//import java.util.concurrent.*;
//
///**
// * https://monkeysayhi.github.io/tags/%E5%B9%B6%E5%8F%91/page/3/
// *
// * guava examples
// * https://stonelion.gitbooks.io/guava_ch/content/index.html
// */
//
//public class T {
//
//
//    public void copuGuava() {
//        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
//
//        ListenableFuture<String> explosion = service.submit(new Callable<String>() {
//            public String call() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName());
//                return "";
//            }
//        });
//
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        Future<String> f = executorService.submit(new Callable<String>() {
//            public String call() {
//                return "";
//            }
//        });
//
//
////
////        explosion.addListener(() -> {
////            System.out.println(Thread.currentThread().getName());
////            System.out.println("sssss");
////        }, service);
//
//        Futures.addCallback(explosion, new FutureCallback<String>() {
//            // we want this handler to run immediately after we push the big red button!
//            public void onSuccess(String explosion) {
//                System.out.println(Thread.currentThread().getName());
//                ;
//            }
//            public void onFailure(Throwable thrown) {
//
//            }
//        });
//    }
//
//
//    public static void main(String[] args) {
//        T t = new T();
////        t.copuGuava();
//        t.tt();
////
////        try {
////            Thread.currentThread().join();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//    }
//
//    public void n() {
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        Future f = executorService.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        });
//        try {
//            f.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void tt() {
//        AsyncRestTemplate tp = new AsyncRestTemplate();
//        org.springframework.util.concurrent.ListenableFuture<ResponseEntity<String>> response = tp
//                .getForEntity("http://blog.csdn.net/pistolove", String.class);
//
//        response.addCallback(
//                new SuccessCallback<ResponseEntity<String>>() {
//                    @Override
//                    public void onSuccess(ResponseEntity<String> stringResponseEntity) {
//
//                        System.out.println(Thread.currentThread().getName() + ": success");
////                        System.err.printf("success", stringResponseEntity);
//                    }
//                },
//        null
//        );
//
////        ListenableFuture<ResponseEntity<String>> listenInPoolThread = JdkFutureAdapters.listenInPoolThread(response);
////
////        Futures.addCallback(listenInPoolThread, new FutureCallback<Object>() {
////            @Override
////            public void onSuccess(Object result) {
////                System.err.println(result.getClass());
////                System.err.printf("success", result);
////            }
////
////            @Override
////            public void onFailure(Throwable t) {
////                System.out.printf("failure");
////            }
////        });
//    }
//}

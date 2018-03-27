package lapd.k.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PromiseThread {


    ExecutorService executorService = Executors.newFixedThreadPool(1);

//    public PromiseThread(Runnable o) {
//        super(o);
//    }

    public Promise newPromise() {
        return new DefaultPromise();
    }

//    @Override
//    public void run() {
//        super.run();
//        notifyListeners();
//    }

    private void notifyListeners() {
    }

    private Runnable task;

    public Promise submit(Runnable b) {
//        task = b;
        executorService.submit(b);
        return new DefaultPromise();
    }
}

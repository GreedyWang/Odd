package lapd.k.async;

import java.util.concurrent.ExecutorService;

final class MyMoreExecutors {

    private MyMoreExecutors() {
    }

    public static MyListeningExecutorService listeningDecorator(ExecutorService delegate) {
        return new MyListeningExecutorService(delegate);
    }
}

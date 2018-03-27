package lapd.k.async;

public final class Futures {

    private Futures() {
    }

    public static void addCallback(ListenableFuture future, Listener listener) {
        future.addListener(listener);
    }
//
//    private static ListenableFuture<T> wrap(Future<T> future) {
//        return new DefaultListenableFuture<T>();
//    }
}

package lapd.k.async;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class DefaultListenableFuture<T> extends FutureTask<T> implements ListenableFuture<T> {

    private Queue<Listener> listeners = new LinkedList();

    public DefaultListenableFuture(Callable<T> callable) {
        super(callable);
    }

    @Override
    public void addListener(Listener... listener) {

    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    protected void done() {
        try {
            Object v = this.get();
            listeners.stream().forEach(l -> {l.onComplete(v);});

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

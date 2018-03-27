package lapd.k.async;//import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class MyListeningExecutorService implements ExecutorService {

    ExecutorService delegate;

    public MyListeningExecutorService(ExecutorService executorService) {
        delegate = executorService;
    }

    @Override
    public void shutdown() {
        delegate.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public  Future submit(Runnable task, Object result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return delegate.submit(task);
    }

    @Override
    public Object invokeAny(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public Object invokeAny(Collection tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public List<Future> invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public  List<Future> invokeAll(Collection tasks) throws InterruptedException {
        return null;
    }

    @Override
    public  DefaultListenableFuture submit(Callable task) {
        DefaultListenableFuture f = new DefaultListenableFuture(task);
        delegate.submit(f);
        return f;
    }

    @Override
    public void execute(Runnable command) {

    }
}

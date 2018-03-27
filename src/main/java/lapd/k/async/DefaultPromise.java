package lapd.k.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DefaultPromise implements Promise<String> {

    private Thread t;

    public DefaultPromise(Thread thread) {
        t = thread;
    }


    public DefaultPromise() {
    }


    private List<Listener> listenerList = new ArrayList<Listener>();

    public void setSuccess(String value) {

    }

    public void setFailure() {

    }

    public void addListener(Listener listener) {
            listenerList.add(listener);
    }

    public void addListener(Listener... listener) {
        for (Listener l : listener) {
            listenerList.add(l);
        }
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return false;
    }

    public String get() throws InterruptedException, ExecutionException {
        return null;
    }

    public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}

package lapd.k.async;

public interface Promise<T> extends ListenableFuture<T> {

    void setSuccess(T value);

    void setFailure();
}

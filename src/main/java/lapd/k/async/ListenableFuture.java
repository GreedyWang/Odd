package lapd.k.async;//import com.sun.corba.se.impl.orbutil.closure.Future;/

import java.util.concurrent.Future;

public interface ListenableFuture<T> extends Future<T> {

     void addListener(Listener... listener);

     void addListener(Listener listener);
}

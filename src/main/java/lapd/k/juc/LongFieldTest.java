package lapd.k.juc;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * todo: http://www.cnblogs.com/skywang12345/p/3496147.html
 * todo: Exchanger
 * todo: ConcurrentSkipListMap
 */
public class LongFieldTest {

    public void test() {
        Class c = Person.class;
        AtomicLongFieldUpdater atomicIntegerFieldUpdater =
            AtomicLongFieldUpdater.newUpdater(c, "id");

        Person p = new Person(1234567L);
        atomicIntegerFieldUpdater.compareAndSet(p, 1234567L, 1L);
        System.out.println(p.id);
    }

    public static void main(String[] args) {
        LongFieldTest t = new LongFieldTest();
        t.test();
    }
}


class Person {
    volatile long id;

    public Person(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

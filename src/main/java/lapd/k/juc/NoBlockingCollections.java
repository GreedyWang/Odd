package lapd.k.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NoBlockingCollections {
}


class NonBlockingCount {
    AtomicInteger value;

    public int get() {
        return value.get();
    }

    public void set(int i) {
        int v;
        for (; ; ) {
            v = value.get();
            if (value.compareAndSet(v, i)) {
                return;
            }
        }
    }
}

class ConcurrentStack<E> {
    Node<E> head = new Node<E>(null);
    AtomicReference<Node<E>> headR = new AtomicReference<>();

    public void push(E item) {
        Node newHead = new Node(item);
        Node oldHead;
        do {
            oldHead = headR.get();
            newHead.next = oldHead;
        }
        while (!headR.compareAndSet(oldHead, newHead));
    }

    public E pop() {

        Node<E> oldHead;
        Node newHead;

        do {
            oldHead = headR.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        }
        while (!headR.compareAndSet(oldHead, newHead));

        // head = head.next;
        return oldHead.item;
    }

    static class Node<E> {
        Node<E> next;
        final E item;

        Node(E item) {
            this.item = item;
        }
    }

    //偏向锁 和 可重入锁
}

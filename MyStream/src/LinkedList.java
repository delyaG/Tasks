import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> head;
    private Node<T> tail;

    public void add(T o) {
        if (head == null) {
            head = new Node<>(o, null, null);
            tail = head;
        } else {
            tail.setNext(new Node<>(o, null, tail));
            tail = tail.getNext();
        }
    }

    public T get(int index) {
        Node<T> node = getNode(index);
        if (node != null) {
            return node.getValue();
        } else {
            return null;
        }
    }

    private Node<T> getNode(int index) {
        int count = 0;
        Node<T> current = head;
        while (current != null && count < index) {
            current = current.getNext();
            count++;
        }
        return current;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void remove(T o) {
        for (Node<T> current = head; current != null; current = current.getNext()) {
            if (current.getValue().equals(o)) {
                removeNode(current);
                return;
            }
        }
    }

    private void removeNode(Node<T> node) {
        Node<T> next = node.getNext();
        Node<T> prev = node.getPrev();
        if (prev == null) {
            head = next;
        } else {
            prev.setNext(next);
        }
        if (next == null) {
            tail = prev;
        } else {
            next.setPrev(prev);
        }
    }

    public void remove(int index) {
        Node<T> nodeToRemove = getNode(index);
        if (nodeToRemove != null) {
            removeNode(nodeToRemove);
        }
    }

    public void sort() {
        class BucketElement implements Comparable<BucketElement> {
            LinkedList<T> list;
            int rang;

            BucketElement(T o) {
                rang = 1;
                list = new LinkedList<>();
                list.add(o);
            }

            @Override
            public int compareTo(BucketElement o) {
                return Integer.compare(this.rang, o.rang);
            }

        }
        LinkedList<BucketElement> buckets = new LinkedList<>();
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            buckets.add(new BucketElement(iterator.next()));

            while (buckets.tail.getPrev() != null &&
                    buckets.tail.getPrev().getValue().compareTo(buckets.tail.getValue()) == 0) {
                BucketElement secondToLast = buckets.tail.getPrev().getValue();
                BucketElement last = buckets.tail.getValue();
                secondToLast.list = merge(secondToLast.list, last.list);
                buckets.removeNode(buckets.tail);
            }
        }

        while (buckets.tail.getPrev() != null) {
            BucketElement secondToLast = buckets.tail.getPrev().getValue();
            BucketElement last = buckets.tail.getValue();
            secondToLast.list = merge(secondToLast.list, last.list);
            buckets.removeNode(buckets.tail);
        }

        BucketElement first = buckets.head.getValue();
        this.head = first.list.head;
        this.tail = first.list.tail;
    }

    private static <T extends Comparable<T>> LinkedList<T> merge(LinkedList<T> firstList,
                                                                 LinkedList<T> secondList) {
        LinkedList<T> result = new LinkedList<>();
        Node<T> curFirst = firstList.head;
        Node<T> curSecond = secondList.head;
        while (curFirst != null && curSecond != null) {
            if (curFirst.getValue().compareTo(curSecond.getValue()) < 0) {
                result.add(curFirst.getValue());
                curFirst = curFirst.getNext();
            } else {
                result.add(curSecond.getValue());
                curSecond = curSecond.getNext();
            }
        }
        for (; curFirst != null; curFirst = curFirst.getNext()) {
            result.add(curFirst.getValue());
        }
        for (; curSecond != null; curSecond = curSecond.getNext()) {
            result.add(curSecond.getValue());
        }
        return result;
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;

        private Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        private E getValue() {
            return value;
        }

        private Node<E> getNext() {
            return next;
        }

        private Node<E> getPrev() {
            return prev;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }

        private void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    private class LinkedListImplIterator implements Iterator<T> {

        Node<T> current;

        private LinkedListImplIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T result = current.getValue();
            current = current.getNext();
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListImplIterator();
    }
}

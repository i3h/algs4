package main.java.com.github.i3h.algs4.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private DequeNode first;
    private DequeNode last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // unit testing (required)
    public static void main(String[] args) {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private void assertNotNull(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    private void addTheFirstItem(Item item) {
        if (size != 0) {
            throw new UnsupportedOperationException();
        }
        DequeNode node = new DequeNode(item, null, null);
        first = node;
        last = node;
        size++;
    }

    private Item removeTheLastItem() {
        if (size != 1) {
            throw new UnsupportedOperationException();
        }
        Item item = first.item;
        first = null;
        last = null;
        size = 0;
        return item;
    }

    // add the item to the front
    public void addFirst(Item item) {
        assertNotNull(item);
        if (size == 0) {
            addTheFirstItem(item);
            return;
        }
        DequeNode node = new DequeNode(item, null, first);
        first.prev = node;
        first = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        assertNotNull(item);
        if (size == 0) {
            addTheFirstItem(item);
            return;
        }
        DequeNode node = new DequeNode(item, last, null);
        last.next = node;
        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            return removeTheLastItem();
        }
        Item item = first.item;
        first = first.next;
        first.prev = null;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            return removeTheLastItem();
        }
        Item item = last.item;
        last = last.prev;
        last.next = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private DequeNode cur = first;

        private DequeIterator() {
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class DequeNode {
        private Item item;
        private DequeNode prev;
        private DequeNode next;

        private DequeNode(Item item, DequeNode prev, DequeNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

}
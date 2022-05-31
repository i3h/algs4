import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private int capacity = 1;
    private Object[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = new Object[capacity];
        size = 0;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        if (capacity > size) {
            Object[] tmp = new Object[capacity];
            System.arraycopy(queue, 0, tmp, 0, size);
            queue = tmp;
        }
    }

    // add the item
    public void enqueue(Item item) {
        assertNotNull(item);
        if (size == queue.length)
            resize(queue.length * 2);
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException();
        int k = StdRandom.uniform(size);
        Item item = (Item) queue[k];
        queue[k] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();
        int k = StdRandom.uniform(size);
        Item item = (Item) queue[k];
        return item;
    }

    private void assertNotNull(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private Object[] randomQueue;

        public RandomizedQueueIterator() {
            i = size;
            randomQueue = new Object[size];
            System.arraycopy(queue, 0, randomQueue, 0, size);
            StdRandom.shuffle(randomQueue);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            i--;
            Item item = (Item) randomQueue[i];
            randomQueue[i] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}

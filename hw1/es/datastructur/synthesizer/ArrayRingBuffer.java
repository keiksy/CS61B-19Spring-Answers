package es.datastructur.synthesizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>
public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) throw new RuntimeException("Ring buffer overflow");
        if(x==null) throw new RuntimeException("can not add null items!");
        rb[last] = x;
        fillCount++;
        last = (last+1) % capacity();
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) throw new RuntimeException("Ring buffer underflow");
        T ans = rb[first];
        first = (first+1) % capacity();
        fillCount--;
        return ans;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()) throw new RuntimeException("Ring buffer underflow");
        return rb[first];
    }

    private class ARBIterator<K> implements Iterator<K> {
        private int cursor = first;
        private int counter = fillCount;

        @Override
        public boolean hasNext() {
            return counter>0;
        }

        @Override
        public K next() {
            if(!hasNext()) throw new RuntimeException("no more!");
            K ans = (K)rb[cursor];
            cursor = (cursor+1)%capacity();
            counter--;
            return ans;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator<T>();
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(this==o) return true;
        if(o.getClass() != this.getClass()) return false;
        ArrayRingBuffer<T> oo = (ArrayRingBuffer<T>)o;
        if (oo.fillCount!=this.fillCount
                    ||oo.first!=this.first || oo.last!=this.last) return false;
        return Arrays.equals(oo.rb, this.rb);
    }

}

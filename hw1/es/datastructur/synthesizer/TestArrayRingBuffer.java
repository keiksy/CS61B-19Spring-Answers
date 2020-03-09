package es.datastructur.synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(5);
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(1, (int)arb.dequeue());
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertEquals(2, (int)arb.dequeue());
        assertFalse(arb.isFull());
        arb.enqueue(7);
        assertTrue(arb.isFull());

        for(int i:arb) {
            System.out.println(i);
        }
    }
}

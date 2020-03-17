package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void arrayHeapMinPQTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for(int i = 1; i <= 5; i++) {
            pq.add(i,6-i);
            //pq.add(i*2, 6-i);
        }
        int a = 3;
        assertEquals(5, pq.size());
        assertEquals(5, (int)pq.getSmallest());
        assertEquals(5, (int)pq.removeSmallest());
        assertEquals(4, (int)pq.removeSmallest());
        assertEquals(true, pq.contains(3));
        assertEquals(3, pq.size());
        assertEquals(3, (int)pq.removeSmallest());
        assertEquals(false, pq.contains(3));
        assertEquals(2, (int)pq.removeSmallest());
        assertEquals(1, (int)pq.removeSmallest());
        assertEquals(0, pq.size());
        assertEquals(false, pq.contains(5));
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for(int i = 1; i <= 5; i++)
            pq.add(i,6-i);
        pq.changePriority(5, 6);
        assertEquals(true, pq.contains(5));
        assertNotEquals(5, (int)pq.getSmallest());

    }
}

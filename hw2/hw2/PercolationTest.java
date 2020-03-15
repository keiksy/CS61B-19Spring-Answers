package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {

    @Test
    public void testPercolation() {
        Percolation percolation = new Percolation(4);
        percolation.open(0,0);
        percolation.open(2,0);
        assertFalse(percolation.isFull(2,0));
        assertFalse(percolation.percolates());
        percolation.open(1,0);
        percolation.open(2,1);
        percolation.open(3, 1);
        assertTrue(percolation.isFull(2,1));
        assertTrue(percolation.percolates());
    }
}

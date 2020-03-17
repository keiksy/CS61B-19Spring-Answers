package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KDTreeTest {

    @Test
    public void testBasicCases() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2,3));
        points.add(new Point(4,2));
        points.add(new Point(4,5));
        points.add(new Point(3,3));
        points.add(new Point(1,5));
        points.add(new Point(4,4));
        KDTree kdTree = new KDTree(points);
        assertEquals(new Point(1,5), kdTree.nearest(0, 7));
    }
}

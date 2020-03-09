import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testUnionFind() throws Exception{
        UnionFind uf = new UnionFind(6);
        assertEquals(1, uf.sizeOf(1));
        assertFalse(uf.connected(4,2));
        uf.union(1,3);
        uf.union(3,5);
        uf.union(1,2);
        assertTrue(uf.connected(2,5));
        assertEquals(uf.find(2), 3);
    }
}

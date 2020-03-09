import java.util.ArrayList;
import java.util.List;

public class UnionFind {

    // TODO - Add instance variables?
    int[] nodes;
    int[] sizeOfSets;
    int[] rank;
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        sizeOfSets = new int[n];
        rank = new int[n];
        parent = new int[n];
        nodes = new int[n];
        for(int i = 0; i < n; i++) {
            nodes[i] = i;
            sizeOfSets[i] = 1;
            rank[i] = 1;
            parent[i] = i;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception{
        // TODO
        if (nodes.length <= vertex)
            throw new Exception("not a valid index");
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) throws Exception{
        // TODO
        validate(v1);
        return sizeOfSets[find(nodes[v1])];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) throws Exception{
        // TODO
        validate(v1);
        return parent[v1]==v1 ? -1*sizeOfSets[v1] : parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) throws Exception{
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) throws Exception{
        // TODO
        validate(v1);
        validate(v2);
        int rootOfV1 = find(v1), rootOfV2 = find(v2);
        if (rank[rootOfV1] <= rank[rootOfV2]) {
            rank[rootOfV2] = Math.max(rank[rootOfV2], 1+rank[rootOfV1]);
            sizeOfSets[rootOfV2] += sizeOfSets[rootOfV1];
            parent[rootOfV1] = rootOfV2;
        } else {
            rank[rootOfV1] = Math.max(rank[rootOfV1], 1+rank[rootOfV2]);
            sizeOfSets[rootOfV1] += sizeOfSets[rootOfV2];
            parent[rootOfV2] = rootOfV1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) throws Exception{
        // TODO
        validate(vertex);
        if (parent[vertex] == vertex) {
            return vertex;
        } else {
            parent[vertex] = find(parent[vertex]);
            return parent[vertex];
        }
    }

}

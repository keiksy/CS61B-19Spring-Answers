package hw2;

public class Percolation {

    private static final int[][] directions = {
            {1, 0},{-1, 0},{0, 1},{0, -1}
    };

    //0 blocked
    //1 opened
    //2 full opened
    private int[] array;
    private UnionFind sites;
    private int width;
    private int numOfOpenSites;

    public Percolation(int N) {
        sites = new UnionFind(N*N);
        array = new int[N*N];
        width = N;
    }

    private int twoD2oneD (int row, int col) {
        int position = row*width + col;
        if (position<0 || position>=width*width) throw new IllegalArgumentException();
        return position;
    }

    public void open(int row, int col) {
        int pos = twoD2oneD(row, col);
        array[pos] = 1;
        if(row==0) array[sites.find(pos)]=2;
        for (int[] dir:directions) {
            try{
                int neighborPos = twoD2oneD(row+dir[0], col+dir[1]);
                if (array[neighborPos] != 0) sites.union(pos, neighborPos);
            } catch (Exception e) {
                continue;
            }
        }
        numOfOpenSites++;
    }

    public boolean isOpen(int row, int col) {
        int pos = twoD2oneD(row, col);
        return array[pos]!=0;
    }

    public boolean isFull(int row, int col) {
        int pos = twoD2oneD(row, col);
        if(array[pos]==0) return false;
        int parentPos = sites.find(pos);
        return array[parentPos]==2;
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        for (int i = width*(width-1); i<width*width; i++)
            if (isFull(width-1, i-(width*(width-1)))) return true;
        return false;
    }
}

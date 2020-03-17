package bearmaps;

import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    class Node implements Comparable<Node>{
        T item;
        double priority;

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(Node n) {
            double subtract = this.priority - n.priority;
            if (subtract<0) {
                return -1;
            } else if (Math.abs(subtract-0.0) < 1e-6) {
                return 0;
            } else return 1;
        }
    }

    ListBasedHeap<Node> pq = new ArrayListHeap<>();

    private void adjust(int idx) {
        if(idx==1
            ||pq.getParent(idx).compareTo(pq.getNodeAt(idx))==0
                || pq.getParent(idx).compareTo(pq.getNodeAt(idx))<0) return;
        else {
            pq.swap(idx, idx/2);
            adjust(idx/2);
        }
    }

    @Override
    public void add(T item, double priority) {
        pq.add(new Node(item, priority));
        adjust(pq.size());
    }

    private int indexOf(T item) {
        for(int i = 1; i <= pq.size(); i++) {
            if(pq.getNodeAt(i).item.equals(item)) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item)!=-1;
    }

    @Override
    public T getSmallest() {
        return pq.getNodeAt(1).item;
    }

    @Override
    public T removeSmallest() {
        pq.swap(1, pq.size());
        Node oldVal = pq.removeNodeAt(pq.size());
        if(size() > 1)adjustDown(1);
        return oldVal.item;
    }

    private void adjustDown(int idx) {
        Node leftSon = pq.getLeftSon(idx), rightSon = pq.getRightSon(idx);
        if(leftSon!= null && pq.getNodeAt(idx).compareTo(leftSon)<0 &&
                rightSon!=null && pq.getNodeAt(idx).compareTo(rightSon)<0) return;

        //calculate the least node of three nodes.
        Node min = pq.getNodeAt(idx);
        if (leftSon==null && rightSon!=null) {
            if (rightSon.compareTo(min)<0) min = rightSon;
        } else if (leftSon!=null && rightSon==null) {
            if (leftSon.compareTo(min)<0) min = leftSon;
        } else if (leftSon!=null && rightSon!=null) {
            if (leftSon.compareTo(rightSon)<0) {
                if (leftSon.compareTo(min)<0) min = leftSon;
            } else if (rightSon.compareTo(min)<0){
                if (rightSon.compareTo(min)<0) min = rightSon;
            }
        }

        if (min == leftSon) {
            pq.swap(idx, idx*2);
            adjustDown(idx*2);
        } else if (min == rightSon) {
            pq.swap(idx, idx*2+1);
            adjustDown(idx*2+1);
        } else return;
    }

    @Override
    public int size() {
        return pq.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        int idx = indexOf(item);
        if (idx==-1) throw new NoSuchElementException();
        pq.setNodeAt(idx, new Node(item, priority));
        adjustDown(1);
    }
}

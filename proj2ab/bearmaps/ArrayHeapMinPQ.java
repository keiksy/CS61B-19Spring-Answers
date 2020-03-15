package bearmaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListHeap<T> extends ArrayList<T> {

    public T getNodeAt(int idx) {
        return get(idx-1);
    }

    public void setNodeAt(int idx, T newNode) {
        set(idx-1, newNode);
    }

    public T getParent(int idx) {
        if(idx<=1) return getNodeAt(1);
        return getNodeAt(idx/2);
    }

    public T getLeftSon(int idx) {
        if(idx>size() || idx*2>size()) throw new RuntimeException();
        return getNodeAt(idx*2);
    }

    public T getRightSon(int idx) {
        if(idx>size() || idx*2+1>size()) throw new RuntimeException();
        return getNodeAt(idx*2+1);
    }

    public void swap(int idxSrc, int idxDst) {
        T t = getNodeAt(idxSrc);
        set(idxSrc-1, getNodeAt(idxDst));
        set(idxDst-1, t);
    }
}

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

    ArrayListHeap<Node> pq = new ArrayListHeap<>();

    private void adjust(int idx) {
        if(idx==1
            ||pq.getParent(idx).compareTo(pq.getNodeAt(idx))==0
                || pq.getParent(idx).compareTo(pq.getNodeAt(idx))<0) return;
        else {
            pq.swap(idx, (idx/2)-1);
            adjust((idx/2)-1);
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
        adjustDown(1);
        return pq.getNodeAt(pq.size()).item;
    }

    private void adjustDown(int idx) {
        Node leftSon = pq.getLeftSon(idx), rightSon = pq.getRightSon(idx);
        if(pq.getNodeAt(idx).compareTo(leftSon)<0 &&
                pq.getNodeAt(idx).compareTo(rightSon)<0) return;
        Node min;
        if (leftSon.compareTo(rightSon)<0) {
            if (leftSon.compareTo(pq.getNodeAt(idx))<0) {
                min = leftSon;
            } else {
                min = pq.getNodeAt(idx);
            }
        } else {
            if (rightSon.compareTo(pq.getNodeAt(idx))<0) {
                min = rightSon;
            } else {
                min = pq.getNodeAt(idx);
            }
        }
        if (min == pq.getNodeAt(idx)) {
            return;
        } else if (min == pq.getLeftSon(idx)) {
            pq.swap(idx, idx*2);
            adjustDown(idx*2);
        } else {
            pq.swap(idx, idx*2+1);
            adjustDown(idx*2+1);
        }
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

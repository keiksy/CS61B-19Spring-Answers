package bearmaps.proj2ab;

import java.util.ArrayList;

/*
 * An ArrayList based Heap(Binary Tree Heap).
 * index started from 1, then 2,3,4... and so on.
 */
public class ArrayListHeap<T> extends ArrayList<T> implements ListBasedHeap<T>{

    public T getNodeAt(int idx) {
        return get(idx-1);
    }

    public void setNodeAt(int idx, T newNode) {
        set(idx-1, newNode);
    }

    public T removeNodeAt(int idx) {
        return remove(idx-1);
    }

    public T getParent(int idx) {
        if(idx<=1) return getNodeAt(1);
        return getNodeAt(idx/2);
    }

    public T getLeftSon(int idx) {
        if(idx>size() || idx*2>size()) return null;
        return getNodeAt(idx*2);
    }

    public T getRightSon(int idx) {
        if(idx>size() || idx*2+1>size()) return null;
        return getNodeAt(idx*2+1);
    }

    public void swap(int idxSrc, int idxDst) {
        T t = getNodeAt(idxSrc);
        set(idxSrc-1, getNodeAt(idxDst));
        set(idxDst-1, t);
    }
}

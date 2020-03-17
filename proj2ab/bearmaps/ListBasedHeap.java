package bearmaps;

import java.util.List;

public interface ListBasedHeap<T> extends List<T> {

    T getNodeAt(int idx);

    void setNodeAt(int idx, T newNode);

    T removeNodeAt(int idx);

    T getParent(int idx);

    T getLeftSon(int idx);

    T getRightSon(int idx);

    void swap(int idxSrc, int idxDst);
}

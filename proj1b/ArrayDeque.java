public class ArrayDeque<T> implements Deque<T>{
    T[] data = (T[]) new Object[10];
    int size = 10;
    int length = 0;

    public ArrayDeque(){}

    public ArrayDeque(ArrayDeque other) {
        validateSize(other.size());
        System.arraycopy((T[])other.data, 0, data, 0, other.size());
    }

    private void validateSize(int needSize) {
        if(needSize <= size) return;
        int newSize = size+needSize+(size>>1);
        T[] newData = (T[]) new Object[newSize];
        System.arraycopy(data, 0,  newData, 0, size());
    }

    @Override
    public void addFirst(T item) {
        validateSize(size()+1);
        System.arraycopy(data, 0, data, 1, size());
        data[0] = item;
        length++;

    }

    @Override
    public void addLast(T item) {
        validateSize(size()+1);
        data[size()] = item;
        length++;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void printDeque() {
        for(int i = 0; i < length; i++) {
            System.out.print(data[i].toString());
            if(i != size()-1) System.out.print(" ");
        }
    }

    @Override
    public T removeFirst() {
        if(size() == 0) return null;
        T first = data[0];
        length--;
        System.arraycopy(data, 1, data, 0, size());
        return first;
    }

    @Override
    public T removeLast() {
        if(size() == 0) return null;
        T last = data[size()-1];
        length--;
        return last;
    }

    @Override
    public T get(int index) {
        if (index >= size() || index<0) return null;
        return data[index];
    }
}

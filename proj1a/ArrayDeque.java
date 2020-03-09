public class ArrayDeque<T> {
    T[] data = (T[]) new Object[10];
    int size = 10;
    int length = 0;

    public ArrayDeque(){}

    public ArrayDeque(ArrayDeque other) {
        validateSize(other.length);
        System.arraycopy((T[])other.data, 0, data, 0, other.length);
    }

    private void validateSize(int needSize) {
        if(needSize <= size) return;
        int newSize = size+needSize+(size>>1);
        T[] newData = (T[]) new Object[newSize];
        System.arraycopy(data, 0,  newData, 0, length+size);
    }

    public void addFirst(T item) {
        validateSize(length+1);
        System.arraycopy(data, 0, data, 1, length);
        data[0] = item;
        length++;

    }

    public void addLast(T item) {
        validateSize(length+1);
        data[length] = item;
        length++;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void printDeque() {
        for(int i = 0; i < length; i++) {
            System.out.print(data[i].toString());
            if(i != length-1) System.out.print(" ");
        }
    }

    public T removeFirst() {
        T first = data[0];
        System.arraycopy(data, 1, data, 0, length);
        length--;
        return first;
    }

    public T removeLast() {
        T last = data[length-1];
        length--;
        return last;
    }

    public T get(int index) {
        if (index >= length || index<0) return null;
        return data[index];
    }
}

import java.util.ArrayList;

public class LinkedListDeque<T> {
    class DLLNode {
        T item;
        DLLNode former;
        DLLNode next;

        public DLLNode(T item) {
            this.item = item;
        }
    }

    DLLNode head, tail;
    int length;

    public LinkedListDeque(){}

    public LinkedListDeque(LinkedListDeque other) {
        LinkedListDeque deque = new LinkedListDeque();
        DLLNode ocur = other.head;
        while(ocur != null) {
            deque.addLast((T)ocur.item);
            ocur = ocur.next;
        }
    }

    public void addFirst(T item) {
        DLLNode temp = new DLLNode(item);
        if (length == 0) {
            head = temp;
            tail = temp;
        }
        head.former = temp;
        temp.next = head;
        head = temp;
        length++;
    }

    public void addLast(T item) {
        DLLNode temp = new DLLNode(item);
        if (length == 0) {
            head = temp;
            tail = temp;
        }
        tail.next = temp;
        temp.former = tail;
        tail = temp;
        length++;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void printDeque() {
        StringBuffer sb = new StringBuffer();
        for(DLLNode cur = head; cur != null; cur = cur.next) {
            sb.append(cur.item.toString());
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    public T removeFirst(){
        DLLNode first = head;
        head = head.next;
        first.next.former = null;
        first.next = null;
        length--;
        return first.item;
    }

    public T removeLast() {
        DLLNode last = tail;
        tail = tail.former;
        last.former.next = null;
        last.former = null;
        length--;
        return last.item;
    }

    public T get(int index) {
        if (index >= length) return null;
        return index>=0 ? getItemOfIdx(index, head, a->a.next) : getItemOfIdx(-1*index, tail, a->a.former);
    }

    private T getItemOfIdx(int posIdx, DLLNode head, Callable callable) {
        int cnt = 0;
        DLLNode cur = head;
        while(cnt < posIdx) {
            cur = callable.call(cur);
            cnt++;
        }
        return cur.item;
    }

    public T getRecursive(int index) {
        return recursiveHelper(index, head);
    }

    private T recursiveHelper(int idx, DLLNode cur) {
        return idx==0 ? cur.item : recursiveHelper(idx-1, cur.next);
    }
}

interface Callable{
    LinkedListDeque.DLLNode call(LinkedListDeque.DLLNode node);
}

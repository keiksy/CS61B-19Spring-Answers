public class LinkedListDeque<T> implements Deque<T>{
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

    @Override
    public void addFirst(T item) {
        DLLNode temp = new DLLNode(item);
        if (size() == 0) {
            head = temp;
            tail = temp;
            length++;
            return;
        }
        head.former = temp;
        temp.next = head;
        head = temp;
        length++;
    }

    @Override
    public void addLast(T item) {
        DLLNode temp = new DLLNode(item);
        if (size() == 0) {
            head = temp;
            tail = temp;
            length++;
            return;
        }
        tail.next = temp;
        temp.former = tail;
        tail = temp;
        length++;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void printDeque() {
        StringBuffer sb = new StringBuffer();
        for(DLLNode cur = head; cur != null; cur = cur.next) {
            sb.append(cur.item.toString());
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    @Override
    public T removeFirst(){
        if(size() == 0) {
            return null;
        } else {
            DLLNode first = head;
            if (size() == 1) {
                head = null;
                tail = null;
                length--;
                return first.item;
            }
            head = head.next;
            first.next.former = null;
            first.next = null;
            length--;
            return first.item;
        }
    }

    @Override
    public T removeLast() {
        if(size() == 0) {
            return null;
        } else {
            DLLNode last = tail;
            if (size() == 1) {
                head = null;
                tail = null;
                length--;
                return last.item;
            }
            tail = tail.former;
            last.former.next = null;
            last.former = null;
            length--;
            return last.item;
        }
    }

    @Override
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

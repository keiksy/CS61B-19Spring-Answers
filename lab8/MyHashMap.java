import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V> {

    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    class Node{
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    LinkedList<Node>[] data;
    private int size;
    private int threshold;
    private int initialSize;
    private Set<K> keySet;


    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        data = new LinkedList[initialSize];
        threshold = (int)Math.floor(initialSize*loadFactor);
    }

    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    @Override
    public void clear() {
        data = new LinkedList[initialSize];
        size = 0;
    }

    private int fromKToIndex(K key, int numOfBucket) {
        if (key==null) throw new RuntimeException("key can not be null");
        return (key.hashCode() & 0x7FFFFFFF) % numOfBucket;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key)!=null;
    }

    @Override
    public V get(K key) {
        int idx = fromKToIndex(key, data.length);
        if(data[idx]==null) return null;
        LinkedList<Node> ll = data[idx];
        for(Node n:ll)
            if(n.key.equals(key)) return n.value;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int idx = fromKToIndex(key, data.length);
        if(data[idx]==null) data[idx] = new LinkedList<>();
        LinkedList<Node> ll = data[idx];
        for(Node n:ll)
            if(n.key.equals(key)) {
                n.value = value;
                return;
            }
        ll.addLast(new Node(key, value));
        size++;
        if(size >= threshold) resize();
    }

    private void resize() {
        LinkedList<Node>[] newdata = new LinkedList[threshold*2];
        threshold *= 2;
        for(int i = 0; i < data.length; i++) {
            if(data[i] != null) {
                LinkedList<Node> ll = data[i];
                for(Node n:ll) {
                    int newIdx = fromKToIndex(n.key, newdata.length);
                    if(newdata[newIdx]==null) newdata[newIdx] = new LinkedList<>();
                    newdata[newIdx].addLast(n);
                }
            }
        }
        data = newdata;
    }

    @Override
    public Set<K> keySet() {
        if(keySet!=null && keySet.size()==size) return keySet;
        keySet = new HashSet<>();
        for(int i = 0; i < data.length; i++) {
            if(data[i] != null) {
                LinkedList<Node> ll = data[i];
                for(Node n:ll) {
                    keySet.add(n.key);
                }
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key, V value) {
        int idx = fromKToIndex(key, data.length);
        if(data[idx]==null) return null;
        ListIterator<Node> iterator = data[idx].listIterator(0);
        while(iterator.hasNext()) {
            Node cur = iterator.next();
            if(cur.key.equals(key) && (value==null || cur.value.equals(value))) {
                iterator.remove();
                size--;
                return cur.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        return remove(key, null);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

import java.rmi.MarshalledObject;
import java.util.*;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{

    class MapNode {
        K key;
        V value;
        MapNode left;
        MapNode right;

        MapNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    MapNode root;
    int size;
    Set<K> keySet;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key)==null ? false : true;
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(MapNode root, K key) {
        if (root==null) return null;
        if (root.key.compareTo(key)==0) return root.value;
        if (root.key.compareTo(key)<0) return getHelper(root.right, key);
        return getHelper(root.left, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key==null) throw new NullPointerException();
        if (root==null) {
            root = new MapNode(key, value);
            size++;
        }
        else putHelper(root, key, value);
    }

    private void putHelper(MapNode root ,K key, V value) {
        if (root.key.compareTo(key)==0) return;
        else if (root.key.compareTo(key)<0) {
            if (root.right==null) {
                root.right = new MapNode(key, value);
                size++;
            } else putHelper(root.right, key, value);
        } else {
            if (root.left==null) {
                root.left = new MapNode(key, value);
                size++;
            } else putHelper(root.left, key, value);
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(MapNode root) {
        if (root==null) return;
        printHelper(root.left);
        System.out.println(root.value+",");
        printHelper(root.right);
    }

}

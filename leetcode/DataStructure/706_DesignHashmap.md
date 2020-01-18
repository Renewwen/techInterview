## [706. Design HashMap](https://leetcode.com/problems/design-hashmap/)

Design a HashMap without using any built-in hash table libraries.

```Java
/**
* HashMap implementation, Generic Type
* K is String, V is Integer for a simpler version
* Support operations:
*
* size()
* isEmpty()
* clear()
* put(K key, V value)
* get(K key)
* containsKey(K key)
* containsValue(V value) // check if the desired value is the map. O(n)
* remove(K key)
*/
```

```Java
public class MyHashMap<K, V> {

    // Node is a static class of MyHashMap, since it is:
    // very closely bonded to MyHashMap class.
    // we probably need to access this class outside from MyHashMap class.
    public static class Node<K, V> {
        final K key; // haha, final...
        V value;
        Node<K, V> next;
        public Node<K, V>(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // static final variable are global constants
    public static final int DEFAULT_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] array;
    private int size; // How many key-value pairs are actucally stored in the HashMap
    private float loadFactor; // determine when to rehash

    public MyHashMap() {
        this(DEFALUT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int cap, float loadFactor) {
        if (cap <= 0 ) {
            throw new IlegalArgumentException("cap can not be <= 0");
        }
        this.array = (Node<K, V>[])(new Node[cap]); // no generic arrays in Java
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }

    // non-negative
    private int hash(K key) {
        // 1. null key
        if (key == null) {
            return 0;
        }
        // 2.3 hashCode()
        // .... don't need to implement this one
    }

    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean equalsValue(V v1, V v2) {
        return v1 == v2 || v1 != null && v1.equals(v2);
    }

    // O(n), traverse the whole array, and traverse each of the linked list in the array
    public boolean containsValue(V value) {
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> node : array) {
            while (node != null) {
                if (equalsValue(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    private boolean equalsKey(K k1, K k2) {
        return k1 == k2 || k1 != null && k1.equals(k2);
    }

    public boolean containsKey(K key) {
        // get index of the key
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            // check if the key equals()
            // key, node.key all possible to be null
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    // if key does not exists in the HashMap, return null.
    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    // insert/update
    // if the key exists, return the old value
    // if the key not exists, return null
    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = array[index];
        Node<K, V> node = head;
        // check if the key exist
        while (node != null) {
            if (equalsKey(node.key, key)) {
                V result = node.value;
                node.value = value;
                return result;
            }
            node = node.next;
        }

        // append the new node before the head and update the new head insert operation.
        Node<K, V> newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if (needRehashing()) {
            rehashing();
        }
        return null;
    }

    private boolean needRehashing() {
        // float loadFactor
        float ratio = (size + 0.0f) / array.length;
        return ratio >= loadFactor;
    }

    private void rehashing() {
        // new double sized array
        // for each node in the old array
        // do the put operation to the new larger array.
        Node<K, V>[] newArray = (Node<K, V>[])(new Node[size * 2]);
        Node<K, V>[] preArray = array;
        array = newArray;
        for (Node<K, V> node : preArray) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    // if the key exist, remove the <key, value> from the HashMap, return the value
    // if the key not exists, return null
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                // remove one node in linked-list.....
            }
            node = node.next;
        }
        return null;
    }
}
```

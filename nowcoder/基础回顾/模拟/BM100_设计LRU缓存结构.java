import java.util.*;


public class Solution {

    // 实现方式：双向链表 + hashMap
    // 双向链表：用于头部插入 addHead() 和尾部删除 deleteTail()
    // hashMap：用于定位当前节点

    static class Node {
        Node prev;
        Node next;
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public Solution(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;

        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            remove(cur);
            addHead(cur);
            return cur.val;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.val = value;
            remove(cur);
            addHead(cur);
        } else {
            Node cur = new Node(key, value);
            if (map.size() == capacity) {
                Node delete = removeTail();
                map.remove(delete.key);
            }
            System.out.println(capacity);
            addHead(cur);
            map.put(key, cur);
        }
    }

    private void addHead(Node cur) {
        // 将cur放置到头部
        Node begin = head.next;
        cur.next = begin;
        begin.prev = cur;
        cur.prev = head;
        head.next = cur;
    }

    private Node remove(Node cur) {
        // 移除cur
        Node curPrev = cur.prev;
        Node curNext = cur.next;
        curPrev.next = curNext;
        curNext.prev = curPrev;
        return cur;
    }

    private Node removeTail() {
        return remove(tail.prev);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution solution = new Solution(capacity);
 * int output = solution.get(key);
 * solution.set(key,value);
 */

package simple_fun.data_structure.linear.linkedlist;

import java.util.HashMap;

/**
 * Description:设计可以变更的缓存结构LRU（Least Recently Used的缩写，即最近最少使用）
 * 【题目】
 * 设计一种缓存结构，该结构在构造时确定大小，假设大小为K，并有两个功能：
 * set(key,value)：将记录(key,value)插入该结构。
 * get(key)：返回key对应的value值。
 * 【要求】
 * 1．set和get方法的时间复杂度为O(1)。
 * 2．某个key的set或get操作一旦发生，认为这个key的记录成了最经常使用的。
 * 3．当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 *
 * @author: NULL
 * @date: 2018/10/30 21:25
 */
public class LRU {
    //定义节点类型
    public static class Node<V> {
        public V value;//存的值
        public Node<V> last;//指向前节点的指针
        public Node<V> next;//指向后节点的指针

        public Node(V value) {
            this.value = value;
        }
    }

    //定义双向链表
    public static class NodeDoubleLinkedList<V> {
        public Node<V> head;
        public Node<V> tail;

        public NodeDoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        //添加新节点
        public void addNode(Node<V> newNode) {
            if (newNode == null) {
                return;
            }
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;//尾节点的后指针指向新节点
                newNode.last = this.tail;//新节点的前指针指向尾节点
                this.tail = newNode;//插入新节点
            }
        }

        //移动某个节点到尾节点(把优先级设为最高)
        public void moveNodeToTail(Node<V> node) {
            if (this.tail == node) {
                return;
            }
            if (this.head == node) {
                this.head = node.next;
                this.head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        public Node<V> removeHead() {
            if (this.head == null) {
                return null;
            }
            Node<V> res = this.head;
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    public static class MyCache<K, V> {
        private HashMap<K, Node<V>> keyNodeMap;
        private HashMap<Node<V>, K> nodeKeyMap;
        private NodeDoubleLinkedList<V> nodeList;
        private int capacity;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            this.keyNodeMap = new HashMap<K, Node<V>>();
            this.nodeKeyMap = new HashMap<Node<V>, K>();
            this.nodeList = new NodeDoubleLinkedList<V>();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<V> res = this.keyNodeMap.get(key);
                this.nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<V> node = this.keyNodeMap.get(key);
                node.value = value;
                this.nodeList.moveNodeToTail(node);
            } else {
                Node<V> newNode = new Node<V>(value);
                this.keyNodeMap.put(key, newNode);
                this.nodeKeyMap.put(newNode, key);
                this.nodeList.addNode(newNode);
                if (this.keyNodeMap.size() == this.capacity + 1) {
                    this.removeMostUnusedCache();
                }
            }
        }
//          删除最不常用记录(即链表头)
        private void removeMostUnusedCache() {
            Node<V> removeNode = this.nodeList.removeHead();
            K removeKey = this.nodeKeyMap.get(removeNode);
            this.nodeKeyMap.remove(removeNode);
            this.keyNodeMap.remove(removeKey);
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        testCache.set("D", 4);
        testCache.set("E", 5);
        testCache.set("F", 6);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("G", 7);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));
        System.out.println(testCache.get("E"));
        System.out.println(testCache.get("F"));
        System.out.println(testCache.get("G"));
    }
    
}

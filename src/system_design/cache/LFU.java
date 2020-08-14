package system_design.cache;

import java.util.HashMap;

/**
 * Description:LFU(least frequently used，即最不经常使用页置换算法)
 * 解决思路:
 * 1.构造两个双向链表，(first)一个存放使用频数节点，再在每个频数下挂存值(Node)的双向链表(seond)；
 * 2.当某个Node被使用时，相应的调整其到频数节点的双向链表头部(head)；
 * 3.当内存满时，删除最小频数的尾结点(tail)
 *
 * @author: matreeix
 * @date: 2018/10/31 10:24
 */
public class LFU {
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache {

        public static class NodeList {
            public Node head;
            public Node tail;
            public NodeList last;
            public NodeList next;

            public NodeList(Node node) {
                head = node;
                tail = node;
            }

            public void addNodeFromHead(Node newHead) {
                newHead.down = head;
                head.up = newHead;
                head = newHead;
            }

            public boolean isEmpty() {
                return head == null;
            }

            public void deleteNode(Node node) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    if (node == head) {
                        head = node.down;
                        head.up = null;
                    } else if (node == tail) {
                        tail = node.up;
                        tail.down = null;
                    } else {
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
                node.up = null;
                node.down = null;
            }
        }

        private int capacity;
        private int size;
        private HashMap<Integer, Node> records;//每个节点记录
        private HashMap<Node, NodeList> heads;//头节点的链表
        private NodeList headList;//头节点的链表的头

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
            headList = null;
        }

        public void set(int key, int value) {//插入操作
            if (records.containsKey(key)) {//当前结构含有key
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList curNodeList = heads.get(node);
                move(node, curNodeList);
            } else {
                if (size == capacity) {//不含有key且容量满时
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    modifyHeadList(headList);
                    records.remove(node.key);
                    heads.remove(node);
                    size--;
                }
                Node node = new Node(key, value, 1);
                if (headList == null) {//容量不满且是新加的第一个节点
                    headList = new NodeList(node);
                } else {
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);//如果头节点链表次数为一次时，就直接添加到头部
                    } else {//否则，新建一个头节点链表
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last = newList;
                        headList = newList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        private void move(Node node, NodeList oldNodeList) {//节点在头节点链表间的移动
            oldNodeList.deleteNode(node);
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
                    : oldNodeList;//看老头节点链表删除节点后是否为空，以确定移动到的头节点链表的前头节点链表是谁
            NodeList nextList = oldNodeList.next;
            if (nextList == null) {//后头节点链表为空时，新建
                NodeList newList = new NodeList(node);
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                if (headList == null) {
                    headList = newList;
                }
                heads.put(node, newList);
            } else {
                if (nextList.head.times.equals(node.times)) {//后头节点链表次数等于node次数加一
                    nextList.addNodeFromHead(node);
                    heads.put(node, nextList);
                } else {//不等于时
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    heads.put(node, newList);
                }
            }
        }

        // return whether delete this head
        private boolean modifyHeadList(NodeList nodeList) {//若头节点链表删为空时，看是否更新头节点链表的头
            if (nodeList.isEmpty()) {
                if (headList == nodeList) {//是头节点链表的头
                    headList = nodeList.next;
                    if (headList != null) {//新头也为空，说明整个结构没东西了
                        headList.last = null;
                    }
                } else {//不是头节点链表的头
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            return false;
        }

        public int get(int key) {
            if (!records.containsKey(key)) {
                return -1;
            }
            Node node = records.get(key);
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList);
            return node.value;
        }

    }
}

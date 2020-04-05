package more_fun.database.Redis;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Description:跳表
 * 百科：增加了向前指针的链表叫作跳表。跳表全称叫做跳跃表，简称跳表。
 * 跳表是一个随机化的数据结构，实质就是一种可以进行二分查找的有序链表。
 * 跳表在原有的有序链表上面增加了多级索引，通过索引来实现快速查找。
 * 跳表不仅能提高搜索性能（logN），同时也可以提高插入和删除操作的性能。
 * <p>
 * 跳跃表(Skiplist)是一种随机化数据结构，它在查找、插入、删除等操作的期望时间复杂度都能达到对数级，
 * 并且编码相对简单许多，跳跃表目前是Redis中用于存储有序集合的底层数据结构，
 * 另外可以存储有序集的数据结构是字典，Redis中还有一种底层数据结构intset可以用来存储有序整数集。
 *
 * @author: 67ng
 * @date: 2018/10/31 15:52
 */
public class SkipListDemo {
    public static class SkipListNode {
        public Integer value;
        public ArrayList<SkipListNode> nextNodes;

        public SkipListNode(Integer value) {
            this.value = value;
            nextNodes = new ArrayList<SkipListNode>();
        }
    }

    public static class SkipListIterator implements Iterator<Integer> {
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList list) {
            this.list = list;
            this.current = list.getHead();
        }

        public boolean hasNext() {
            return current.nextNodes.get(0) != null;
        }

        public Integer next() {
            current = current.nextNodes.get(0);
            return current.value;
        }
    }

    public static class SkipList {
        private SkipListNode head;
        private int maxLevel;
        private int size;
        private static final double PROBABILITY = 0.5;

        public SkipList() {
            size = 0;
            maxLevel = 0;
            head = new SkipListNode(null);
            head.nextNodes.add(null);
        }

        public SkipListNode getHead() {
            return head;
        }

        public void add(Integer newValue) {//添加节点
            if (!contains(newValue)) {
                size++;
                int level = 0;
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                while (level > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode newNode = new SkipListNode(newValue);
                SkipListNode current = head;
                do {
                    current = findNext(newValue, current, level);//代码有问题，应从最高层开始找
                    newNode.nextNodes.add(0, current.nextNodes.get(level));
                    current.nextNodes.set(level, newNode);
                } while (level-- > 0);
            }
        }

        public void delete(Integer deleteValue) {//删除节点
            if (contains(deleteValue)) {
                SkipListNode deleteNode = find(deleteValue);
                size--;
                int level = maxLevel;
                SkipListNode current = head;
                do {
                    current = findNext(deleteNode.value, current, level);
                    if (deleteNode.nextNodes.size() > level) {
                        current.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        // Returns the skiplist node with greatest value <= e
        private SkipListNode find(Integer e) {
            return find(e, head, maxLevel);
        }

        // Returns the skiplist node with greatest value <= e
        // Starts at node start and level
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level);
            } while (level-- > 0);
            return current;
        }

        // Returns the node at a given level with highest value less than e
        private SkipListNode findNext(Integer e, SkipListNode current, int level) {
            SkipListNode next = current.nextNodes.get(level);
            while (next != null) {
                Integer value = next.value;
                if (lessThan(e, value)) { // e < value
                    break;
                }
                current = next;
                next = current.nextNodes.get(level);
            }
            return current;
        }

        public int size() {
            return size;
        }

        public boolean contains(Integer value) {
            SkipListNode node = find(value);
            return node != null && node.value != null && equalTo(node.value, value);
        }

        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }

        /******************************************************************************
         * Utility Functions *
         ******************************************************************************/

        private boolean lessThan(Integer a, Integer b) {
            return a.compareTo(b) < 0;
        }

        private boolean equalTo(Integer a, Integer b) {
            return a.compareTo(b) == 0;
        }
    }

}

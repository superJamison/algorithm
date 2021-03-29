package com.jms.map;

import com.jms.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 21:18
 */
public class TreeMap<K, V> implements Map<K, V>, BinaryTreeInfo {

    protected int size;
    protected Node<K, V> root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Comparator<K> comparator;

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public TreeMap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public V put(K key, V value) {
        if ("".equals(key)){
            return null;
        }
        keyNotNullCheck(key);
        //添加第一个节点
        if (root == null) {
            root = createNode(key, value, null);
            size++;
            afterPut(root);
            return null;
        }

        //添加的不是第一个节点
        //找到父节点
        Node<K, V> node = root;
        Node<K, V> parentNode = root;
        int compare = 0;
        while (node != null) {
            compare = compare(key, node.key);
            parentNode = node;
            if (compare > 0) {
                //右边
                node = node.right;
            } else if (compare < 0) {
                //左边
                node = node.left;
            } else {
                node.key = key;
                node.value = value;
                return node.value;
            }
        }

        Node<K, V> newNode = createNode(key, value, parentNode);
        //看看插入的节点位置是在那个位置
        if (compare > 0) {
            parentNode.right = newNode;
        } else if (compare < 0) {
            parentNode.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare == 0) {
                return node;
            } else if (compare < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    public V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        V oldValue = node.value;
        // 节点存在，必定要删除
        size--;
        /**
         * 删除度为2的节点：
         * 1.先用前驱或者后继节点的值覆盖原节点的值
         * 2.然后删除相应的前驱或者后继
         * 3 .如果一个节点的度为2，那么：它的前驱或者后继的度只可能是1或0
         */
        if (node.hasTwoLeaf()) {// 代表是度为2，存在左右子节点
            // 找到后继节点
            Node<K, V> successor = successor(node);
            // 用后继节点的值覆盖node节点的值
            node.key = successor.key;
            node.value = successor.value;
            // 删除后继节点
            node = successor;
        }

        // 删除node的节点(node的度必然是1或0)
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {//node是度为1的节点
            //更改parent
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            //删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) {//node是叶子节点并且是root节点
            root = null;
            //删除节点之后的处理
            afterRemove(node);
        } else {// node是叶子结点，但不是root节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            //删除节点之后的处理
            afterRemove(node);
        }
        return oldValue;
    }

    protected void afterRemove(Node<K, V> node) {
        //删除的是red节点（不会对红黑树造成任何影响，不做任何操作）或者删除的是黑色节点
        // 1.拥有一个red红色子节点
        if (isRed(node)) {
            black(node);
            return;
        }

        // 删除black的叶子节点（没有子节点）【下溢】
        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }
        // 被删除节点是左还是右
        boolean isLeft = parent.left == null;
        Node<K, V> sibling = isLeft ? parent.right : parent.left;
        if (isLeft) {//删除节点在左边
            // 删除节点的兄弟节点是红色节点
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.right;
                System.out.println(sibling);
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {//sibling没有1个RED子节点
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack) {//导致parent下溢
                    afterRemove(parent);
                }
            } else {//sibling至少有一个red节点
                if (isRed(sibling.right)) {//sibling左边是red节点 LL
                    color(sibling, colorOf(parent));
                    black(sibling.right);
                } else {//sibling左边没有red节点 LR
                    color(sibling.left, colorOf(parent));
                    rotateRight(sibling);
                }
                black(parent);
                rotateLeft(parent);
            }
        } else {//删除节点在右边
            // 删除节点的兄弟节点是红色节点
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.left;
            }
            // TODO java.lang.NullPointerException
            if (isBlack(sibling.left) && isBlack(sibling.right)) {//sibling没有1个RED子节点
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack) {//导致parent下溢
                    afterRemove(parent);
                }
            } else {//sibling至少有一个red节点
                if (isRed(sibling.left)) {//sibling左边是red节点 LL
                    color(sibling, colorOf(parent));
                    black(sibling.left);
                } else {//sibling左边没有red节点 LR
                    color(sibling.right, colorOf(parent));
                    rotateLeft(sibling);
                }
                black(parent);
                rotateRight(parent);
            }
        }
    }

    @Override
    public boolean containKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containValue(V value) {
        if (root == null) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            if (valueEquals(value, node.value)) {
                return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    private boolean valueEquals(V v1, V v2) {
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        traversal(node.left, visitor);
        if (visitor.stop){
            return;
        }
        visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    /**
     * 左旋转
     *
     * @param grand
     */
    protected void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * @param grand
     */
    protected void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {

        //更新parent的父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        //更新child的父节点
        if (child != null) {
            child.parent = grand;
        }

        //更新grand的父节点
        grand.parent = parent;
    }

    /**
     * 查找指定节点的前驱
     *
     * @param node
     * @return
     */
    protected Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> p = node.left;
        //左子树不为空，前驱节点在左子树上
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        //从父节点，祖父节点中找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }

    /**
     * 查找指定节点的后继
     *
     * @param node
     * @return
     */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> s = node.right;
        //左子树不为空，前驱节点在左子树上
        if (s != null) {
            while (s.left != null) {
                s = s.left;
            }
            return s;
        }

        //从父节点，祖父节点中找后继节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }

    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null!");
        }
    }

    protected Node<K, V> createNode(K key, V value, Node<K, V> parentNode) {
        return new Node<>(key, value, parentNode);
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        if (parent == null) {//添加的节点没有父节点，即添加的是root节点
            black(node);
            return;
        }

        //如果父节点是黑色，不用做任何操作，直接返回
        if (isBlack(parent)) {
            return;
        }

        //叔父节点
        Node<K, V> uncle = parent.sibling();
        //祖父节点
        Node<K, V> grand = parent.parent;
        if (isRed(uncle)) {//叔父节点是红色【上溢】
            black(uncle);
            black(parent);
            //把祖父节点当做新添加的节点
            afterPut(red(grand));
            return;
        }

        //叔父节点不是红色
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//LL
                red(grand);
                black(parent);
                rotateRight(grand);
            } else {//LR
                red(grand);
                black(node);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {//R
            if (node.isLeftChild()) {//RL
                red(grand);
                black(node);
                rotateRight(parent);
                rotateLeft(grand);
            } else {//RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>) k1).compareTo(k2);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<K, V>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<K, V>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<K, V>)node).key + "_" + ((Node<K, V>)node).value;
    }

    private static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        public Node<K, V> parent;
        public Node<K, V> left;
        public Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * 判断是否为叶子节点
         *
         * @return 返回值  true:是叶子节点   false：不是叶子节点
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断是否有两个叶子节点
         *
         * @return 返回值：true:有两个叶子节点  false：没有两个叶子节点
         */
        public boolean hasTwoLeaf() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }

        @Override
        public String toString() {
            return "key:"+ key;
        }
    }
}

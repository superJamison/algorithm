package com.jms.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/29 17:32
 */
public class HashMap<K, V> implements Map<K, V>{

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;

    Node<K, V>[] table;

    public HashMap(){
        table = new Node[DEFAULT_CAPACITY];
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
        if (size == 0){
            return;
        }
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        //扩容
        resize();

        int index = index(key);
        //找到对应index的元素
        Node<K, V> root = table[index];
        if (root == null){
            //该索引位置之前还没有存放元素
            Node<K, V> kvNode = createNode(key, value, null);
            black(kvNode);
            table[index] = kvNode;
            size++;
            return null;
        }

        //添加的不是第一个节点
        //找到父节点
        Node<K, V> parentNode = root;
        Node<K, V> node = root;
        int compare = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result;
        boolean searched = false; //是否已经搜索过
        do {
            parentNode = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                compare = 1;
            }else if (h1 < h2){
                compare = -1;
            }else if (Objects.equals(k1, k2)){
                compare = 0;
            }else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (compare = ((Comparable) k1).compareTo(k2)) != 0){

            }else if (searched){// searched == true
                compare = System.identityHashCode(k1) - System.identityHashCode(k2);
            }else {// 先扫描，然后再根据内存地址决定左右
                if ((node.left != null && (result = node(node.left, k1)) != null) ||
                        (node.right != null && (result = node(node.right, k1)) != null)){
                    // 已经存在这个key
                    node = result;
                    compare = 0;
                }else {
                    // 不存在这个key，根据内存地址存放在左边还是右边
                    searched = true;
                    compare = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

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
        }while (node != null);

        Node<K, V> newNode = createNode(key, value, parentNode);
        //看看插入的节点位置是在那个位置
        if (compare > 0) {
            parentNode.right = newNode;
        } else if (compare < 0) {
            parentNode.left = newNode;
        }
        size++;
        fixAfterPut(newNode);
        return null;
    }

    private void resize() {
        //装载因子
        if ((size / table.length) <= DEFAULT_LOAD_FACTOR){
            return;
        }

        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()){
                Node<K, V> node = queue.poll();

                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                //移动代码放到后面
                moveNode(node);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        //重置node
        newNode.left = null;
        newNode.right = null;
        newNode.parent = null;
        newNode.color = RED;

        int index = index(newNode);
        //找到对应index的元素
        Node<K, V> root = table[index];
        if (root == null){
            //该索引位置之前还没有存放元素
            Node<K, V> kvNode = newNode;
            black(kvNode);
            table[index] = kvNode;
            return;
        }

        //添加的不是第一个节点
        //找到父节点
        Node<K, V> parentNode = root;
        Node<K, V> node = root;
        int compare = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        do {
            parentNode = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                compare = 1;
            }else if (h1 < h2){
                compare = -1;
            }else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (compare = ((Comparable) k1).compareTo(k2)) != 0){
            }else {// searched == true
                compare = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (compare > 0) {
                //右边
                node = node.right;
            } else if (compare < 0) {
                //左边
                node = node.left;
            }
        }while (node != null);
        newNode.parent = parentNode;
        //看看插入的节点位置是在那个位置
        if (compare > 0) {
            parentNode.right = newNode;
        } else if (compare < 0) {
            parentNode.left = newNode;
        }

        fixAfterPut(newNode);
    }

    private int compare(K k1, K k2, int h1, int h2) {
        //比较hash值
        int result = h1 - h2;
        if (result != 0) {
            return result;
        }
        if (k1 == null && k2 == null){
            return 0;
        }
        //比较equals
        if (k1.equals(k2)){
            return 0;
        }

        //hash相同equals不相等
        //比较类名
        if(k1 != null && k2 != null){
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            result = k1Cls.compareTo(k2Cls);
            if (result != 0){
                return result;
            }

            //同一种类并且具备可比较性
            if (k1 instanceof Comparable){
                return ((Comparable) k1).compareTo(k1);
            }

            //同一种类型，hash值相等，但不具备可比较性
            //k1为null，k2不为null
            //k1不为null，k2为null
            return System.identityHashCode(k1) - System.identityHashCode(k2);
        }

        return 0;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        Node<K, V> result;
        int compare = 0;
        while (node != null){
            int h2 = node.hash;
            K k2 = node.key;
            if (h1 > h2){
                node = node.right;
            }else if (h1 < h2){
                node = node.left;
            }else if (Objects.equals(k1, k2)){
                return node;
            }else if (k1 != null && k2 != null
                        && k1.getClass() == k2.getClass()
                        && k1 instanceof Comparable
                        && (compare = ((Comparable) k1).compareTo(k2)) != 0){
                node = compare > 0 ? node.right : node.left;
            }else {//hash值相等，但不具备可比较性，也不equals
                //扫描
                if ((node.left != null && (result = node(node.left, k1)) != null)){
                    return result;
                }else {//只能往右边找
                    node = node.right;
                }
            }
        }
        return null;
    }

//    private Node<K, V> node(K key) {
//        Node<K, V> node = table[index(key)];
//        int h1 = key != null ? key.hashCode() : 0;
//        int cmp;
//        while (node != null){
//            cmp = compare(key, node.key, h1, node.hash);
//            if (cmp == 0){
//                return node;
//            }else if (cmp < 0){
//                node = node.left;
//            }else if (cmp > 0){
//                node = node.right;
//            }
//        }
//        return null;
//    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containValue(V value) {
        if (size == 0) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)){
                    return true;
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null){
            return;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) {
                    return;
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
    }

    /**
     * 根据key生成hashCode值（对应桶tables的索引）
     * @param key
     * @return
     */
    private int index(K key){
        return hash(key) & (table.length - 1);
    }

    /**
     * hash ^ (hash >>> 16) 扰动计算
     * @param key
     * @return
     */
    private int hash(K key){
        if (key == null){
            return 0;
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    private int index(Node<K, V> node){
        return node.hash & (table.length - 1);
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

    public V remove(Node<K, V> node) {
        if (node == null) return null;
        Node<K, V> willNode = node;
        size--;

        V oldValue = node.value;

        if (node.hasTwoLeaf()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            fixAfterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            fixAfterRemove(node);
        }

        afterRemove(willNode, node);

        return oldValue;
    }

    protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) { }

    private void fixAfterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /**
     * 查找指定节点的前驱
     *
     * @param node
     * @return
     */
    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<K, V> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    private void fixAfterPut(Node<K, V> node) {
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
            fixAfterPut(red(grand));
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

    /**
     * 左旋转
     *
     * @param grand
     */
    private void rotateLeft(Node<K, V> grand) {
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
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {

        //更新parent的父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            table[index(grand)] = parent;
        }

        //更新child的父节点
        if (child != null) {
            child.parent = grand;
        }

        //更新grand的父节点
        grand.parent = parent;
    }

    protected static class Node<K, V> {
        K key;
        V value;
        int hash;
        boolean color = RED;
        public Node<K, V> parent;
        public Node<K, V> left;
        public Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
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
    }
}

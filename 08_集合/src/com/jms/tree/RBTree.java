package com.jms.tree;

import java.util.Comparator;

/**
 * 红黑树
 * @author Jamison
 * @version 1.0
 * @date 2021/3/26 23:15
 */
public class RBTree<T> extends BBST<T>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<T> comparator) {
        super(comparator);
    }

    private Node<T> color(Node<T> node, boolean color){
        if (node == null) return node;
        ((RBNode<T>)node).color = color;
        return node;
    }

    @Override
    protected Node<T> createNode(T element, Node<T> parentNode) {
        return new RBNode<>(element, parentNode);
    }

    private Node<T> red(Node<T> node){
        return color(node, RED);
    }

    private Node<T> black(Node<T> node){
        return color(node, BLACK);
    }

    private boolean colorOf(Node<T> node){
        return node == null ? BLACK : ((RBNode<T>)node).color;
    }

    private boolean isRed(Node<T> node){
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<T> node){
        return colorOf(node) == BLACK;
    }

    /**
     * 在添加节点之后，检查节点是否满足红黑树的5个性质，如果不满足则修复
     * 添加节点后的12种情况：
     * 父节点是黑色【4种】，直接返回不处理
     * 叔父节点是红色【上溢】【4种】，
     * 叔父节点不是红色【4种】，LL/LR/RL/RR，并且换节点的颜色
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<T> node) {
        Node<T> parent = node.parent;
        if (parent == null){//添加的节点没有父节点，即添加的是root节点
            black(node);
            return;
        }

        //如果父节点是黑色，不用做任何操作，直接返回
        if (isBlack(parent)){
            return;
        }

        //叔父节点
        Node<T> uncle = parent.sibling();
        //祖父节点
        Node<T> grand = parent.parent;
        if (isRed(uncle)){//叔父节点是红色【上溢】
            black(uncle);
            black(parent);
            //把祖父节点当做新添加的节点
            afterAdd(red(grand));
            return;
        }

        //叔父节点不是红色
        if (parent.isLeftChild()){//L
            if (node.isLeftChild()){//LL
                red(grand);
                black(parent);
                rotateRight(grand);
            }else {//LR
                red(grand);
                black(node);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {//R
            if (node.isLeftChild()){//RL
                red(grand);
                black(node);
                rotateRight(parent);
                rotateLeft(grand);
            }else {//RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRotate(Node<T> grand, Node<T> parent, Node<T> child) {
        super.afterRotate(grand, parent, child);
    }

    @Override
    protected void afterRemove(Node<T> node) {
        //删除的是red节点（不会对红黑树造成任何影响，不做任何操作）或者删除的是黑色节点
        // 1.拥有一个red红色子节点
        if (isRed(node)){
            black(node);
            return;
        }

        // 删除black的叶子节点（没有子节点）【下溢】
        Node<T> parent = node.parent;
        // 删除的是根节点
        if (parent == null){
            return;
        }
        // 被删除节点是左还是右
        boolean isLeft = parent.left == null;
        Node<T> sibling = isLeft ? parent.right : parent.left;
        if (isLeft){//删除节点在左边
            // 删除节点的兄弟节点是红色节点
            if (isRed(sibling)){
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.right;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)){//sibling没有1个RED子节点
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack){//导致parent下溢
                    afterRemove(parent);
                }
            }else {//sibling至少有一个red节点
                if (isRed(sibling.right)){//sibling左边是red节点 LL
                    color(sibling, colorOf(parent));
                    black(sibling.right);
                }else {//sibling左边没有red节点 LR
                    color(sibling.left, colorOf(parent));
                    rotateRight(sibling);
                }
                black(parent);
                rotateLeft(parent);
            }
        }else {//删除节点在右边
            // 删除节点的兄弟节点是红色节点
            if (isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.left;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)){//sibling没有1个RED子节点
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack){//导致parent下溢
                    afterRemove(parent);
                }
            }else {//sibling至少有一个red节点
                if (isRed(sibling.left)){//sibling左边是red节点 LL
                    color(sibling, colorOf(parent));
                    black(sibling.left);
                }else {//sibling左边没有red节点 LR
                    color(sibling.right, colorOf(parent));
                    rotateLeft(sibling);
                }
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /*@Override
    protected void afterRemove(Node<T> node, Node<T> replacement) {
        //删除的是red节点，不会对红黑树造成任何影响，不做任何操作
        if (isRed(node)){
            return;
        }

        //下面的都是删除的是黑色节点
        // 1.拥有一个red红色子节点
        if (isRed(replacement)){
            black(replacement);
            return;
        }

        // 删除black的叶子节点（没有子节点）【下溢】
        Node<T> parent = node.parent;
        // 删除的是根节点
        if (parent == null){
            return;
        }
        // 被删除节点是左还是右
        boolean isLeft = parent.left == null;
        Node<T> sibling = isLeft ? parent.right : parent.left;
        if (isLeft){//删除节点在左边
            // 删除节点的兄弟节点是红色节点
            if (isRed(sibling)){
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.right;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)){//sibling没有1个RED子节点
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack){//导致parent下溢
                    afterRemove(parent, null);
                }
            }else {//sibling至少有一个red节点
                if (isRed(sibling.right)){//sibling左边是red节点 LL
                    color(sibling, colorOf(parent));
                    black(sibling.right);
                }else {//sibling左边没有red节点 LR
                    color(sibling.left, colorOf(parent));
                    rotateRight(sibling);
                }
                black(parent);
                rotateLeft(parent);
            }
        }else {//删除节点在右边
            // 删除节点的兄弟节点是红色节点
            if (isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.left;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)){//sibling没有1个RED子节点
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack){//导致parent下溢
                    afterRemove(parent, null);
                }
            }else {//sibling至少有一个red节点
                if (isRed(sibling.left)){//sibling左边是red节点 LL
                    color(sibling, colorOf(parent));
                    black(sibling.left);
                }else {//sibling左边没有red节点 LR
                    color(sibling.right, colorOf(parent));
                    rotateLeft(sibling);
                }
                black(parent);
                rotateRight(parent);
            }
        }
    }
*/
    protected static class RBNode<T> extends Node<T> {
        boolean color = RED;

        public RBNode(T element, Node<T> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED){
                str += "R_";
            }
            return str + element;
        }
    }
}

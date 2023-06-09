package alg_01_ds_dm._02_tree._04_rb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-15 12:55
 * @Version 1.0
 */

// KeyPoint 红黑树本质也是一颗二叉查找树，基于二叉查找树的代码(递归版本)来实现红黑树
public class RBTree<E extends Comparable<E>> {
    // 使用 boolean 值表示红黑
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        boolean color; // 颜色

        public TreeNode(E data) {
            this.data = data;
            // 1. 在红黑树中，红色的节点代表的就是它和它的父亲，在 2-3 树中是在融合在一起的
            // 2. 在 2-3 树中插入新建的节点的时候，都是先和叶子节点进行融合，不会插入到一个空节点中
            this.color = RED;
        }
    }

    private TreeNode root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // KeyPoint 判断一个节点是否是红色的
    private boolean isRED(TreeNode node) {
        // 根据红黑树的定义，空节点的颜色是黑色的
        if (node == null) {
            return BLACK;
        }
        // 节点不为 null，直接返回节点颜色
        return node.color;
    }

    // KeyPoint 左旋转 => 右侧不平衡
    //    node                    x
    //    /  \       左旋转      /   \
    //   T1   x     ------->  node  T3
    //       / \              /  \
    //      T2 T3            T1  T2
    //  传入根节点 => 左旋转 => 保持二叉查找大小关系
    private TreeNode leftRotate(TreeNode node) {

        // 获取 x 位置
        TreeNode x = node.right;

        // 左旋  => 目的:将 x 左边空出来，为了挂载 node
        // 1 原来 x 左挂载到 node.right
        node.right = x.left;
        // 2 再将 node 挂载到 x.left
        x.left = node;

        // 将老根(node)颜色赋值给新根(x)
        x.color = node.color;
        // 该节点表示待融合的节点
        node.color = RED;

        // 将左旋之后的 x 进行返回
        return x;
    }

    // KeyPoint 右旋转 => 左侧不平衡
    //     node                    x
    //     /  \       右旋转      /  \
    //    x   T2     ------->   y   node
    //   / \                        /  \
    //  y  T1                      T1  T2
    //  传入根节点 => 右旋转
    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;
        // 右旋
        node.left = x.right;
        x.right = node;

        // 将老根(node)颜色赋值给新根(x)
        x.color = node.color;
        node.color = RED;

        return x;
    }

    // KeyPoint 颜色翻转 => 对某颗树进行颜色反转
    //      在 3-节点插入一个比根节点要大的节点时进行颜色反转
    private void flipColors(TreeNode node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // KeyPoint 插入操作 => 维护黑平衡
    // 时间复杂度：O(logn)
    public void add(E e) {
        root = add(root, e);
        // 保持根节点为黑色
        root.color = BLACK;
    }

    // 将节点 e 插入到以 node 为根节点的子树当中
    // 并返回插入节点后的二叉查找树的根节点
    private TreeNode add(TreeNode node, E e) {

        // KeyPoint 红黑树 <=> 2-3 树，两者插入节点逻辑是一样的

        // 1. 递归终止条件
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // 2. 递归调用
        // bug 修复：插入的时候只考虑不相等的元素，相等的元素不做任何插入动作
        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        } else {
            return node;
        }

        // KeyPoint
        //  1 维护以 node 为根节点的子树的黑平衡
        //  2 注意这里不是 if else 关系，而是多个 if 并行的关系，每种情况需要依次进行判断
        //    代码是按照顺序关系执行的，有些情况不是进行一次调整就能解决的，而是需要一连串的操作
        //  3 这些调整操作并不是相互独立的，而是相互联系的，是有关联的
        //    即左旋，右旋，颜色反转

        // 1 不管根节点是红还是黑，只要右子节点是红色 => 左旋
        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        // 2 左子节点为红，左子节点的左子节点为红 => 右旋
        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        // 3  根黑左右红 => 临时 4-节点 => 颜色反转
        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

        // 返回根节点
        return node;
    }

    // 查询
    public boolean contains(E target) {
        TreeNode node = find(target);
        return node != null;
    }

    // 时间复杂度：O(logn)
    public TreeNode find(E target) {
        return find(root, target);
    }

    private TreeNode find(TreeNode node, E target) {
        if (node == null) return null;

        if (target.compareTo(node.data) == 0) {
            return node;
        } else if (target.compareTo(node.data) < 0) {
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
    }

    // 遍历
    public List<E> preOrder() {
        List<E> res = new ArrayList<>();

        preOrder(root, res);

        return res;
    }

    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        res.add(node.data);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    public List<E> inOrder() {
        List<E> res = new ArrayList<>();

        inOrder(root, res);

        return res;
    }

    private void inOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        inOrder(node.left, res);
        res.add(node.data);
        inOrder(node.right, res);
    }

    public List<E> postOrder() {
        LinkedList<E> res = new LinkedList<>();

        postOrder(root, res);

        return res;
    }

    private void postOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.data);
    }

    // 最值
    // 时间复杂度：O(logn)
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    // 时间复杂度：O(logn)
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }

    private TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;
        return maxValue(node.right);
    }

    // 时间复杂度：O(logn)
    public E removeMin() {
        E res = minValue();
        root = removeMin(root);
        return res;
    }

    // 删除以 node 为根节点的子树的最小节点
    // 并返回删除完最小节点的子树的根节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        TreeNode leftRoot = removeMin(node.left);
        node.left = leftRoot;

        return node;
    }

    // 时间复杂度：O(logn)
    public E removeMax() {
        E res = maxValue();
        root = removeMax(root);
        return res;
    }

    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            TreeNode leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        TreeNode rightRoot = removeMax(node.right);
        node.right = rightRoot;

        return node;
    }

    // KeyPoint 删除操作 => 删除节点导致满足不了红黑树的性质，需要进行自平衡，从而维护黑平衡
    //                 该过程异常复杂，面试中几乎不会出现，掌握之前讲的知识即可
    // 时间复杂度：O(logn)
    public void remove(E e) {
        root = remove(root, e);
    }

    // 在以 node 为根节点的子树中删除节点 e
    // 并且返回删除后的子树的根节点
    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 要删除的节点就是 node
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // node 的 left 和 right 都不为空
            TreeNode successor = minValue(node.right);

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;
            size--;
            return successor;
        }
    }
}

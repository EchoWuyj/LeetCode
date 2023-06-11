package alg_01_ds_dm._02_tree._02_bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-14 15:24
 * @Version 1.0
 */
public class BSTR<E extends Comparable<E>> {
    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }
    }

    private TreeNode root;
    private int size;

    public BSTR() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // KeyPoint 1 插入操作
    // 时间复杂度：O(logn)
    public void add(E e) {
        // 使用原始的 root 来接受
        root = add(root, e);
    }

    // KeyPoint 递归语义：
    //  将节点 e 插入到以 node 为根节点的子树当中 => 递
    //  并返回插入节点后的二叉查找树的根节点 => 归
    //  注意：最开始 node 节点是 e 插入到 root 为根节点的左子节点或者右子节点
    //        同时递归过程 node 是不断变化的
    private TreeNode add(TreeNode node, E e) {
        // 1. 递归终止条件
        if (node == null) {
            size++;
            // 树为 null 时，才进行新增节点，size++
            return new TreeNode(e);
        }

        // 2. 递归调用
        // bug 修复：插入的时候只考虑不相等的元素，相等的元素不做任何插入动作
        if (e.compareTo(node.data) < 0) {
            // KeyPoint 递和归
            //  1 add(node.left, e); 递:选择插入位置 => 不断递进比较节点值大小，选择合适位置
            //  2 node.left = add(node.left, e); 递:挂载位置 =>插入节点后的二叉查找树应该挂在左/右子节点
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        }
        // 最终返回本层的根节点 node
        return node;
    }

    // KeyPoint 2 查询操作
    public boolean contains(E target) {
        TreeNode node = find(target);
        // 只要 node 不为 null，则找到该节点
        return node != null;
    }

    // KeyPoint 查找目标值节点 target
    // 时间复杂度：O(logn)
    public TreeNode find(E target) {
        return find(root, target);
    }

    // 以 node 为根节点，去该二叉查找树中进行查找
    private TreeNode find(TreeNode node, E target) {
        if (node == null) return null;
        if (target.compareTo(node.data) == 0) {
            return node;
        } else if (target.compareTo(node.data) < 0) {
            // KeyPoint 这里只是涉及查询的过程，没有树的结构进行修改，所以不用将下层的 root 挂在上层的左/右子节点
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
    }

    // KeyPoint 3 遍历
    // 3.1 前序遍历
    public List<E> preOrder() {
        List<E> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    // 递归辅助方法 <=> process
    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }
        res.add(node.data);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    // 3.2 后序遍历
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

    // 3.3 后序遍历
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

    // KeyPoint 4 最值
    // 4.1 最小值
    // 时间复杂度：O(logn)
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        // 递归函数最终返回的是最小值节点 node，还要取值 data
        return minValue(root).data;
    }

    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        // KeyPoint 需要有 return，不能直接是 minValue(node.left)
        return minValue(node.left);
    }

    // 4.2 最大值
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

    // KeyPoint 5 删除
    // 5.1 删除最小值
    // 时间复杂度：O(logn)
    public E removeMin() {
        // 获取最小值，为了后面进行返回
        E res = minValue();
        // 删除最小值的树，重新赋值给 root
        root = removeMin(root);
        return res;
    }

    // KeyPoint 删除操作涉及对树的结构修改，所以重新连接树的左右子节点
    // 删除以 node 为根节点的子树的最小节点 => 递
    // 并返回删除完最小节点的子树的根节点 => 归
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) { // 递
            // 不管 node 的 right 是否为 null，统一进行该操作，避免 node 节点存在右子树
            TreeNode rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        TreeNode leftRoot = removeMin(node.left); // 递 => 一开始不断左下递进
        node.left = leftRoot; // 归 => 修改 node 的左子树
        return node; // 归 => 将本层 node(子树) 返回上层，接在上层的左子节点
    }

    // 5.2 删除最大值
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

    // 5.3 删除一般节点值
    // 时间复杂度：O(logn)
    public void remove(E e) {
        // KeyPoint 新的根节点替换原来的根节点
        root = remove(root, e);
    }

    // 在以 node 为根节点的子树中删除节点 e => 递
    // 并且返回删除后的子树的根节点 => 归
    private TreeNode remove(TreeNode node, E e) {
        // 删除的节点为 null，直接返回
        if (node == null) return null;
        // KeyPoint 进行左右递归比较查找
        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            // 将 node 返回，为了能挂在上层的左子节点或者右子节点
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {

            // KeyPoint 经过上面的 if else 判断后，要删除的节点就是 node
            //      需要进一步对 node 的左右子树分情况讨论

            // 1 只有右子树
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 2 只有左子树
            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 3 既有左子树又有右子树 => node 的 left 和 right 都不为空

            // 右子树的最小值为后继节点
            TreeNode successor = minValue(node.right);

            // 通过移动 left 和 right 指针来实现 successor 覆盖 node
            // 注意:必须先移动 right 指针，再去移动 left 指针，否则形成环路

            //     17
            //    /  \
            //  16   19
            //      /  \
            //    18   21
            //        /  \
            //      20   22

            // 1 删除右子树最小值的根节点
            successor.right = removeMin(node.right);
            // 2 node 的左子树移动到 successor 左子节点
            successor.left = node.left;

            // 断开联系
            node.left = null;
            node.right = null;

            // KeyPoint bug 修复：这里不用修改 size 了，因为在上面的 removeMin 已经修改过了
            // size--;

            // 递归返回时，使用新的后继节点(successor)作为 root 挂在上层的左子节点位置或者右子节点位置，
            // 递归中通过移动指针，区别迭代中赋值的写法
            return successor;
        }
    }
}

package alg_01_ds_wyj._02_tree._04_rb;

import com.sun.org.apache.bcel.internal.generic.BALOAD;
import com.sun.prism.ReadbackRenderTarget;
import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

import javax.swing.event.TreeModelEvent;
import javax.swing.undo.CannotUndoException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-18 18:43
 * @Version 1.0
 */
public class RBTree<E extends Comparable<E>> {
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        boolean color; // 颜色

        public TreeNode(E data) {
            this.data = data;
            this.color = RED;
        }
    }

    private TreeNode root;
    private int size;

    public RBTree() {
        this.root = root;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRED(TreeNode node) {
        if (node == null) return BLACK;
        return node.color;
    }

    // 左旋
    private TreeNode leftRotate(TreeNode node) {
        TreeNode x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 右旋
    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 颜色反转
    private void flipColors(TreeNode node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void add(E e) {
        root = add(root, e);
        root.color = BLACK;
    }

    public TreeNode add(TreeNode node, E e) {
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        } else {
            return node;
        }

        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

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

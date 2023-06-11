package alg_01_ds_wyj._02_tree._02_bst;

import com.sun.prism.ReadbackRenderTarget;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-14 23:19
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
    public void add(E e) {
        root = add(root, e);
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
        }
        return node;
    }

    // KeyPoint 2 查询操作
    public boolean contains(E target) {
        TreeNode node = find(target);
        return node != null;
    }

    public TreeNode find(E target) {
        return find(root, target);
    }

    public TreeNode find(TreeNode node, E e) {
        if (node == null) return null;
        if (e.compareTo(node.data) == 0) {
            return node;
        } else if (e.compareTo(node.data) < 0) {
            return find(node.left, e);
        } else {
            return find(node.right, e);
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
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    public TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    public E maxValue() {
        if (root == null) throw new RuntimeException("tree is null");
        return maxValue(root).data;
    }

    public TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;
        return maxValue(node.right);
    }

    // KeyPoint 5 删除
    public E removeMin() {
        E res = minValue();
        root = removeMin(root);
        return res;
    }

    public TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E res = maxValue();
        root = removeMax(root);
        return res;
    }

    public TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            TreeNode left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    public TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;
        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                TreeNode right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right == null) {
                TreeNode left = node.left;
                node.left = null;
                size--;
                return left;
            }

            TreeNode successor = minValue(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;
            return successor;
        }
    }
}

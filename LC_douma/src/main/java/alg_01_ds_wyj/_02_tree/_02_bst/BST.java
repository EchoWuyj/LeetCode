package alg_01_ds_wyj._02_tree._02_bst;

import javafx.scene.shape.CullFace;

import javax.swing.tree.TreeModel;
import java.awt.event.HierarchyBoundsAdapter;
import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-14 10:31
 * @Version 1.0
 */
public class BST<E extends Comparable<E>> {
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

    public BST() {
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
        if (root == null) {
            root = new TreeNode(e);
        } else {
            TreeNode cur = root;
            while (cur != null) {
                if (e.compareTo(cur.data) == 0) {
                    return;
                } else if (e.compareTo(cur.data) < 0 && cur.left == null) {
                    cur.left = new TreeNode(e);
                    size++;
                    return;
                } else if (e.compareTo(cur.data) > 0 && cur.right == null) {
                    cur.right = new TreeNode(e);
                    size++;
                }

                if (e.compareTo(cur.data) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
        }
    }

    // KeyPoint 2 查询操作
    public boolean contains(E target) {
        TreeNode node = find(target);
        return node != null;
    }

    public TreeNode find(E target) {
        if (root == null) return null;
        TreeNode cur = root;
        while (cur != null) {
            if (target.compareTo(cur.data) == 0) {
                return cur;
            } else if (target.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    public void set(E src, E target) {
        if (contains(target)) return;
        TreeNode srcNode = find(src);
        srcNode.data = target;
    }

    // KeyPoint 3 遍历
    // 前序遍历
    public List<E> preOrder() {
        List<E> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.data);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res;
    }

    // 中序遍历
    public List<E> inOrder() {
        List<E> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.data);
            cur = node.right;
        }
        return res;
    }

    // 后续遍历
    public List<E> postOrder() {
        LinkedList<E> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.data);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return res;
    }

    // 层次遍历
    public List<List<E>> levelOrder() {
        List<List<E>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<E> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                temp.add(cur.data);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(temp);
        }
        return res;
    }

    // KeyPoint 4 最值
    // 1 最小值
    public E minValue() {
        if (root == null) throw new RuntimeException(" tree is null");
        TreeNode min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min.data;
    }

    // 2 最大值
    public E maxValue() {
        if (root == null) throw new RuntimeException(" tree is null");
        TreeNode max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max.data;
    }

    // KeyPoint 5 删除操作
    // 1 删除最小值
    public E removeMin() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode min = root;
        TreeNode parent = null;

        while (min.left != null) {
            parent = min;
            min = min.left;
        }

        if (parent == null) {
            root = root.right;
        } else {
            parent.left = min.right;
        }
        min.right = null;
        size--;
        return min.data;
    }

    // 2 删除最大值
    public E removeMax() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode max = root;
        TreeNode parent = null;
        while (max.right != null) {
            parent = max;
            max = max.right;
        }

        if (parent == null) {
            root = root.left;
        } else {
            parent.right = max.left;
        }
        max.left = null;
        size--;
        return max.data;
    }

    // 3 删除一般节点
    public void remove1(E e) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode parent = null;

        while (cur != null && e.compareTo(cur.data) != 0) {
            parent = cur;
            if (e.compareTo(cur.data) < 0) cur = cur.left;
            else cur = cur.right;
        }
        if (cur == null) return;

        // 1 删除叶子节点
        if (cur.left == null && cur.right == null) {
            if (parent == null) {
                root = null;
            } else if (cur == parent.left) {
                parent.left = null;
            } else if (cur == parent.right) {
                parent.right = null;
            }
        } else if (cur.left != null && cur.right == null) {
            if (parent == null) {
                root = cur.left;
                cur.left = null;
            } else if (cur == parent.left) {
                parent.left = cur.left;
                cur.left = null;
            } else if (cur == parent.right) {
                parent.right = cur.left;
                cur.left = null;
            }
        } else if (cur.left == null && cur.right != null) {
            if (parent == null) {
                root = cur.right;
                cur.right = null;
            } else if (cur == parent.left) {
                parent.left = cur.right;
                cur.right = null;
            } else if (cur == parent.right) {
                parent.right = cur.right;
                cur.right = null;
            }
        } else if (cur.left != null && cur.right != null) {
            TreeNode min = cur.right;
            TreeNode minParent = cur;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            cur.data = min.data;
            TreeNode right = min.right;
            if (min == minParent.right) {
                minParent.right = right;
            } else {
                minParent.left = right;
            }
            min.right = null;
        }
        size--;
    }

    public void remove(E e) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode parent = null;

        while (cur != null && e.compareTo(cur.data) != 0) {
            if (e.compareTo(cur.data) < 0) cur = cur.left;
            else cur = cur.right;
        }
        if (cur == null) return;
        if (cur.left != null && cur.right != null) {
            TreeNode min = cur.right;
            TreeNode minParent = cur;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            cur.data = min.data;
            cur = min;
            parent = minParent;
        }

        TreeNode child;
        if (cur.left != null) {
            child = cur.left;
            cur.left = null;
        } else if (cur.right != null) {
            child = cur.right;
            cur.right = null;
        } else {
            child = null;
        }

        if (parent == null) {
            root = child;
        } else if (cur == parent.left) {
            parent.left = child;
        } else if (cur == parent.right) {
            parent.right = child;
        }
        size--;
    }
}

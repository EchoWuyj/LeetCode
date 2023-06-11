package alg_01_ds_dm._02_tree._03_avl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-14 15:51
 * @Version 1.0
 */

// AVLTree 递归版本
public class AVLTree<E extends Comparable<E>> {
    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        // 节点高度 1 (叶子节点) => AVL 高度理解成节点数
        // => 叶子节点只有一个，所以其高度就是 1
        int height = 1;

        public TreeNode(E data) {
            this.data = data;
        }
    }

    private TreeNode root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // KeyPoint 辅助方法:获取节点高度
    // 设置为 private 都是 AVLTree 内部调用的方法，外部是调用不了的，用来辅助实现 public 方法
    // public 修饰的方法才是对外提供的 API 方法
    private int getHeight(TreeNode node) {
        // 空树高度为0
        if (node == null) return 0;
        return node.height;
    }

    // KeyPoint 辅助方法:获取节点平衡因子
    private int getBalanceFactor(TreeNode node) {
        // 空树，平衡因子为0
        if (node == null) return 0;
        // 平衡因子 => 左右子节点高度差
        return getHeight(node.left) - getHeight(node.right);
    }

    // KeyPoint 辅助方法:判断一棵二叉树是否是二叉查找树
    public boolean isBST() {
        // 1 先中序遍历二叉查找树，得到遍历的结果列表
        List<E> res = inOrder();
        int len = res.size();
        if (len <= 1) {
            return true;
        }

        // 2 然后判断结果列表是否是增序排列的
        // KeyPoint 因为索引下标涉及 i-1，故 i 从 1 开始的
        for (int i = 1; i < len; i++) {
            // i 值比 i-1 值要小，则返回 false;
            if (res.get(i).compareTo(res.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    // KeyPoint 辅助方法:判断一棵树是否平衡
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以 node 为根节点的树是否为平衡
    private boolean isBalanced(TreeNode node) {
        if (node == null) return true;
        // KeyPoint
        //  1 先将否定的情况都排除，剩下的情况都成立，才是真的成立，
        //    而不是一个成立就直接返回 true，其实并不能保证为 true
        //  2 同一类中，可以直接调用方法，不用通过创建对象进行调用
        int balanceFactor = getBalanceFactor(node);
        // 绝对值 > 1，不是单个 > 1
        if (Math.abs(balanceFactor) > 1) return false;
        // 递归判断其左右子树是否平衡
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // KeyPoint 辅助方法:右旋(传入根节点)
    // 对节点 y 进行向右旋转操作，返回旋转后新的根节点 x
    //        y                                    x
    //       / \                                 /   \
    //      x   T4        向右旋转 (y)          z       y
    //     / \          --------------->      / \     / \
    //    z   T3                            T1   T2 T3   T4
    //   / \
    // T1   T2
    private TreeNode rightRotate(TreeNode y) {
        // 先找到 x
        TreeNode x = y.left;
        // t3 子树
        TreeNode t3 = x.right;

        // 右旋转
        x.right = y;
        y.left = t3;

        // KeyPoint
        //  1 因为对 x 和 y 都进行了调整，其左右子树都是发生了变化，更新 x 和 y 的高度
        //  2 bug 修复，必须先要计算 y 节点的高度，然后才计算 x 节点的高度
        //    原因：上面的 x.left = y ，说明 y 是 x 的子节点，需要先计算子节点的高度，才能计算父节点高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // 返回调整之后的根节点，此时 x 已经成为根节点了
        return x;
    }

    // KeyPoint 辅助方法:左旋(传入根节点)
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T4   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T3  z                     T4 T3 T1 T2
    //      / \
    //     T1 T2
    private TreeNode leftRotate(TreeNode y) {
        TreeNode x = y.right;
        TreeNode t3 = x.left;

        // 左旋转
        x.left = y;
        y.right = t3;

        // 更新 x 和 y 的高度
        // bug 修复，必须先要计算 y 节点的高度，然后才计算 x 节点的高度
        // 原因:上面的 x.left = y 说明 y 是 x 的子节点，需要先计算子节点的高度，才能计算父节点高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // 返回调整之后的根节点
        return x;
    }

    // KeyPoint 插入操作
    // 时间复杂度：O(logn)
    public void add(E e) {
        root = add(root, e);
    }

    // 将节点 e 插入到以 node 为根节点的子树当中
    // 并返回插入节点后的二叉查找树的根节点
    private TreeNode add(TreeNode node, E e) {
        // 1. 递归终止条件
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // 2. 递归调用
        // bug 修复：插入的时候只考虑不相等的元素，相等的元素不做任何插入动作
        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
            // KeyPoint AVL 树在插入时需要进行平衡调整，故先不直接返回 node，等下面代码平衡调整之后，再去返回 node
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        }

        // KeyPoint 注意
        //  1 在节点插入好后，在归的过程中 => 更新父亲节点的高度值
        //  2 父亲节点的高度值等于左右子节点最大的高度值再加上 1
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // KeyPoint 注意
        //  1 在节点插入后，父亲节点高度可能发生变化，则其平衡因子也可能发生变化
        //  2 故需要重新计算每个父亲节点的平衡因子
        int balanceFactor = getBalanceFactor(node);

        // KeyPoint 根据平衡因子对 AVL 树进行平衡调整，一共 4 种情况

        // LL 结构 => 右旋 => 调用 leftRotate 方法
        // 严格 > 1，正侧右旋
        // 正负性保持一致，一次旋转即可
        // KeyPoint 正负性保持一致 => 必然 getBalanceFactor >= 0
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            // 处理左边不平衡，进行右旋转，传入最小不平衡子树的根节点
            return rightRotate(node);
        }
        // RR 结构 => 左旋 => 调用 leftRotate 方法
        // 严格 < -1，负侧左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            // 处理右边不平衡，进行左旋转
            return leftRotate(node);
        }

        // LR => RR 结构，LL结构
        //    => 先调用 leftRotate，再调用 rightRotate，和 LR 保持一致
        // 1 balanceFactor > 1 => L
        // 2 正负性不一致推测 getBalanceFactor(node.left) < 0 => R
        // 3 旋转两次
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 先将 node.left 进行左旋，转成 LL，并且将调整后子树挂在 node 的左子树上
            node.left = leftRotate(node.left);
            // 然后对 node 进行右旋
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 先将 node.right 进行右旋，转成 RR
            node.right = rightRotate(node.right);
            // 再将 node 进行左旋
            return leftRotate(node);
        }

        // KeyPoint 平衡调整之后，再去返回 node
        return node;
    }

    // KeyPoint 查询操作
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

    // KeyPoint 遍历
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

    // KeyPoint 最值
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

    // KeyPoint 删除操作
    // 删除最小值
    // 时间复杂度：O(logn)
    public E removeMin() {
        E res = minValue();
        // 统一使用 remove 方法
        root = remove(root, res);
        return res;
    }

    // 删除最大值
    // 时间复杂度：O(logn)
    public E removeMax() {
        E res = maxValue();
        root = remove(root, res);
        return res;
    }

    // 时间复杂度：O(logn)
    public void remove(E e) {
        root = remove(root, e);
    }

    // 在以 node 为根节点的子树中删除节点 e
    // 并且返回删除后的子树的根节点
    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;

        // KeyPoint 定义变量 retNode 来暂时接受返回节点，对其进行平衡性维护
        TreeNode retNode;
        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            // 不直接 return 返回，而是使用 retNode 进行接受，再返回之前先进行平衡调整
            retNode = node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {
            // 要删除的节点就是 node
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                // 注意：这里其实可以直接返回 retNode 的，因为右子树是已经平衡了的，不需要再调整了
                // 这里不直接返回，是为了统一对 retNode 的处理
                retNode = rightNode;
            } else if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                // 注意：这里其实可以直接返回 retNode 的，因为左子树是已经平衡了的，不需要再调整了
                // 这里不直接返回，是为了统一对 retNode 的处理
                retNode = leftNode;
            } else {
                // node 的 left 和 right 都不为空
                // 找 node.right 的最小值(后继节点)
                //     17
                //    /  \
                //  16   19
                //      /  \
                //    18   21
                //        /  \
                //      20   22
                TreeNode successor = minValue(node.right);
                // KeyPoint 不能直接使用 removeMin 方法，因为该方法不会维护平衡性
                successor.right = remove(node.right, successor.data);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                // bug 修复，因为递归 remove 中已经进行了 size-- 了
                // 所以这里不需要了 size--;
                retNode = successor;
            }
        }

        // KeyPoint bug 修复：需要对 retNode 判空
        //      注意:以后使用文档比较，关键看左侧的爆红的位置，看的重点不仅仅是注释，关键是否漏了代码
        //
        if (retNode == null) return null;

        // KeyPoint 平衡维护
        //  1 关键：对删除节点值 e 后，返回新的根节点 retNode，进行平衡维护
        //  2 代码逻辑和 add 方法一样，将原来的 node 替换成 retNode 即可
        //  3 平衡维护操作是常量级别时间复杂度，整体的时间复杂度还是 O(logn)
        retNode.height = Math.max(getHeight(retNode.left),
                getHeight(retNode.right)) + 1;

        // 计算每个父亲节点的平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            // 处理左边不平衡，进行右旋转
            return rightRotate(retNode);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            // 处理右边不平衡，进行左旋转
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            // 先将 retNode.left 进行左旋，转成 LL
            retNode.left = leftRotate(retNode.left);
            // 然后对 retNode 进行右旋
            return rightRotate(retNode);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // 先将 retNode.right 进行右旋，转成 RR
            retNode.right = rightRotate(retNode.right);
            // 再将 retNode 进行左旋
            return leftRotate(retNode);
        }
        // 调整之后的新根节点进行返回
        return retNode;
    }
}

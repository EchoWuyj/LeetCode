package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 20:04
 * @Version 1.0
 */

// 判断一颗树是否是对称的
public class _101_SymmetricTree {

    // KeyPoint 方法一 BFS 迭代
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // 使用两队列来对根节点进队两次，等价于两颗树进行比较，自己和自己比较
        // 同时进队时，修改进队的顺序
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(root);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode cur1 = queue1.poll();
            TreeNode cur2 = queue2.poll();

            // 区别于:判断两颗树是否相同的进队写法(层次遍历判空的进队写法)，即判空之后再去进队
            // 若是先进行 if 判空，则破坏了原来的树的结构，导致 null 节点不进入队列，导致非镜像也会判断成镜像
            //          1
            //     2       2
            //  null 3  null  3

//            if (cur1.left != null) queue1.offer(cur1.left);
//            if (cur1.right != null) queue1.offer(cur1.right);
//
//            if (cur2.right != null) queue2.offer(cur2.right);
//            if (cur2.left != null) queue2.offer(cur2.left);

            // KeyPoint 完全按照树的结构进行入队，只不过对于 null 节点，将判空的逻辑进行提前抽取判断
            //          保证 cur1.val != cur2.val 不会出现空指针异常
            if (cur1 == null && cur2 == null) continue; // (√,√)
            if (cur1 == null || cur2 == null) return false; // 排除了 (×,√) 和 (√,×) 的情况，只剩下 (×,×)
            if (cur1.val != cur2.val) return false;

            // 正常的树，层次遍历，从左往右依次进队
            queue1.offer(cur1.left);
            queue1.offer(cur1.right);

            // 镜像的树，层次遍历，从右往左依次进队，故需要调整进队的次序
            // 即 t1 的 left 和 t2 的 right 比较
            queue2.offer(cur2.right);
            queue2.offer(cur2.left);

            // KeyPoint 写代码中遇到 bug，花了时间没有看出来，则自己尝试 debug，效率很高，不要怕麻烦
            //          一定需要通过 debug 的方式来排除 bug，效率非常的高，很快就能找到问题所在
        }
        return true;
    }

    // KeyPoint 方法二 BFS 迭代(另外一种方式)
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        return isSymmetric1(root, root);
    }

    private static boolean isSymmetric1(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 == null && node2 == null) continue;
            if (node1.val != node2.val) return false;

            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;

            if (left1 == null ^ right2 == null) return false;
            if (right1 == null ^ left2 == null) return false;

            queue1.offer(left1);
            queue1.offer(right1);

            queue2.offer(right2);
            queue2.offer(left2);
        }

        // 这里对同一棵树进行 BFS，所以节点数一定相同，这里可以直接返回 true
        return queue1.isEmpty() && queue2.isEmpty();
    }

    // KeyPoint 方法三 DFS 递归实现
    // 判断一颗树是否是对称的 => 左右两颗子树是否是镜像对称的
    public boolean isSymmetric2(TreeNode root) {
        // 空树认为是对称的
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 判断两颗树是否是镜像的
    private boolean isMirror(TreeNode t1, TreeNode t2) {

        // KeyPoint 返回值为 boolean 值的递归逻辑
        //  1 最先判断条件的返回值为 true 的情况
        //  2 之后再返回 false 的多种情况
        //  3 最后递归调用 isMirror 进行后续判断

        // 1 先对 t1 和 t2 的根节点进行判断
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        // 2 再对 t1，t2 的左右字节点进行镜像判断
        // 类似于 SameTree 的判断 isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right)
        // 将原来的左对左(相同的树) => 左对右(对称二叉树，左右两颗子树是否是镜像对称的)
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}

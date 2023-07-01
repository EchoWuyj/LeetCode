package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-06-30 17:14
 * @Version 1.0
 */
public class _101_SymmetricTree2 {

    // KeyPoint 方法二 BFS 迭代
    // 思路：使用 2 个队列来对 root 进队两次，等价于两颗树进行比较，自己和自己比较
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;

        // 同时进队时，修改进队的顺序
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(root);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode cur1 = queue1.poll();
            TreeNode cur2 = queue2.poll();

            // 区别：判断两颗树是否相同进队代码写法 => 先判空，再进队 (层次遍历，判空进队)
            // 若是先进行 if 判空，则破坏了原来的树的结构，导致 null 节点不进入队列，导致非镜像也会判断成镜像

            //          1
            //     2       2
            //  null 3  null  3

            // if (cur1.left != null) queue1.offer(cur1.left);
            // if (cur1.right != null) queue1.offer(cur1.right);
            //
            // if (cur2.right != null) queue2.offer(cur2.right);
            // if (cur2.left != null) queue2.offer(cur2.left);

            // KeyPoint 关键
            // 完全按照树的结构进行入队，只不过对于 null 节点
            // 将判空的逻辑进行提前抽取判断，保证 cur1.val != cur2.val 不会出现空指针异常

            // cur1 和 cur2 都为 null => (√,√)
            if (cur1 == null && cur2 == null) continue;

            // cur1 和 cur2 其中有个不为 null
            // 排除了 (×,√) 和 (√,×) 的情况，只剩下 (×,×)
            if (cur1 == null || cur2 == null) return false;

            // cur1 和 cur2 都不为 null => (×,×)
            if (cur1.val != cur2.val) return false;

            // 正常的树，层次遍历，从左往右依次进队
            queue1.offer(cur1.left);
            queue1.offer(cur1.right);

            // KeyPoint 特别注意
            // 镜像的树，层次遍历，从右往左依次进队，故需要调整进队的次序
            // 即 t1 的 left 和 t2 的 right 比较

            // 镜像二叉树
            //                1
            //              ↙↘
            //             2    2
            //           ↙↘  ↙↘
            //          3   4 4   3
            //  queue1  →         ← queue2

            queue2.offer(cur2.right);
            queue2.offer(cur2.left);

            // 经验总结：
            // 写代码中遇到 bug，花了时间没有看出来，则自己尝试 debug，效率很高，不要怕麻烦
            // 一定需要通过 debug 的方式来排除 bug，效率非常的高，很快就能找到问题所在
        }
        return true;
    }

    // KeyPoint 方法二 BFS 迭代(另外一种方式)
    public static boolean isSymmetric2(TreeNode root) {
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
}

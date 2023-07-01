package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 21:38
 * @Version 1.0
 */

//
public class _100_SameTree {

    /*
        100. 相同的树
        给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
        如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

        输入：p = [1,2,3], q = [1,2,3]
        输出：true

        提示：
        两棵树上的节点数目都在范围 [0, 100] 内
        -104 <= Node.val <= 104

     */

    // KeyPoint 方法一 DFS 递归实现
    // 递归模型：两个颗树的对应节点对比
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // 1 先对 p，q 的根节点判断
        // 1.1 p q 都为 nul 两颗空树，一定相同 => 一定为 true 的情况
        if (p == null && q == null) return true;

        // 1.2 经过上面的 if 判断之后，p != null || q != null，即 p 和 q 至少有一个不为 null
        //     即在这个前提下，再去加强判断 => 固定 p 判断 q 或者 固定 q 判断 p
        //     1.p != null && q == null => return false
        //     2.q != null && p == null => return false
        //     => 将两种情况整合
        if (p == null || q == null) return false;

        // 1.3 p q 都不为 null，再去判断 p 和 q 的 val
        // 关键：罗列为 false的情况，但不能因为 p.val = q.val 一个 if 条件成立
        //       就直接返回 true，而是将所有 false 情况都罗列完，剩下的就是 true
        if (p.val != q.val) return false;

        // 2 再先序对 p，q 的左右子节点判断
        // 此时只剩下 p.val == q.val，这种情况是为 true 的，但是不能直接返回 true
        // 这只是单层递归逻辑处理完了，还得递归判断其左右子树是否相同
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

        // KeyPoint 补充说明
        // 关于 if 条件整合 => 100_Note_if_Condition

    }

    // KeyPoint 方法二：BFS 迭代
    public boolean isSameTree1(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        // 两个队列，层次遍历，逐一比较节点值是否相等
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);

        // KeyPoint while 循环里，使用 && 或者 || 都是可以的
        // 1.&& 表示 queue1 或者 queue2，其中有一个 null，跳出 while 循环，return false;
        // 2.|| 表示 或者 queue1 或者 queue2，其中有一个 null，继续执行 while 循环，异或判断成立，return false
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            // 值不相等直接返回 false
            if (node1.val != node2.val) return false;

            // 分别获取两颗树的 left 和 right
            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;

            // node1 和 node2 的左节点，其中有一个为 null，一个不为 null，直接返回 false
            // 异或看两者是不同 => 即异或成立，直接返回 false

            // 左节点
            if (left1 == null ^ left2 == null) return false;
            // 右节点
            if (right1 == null ^ right2 == null) return false;

            // 注意：进入队列的顺序不能乱 => 只要保证同一个队列的 left 和 right 次序不乱即可
            if (left1 != null) queue1.offer(left1);
            if (right1 != null) queue1.offer(right1);

            if (left2 != null) queue2.offer(left2);
            if (right2 != null) queue2.offer(right2);
        }

        // 最后判断两个队列是否同时为空
        // 若有一个队列不为 null，存在多余的节点，说明两颗二叉树的结构不同，则返回 false
        // 不能直接返回 true 或者 false，需要通过两个队列是否为空与的结果来决定
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

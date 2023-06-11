package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 21:38
 * @Version 1.0
 */

// 判断两颗树是否相同
public class _100_SameTree {

    // KeyPoint 方法一:DFS 递归实现
    // 递归模型：两个颗树的对应节点对比
    public boolean isSameTree1(TreeNode p, TreeNode q) {

        // KeyPoint 1 先对 p，q 的根节点判断
        // 1.1 p q 都为 nul 两颗空树，一定相同 => 一定为 true 的情况
        if (p == null && q == null) return true;
        // 1.2 经过上面的 if 判断之后，p != null || q != null，即 p 和 q 至少有一个不为 null
        //   即在这个前提下，再去加强判断，而不是只是 p != null || q != null
        if (p == null || q == null) return false;
        // 1.3 p q 都不为 null，再去判断 p 和 q 的 val
        // KeyPoint 关键是罗列为 false的情况，但不能因为 p.val = q.val 一个 if 条件成立
        //          就直接返回 true，而是将所有 false 情况都罗列完，剩下的就是 true
        if (p.val != q.val) return false;

        // KeyPoint 2 再先序对 p，q 的左右子节点判断
        // 就只剩下 p.val == q.val，这种情况是为 true的，但是不能直接返回 true
        // 这只是单层递归逻辑处理完了，还得递归判断其左右子树是否相同
        return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);

        // KeyPoint 利用 if 潜在的逻辑，简化代码

        // 1 没有使用 if 的潜在逻辑的代码，其中有很多无效的判断
//        if (p == null && q == null) return true;

//        if (p == null && q != null) return false;
//        if (p != null && q == null) return false;

        // 2 经过上面的 if 判断之后，p != null || q != null
        //   在此基础上，若是 p 和 q 有一个为 null，则  return false
        //    if (p == null || q == null) return false;

        // 3 最后只有 p q 都不为 null 的情况，故 p != null && q != null 可以省略
//        if ((p != null && q != null) && (p.val == q.val)) return true;
//        return isSameTree2(p.left, q.left) && isSameTree2(p.right, p.left);

        // KeyPoint 通过列可能方式分析 p 和 q
        // 通过 ×，√ 分别表示 != null，= null
        //  q        ×      √
        //  p  × | (×,×)  (×,√)
        //     √ | (√,×)  (√,√)
        // 在可能性表格的基础上来写 if 的条件

        // KeyPoint 等价写法
        // 使用 else if 来替换多个独立的 if 语句， 这可以减少条件判断的次数

//        if (p == null && q == null) {
//            return true;
//        } else if (p == null || q == null) {
//            return false;
//        } else if (p.val != q.val) {
//            return false;
//        }
//      // KeyPoint if 语句最终的 else，使用最后一个 return 代替了
//        return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);

    }

    // KeyPoint 方法二：BFS 迭代
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        // 两个队列，层次遍历，逐一比较节点值是否相等
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);

        // KeyPoint while 循环里，使用 && 或者 || 都是可以的
        //  && 表示 queue1 或者 queue2，其中有一个 null，跳出 while 循环， return false;
        //  || 或者 queue1 或者 queue2，其中有一个 null，继续执行 while 循环，异或判断成立，return false
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
        // 最后判断两个队列是否同时为空，若有一个队列不为 null，存在多余的节点，说明两颗二叉树的结构不同，则返回 false
        // 不能直接返回 true 或者 false，需要通过两个队列是否为空与的结果来决定
        return queue1.isEmpty() && queue2.isEmpty();

        // KeyPoint 另外一个版本
//        if (p == null && q == null) return true;
//        if (p == null || q == null) return false;
//
//        Queue<alg_02_train_wyj._16_day_二叉树一.TreeNode> queue1 = new LinkedList<>();
//        Queue<alg_02_train_wyj._16_day_二叉树一.TreeNode> queue2 = new LinkedList<>();
//
//        queue1.offer(p);
//        queue2.offer(q);
//        while (!queue1.isEmpty() && !queue2.isEmpty()) {
//            alg_02_train_wyj._16_day_二叉树一.TreeNode node1 = queue1.poll();
//            alg_02_train_wyj._16_day_二叉树一.TreeNode node2 = queue2.poll();
//            if (node1 == null && node2 == null) continue;
//            if (node1.val != node2.val) return false;
//            alg_02_train_wyj._16_day_二叉树一.TreeNode left1 = node1.left, right1 = node1.right;
//            alg_02_train_wyj._16_day_二叉树一.TreeNode left2 = node2.left, right2 = node2.right;
//            if (left1 == null ^ left2 == null) return false;
//            if (right1 == null ^ right2 == null) return false;
//            queue1.offer(left1);
//            queue1.offer(right1);
//            queue2.offer(left2);
//            queue2.offer(right2);
//        }
//        return queue1.isEmpty() && queue2.isEmpty();
    }
}

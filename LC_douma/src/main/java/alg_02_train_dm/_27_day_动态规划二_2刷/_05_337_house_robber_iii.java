package alg_02_train_dm._27_day_动态规划二_2刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 18:47
 * @Version 1.0
 */
public class _05_337_house_robber_iii {
      /*
        337. 打家劫舍 III
        在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
        这个地区只有一个入口，我们称之为"根"。

        除了"根"之外，每栋房子有且只有一个"父"房子与之相连。
        一番侦察之后，聪明的小偷意识到"这个地方的所有房屋的排列类似于一棵二叉树"

        如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
        计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

        示例 1:
        输入: [3,2,3,null,3,null,1]

             3
            / \
           2   3
            \   \
             3   1

        输出: 7
        解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.

        示例 2:
        输入: [3,4,5,1,3,null,1]

             3
            / \
           4   5
          / \   \
         1   3   1

        输出: 9
        解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

        提示：
        树的节点数在 [1, 10^4] 范围内
        0 <= Node.val <= 10^4

     */

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // 本质：深度优先遍历 dfs，遍历每个节点，计算在偷与不偷情况下获取最大金钱
    // 每个节点，返回值是 int 数组，不是单一值，其中，[0] 表示不偷，[1]表示偷
    public int[] dfs(TreeNode node) {
        // 数组 int[] 默认返回 0
        if (node == null) return new int[2];

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // 先求出左右子节点 偷 与 不偷 的结果数组，返回给父节点
        // 父节点再根据返回结果，计算偷与不偷的结果数组

        // 当前节点(父节点) 返回值 res
        int[] res = new int[2];

        // 1. 当前节点，选择不偷
        // => 当前节点能偷到的最大钱数 = 左孩子能得到的最大钱 + 右孩子能得到的最大钱
        // => 父节点不偷，不一定是子节点偷时，获取金额最大，也可能是子节点不偷时，获取金额最大，故需要进行比较
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // 补充：更细粒度划分，当前节点选择不偷，那么有四种情况，在其中选择选择最大值
        //  1.偷了左子节点，偷了右子节点  => left[1] + right[1]
        //  2.偷了左子节点，不偷右子节点  => left[1] + right[0]
        //  3.不偷左子节点，偷了右子节点  => left[0] + right[1]
        //  4.不偷左子节点，不偷右子节点  => left[0] + right[0]

        // 2. 当前节点，选择偷
        // => 当前节点能偷到的最大钱数 = 左孩子选择不偷时能得到的最大钱 + 右孩子选择不偷时能得到的最大钱 + 当前节点的钱数
        // => 父节点偷了，故左右子节点只能计算不偷时最大金额，同时加上当前节点值
        // res[1] 表示父节点偷的
        // left[0]，right[0]，表示左右子节点不偷的
        res[1] = left[0] + right[0] + node.val;
        return res;
    }

    // KeyPoint BFS => 存在 bug
    public int rob1(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int odd = 0, even = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                // 错误的三元运算符写法
                // (level % 2 == 1) ? odd += cur.val : even += cur.val

                if (level % 2 == 1) {
                    odd += cur.val;
                } else {
                    even += cur.val;
                }

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            level++;
        }
        return Math.max(odd, even);

        // 测试用例
        //        4       1 层
        //      1         2 层
        //    2           3 层
        //  3             4 层
        // 关键：不一定都是按照奇偶层，可以奇偶混合层，如：1 和 4 层，只要保证两次盗取不是相邻层即可
    }
}




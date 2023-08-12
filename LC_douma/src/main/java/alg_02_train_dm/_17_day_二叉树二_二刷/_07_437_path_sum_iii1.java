package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:54
 * @Version 1.0
 */

public class _07_437_path_sum_iii1 {
     /*
        437. 路径总和 III
        给定一个二叉树，它的每个结点都存放着一个整数值。
        找出路径和等于给定数值的路径总数。

        路径不需要从根节点开始，也不需要在叶子节点结束
        但是路径方向必须是向下的(只能从父节点到子节点)

        root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

              10
             /  \
            5   -3
           / \    \
          3   2   11
         / \   \
        3  -2   1

        返回 3 和等于 8 的路径有:
        [5 -> 3]
        [5 -> 2 -> 1]
        [-3 -> 11]

        提示:
        二叉树的节点个数的范围是 [0,1000]
        -10^9 <= Node.val <= 10^9
        -1000 <= targetSum <= 1000
     */

    // KeyPoint 方法一 DFS => 计算每个节点所有路径和
    // O(nlogn)
    public static int pathSum1(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, new ArrayList<>(), sum);
    }

    // KeyPoint 前序遍历 + 后序遍历 => 不同位置执行不同操作
    // 递归含义：以当前 node 根节点，路径和等于给定数值的路径数
    // O(nlogn)
    // => 每个节点：从父节点遍历到当前节点，时间复杂度 O(logn)
    // => 一共 n 个节点，故总的时间复杂度 O(nlogn)
    private static int dfs(TreeNode node, List<Long> parentPathSumList, long target) {

        //       1
        //     /  \
        //    2    3
        //   /\   / \
        //  4  5  6  7
        //
        // dfs-1 list [1] => 作为 dfs-2 的 parentPathSumList
        //
        // dfs-2 list [1, 3, 2] => 作为 dfs-3 的 parentPathSumList
        //                ↑
        //               1+2
        // dfs-3 list [5,   7,   6, 4]
        //             ↑    ↑    ↑
        //            1+4 1+2+4 2+4

        // KeyPoint 前序遍历中执行操作
        if (node == null) return 0;
        // 计算路径个数 cnt
        int cnt = 0;
        // 1.创建 list，记录从 root 到当前 node，每层累加值，传递给递归下层 parentPathSumList
        // 2.-10^9 <= Node.val <= 10^9 且最大值约为：2.1 * 10^9
        //    故几个数字相加，很容易出现数据溢出，使用 long 数据类型
        List<Long> list = new ArrayList<>();
        int size = parentPathSumList.size();
        // O(logn)
        for (int i = 0; i < size; i++) {
            // 记录从 root 到当前 node，每层累加值
            long num = parentPathSumList.get(i) + node.val;
            list.add(num);
            if (num == target) cnt++;
        }
        list.add((long) node.val);
        if (node.val == target) cnt++;

        int leftCnt = dfs(node.left, list, target);
        int rightCnt = dfs(node.right, list, target);

        // KeyPoint 后序遍历中执行操作
        // 左右子树统计信息 + 自身 node 为根节点统计信息
        return cnt + leftCnt + rightCnt;

        // KeyPoint 总结
        // 自己举个例子，然后 debug 一下就出来了
        // 不要依赖 ChatGPT 工具，ChatGPT 3.5 代码分析能力还是很差的
    }


}

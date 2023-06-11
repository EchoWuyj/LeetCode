package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:54
 * @Version 1.0
 */

public class _07_437_path_sum_iii {
     /*
        437. 路径总和 III
        给定一个二叉树，它的每个结点都存放着一个整数值。
        找出路径和等于给定数值的路径总数。

        路径不需要从根节点开始，也不需要在叶子节点结束，
        但是路径方向必须是向下的(只能从父节点到子节点)。

        root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

              10
             /  \
            5   -3
           / \    \
          3   2   11
         / \   \
        3  -2   1

        返回 3。和等于 8 的路径有:

        1.  5 -> 3
        2.  5 -> 2 -> 1
        3.  -3 -> 11

        提示:
        二叉树的节点个数的范围是 [0,1000]
        -10^9 <= Node.val <= 10^9
        -1000 <= targetSum <= 1000
     */

    // KeyPoint 方法一 dfs => 计算每个节点所有路径和
    // O(nlogn)
    public static int pathSum1(TreeNode root, int sum) {
        return dfs(root, new ArrayList<>(), sum);
    }

    // KeyPoint 前序遍历过程，执行本题逻辑操作
    // O(nlogn)
    // n 个节点，每个节点：从父节点遍历到当前节点，时间复杂度 O(logn)，故总的时间复杂度 O(nlogn)
    private static int dfs(TreeNode node, List<Long> parentPathSumList, long targetSum) {
        if (node == null) return 0;

        // 计算路径个数 cnt
        int cnt = 0;
        // 创建 tmp，记录从 root 到当前 node，每层累加值，传递给递归下层 parentPathSumList
        // 使用 long 避免数据溢出
        List<Long> tmp = new ArrayList<>();
        // O(logn)
        for (int i = 0; i < parentPathSumList.size(); i++) {
            // 记录从 root 到当前 node，每层累加值
            long num = parentPathSumList.get(i) + node.val;
            tmp.add(num);
            if (num == targetSum) cnt++;
        }
        tmp.add((long) node.val);
        if (node.val == targetSum) cnt++;

        int leftCnt = dfs(node.left, tmp, targetSum);
        int rightCnt = dfs(node.right, tmp, targetSum);

        return cnt + leftCnt + rightCnt;
    }

    // 自己举个例子，然后 debug 一下就出来了，不要依赖 ChatGPT 工具，ChatGPT 3.5 代码分析能力还是很差的
    public static void test1() {

        /*
                5
              / \
             4   8
            /   / \
           11  13  4
          /  \      \
         7    2      1

       */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);

        System.out.println(pathSum1(root, 22));
    }

    // 在一个数组 nums 中，求连续子数组(区间)之和[路径和]等于 targetSum 的连续子数组的个数 => 通过使用前缀和的思想，避免了重复计算路径的过程，
    // 注意
    // 1.对于一段新的代码，从操作逻辑上对其理解，对其有宏观的认识
    // 2.通过从详细的模拟过程，加强对代码边界条件的理解
    public static int pathSum(int[] nums, int targetSum) {
        int res = 0;
        // prefixSumMap 前缀和映射
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        // 当前的前缀和   累加和为0的路径有1条
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 累加路径和
            currSum += nums[i];
            res += prefixSumMap.getOrDefault(currSum - targetSum, 0);
            prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        }
        return res;
    }

    public static void test2() {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(pathSum(arr, 10)); // 0
    }

    // DFS(前序遍历) + 前缀和
    // O(n)
    private int res = 0;

    public int pathSum(TreeNode root, int sum) {
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, 1);
        dfs1(root, 0, sum, prefixSumMap);
        return res;
    }

    private void dfs1(TreeNode node,
                      long currSum, int targetSum,
                      Map<Long, Integer> prefixSumMap) {
        if (node == null) return;

        currSum += node.val;
        res += prefixSumMap.getOrDefault(currSum - targetSum, 0);
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        dfs1(node.left, currSum, targetSum, prefixSumMap);
        dfs1(node.right, currSum, targetSum, prefixSumMap);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);
    }

    // DFS(后序遍历) +前缀和
    public int pathSum3(TreeNode root, int sum) {
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        int currSum = 0;
        prefixSumMap.put(0L, 1);
        return dfs2(root, currSum, sum, prefixSumMap);
    }

    private int dfs2(TreeNode node,
                     long currSum, int target,
                     Map<Long, Integer> prefixSumMap) {
        if (node == null) return 0;

        currSum += node.val;
        int res = prefixSumMap.getOrDefault(currSum - target, 0);
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        res += dfs2(node.left, currSum, target, prefixSumMap);
        res += dfs2(node.right, currSum, target, prefixSumMap);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return res;
    }

    public static void main(String[] args) {
        // test1();
        // test2();
    }
}

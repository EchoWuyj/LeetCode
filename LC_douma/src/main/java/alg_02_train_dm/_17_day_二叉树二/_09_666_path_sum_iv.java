package alg_02_train_dm._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:55
 * @Version 1.0
 */
public class _09_666_path_sum_iv {
    /*
        666. 路径总和 IV
        对于一棵深度小于 5 的树，可以用一组三位十进制整数来表示。
        对于每个整数：
            1. 百位上的数字表示这个节点的深度 D，1 <= D <= 4。
            2. 十位上的数字表示这个节点在当前层所在的位置 P， 1 <= P <= 8。
               位置编号与一棵满二叉树的位置编号相同。
            3.个位上的数字表示这个节点的权值 V，0 <= V <= 9。
        给定一个包含三位整数的升序数组，表示一棵深度小于 5 的二叉树，
        请你返回从根到所有叶子结点的路径之和。

        输入: [113, 215, 221]
        输出: 12
        解释:
            3
           / \
          5   1
        路径和 = (3 + 5) + (3 + 1) = 12
 */
    private static int ans = 0;

    public static int pathSum(int[] nums) {
        // 构建二叉树，使用数组存储，比较方便
        Integer[] tree = new Integer[15]; // 4层，满二叉树一共 15 个节点
        for (int num : nums) {
            // 获取个，十，百位上的数字
            int bai = num / 100;
            int shi = num % 100 / 10;
            int ge = num % 10;
            // 百位数字表示层数
            // 十位数字表示节点在当前层所在的位置
            // 数组索引从 0 开始的，故 2^(level)-1，减 1 不能丢掉
            // KeyPoint 凡是涉及位运算，注意使用 ()，提高优先级
            int index = ((1 << (bai - 1)) - 1) + shi - 1;
            // 个位数字表示节点值
            tree[index] = ge;
        }

        // DFS 遍历树
        dfs(tree, 0, 0);
        return ans;
    }

    // currPathSum：当前递归调用时，路径的累加和
    private static void dfs(Integer[] tree, int index, int currPathSum) {
        // Integer 构建的数组，判空是用 null
        if (tree[index] == null) return;

        currPathSum += tree[index];
        // index = 7 为 4 层第一节点，该层是叶子节点，同时创建的数组大小为 15
        // 最多包括 4 层节点，若访问其左右子节点，则导致索引越界，因此 if 判断需要限制 index >= 7
        if (index >= 7 || (tree[2 * index + 1] == null && tree[2 * index + 2] == null)) {
            ans += currPathSum;
            return;
        }

        dfs(tree, 2 * index + 1, currPathSum);
        dfs(tree, 2 * index + 2, currPathSum);
    }

    public static void main(String[] args) {
        int[] arr = {113, 215, 221};
        System.out.println(pathSum(arr)); // 12
    }
}

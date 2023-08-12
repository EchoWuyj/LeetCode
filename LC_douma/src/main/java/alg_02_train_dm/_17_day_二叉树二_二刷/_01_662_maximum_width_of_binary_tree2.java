package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 21:35
 * @Version 1.0
 */
public class _01_662_maximum_width_of_binary_tree2 {

    // KeyPoint 方法二 DFS
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    // 递归函数含义：返回以 node 为根节点的子树的最大宽度，返回值为 int
    // => 二叉树递归含义：一般都是以 node 为根节点的 xxx，返回为 xxx
    // 递归调用中，通过增加形参方式来传递更多信息
    // 1.level 层数，dfs 不是一层结束再去遍历下一层的，因此遍历每个节点时需要传入 level 信息
    // 2.seqNum 序号
    // 3.dfs 遍历到每个节点，并知道该节点是 start 起始节点或者是 end 结束节点，所以需要传入两个集合
    //   分别记录每层的 start 和 end
    // 注意：本题 dfs 不是简单的先序或者后序，而是两者结合，分别在递和归的过程中进行不同的操作
    private int dfs(TreeNode node, int level, int seqNum,
                    List<Integer> start, List<Integer> end) {

        // 遇到 null，则最大宽度为 0
        if (node == null) return 0;

        // KeyPoint 前序(深入) => 递过程，直到递归边界(最小子问题)
        // level 和 seqNum 的值已经在递归函数中维护好了，关键维护 start 和 end 的值
        // 通过两个 start 和 end 集合，在前序遍历的过程中进行维护

        // level 层的第一个节点
        if (start.size() == level) {
            // start 和 end 集合中，
            // index = 0 => 表示 0 层
            // value => start[0] 和 end[0] 分别记录该层，start 和 end 值
            start.add(seqNum);
            end.add(seqNum);
        } else {
            // 不是 level 层的第一个节点，则只是更新 end 序号即可
            end.set(level, seqNum);
        }

        // 左子树最大宽度
        int left = dfs(node.left, level + 1, 2 * seqNum, start, end);
        // 右子树最大宽度
        int right = dfs(node.right, level + 1, 2 * seqNum + 1, start, end);

        // KeyPoint 后序(回溯) => 归过程，返回递归上层操作逻辑
        // 只有将 子节点为 root 的左右子树 都已经访问完成，维护好 start 和 end 集合之后
        // 才能计算以 root 为当根节点的子树最大宽度
        int cur = end.get(level) - start.get(level) + 1;

        // 明确递归含义，从而进行返回值
        // => node 为根节点的子树的最大宽度
        // => 因为 最大宽度 确定在左子树，右子树，还是本身，故需要比较
        //   当前节点最大宽度，左子树最大宽度，右子树最大宽度，三者取最大值
        return Math.max(cur, Math.max(left, right));
    }
}

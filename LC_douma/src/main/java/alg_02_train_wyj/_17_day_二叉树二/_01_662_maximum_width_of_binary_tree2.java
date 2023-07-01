package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-30 18:51
 * @Version 1.0
 */
public class _01_662_maximum_width_of_binary_tree2 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    public int dfs(TreeNode node, int level, int seqNum,
                   List<Integer> start, List<Integer> end) {
        if (node == null) return 0;

        if (start.size() == level) {
            start.add(seqNum);
            end.add(seqNum);
        } else {
            end.set(level, seqNum);
        }

        int left = dfs(node.left, level + 1, 2 * seqNum, start, end);
        int right = dfs(node.right, level + 1, 2 * seqNum + 1, start, end);

        int cur = end.get(level) - start.get(level) + 1;

        return Math.max(cur, Math.max(left, right));

    }
}

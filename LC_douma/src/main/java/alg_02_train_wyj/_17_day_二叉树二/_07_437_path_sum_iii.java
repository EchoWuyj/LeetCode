package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-02 15:00
 * @Version 1.0
 */
public class _07_437_path_sum_iii {

    public static int pathSum1(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return dfs(root, new ArrayList<>(), targetSum);
    }

    private static int dfs(TreeNode node, List<Long> parentPathSumList, long targetSum) {
        if (node == null) return 0;

        int cnt = 0;
        List<Long> tmp = new ArrayList<>();
        for (int i = 0; i < parentPathSumList.size(); i++) {
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

    public static void main(String[] args) {
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

    public int pathSum(int[] nums, int targetSum) {
        return -1;
    }
}

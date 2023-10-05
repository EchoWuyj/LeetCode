package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;

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

    public static int dfs(TreeNode node, ArrayList<Long> parentPath, int targetSum) {
        if (node == null) return 0;
        int cnt = 0;
        int size = parentPath.size();
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            long num = parentPath.get(i) + node.val;
            list.add(num);
            if (num == targetSum) cnt++;
        }
        if (node.val == targetSum) cnt++;
        list.add((long)node.val);

        int leftCnt = dfs(node.left, list, targetSum);
        int rightCnt = dfs(node.right, list, targetSum);

        return leftCnt + rightCnt + cnt;
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

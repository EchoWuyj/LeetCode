package alg_02_train_wyj._18_二叉树三;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 23:42
 * @Version 1.0
 */
public class _10_501_find_mode_in_binary_search_tree {

    private int prevNum = 0;
    private int count = 0;
    private int maxCount = 0;
    private List<Integer> list = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        int[] res = new int[list.size()];
        int index = 0;
        for (int num : list) {
            res[index++] = num;
        }
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        update(node.val);
        dfs(node.right);
    }

    private void update(int value) {
        if (value == prevNum) {
            count++;
        } else {
            prevNum = value;
            count = 1;
        }
        if (count == maxCount) {
            list.add(value);
        } else if (count > maxCount) {
            list.clear();
            list.add(prevNum);
            maxCount = count;
        }
    }
}

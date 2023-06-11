package alg_02_体系班_wyj.class11;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-10-07 15:24
 * @Version 1.0
 */
public class Code06_SuccessorNode02 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        ArrayList<TreeNode> list = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    list.add(root);
                    root = root.right;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).val == target.val) {
                if (i == list.size() - 1) {
                    return null;
                } else {
                    return list.get(i + 1);
                }
            }
        }

        return null;
    }
}

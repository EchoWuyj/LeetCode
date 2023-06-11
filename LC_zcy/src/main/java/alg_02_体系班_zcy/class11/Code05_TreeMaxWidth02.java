package alg_02_体系班_zcy.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-10-05 18:43
 * @Version 1.0
 */
public class Code05_TreeMaxWidth02 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 最开始为只有一个根节点情况,故maxWidth为1
    public int maxWidth = 1;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> indexList = new LinkedList<>();
        // root节点所需要的信息
        queue.add(root);
        indexList.add(1);
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            int index = indexList.removeFirst();
            if (node.left != null) {
                queue.add(node.left);
                indexList.add(2 * index);
            }
            if (node.right != null) {
                queue.add(node.right);
                indexList.add(2 * index + 1);
            }
            // size减成0,则表示该层结束了,要开始下一层了
            if (size == 0) {
                // indexList至少得有2个元素以上,这样最后一层才算进去
                if (indexList.size() >= 2) {
                    maxWidth = Math.max(maxWidth, (indexList.getLast() - indexList.getFirst() + 1));
                }
                // size表示队列中元素的个数
                size = queue.size();
            }
        }
        return maxWidth;
    }
}

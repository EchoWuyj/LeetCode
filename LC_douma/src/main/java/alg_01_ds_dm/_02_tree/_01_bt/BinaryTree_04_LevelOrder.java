package alg_01_ds_dm._02_tree._01_bt;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 15:09
 * @Version 1.0
 */
public class BinaryTree_04_LevelOrder {

    // KeyPoint 4 层序遍历
    // 层序遍历
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> levelOrder(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        // 使用 LinkedList 实现的队列
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        // KeyPoint Queue 接口中没有 enqueue 和 dequeue 方法
        //          故相应的使用 offer 和 poll 代替，栈是 push 和 pop 以及 peek
        queue.offer(root);
        // 队列不为空，处理每一个节点
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一个节点
            TreeNode<Integer> curr = queue.poll();
            // 加入 res 中
            res.add(curr.data);
            // 将遍历处理的节点的左，右子节点入队，等到后序的处理
            // 加入队列的节点必然不能是 null，因为后面还要从队列中出队
            // 且要调用 cur.data，否则空指针异常
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return res;
    }

    // 层序遍历 => 将每层节点分组存储
    public static List<List<Integer>> levelOrder_2(TreeNode<Integer> root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 在出队之前先计算队列中元素个数，用于处理一层循环的次数
            int size = queue.size();
            // 当层的结果集
            List<Integer> levelNodes = new ArrayList<>();
            // KeyPoint 循环每层所有节点，将其左右子节点加入队列
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> curr = queue.poll();
                levelNodes.add(curr.data);
                // 将遍历处理的节点的左右子节点入队，等到后序的处理
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // 每层处理完，将其加入结果集合 res
            res.add(levelNodes);
        }
        return res;
    }
}

package _03_binary_tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-08-25 18:13
 * @Version 1.0
 */
public class Offer_32_LevelOrder_03 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // KeyPoint 需求:
        //      即第一行按照从左到右的顺序打印，
        //      第二层按照从右到左的顺序打印，
        //      第三行再按照从左到右的顺序打印，其他行以此类推。

        // 设置 res 用来保存输出结果
        // KeyPoint 注意使用 List<List<Integer>> 来接受
        List<List<Integer>> res = new LinkedList<>();

        // 边界情况处理
        if (root == null) return res;

        // 设置一个队列，用来存储二叉树中的元素
        Queue<TreeNode> queue = new LinkedList<>();

        // 队列添加二叉树的根节点
        queue.add(root);

        // KeyPoint 用来判断当前的层数是否为奇数层，初始化在第 0 层，为偶数层
        boolean isOddNumber = false;

        // 遍历队列，直到队列为空，说明访问了二叉树中所有的节点
        while (!queue.isEmpty()) {

            // 用来记录 queue 的长度，即每层节点的个数
            int size = queue.size();

            // 奇偶层总是交替出现的，通过取反操作，判断当前的层数是否为奇偶层
            // 由于 isOddNumber 初始化为 false，所以第一次进来这个 while 循环取反后为 true
            // 符合第一层是奇数层的定义
            isOddNumber = !isOddNumber;

            // 生成一个双端队列 temp，用来保存每一层节点，保存成功后添加到 res 中
            LinkedList<Integer> temp = new LinkedList<>();

            // 使用 for 循环，将 queue 中的元素按照给定的规则添加的 temp 中
            for (int i = 0; i < size; i++) {
                // 从 queue 中取出一个节点
                TreeNode node = queue.poll();

                // 如果是奇数层，那么按顺序添加到双端队列的尾部
                if (isOddNumber) {
                    temp.addLast(node.val);
                } else {
                    temp.addFirst(node.val);
                }

                // 判断当前节点的左子节点是否有值，如果有，则添加到 queue 中
                if (node.left != null) {
                    queue.add(node.left);
                }

                // 判断当前节点的右子节点是否有值，如果有，则添加到 queue 中
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // 把存放了每一层元素的数组 temp 添加到 res 中
            res.add(temp);
        }

        // 返回 res
        return res;


    }
}

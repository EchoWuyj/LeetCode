package _03_binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-08-25 15:29
 * @Version 1.0
 */
public class Offer_32_LevelOrder_02 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 设置 res 用来保存输出结果(模拟二维数组)
        // KeyPoint 注意事项
        //  1)List是interface,不能是直接实例化,得使用LinkedList或者ArrayList
        //  2)前面定义了泛型,后面就不需要再去定义了
        List<List<Integer>> res = new LinkedList<>();
        // 边界情况处理
        if (root == null) return res;

        // 设置一个队列,用来存储二叉树中的元素
        // KeyPoint 一般都是使用LinkedList来模拟Queue
        Queue<TreeNode> queue = new LinkedList<>();

        // 队列添加二叉树的根节点
        queue.add(root);

        // 遍历队列,直到队列为空,说明访问了二叉树中所有的节点
        while (!queue.isEmpty()) {
            // 用来记录 queue 的长度,即每层节点的个数
            // 为了方便后续for循环,将queue中数据添加到temp中
            int size = queue.size();

            // 用来保存每一层节点,保存成功后添加到 res 中
            // KeyPoint 每次for循环都是new一个新的ArrayList,避免混淆
            ArrayList<Integer> temp = new ArrayList<>();

            // 使用 for 循环,将 queue 中的元素添加的 temp 中
            for (int i = 0; i < size; i++) {
                // 从 queue 中取出一个节点
                TreeNode node = queue.poll();
                // 把节点存放到 list 中
                temp.add(node.val);

                // 判断当前节点的左子节点是否有值,如果有,则添加到 queue 中
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            res.add(temp);
        }

        return res;
    }
}
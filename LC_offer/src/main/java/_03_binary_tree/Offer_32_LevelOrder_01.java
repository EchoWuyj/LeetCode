package _03_binary_tree;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2022-08-25 14:22
 * @Version 1.0
 */
public class Offer_32_LevelOrder_01 {
    public static void main(String[] args) {
        test();
    }

    public int[] levelOrder(TreeNode root) {
        // KeyPoint
        //  问题:从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印(层次遍历)
        //  核心:通过队列来辅助实现

        // 根节点为空的情况返回空数组
        if (root == null) return new int[0];

        // 生成一个队列，用来保存节点
        // KeyPoint 队列中的数据类型是TreeNode类型,而不是Integer
        //      前面的数据类型是Queue,而不是LinkedList,父类引用指向子类对象
        Queue<TreeNode> queue = new LinkedList<>();

        // 生成一个 list，用来保存输出的节点
        ArrayList<Integer> list = new ArrayList<>();

        // 首先让根节点入队
        queue.add(root);

        // 遍历队列，直到队列为空
        while (!queue.isEmpty()) {
            // 获取队列的头部元素(有返回值)
            TreeNode node = queue.poll();

            // 把结点值存放到 list 中
            list.add(node.val);

            // 判断该节点是否有左右子节点
            // 如果左子节点有值，则把左子节点加入到队列中
            if (node.left != null) {
                queue.add(node.left);
            }

            // 如果右子节点有值，则把右子节点加入到队列中
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void test() {

        // KeyPoint 补充:数组转集合
        // (1) 遍历集合, 将元素保存到数组中
        int[] arr = {1, 2, 3};
        List<Integer> integers = new ArrayList<Integer>();
        for (int n : arr) {
            integers.add(n);
        }
        System.out.println(integers); // [1, 2, 3]

        //  (2)使用asList()方法
        Integer[] array = {1, 2, 3};
        List asList = Arrays.asList(array);
        System.out.println(asList.toString()); // [1, 2, 3]
    }
}

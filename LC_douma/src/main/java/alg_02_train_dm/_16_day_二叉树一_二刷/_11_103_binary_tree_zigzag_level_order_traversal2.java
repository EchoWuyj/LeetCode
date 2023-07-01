package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 21:24
 * @Version 1.0
 */
public class _11_103_binary_tree_zigzag_level_order_traversal2 {

    // KeyPoint 方法二  层次遍历 => DFS 前序遍历-递归 实现
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> res) {
        if (node == null) return;

        // 前序遍历 => 处理当前遍历节点逻辑在递归之前
        // 处理当前遍历的节点
        if (res.size() == level) {

            List<Integer> levelList = new LinkedList<>();
            res.add(levelList);

            // 补充说明：
            // 1.使用父类引用 List 接受，则 res.get(level) 获取集合类型是 List
            //   => List<Integer> levelList = res.get(level);
            //   无法调用 LinkedList 对象 addFirst 方法，需要向下强转
            //   => (LinkedList<Integer>) res.get(level);
            // 2.向下转型 LinkedList 需要加上 <Integer>，否则报错 Raw use of parameterized class
            //   同时，必须先向下强转，生成变量，再去调用 addFirst 方法
            //   => (LinkedList<Integer>) res.get(level).addFirst 不行

            // 注意事项：
            // 在主方法中，返回值类型 List<List<Integer>>，则定义 res 的父类引用得是 List<List<Integer>>
            // 不能将其 替换成 List<LinkedList<Integer>> res
            // 一般情况，创建对象时显示申明 LinkedList<Integer> levelList = new LinkedList<>();

        }

        // 注意：if 条件中变量 levelList，只是在 if 条件中生效，不能被 if 之外使用
        //       同时 if 之外定义变量名可以是 levelList，两者互不干扰

        // 获取当前层的结果集
        LinkedList<Integer> levelList = (LinkedList<Integer>) res.get(level);

        // level 是从 0 开始的，故从左往右添加
        if (level % 2 == 0) levelList.add(node.val);
        else levelList.addFirst(node.val);

        dfs(node.left, level + 1, res);
        dfs(node.right, level + 1, res);
    }
}

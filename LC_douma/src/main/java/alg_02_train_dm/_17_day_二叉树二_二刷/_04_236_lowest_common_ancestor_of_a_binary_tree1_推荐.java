package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:52
 * @Version 1.0
 */
public class _04_236_lowest_common_ancestor_of_a_binary_tree1_推荐 {
      /*
          236. 二叉树的最近公共祖先
          给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

          最近公共祖先的定义为：
          对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
          满足 x 是 p、q 的祖先且 x 的深度尽可能大(一个节点也可以是它自己的祖先)

              3
             / \
            5   1
           /\   /\
          6 2  0 8
           / \
          7   4

          节点 7 的祖先：7，2，5，3

          输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
          输出：3
          解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

          提示
          树中节点数目在范围 [2, 10^5] 内。
          -10^9 <= Node.val <= 10^9
          所有 Node.val 互不相同
           p != q
           p 和 q 均存在于给定的二叉树中。
     */

    // KeyPoint 方法一 Map 映射 => 根据节点与父亲节点关系来实现
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 维护子节点与其对应父节点的关系
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        // 前序遍历
        // => 记录完整二叉树中所有子节点与其对应父节点的关系
        dfs(root, parentMap);

        // 用于记录 p 依次访问它的祖先的容器
        Set<Integer> visited = new HashSet<>();

        // 2. 从节点 p 开始，依次访问它的祖先
        while (p != null) {
            // 一个节点也可以是它自己的祖先，故也是需要将其放入到 HashSet 中的
            visited.add(p.val);
            // 获取 p 节点的父节点
            p = parentMap.get(p.val);
        }

        // 3. 从节点 q 开始，依次访问它的祖先
        // 如果第一次遇到了 p 的祖先的话，那么这个祖先就是最近的公共祖先
        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = parentMap.get(q.val);
        }

        // while 循环结束，没有找到公共祖先，直接返回 null
        return null;
    }

    // 前序遍历 => 记录完整二叉树中所有子节点与其对应父节点的关系
    private void dfs(TreeNode node, Map<Integer, TreeNode> parentMap) {
        if (node == null) return;

        // 记录子节点与其对应父节点的关系
        // 调用 .val 之前，判空
        if (node.left != null) parentMap.put(node.left.val, node);
        dfs(node.left, parentMap);

        if (node.right != null) parentMap.put(node.right.val, node);
        dfs(node.right, parentMap);

        // dfs(root.left, parentMap.put(root.left.val, root)); 错误写法
        // map.put 是存在返回值的

        // KeyPoint 补充说明：map.put 返回值
        // 1.若 Map 之前不存在具有相同键的映射，则返回 null
        //   表示之前没有与给定键相关联的值，通常表示成功地将键值对添加到 Map 中

        // 2.若 Map 之前已经存在具有相同键的映射，则返回先前与该键相关联的值
        //   然后将新值与键相关联，通常表示对现有的键进行了更新操作。
    }
}

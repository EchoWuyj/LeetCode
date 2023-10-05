package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:51
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list1 {
      /*
        114. 二叉树展开为链表
        给你二叉树的根结点 root，请你将它展开为一个单链表

        1. 展开后的单链表应该同样使用 TreeNode
           其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
        2. 展开后的单链表应该与二叉树 先序遍历 顺序相同。

                   1               1
                 ↙ ↘            ↙ ↘
                2     5    =>         2
              ↙ ↘    ↘           ↙  ↘
             3    4     6                 3
                                       ↙   ↘
                                             4
                                           ↙  ↘
                                                5
                                               ↙ ↘
                                                    6
                                                  ↙  ↘


        输入：root = [1,2,5,3,4,null,6]
        输出：[1,null,2,null,3,null,4,null,5,null,6]

        提示：
        树中结点数在范围 [0, 2000] 内
        -100 <= Node.val <= 100

        进阶：你可以使用原地算法[O(1)额外空间]展开这棵树吗？
     */

    // KeyPoint 方法一 DFS => 遍历两边
    // 先前序遍历，得到结果，在串联成链表 => 原链表节点上修改
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        // 遍历得到结果集
        preOrder(root, list);

        // KeyPoint 原链表节点上修改
        // list 存储的是 TreeNode，其中每个 TreeNode 中，left 和 right 指针都是有指向的
        // 故 left 和 right 都需要相应调整
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // KeyPoint 方法一 DFS 另外一种实现 => 遍历两边
    // 先序并记录 val，再挨个创建节点
    public void flatten2(TreeNode root) {
        if (root == null) return;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int size = list.size();
        root.left = null;
        root.right = null;

        // 串联指针
        TreeNode cur = root;

        // KeyPoint 易错点
        // 若通过新创建节点方式，将二叉树展开成单链表
        // 则要将 root 节点原始 val,left,right 信息全部覆盖
        // 避免原始链表信息干扰展开后的单链表
        // 因为本题返回值为 void，测试用例只能通过传入 root 来判断
        // 否则，输出结果还是原始二叉树结构

        root.val = list.get(0);
        root.left = null;
        root.right = null;

        for (int i = 1; i < size; i++) {
            // 串联过程避免断链，需要遍历串联指针 cur
            TreeNode next = new TreeNode(list.get(i));
            cur.right = next;
            cur = next;
        }

        // KeyPoint 代码经验
        // 若代码出现 bug，和手动模拟实现不一样
        // 则按照缩写代码，手动模拟，看看代码那里有问题
        // 不要蜜汁自信，觉得自己代码没有问题，从而浪费时间
        // 核心：快速高效，解决 bug 能力
    }

    public static void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }
}

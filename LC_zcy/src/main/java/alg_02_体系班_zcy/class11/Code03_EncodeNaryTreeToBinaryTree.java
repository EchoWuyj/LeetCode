package alg_02_体系班_zcy.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:09
 * @Version 1.0
 */

// 本题测试链接：https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree

public class Code03_EncodeNaryTreeToBinaryTree {

    // 设计一个算法,可以将N叉树编码为二叉树,并能将该二叉树解码为原N叉树(具体的编解码的方法无所谓)

    // 1)首先想一下这道题的难点是什么,N叉树的特点的每个结点最多有N个子结点,而二叉树的每个结点最多只能有两个子结点
    //   那么多余的子结点怎么办,当然只能继续子结点下继续累加,就像泡泡龙游戏一样,一个挂一个的
    // 2)对于当前结点root的N个子结点的处理办法:
    //   a.将第一个结点挂到二叉树的左子结点上,然后将后面的结点依次挂到右子结点,和右子结点的右子结点上
    //   b.这样做的好处是,同一层的子结点都挂在右子结点上,而这些子结点自己的子结点都会挂在左子结点上

    // 解题思路:
    // 在多叉树中,让某一节点X的所有孩子,一律放在左树的右边界上
    // 每一颗多叉数都可以这样转,同时形式唯一,并且很好转回去

    //
    //          a                     a
    //    ↙    ↓   ↘   序列化      ↙
    //   b      c    d   ==>       b
    // ↙↓↘   ↙↘       <==     ↙  ↘
    // e f g  i   k    反序列化  e       c
    //                          ↘     ↙↘
    //                           f   i    d
    //                            ↘  ↘
    //                              g   k

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Codec {

        // 序列化:多叉数转二叉树
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            // 多叉数头节点,也是二叉树头节点
            TreeNode head = new TreeNode(root.val);
            // 所有孩子往左树的右边界上挂
            // 递归函数返回的是整颗子树的头节点,使用head.left接住
            head.left = en(root.children);
            return head;
        }

        // 递归函数
        // 总的操作:
        //  1)一开始向左到底依次建立TreeNode节点,直到遇到null,返回上层节点
        //  2)再逐个向右判断children中的每个孩子节点,是否能向左到底依次建立节点,再返回
        //  3)依次这样判断
        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            // KeyPoint 注意事项
            //  1)多叉树没有孩子节点,按照题目意思,则list为空,但是list!=null
            //    但在Java中,没有赋值的属性,则为null,集合也是这样
            //  2)集合对象不为null,但是集合里面为空,用foreach遍历,不会进入foreach方法,则直接返回head
            //  3)多叉树子节点没有左中右之分,不管遍历的是那种顺序都是可以
            // b,c,d(第一层递归)
            for (Node child : children) {
                // 二叉树节点
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    // 孩子节点中第一个
                    head = tNode;
                } else {
                    // 后续的多叉树的孩子往右边挂
                    // 后面代码使用cur记录当前节点,递归返回时能到该位置
                    cur.right = tNode;
                }
                // 使用cur记录当前节点,为了深度优先遍历
                cur = tNode;
                // 深度优先遍历,使用cur.left指针,用来接住返回的头节点
                // child.children为当前节点的孩子节点list
                cur.left = en(child.children); // e,f,g(第二层递归)
            }
            // 只有在for循环结束之后,才会执行return
            return head;
        }

        // 反序列化:二叉树转多叉数
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            // 左树的右孩子就是root的children
            return new Node(root.val, de(root.left));
        }

        // 思路:序列化是深度优先遍历,反序列化过程也是需要深度优先遍历
        // 总的操作:
        //  1)一开始向左到底依次建立节点Node,直到遇到null,创建好本层children集合(空集合)
        //    返回上层节点,将此时cur节点加入上层的children中
        //  2)再逐个向右判断每个兄弟节点,是否能向左到底依次建立节点Node,再返回
        //  3)依次这样判断
        public List<Node> de(TreeNode root) {
            // 即使遍历过程遇到叶子节点,也是会new一个ArrayList作为children的,不会使得children直接为null
            List<Node> children = new ArrayList<>();
            while (root != null) {
                // root是长兄,需要将所有兄弟们搞成一个链表,返回给上游的父亲
                Node cur = new Node(root.val, de(root.left));
                children.add(cur);
                // root兄弟都在右侧,所以root不断往右移动,同时将兄弟节点计入children中
                root = root.right;
            }
            return children;
        }
    }
}

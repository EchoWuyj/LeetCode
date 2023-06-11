package alg_01_新手班_zcy.class06;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 19:37
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal

public class Code05_LeetCode_105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /*
        从前序与中序遍历序列构造二叉树,给定两个整数数组preorder和inorder
        其中preorder是二叉树的先序遍历,inorder是同一棵树的中序遍历,其中节点是没有重复值的
        请构造二叉树并返回其根节点

        有一棵树,先序结果是pre[L1...R1],中序结果是in[L2...R2],请建出整棵树返回头节点
        preorder = [3,9,20,15,7];inorder = [9,3,15,20,7]
        根节点为3,则中序数组inorder中的3以左为左子树(9),3以右为右子树(15,20,7) => 左根右
        对应preorder中则是3后面等长度范围为(9)用来构建左子树,剩余的(20,15,7)构建右子树 => 根左右


     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // KeyPoint 实现方式一(递归实现)
    // 返回最后的root节点,所以数据类型是TreeNode
    public static TreeNode buildTree(int[] pre, int[] in) {
        // 边界条件
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        // KeyPoint 构建递归函数需要从宏观角度思考,不能受限于局部细节
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    // 功能:根据pre的[L1..R1]和in的[L2..R2]建立整颗树并将头节点返回
    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {

        // 保证[L1..R1]是个有效的范围,左边界到右边界应该是从小到大的
        // 若L1>R1则表示遇到空树,就应该返回null
        if (L1 > R1) {
            return null;
        }

        // 斜树
        //  1
        //    2
        //      3
        // pre[1,2,3],root=1,in[1,2,3] 此时in中1的左侧没有元素,find=L2
        // 代入到head.left递归函数f中L1+1,L1+find-L2,则L1+1>L1,满足L1>R1

        // pre是先序序列,其L1位置就是根节点,故可以 new TreeNode(pre[L1])
        // 整棵树的根节点,需要单独定义出来,因为后面的左右子树,需要通过head.right和head.left定义
        TreeNode head = new TreeNode(pre[L1]);

        // 全树就一个节点,直接返回head
        if (L1 == R1) {
            return head;
        }

        // find从in数组最左侧开始,故find=l2
        int find = L2;

        // 根据pre[L1]值,在in数组中找对应索引
        // 不知道循环次数,则使用while实现
        while (in[find] != pre[L1]) {
            find++;
        }

        //  数字表示位置,不是数值
        //  先序  5  | 6  7   8|          9           10
        //       L1  L1+1 L1+find-L2  L1+find-L2+1   R1

        //  中序 |13 14 15|    16     17      18
        //       L2   find-1  find  find+1   R2

        // KeyPoint 编程小技巧
        // 对于完全抽象化的字母公式,通过具体的例子进行验证
        // 通过假设具体的例子,这样比较稳,避免调试代码,比较节省时间

        // 假设find在16的位置
        // in左子树元素范围,13-15,使用索引计算:13-15,对应抽象字母:find-L2
        // 故pre中左子树范围:L1+find-L2

        // 确定左子树
        // 一颗子树的确定需要pre和in两个数组中序列才能唯一确定其结构
        // 先序序列一段+中序序列一段 => 左子树
        // KeyPoint pre,in数组两端范围是不包括根节点的,纯是左右子树的范围
        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);

        // 确定右子树
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);

        return head;
    }

    // KeyPoint 实现方式二(使用HashMap优化,使用空间换时间)
    // 找find过程是遍历的,一开始使用HashMap对in数组中数字和位置进行映射,后续找find就不需要再去遍历找了
    public static TreeNode buildTree2(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }

        // 最开始将中序数组中的每个元素的位置将其存到HashMap中,之后从HashMap中进行查询
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        // KeyPoint in.length不需要再减1
        for (int i = 0; i < in.length; i++) {
            // 方便后续通过value,获取值在数组中对应的索引位置
            valueIndexMap.put(in[i], i);
        }
        return g(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }

    public static TreeNode g(int[] pre, int L1, int R1, int[] in, int L2, int R2,
                             HashMap<Integer, Integer> valueIndexMap) {

        if (L1 > R1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }

        int find = valueIndexMap.get(pre[L1]);

        // find-L2已经代表界限的长度,若从L1开始加,则直接加上find-L2即可,不用再减1
        head.left = g(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
        head.right = g(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);

        return head;

        // KeyPoint 时间复杂度 O(N)
        // 每次进入递归函数,都会new一个节点,一共多少个N节点,g函数就被调用N次
        // 同时每次在递归函数进行map的get操作,时间复杂度都是O(1),最终N*O(1),则总的时间复杂度为O(N)
    }
}






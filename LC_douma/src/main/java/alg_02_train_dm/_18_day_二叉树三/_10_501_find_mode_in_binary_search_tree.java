package alg_02_train_dm._18_day_二叉树三;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:10
 * @Version 1.0
 */
public class _10_501_find_mode_in_binary_search_tree {


     /*
        501. 二叉搜索树中的众数
        给定一个有相同值的二叉搜索树(BST)，找出 BST 中的所有众数(出现频率最高的元素)。
        给定 BST [1,null,2,2]

             1
            / \
         null  2
              /
             2

        返回：[2]

        提示：如果众数超过 1 个，不需考虑输出顺序
        进阶：你可以不使用额外的空间吗？(假设由递归产生的隐式调用栈的开销不被计算在内)
              => 可以使用递归
     */

    // KeyPoint 方法一 前序遍历 + Map 映射
    private int maxCnt = 0;

    public int[] findMode1(TreeNode root) {
        if (root == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        preorder(root, map);
        // KeyPoint 注意事项
        // Entry 加上泛型 <Integer, Integer>，否则 getValue()和 getKey()
        // 返回的数据类型是 Object，后续无法直接比较
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCnt) {
                list.add(entry.getKey());
            }
        }
        int size = list.size();
        int[] res = new int[size];
        int index = 0;
        for (int num : list) {
            res[index++] = num;
        }
        return res;
    }

    // 前序遍历
    private void preorder(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) return;
        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        maxCnt = Math.max(map.get(node.val), maxCnt);
        preorder(node.left, map);
        preorder(node.right, map);
    }

    // 前一个数字
    private int prevNum = 0;
    // 前一个数字出现次数
    private int count = 0;
    // 最多出现次数
    private int maxCount = 0;
    private List<Integer> list = new ArrayList<>();

    // KeyPoint 方法二 中序遍历
    // BST 性质：左 < 中 < 右 => 相同的值紧挨在一起
    public int[] findMode2(TreeNode root) {

        dfs(root);
        int[] res = new int[list.size()];
        int index = 0;
        for (int num : list) {
            res[index++] = num;
        }
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        // 一边遍历，一边更新
        update(node.val);
        dfs(node.right);
    }

    private void update(int val) {

        // 通过几个变量实现，在有序数组中，找到出现次数最多的元素，节省额外的空间
        // => 通过一轮中序遍历，相同的值紧挨在一起，方便找到次数最多的元素

        // 中序遍历 -1 -1 1 1 2 3 3 3 9
        //             ↑
        //          i

        // prevNum = -1
        // count = 2
        // maxCount = 2

        // 中序遍历 -1 -1 1 1 2 3 3 3 9
        //                 ↑
        //                 i

        // prevNum = 1
        // count = 2
        // maxCount = 2

        // 中序遍历 -1 -1 1 1 2 3 3 3 9
        //                         ↑
        //                 i

        // prevNum = 3
        // count = 3
        // maxCount = 3

        if (val == prevNum) {
            count++;
        } else {
            // 新的值，重置 prevNum
            prevNum = val;
            count = 1;
        }

        if (count == maxCount) {
            list.add(val);
        } else if (count > maxCount) {
            // 清除集合 list，记住该 API
            list.clear();
            list.add(val);
            maxCount = count;
        }
    }
}

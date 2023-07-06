package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:09
 * @Version 1.0
 */
public class _03_46_Permutations1 {

    /*
        46 全排列
        从 n 个不同元素中任取 m （m≤n）个元素，按照一定的顺序排列起来
        叫做从 n 个不同元素中取出 m 个元素的一个排列。
        当 m = n 时所有的排列情况叫全排列。
        给定一串无重复的字符或数字，输出其有所有排列，并统计其共有排列个数

        1 2 3 全排列 => 每个元素只能选用一次
                     => 每个元素次序没有要求
        123  132
        213  231
        321  312

        全排列时间复杂度 O(n!*n^2)

        提示：
        1 <= nums.length <= 6
        -10 <= nums[i] <= 10
        nums 中的所有整数 互不相同
     */

    // 非全排列代码
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // root 一开始为 -1
        dfs(nums, -1, path, res);
        return res;
    }

    // KeyPoint 根据 01_112_PathSum 代码修改而来，非回溯常规解法，理解即可
    // 递归函数，形参需要传入 index，用于遍历数组中的元素
    // 同时每次递归过程，index 也是在不断变化
    private void dfs(int[] nums, int index,
                     List<Integer> path,
                     List<List<Integer>> res) {

        // 递归边界，返回上一层，类比树的 dfs 写法
        // 注意：这里不是使用 index 控制递归边界
        // 递归边界，后续有优化，因为 path.size() == nums.length 判断了两次
        if (path.size() == nums.length) return;

        // 递的过程，执行操作
        // index = -1 表示 root，而 root 是不考虑在内，将其排除
        if (index != -1) path.add(nums[index]);

        // 因为 path.add 缘故，导致其比上面 if 更早到达递归边界
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }

        // 遍历多叉树分支，类似于图顶点的遍历
        // 每层分支都是数组 nums 全部索引 [0,1,2] 对应节点，依次进行循环遍历
        for (int i = 0; i < nums.length; i++) {
            // 叶子节点下面还有三个递归边界的判断
            // 即执行 3次 path.size() == nums.length
            dfs(nums, i, path, res);
        }

        // 回溯的过程中，将当前的节点从 path 中删除
        // 最终回溯，此时 path.size()=0，则 path.size()-1 = -1，remove(-1) 越界
        // 加上 if 判断，避免越界
        if (index != -1) path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations1().permute(new int[]{1, 2, 3}));

        // 每个元素可以重复使用，每次有 3 种选择，一共有 3 次选择，
        // 故一共 3 * 3 * 3 = 27 种情况，注意：这并不是全排列的组合

        // [[1, 1, 1], [1, 1, 2], [1, 1, 3],
        //  [1, 2, 1], [1, 2, 2], [1, 2, 3],
        //  [1, 3, 1], [1, 3, 2], [1, 3, 3],
        //  [2, 1, 1], [2, 1, 2], [2, 1, 3],
        //  [2, 2, 1], [2, 2, 2], [2, 2, 3],
        //  [2, 3, 1], [2, 3, 2], [2, 3, 3],
        //  [3, 1, 1], [3, 1, 2], [3, 1, 3],
        //  [3, 2, 1], [3, 2, 2], [3, 2, 3],
        //  [3, 3, 1], [3, 3, 2], [3, 3, 3]]
    }
}

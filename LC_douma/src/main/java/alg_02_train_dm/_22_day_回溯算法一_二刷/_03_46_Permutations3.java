package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:10
 * @Version 1.0
 */
public class _03_46_Permutations3 {

    // 时间复杂度：dfs 调用的次数 * dfs 自身时间复杂度
    // 1.dfs 调用的次数
    //   1 + A(n,1) + ... + A(n,n-1) < 1 + 2*n!，关于 n! 数量级
    // 2.dfs 自身时间复杂度
    //   O(n^2)
    // => O(n!*n^2)，一共有 n! 个节点，每个节点 O(n^2)

    // 一般回溯算法解决问题，时间复杂度都是挺高的，因为整体是穷举的过程
    // 后面有些题目，可以通过贪心，动态规划来优化，降低其复杂度

    // 补充：排列计算公式
    // A(n,m) = n! / (n-m)! => 大值上 / 作差在下
    // A(4,2) = 4! / 2! = 4 * 3 = 12
    // A(n,m) 需要按照一定顺序

    // 全排列代码
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // dfs(nums, path, res);
        dfs1(nums, path, res);
        return res;
    }

    // 一次 dfs O(n^2)
    private void dfs(int[] nums,
                     List<Integer> path,
                     List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        // for 循环处理一层的节点(分支)
        // O(n^2)
        for (int i = 0; i < nums.length; i++) { // O(n)
            // 剪枝 => 每次遍历节点，判断是否为重复使用的数字
            //      => 若出现相同的数字，跳过当前的 dfs，进行下一轮 dfs
            // 注意：剪枝一定要终止 dfs，不能继续 dfs，否则会出现：递归死循环的代码 => 详见：dfs1
            if (path.contains(nums[i])) continue; // O(n)
            path.add(nums[i]);
            dfs(nums, path, res);
            // 回溯的过程中，将当前的节点从 path 中删除
            path.remove(path.size() - 1);
        }
    }

    /*
        回溯套路
        1.将问题抽取成多叉树
        2.定义触发条件，将符合条件的结果存在结果集
        3.for 循环，穷举所有结果
        4.穷举过程中剪枝，排除不要结果

        伪代码
        def backtrack(路径, 选择列表):
        if 满足结束条件:
            result.add(路径)
            return

        for 选择 in 选择列表:
            做选择
            backtrack(路径, 选择列表)
            撤销选择
     */

    private void dfs1(int[] nums,
                      List<Integer> path,
                      List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // KeyPoint 递归死循环的写法，最终导致 StackOverflowError
            // 当 path 加入 nums[0] 后，即使 if 判断条件不成立，后续 dfs 递归代码还是进行的
            // 导致每次 int i 从 0 开始，无限递归，最终导致 StackOverflowError
            if (!path.contains(nums[i])) {
                path.add(nums[i]);
            }
            dfs1(nums, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations3().permute(new int[]{1, 2, 3}));
        // [[1, 2, 3], [1, 3, 2], [2, 1, 3],
        // [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }
}

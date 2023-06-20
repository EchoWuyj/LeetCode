package alg_02_train_dm._27_day_动态规划二;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 13:30
 * @Version 1.0
 */
public class _10_494_target_sum1 {
     /*
        494.目标和
        给你一个整数数组 nums 和一个整数 target
        向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式

        例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，
        然后串联起来得到表达式 "+2-1 = 1" 。
        返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

        示例 1：
        输入：nums = [1,1,1,1,1], target = 3
        输出：5
        解释：一共有 5 种方法让最终目标和为 3 。
        -1 + 1 + 1 + 1 + 1 = 3
        +1 - 1 + 1 + 1 + 1 = 3
        +1 + 1 - 1 + 1 + 1 = 3
        +1 + 1 + 1 - 1 + 1 = 3
        +1 + 1 + 1 + 1 - 1 = 3

        提示：
        1 <= nums.length <= 20
        0 <= nums[i] <= 1000
        0 <= sum(nums[i]) <= 1000
        -1000 <= target <= 1000



     */

    // KeyPoint 方法一 DFS 解法 (先序) => 比较容易想到
    // 1 <= nums.length <= 20 => 回溯必然能通过
    private int res = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return res;
    }

    // 前序 dfs，一般只有后序 dfs + 记忆化搜索
    // => 后序遍历，每个节点是有返回值，可以通过记忆化搜索避免重复计算
    // => 可以考虑使用：DFS (后序) + 记忆化搜索 优化
    private void dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) res++;
            return;
        }

        // 二叉树，两个分支，对应 '+' 和 '-'
        // 区别 for 循环多叉树，想清楚什么时候使用：二叉树 和 多叉树
        dfs(nums, target, index + 1, sum + nums[index]);
        dfs(nums, target, index + 1, sum - nums[index]);
    }

    // 另外一种方式：前序 dfs
    public void dfs(int[] nums, int index, int target) {
        if (index == nums.length) {
            if (target == 0) res++;
            return;
        }

        dfs(nums, index + 1, target - nums[index]);
        dfs(nums, index + 1, target + nums[index]);
    }

    // KeyPoint 方法二 DFS 解法 (后序) + 记忆化搜索
    public int findTargetSumWays1(int[] nums, int target) {

        // memo 存储是：树形结构中每个节点的状态值 => 通过简单数据集，画出树形结构，从而分析节点状态 => 分析重复值
        // 本题中，不管是 target 或者 index 都无法唯一标识节点，而是 [target,index] 联合表示一个节点
        // 故 [target,index] 数组 整体作为 HashMap (memo) 的 key，value 作为组数和

        // 在 Java 中，HashMap 的键(key)必须是可哈希的对象，即必须实现了 hashCode() 和 equals() 方法
        // 而数组(Array)是一个对象，所以你可以将数组作为 HashMap 的键
        // 但是，数组默认 hashCode() 方法实现是基于其引用的，而不是基于其内容的。
        // 若直接将数组作为 HashMap 的键，它们的哈希码将基于其内存地址，而不考虑数组中的元素。

        HashMap<String, Integer> map = new HashMap<>();
        return dfs1(nums, 0, target, map);
    }

    private int dfs1(int[] nums, int index, int target, HashMap<String, Integer> map) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }
        // 将数组转成 String，作为 map 的 key，而不是直接使用数组作为 map 的 key
        String key = Arrays.toString(new int[]{target, index});

        if (map.containsKey(key)) return map.get(key);
        int left = dfs1(nums, index + 1, target - nums[index], map);
        int right = dfs1(nums, index + 1, target + nums[index], map);

        map.put(key, left + right);
        return map.get(key);
    }
}

package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:36
 * @Version 1.0
 */
public class _13_456_132_pattern {

    /*
        leetcode 456 号算法题：132 模式
        给你一个整数数组 nums ，数组中共有 n 个整数。
        132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
        并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
        如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。

        解释：132 模式
              i < j < k
              ↑   ↑   ↑
              1   3   2

        输入：nums = [1,2,3,4]
        输出：false

        输入：nums = [3,1,4,2]
        输出：true

        1,4,2 => 132 模式

        输入：nums = [-1,3,2,0]
        输出：true

        -1,3,2 => 132 模式

        子序列 => 不要求是连续的
        -1,2,0 => 132 模式

        n == nums.length
        1 <= n <= 2*10^5
        -10^9 <= nums[i] <= 10^9
     */

    // KeyPoint 方法一 暴力解法 => 没有什么思路，就先暴力解
    // O(n^3)
    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        for (int i = 0; i < n; i++) {
            // 严格从后一个位置开始
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // 索引 i < j < k
                    // 数值 nums[i] < nums[k] < nums[j]
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // KeyPoint 方法二 维护最小值 min
    // O(n^2)
    public boolean find132pattern2(int[] nums) {

        // nums 3 1 4 4 2 1
        //      i   j     k
        // 在 0 ~ j 上找最小值，从而使得 min[0,j) < nums[k] < nums[j]

        int n = nums.length;
        if (n < 3) return false;
        // 0 ~ j 中最小值
        int numsi = nums[0];
        // 表示 "132" 中的 3，j 从 1 开始
        for (int j = 1; j < n; j++) {
            // 线性查找 => 优化：查找大于 numsi 的 nums[k]，同时再和 nums[j] 进行比较
            //  => 红黑树(二叉查找树) O(logn)
            for (int k = j + 1; k < n; k++) {
                if (numsi < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }
            // j for 循环一轮(线性时间内)，维护最小的 nums[i]，
            // 尽量让 "132" 中 1 小，这样找到 k 的概率更大
            numsi = Math.min(numsi, nums[j]);
        }
        return false;
    }

    // KeyPoint 方法三 线性查找 -> 红黑树 -> O(logn)
    public static boolean find132pattern3(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int numsi = nums[0];

        // 线性查找 => 红黑树
        // 红黑树本质二叉查找树，在红黑树中查找大于某个值，时间复杂度 O(logn)
        // TreeMap 底层使用红黑树
        TreeMap<Integer, Integer> numskMap = new TreeMap<>();
        // i < j < k => k 从 2 开始，表示第三个元素
        // 前面两个元素留给 i 和 j，将所有能作为 k 的元素放到红黑树中
        for (int k = 2; k < n; k++) {
            // 维护 nums[k] 出现的次数，因为元素 nums[k] 有可能重复出现
            // 若 nums[k] 满足 numsk < nums[j]，则说明有多个 nums[k] 都是满足
            // 注意：TreeMap 不能替换成 TreeSet，TreeSet 会去重，重复元素 nums[k] 正确情况，
            // 去重之后就有可能不对了，从而影响正确性
            // KeyPoint 一定要明确，numskMap 存储是 k 还是 nums[k]，不要混淆了
            numskMap.put(nums[k], numskMap.getOrDefault(nums[k], 0) + 1);
        }

        // O(nlogn)
        // i < j < k => 最后一个留给 k => j 到倒数第二个为止
        for (int j = 1; j < n - 1; j++) {
            // 前置条件
            if (nums[j] > numsi) {
                // 红黑树查找大于左边最小值 numsi 得元素
                // ceilingKey 返回大于或等于给定键的最小键，存在返回，不存在返回 null
                Integer numsk = numskMap.ceilingKey(numsi + 1);
                if (numsk != null && numsk < nums[j]) return true;
            }

            // 维护最小的 nums[i]
            numsi = Math.min(numsi, nums[j]);

            // 遍历完 j 之后，下个元素 j + 1 就不可能作为 k，需要从红黑树中删除掉
            // 注意，这里删除一个 nums[j + 1]，所以只是在次数上减 1，因为 nums[j + 1] 可能存在多个
            // O(logn)
            numskMap.put(nums[j + 1], numskMap.get(nums[j + 1]) - 1);
            // O(logn)
            if (numskMap.get(nums[j + 1]) == 0) numskMap.remove(nums[j + 1]);
        }
        return false;
    }

    // KeyPoint 方法四 单调栈 + 前缀最小值 -> O(n)
    public static boolean find132pattern4(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        // 维护一个前缀最小值数组，即到当前为止的最小值，用于确定 nums[i]
        int[] minPrefix = new int[nums.length];
        minPrefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // nums[i] 有最小值，则更新，没有，则维持之前 minPrefix[i - 1]
            // nums       1 0 2 -4 -3 -2 1
            // minPrefix  1 0 0 -4 -4 -4 -4
            // minPrefix[i - 1] 遇到 -4，会将 -4 一直保留下来
            minPrefix[i] = Math.min(minPrefix[i - 1], nums[i]);
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        // j 从倒数第二个元素开始，j >= 1，即 j 也不可能是第一元素
        // 想利用 minPrefix，只能从后往前遍历，否则不存在 minPrefix
        for (int j = n - 2; j >= 1; j--) {
            // 保证 nums[j] > minPrefix[j](nums[i])
            if (nums[j] > minPrefix[j]) {
                // stack.peek()(nums[k]) <= minPrefix[j](nums[i])
                while (!stack.isEmpty() && stack.peek() <= minPrefix[j]) {
                    // stack 不断弹栈，直到 stack.peek()(nums[k]) > minPrefix[j](nums[i])
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                // 在 j 从后往前遍历过程中，在 nums[j] > nums[i] 情况下
                // 将所有 nums[j] 能作为 num[k] 的值都压栈
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, -4, -3, -2, 1};
        find132pattern4(arr);
    }

    // KeyPoint 方法四 单调栈
    public boolean find132pattern5(int[] nums) {

        // -3 0 -5 -4 -3 -2 1
        // 理论上，从右往左的每个元素都有可能是 132 模式中的 2，一直到遇到第一个
        // nums[j] > nums[k]，所有的 k 才可能成为真正的 2，在所有可能成为真正
        // 2 的所有元素中找到最大的 nums[k]，这样找到 nums[i] 的机会就大点

        int n = nums.length;
        if (n < 3) return false;
        int maxk = Integer.MIN_VALUE;
        // KeyPoint 单调递减栈 => 找到小于当前值的最大值
        // 将小于当前值的最大值作为 nums[k]
        // 在当前值的左边，小于 nums[k] 的值，找到机会更大
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        // 测试数据 5 -1 0 6 3 2 1 4
        for (int i = n - 2; i >= 0; i--) {
            // nums[i] < maxk，直接满足 132 模式，返回 true
            if (nums[i] < maxk) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                // 若存在 maxk，必有 num[j] > maxk
                maxk = stack.peek();
                stack.pop();
            }
            if (nums[i] > maxk) stack.push(nums[i]);
        }
        return false;
    }
}

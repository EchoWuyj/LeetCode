package alg_02_train_dm._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:04
 * @Version 1.0
 */
public class _17_1365_how_many_numbers_are_smaller_than_the_current_number {

    /*
        1365. 有多少小于当前数字的数字
        给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
        换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i]
        以数组形式返回答案。

        KeyPoint  j != i => 本题 i 和 j，没有明确的大小关系，而使用归并排序必须保证 i < j，故本题不能使用
                         => 归并排序一般处理：两个元素对比，或者两个元素符合一定规律，使用归并排序进行计算

        示例 1：
        输入：nums = [8,1,2,2,3]
        输出：[4,0,1,1,3]
        解释：
        对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）
        对于 nums[1]=1 不存在比它小的数字。
        对于 nums[2]=2 存在一个比它小的数字：（1）
        对于 nums[3]=2 存在一个比它小的数字：（1）
        对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）

        示例 2：
        输入：nums = [6,5,4,8]
        输出：[2,1,0,3]

        示例 3：
        输入：nums = [7,7,7,7]
        输出：[0,0,0,0]

        提示：
        2 <= nums.length <= 500
        0 <= nums[i] <= 100

     */

    // KeyPoint 方法一 暴力求解
    // 时间复杂度 O(n^2)
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    // KeyPoint 方法二 排序
    // 时间复杂度 O(nlogn)
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length;

        // 维护元素值 -> 索引关系 -> 使用二维数组做映射，更加轻量级，避免使用 Map 实现
        // data[0] 元素值
        // data[1] 索引值
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }

        // 按照元素值升序排序 => 排序后每个元素索引位置发生改变，故需要维护元素和索引映射关系
        Arrays.sort(data, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            // KeyPoint 细节注意：prev == -1 判断是否成立，而不是赋值 prev = -1
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                // prev 以前表示小于 data[i][0] 的元素个数
                prev = i;
            }
            // 利用数组原始索引，确定 prev 对应位置
            ans[data[i][1]] = prev;
        }

        return ans;
    }

    // KeyPoint 方法三 => 最优解
    // 数据范围很小，使用计数排序来解决，时间复杂度 O(n)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[101];
        for (int num : nums) {
            // 将 nums 中 nums 作为 cnt 索引
            // 索引有序，且对应 nums 中元素值
            cnt[num]++;
        }

        // 计数累和
        for (int i = 1; i < 101; i++) {
            // i 位置对应 cnt[0] 到 cnt[i-1] 的累和
            // => 对应为 i 位置前面多少个小于该值的数字
            cnt[i] += cnt[i - 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            // 求的是小于 num[i]，所以选择 num[i] 前一个位置
            res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }

        return res;
    }
}

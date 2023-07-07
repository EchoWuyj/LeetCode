package alg_02_train_dm._09_day_哈希查找;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-07-06 16:05
 * @Version 1.0
 */
public class _03_128_longest_consecutive_sequence2 {

    // KeyPoint 方法二 哈希查找解法
    // 时间复杂度：O(n)  => 得保证遍历一遍数组，就能找到最长连续序列
    // 空间复杂度：O(n)
    // 注意：虽然时间复杂度 O(n)，但是力扣上执行时间很慢
    //       执行用时：314 ms，在所有 Java 提交中击败了 6.60% 的用户
    // KeyPoint 注意：代码逻辑正确，力扣 OJ 能通过测试用例
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int res = 1;
        for (int num : nums) {
            int curNum = num;

            // 存在重复计算
            // 比如：数组 0 1 2 3 4 0 0 0  5
            //                     ↑
            //                    num
            // 遇到从第二个 0 及其后面的 0，此时 num - 1 = -1，但 set 中并存在 -1
            // 不会执行 continue，故存在重复计算
            // 详见 https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ

            if (set.contains(num - 1)) continue;
            int count = 1;
            while (set.contains(curNum + 1)) {
                curNum++;
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // KeyPoint 注意：代码逻辑正确，力扣 OJ 能通过测试用例
    // 另外一种实现
    public int longestConsecutive3(int[] nums) {
        if (nums.length < 2) return nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int res = 1;
        for (int num : nums) {
            int currNum = num;
            int count = 1;
            // 存在重复计算
            if (!set.contains(currNum - 1)) {
                while (set.contains(currNum + 1)) {
                    currNum += 1;
                    count += 1;
                }
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // KeyPoint 方法二 哈希查找解法 => 超时
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestConsecutive2(int[] nums) {
        if (nums.length < 2) return nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int res = 1;
        int start = Integer.MIN_VALUE, end = Integer.MIN_VALUE;
        for (int num : nums) {

            // 优化：区间的方式来规避重复计算
            if (num >= start && num <= end) continue;
            int count = 1;
            int next = num + 1;

            // 可能存在多个连续数字，故使用循环判断，而不是使用 if 单次判断
            while (set.contains(next)) {
                count++;
                next++;
            }

            if (count >= res) {
                res = count;
                start = num;
                end = next - 1;
            }
        }
        return res;
    }
}

package alg_02_train_dm._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:14
 * @Version 1.0
 */
public class _01_401_binary_watch {
     /*
        401. 二进制手表
        二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
        每个 LED 代表一个 0 或 1，最低位在右侧。

        给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，
        返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。

        小时不会以零开头：
            例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。

        分钟必须由两位数组成，可能会以零开头：
            例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。

        提示：
            0 <= turnedOn <= 10
     */

    // KeyPoint 本质:组合问题(从 n 个数字中，取 k 个数字)
    public List<String> readBinaryWatch(int turnedOn) {
        int[] nums1 = {8, 4, 2, 1}; // 小时
        int[] nums2 = {32, 16, 8, 4, 2, 1}; // 分钟

        List<String> res = new ArrayList<>();
        // KeyPoint bug 修复：需要等于，不然会少一种组合，总共是[0，turnedOn]种
        // 暴力枚举所有组合
        for (int i = 0; i <= turnedOn; i++) {
            // 拿到 i 个小时的组合。注意是 i，不是 turnedOn
            List<Integer> hours = findCombinations(nums1, i);
            // 拿到 turnedOn - i 个分钟的组合
            List<Integer> minutes = findCombinations(nums2, turnedOn - i);
            for (int hour : hours) {
                if (hour > 11) continue;
                for (int minute : minutes) {
                    if (minute > 59) continue;
                    String minuteStr = (minute < 10) ? "0" + minute : minute + "";
                    res.add(hour + ":" + minuteStr);
                }
            }
        }
        return res;
    }

    // 从 nums 数组中找 count 个数字
    private List<Integer> findCombinations(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        dfs(nums, count, 0, 0, res);
        return res;
    }

    // sum:本题中不求路径，而是求路径所有节点值和
    // count:每个遍历完一个节点，count--
    private void dfs(int[] nums, int count, int sum, int start, List<Integer> res) {
        if (count == 0) {
            res.add(sum);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 遍历完一个节点，count--
            // sum + nums[i] 在 dfs 调用中累加，则回溯过程不需要"还原现场"，sum 值本身没有变化
            dfs(nums, count - 1, sum + nums[i], i + 1, res);

            // 另外一种写法
//            sum += nums[i];
//            dfs(nums, count - 1, sum, i + 1, res);
//            sum -= nums[i];
        }
    }
}

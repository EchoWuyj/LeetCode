package alg_02_train_dm._23_day_回溯算法二_2刷;

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

        顶部：8 4 2 1
                 √  √
        底部：32 16 8 4 2 1
                 √  √     √
        表示时间 3:25

        给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，
        返回二进制手表可以表示的 所有可能时间。你可以 按任意顺序 返回答案。

        小时不会以零开头：
        例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。

        分钟必须由两位数组成，可能会以零开头：
        例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。

        输入：turnedOn = 1
        输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]

        示例 2：
        输入：turnedOn = 9
        输出：[]

        解释：
        一共 10 个灯，亮了 9 个，则说明 小时 或者 分钟 必须全亮，
        而 小时 或者 分钟 全亮超过了 11 和 59 这个最大值限制，故最后输出为 []

        提示：
            0 <= turnedOn <= 10
     */

    // 本质：组合问题 => 从 n 个数字中，取 k 个数字
    public List<String> readBinaryWatch(int turnedOn) {

        // 抽取出回溯数据选择范围
        // 小时
        int[] nums1 = {8, 4, 2, 1};
        // 分钟
        int[] nums2 = {32, 16, 8, 4, 2, 1};
        List<String> res = new ArrayList<>();

        // 总的亮着的灯 turnedOn，假设 i 个小时灯亮，则 turnedOn - i 个分钟灯亮
        // 其中 i 范围，0 ~ turnedOn 个，即一个都不亮，也可以全亮满
        // 本质：暴力枚举所有组合
        // 等价于
        // 1.先在 nums1 中选择 i 个 数
        // 2.再在 nums2 中选择 turnedOn - i 个 数
        for (int i = 0; i <= turnedOn; i++) {
            // 拿到 i 个小时的组合
            List<Integer> hours = findCombinations(nums1, i);
            // 拿到 turnedOn - i 个分钟的组合
            List<Integer> minutes = findCombinations(nums2, turnedOn - i);
            for (int hour : hours) {
                // 数字范围判断，无效，跳过
                if (hour > 11) continue;
                for (int minute : minutes) {
                    // 数字范围判断,无效，跳过
                    if (minute > 59) continue;
                    // 拼接时，格式判断
                    String minuteStr = (minute < 10) ? "0" + minute : minute + "";
                    res.add(hour + ":" + minuteStr);
                }
            }
        }
        return res;
    }

    // 找组合：从 nums 数组中找 count 个数字
    private List<Integer> findCombinations(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        dfs(nums, count, 0, 0, res);
        return res;
    }

    // sum：本题中不求路径，而是求路径所有节点值和
    // count：每个遍历完一个节点，count--
    private void dfs(int[] nums, int count, int sum, int index, List<Integer> res) {

        // 通过测试用例，输出结果和预期结果，数据差异，分析代码 bug 可能的位置
        // 输出 ["0:32","0:16","0:08","0:04","0:02","8:00","4:00","2:00"]
        // 预期结果 ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
        // 差异："0:01" 和 "1:00"，则体现：nums1 和 nums2 最后位置元素问题
        // => 故是可能是 index == nums.length 的问题
        // => 在 if (index == nums.length) 不能直接 return，因为，此时 index 已经遍历完数组了，应该将结果加入 res 中
        //    注意：dfs 中，虽然 index = i+1 = nums.length，但是 sum + nums[i] 中 nums[i] 没有越界

//        if (index == nums.length) return;

        if (index > nums.length) return;
        if (count == 0) {
            res.add(sum);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            // 遍历完一个节点，count--
            // sum + nums[i] 在 dfs 调用中累加，则回溯过程不需要 "还原现场"，sum 值本身没有变化
            dfs(nums, count - 1, sum + nums[i], i + 1, res);

            // 回溯：另外一种写法，需要还原现场
//            sum += nums[i];
//            dfs(nums, count - 1, sum, i + 1, res);
//            sum -= nums[i];
        }
    }
}

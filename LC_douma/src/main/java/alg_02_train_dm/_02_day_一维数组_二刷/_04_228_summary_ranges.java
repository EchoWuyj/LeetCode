package alg_02_train_dm._02_day_一维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 18:13
 * @Version 1.0
 */
public class _04_228_summary_ranges {
    /*
        228 汇总区间
        给定一个 无重复元素 的 有序整数数组 nums 。返回 恰好覆盖数组中所有数字 的最小有序区间范围列表。
        也就是说，nums 的每个元素都恰好被某个区间范围覆盖，并且不存在一个覆盖 nums 所有元素的更小的区间。

        列表中的每个区间范围 [a,b] 应该按如下格式输出：
        "a->b" ，如果 a != b
        "a" ，如果 a == b

        示例 1：
        输入：nums = [0,1,2,4,5,7]
        输出：["0->2","4->5","7"]
        解释：区间范围是：
        [0,2] --> "0->2"
        [4,5] --> "4->5"
        [7,7] --> "7"

        示例 2：
        输入：nums = [0,2,3,4,6,8,9]
        输出：["0","2->4","6","8->9"]
        解释：区间范围是：
        [0,0] --> "0"
        [2,4] --> "2->4"
        [6,6] --> "6"
        [8,9] --> "8->9"

        提示
        0 <= nums.length <= 20
        -2^31 <= nums[i] <= 2^31 - 1
        nums 中的所有值都 互不相同
        nums 按升序排列
     */

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        int i = 0;
        while (i < n) {
            // 区间开始位置
            int low = i;
            // nums[i] 和 nums[i-1] 比较，故先移动 i
            i++;
            // nums[i] - nums[i-1] == 1 => 说明：nums[i] 和 nums[i-1] 之间是连续
            // 找到非连续的点
            // KeyPoint 特别注意：这种 while 循环实现方式，需要牢记
            while (i < n && nums[i] - nums[i - 1] == 1) i++;

            // 区间结束位置 high
            // i 位置是不满足 nums[i] - nums[i-1] == 1，但是 i-1 位置是满足的，故返回 i-1
            int high = i - 1;

            // 先将 low 和 high 计算好，再去对已经确定的区间，按照题目要求输出打印
            StringBuilder tmpStr = new StringBuilder();
            tmpStr.append(nums[low]);
            if (low < high) {
                tmpStr.append("->");
                tmpStr.append(nums[high]);
            }
            res.add(tmpStr.toString());
        }
        return res;
    }
}

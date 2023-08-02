package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:04
 * @Version 1.0
 */
public class _10_229_majority_element_ii {
    /*  
        229. 求众数 II
        给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊n/3⌋ 次的元素。
        进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
    
        输入：[3,2,3]
        输出：[3]
    
        输入：[1,1,1,3,3,2,2,2]
        输出：[1,2]

        提示
        1 <= nums.length <= 5 * 10^4
        -10^9 <= nums[i] <= 10^9
     */

    // KeyPoint 方法一 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int key : map.keySet()) {
            if (map.get(key) > n / 3) {
                res.add(key);
            }
        }
        return res;
    }

    // KeyPoint 方法二 Boyer-Moore 投票算法 => 很难想到这种算法，并且经常容易忘记
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public List<Integer> majorityElement1(int[] nums) {

        // 摩尔投票算法：三三抵消

        // 数组中出现次数超过 ⌊n/3⌋ 次的元素，最多只有两个元素 大于 ⌊2n/3⌋
        // 另外一个元素，只能小于 ⌊n/3⌋，故最多有两个候选人

        // 候选人
        int candidate1 = 0, candidate2 = 0;
        // 票数
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                // candidate1 得 1 票
                count1++;
            } else if (num == candidate2) {
                // candidate2 得 1 票
                count2++;
            } else {
                if (count1 == 0) {
                    // 票数为 0，设置新的候选人 candidate1 为 num
                    candidate1 = num;
                    count1++;
                } else if (count2 == 0) {
                    // 票数为 0，设置新的候选人 candidate2 为 num
                    candidate2 = num;
                    count2++;
                } else {
                    // num 和两个 candidate 不同，将两候选人得票抵消
                    // 即将 count1 和 count2 减1
                    count1--;
                    count2--;
                }
            }
        }

        // 经过 for 循环之后，确定下来的 candidate1 和 candidate2 是有可能的是众数
        // 需要对 candidate1 和 candidate2 重新计数

        // 注意：题目中没有保证一定有超过 ⌊n/3⌋ 次的元素，因此，还得再循环遍历一遍数组
        // 确认 candidate 得票数是否大于 ⌊n/3⌋，故还得有个计数的过程

        // 区别：力扣 169 题：假设数组是非空的，并且给定的数组总是存在多数元素

        // 如：数组： 6     7     8      9
        //         [6,1] [6,1] [6,0]  [9,1] => 但是 9 并不是超过 ⌊n/3⌋ 次元素
        //               [7,1] [7,0]

        // 得票数重新置为 0，重新投票
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (count1 > n / 3) res.add(candidate1);
        if (count2 > n / 3) res.add(candidate2);
        return res;
    }
}

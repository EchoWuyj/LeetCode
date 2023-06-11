package alg_02_train_wyj._09_day_哈希查找;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 14:34
 * @Version 1.0
 */
public class _03_128_longest_consecutive_sequence {
    public int longestConsecutive1(int[] nums) {
        if (nums.length < 2) return nums.length;
        Arrays.sort(nums);
        int ans = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else {
                ans = Math.max(ans, count);
                count = 1;
            }
        }
        return Math.max(ans, count);
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int ans = 1, start = 0, end = -1;
        for (int num : nums) {
            if (num >= start && num <= end) continue;
            int currNum = num;
            start = currNum;
            int count = 1;
            while (set.contains(currNum + 1)) {
                currNum += 1;
                count += 1;
            }
            end = currNum;
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test = {0,1,2,3,4,0,0,0,0,0,0,0,5};
        System.out.println(longestConsecutive(test));
    }
}

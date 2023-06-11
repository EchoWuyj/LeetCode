package alg_02_train_wyj._02_day_一维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 18:45
 * @Version 1.0
 */
public class _04_228_summary_ranges {

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] - nums[i - 1] == 1) i++;
            int high = i - 1;
            StringBuilder sb = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                sb.append("->");
                sb.append(nums[high]);
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = new int[]{0, 2, 3, 4, 6, 8, 9};
        int[] test1 = new int[]{0, 1, 2, 4, 5, 7};
        System.out.println(summaryRanges(test)); // [0, 2->4, 6, 8->9]
        System.out.println(summaryRanges(test1)); // [0->2, 4->5, 7]
    }
}

package alg_02_train_wyj._02_day_一维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 21:09
 * @Version 1.0
 */
public class _05_163_missing_ranges {
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        int pre = lower - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == pre + 2) {
                res.add(String.valueOf(pre + 1));
            } else if (nums[i] > pre + 2) {
                res.add((pre + 1) + "->" + (nums[i] - 1));
            }
            pre = nums[i];
        }

        if (upper == pre + 1) res.add(String.valueOf(pre + 1));
        else if (upper > pre + 1) {
            res.add((pre + 1) + "->" + upper);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 50, 75};
        System.out.println(findMissingRanges(arr, 0, 99));
        // [2, 4->49, 51->74, 76->99]
        System.out.println(findMissingRanges(arr, -1, 76));
        // [-1, 2, 4->49, 51->74, 76]
        System.out.println(findMissingRanges(arr, -5, 99));
        // [-5->-1, 2, 4->49, 51->74, 76->99]
    }
}

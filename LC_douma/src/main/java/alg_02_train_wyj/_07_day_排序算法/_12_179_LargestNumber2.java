package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-10 21:50
 * @Version 1.0
 */
public class _12_179_LargestNumber2 {
    public String largestNumber2(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];

        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (x, y) -> {
            String xy = x + y;
            String yx = y + x;
            return yx.compareTo(xy);
        });

        if (strs[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}

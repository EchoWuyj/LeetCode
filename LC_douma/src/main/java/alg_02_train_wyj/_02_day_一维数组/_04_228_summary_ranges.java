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
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] - nums[i - 1] == 1) i++;
            int high = i - 1;
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

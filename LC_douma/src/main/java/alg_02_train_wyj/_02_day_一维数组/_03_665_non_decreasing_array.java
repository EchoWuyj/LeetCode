package alg_02_train_wyj._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 17:01
 * @Version 1.0
 */
public class _03_665_non_decreasing_array {

    public boolean checkPossibility(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                if (count > 1) return false;
                if (i >= 2 && nums[i] < nums[i - 2]) nums[i] = nums[i - 1];
            }
        }
        return true;
    }
}

package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 18:43
 * @Version 1.0
 */
public class _06_26_remove_duplicates_from_sorted_array {

    public int removeDuplicates(int[] nums) {
        int fast = 1;
        int slow = 0;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
                fast++;
            } else {
                fast++;
            }
        }
        return slow + 1;
    }
}

package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 18:52
 * @Version 1.0
 */
public class _07_80_remove_duplicates_from_sorted_array_ii {
    public int removeDuplicates(int[] nums) {
        if (nums != null && nums.length <= 2) return nums.length;
        int n = nums.length;
        int slow = 2;
        int fast = 2;
        while (fast < n) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            } else {
                fast++;
            }
        }
        return slow;
    }

    public int removeDuplicates1(int[] nums) {
        if (nums != null && nums.length <= 2) return nums.length;
        int slow = 1;
        int fast = 2;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != nums[slow - 1]) {
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

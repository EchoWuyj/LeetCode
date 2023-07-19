package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 20:32
 * @Version 1.0
 */
public class _08_27_remove_element {
    public int removeElement1(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        int fast = 0;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != val) {
                if (nums[fast] != nums[slow]) {
                    nums[slow] = nums[fast];
                }
                slow++;
                fast++;
            } else {
                fast++;
            }
        }
        return slow;
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return right + 1;
    }
}

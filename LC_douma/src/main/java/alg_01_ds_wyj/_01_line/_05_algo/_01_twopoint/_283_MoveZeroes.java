package alg_01_ds_wyj._01_line._05_algo._01_twopoint;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 23:17
 * @Version 1.0
 */
public class _283_MoveZeroes {
    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        int[] tmp = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                tmp[j] = nums[i];
                j++;
            }
        }
        for (int i = 0; i < n; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        int slow = 0;
        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] != 0) {
                if (fast != slow) {
                    int temp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = temp;
                }
                slow++;
            }
        }
    }

    public static void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int slow = 0, fast = 0;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != 0) {
                if (fast != slow) {
                    nums[slow] = nums[fast];
                }
                slow++;
            }
            fast++;
        }

        while (slow < n) {
            nums[slow] = 0;
            slow++;
        }
    }
}

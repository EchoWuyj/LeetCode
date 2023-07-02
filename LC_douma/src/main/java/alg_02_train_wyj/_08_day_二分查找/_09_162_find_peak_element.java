package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 17:34
 * @Version 1.0
 */
public class _09_162_find_peak_element {

    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int findPeakElement1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                //System.out.println(i);
                return i - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(findPeakElement(arr));
    }
}

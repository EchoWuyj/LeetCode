package alg_01_ds_wyj._01_line._05_algo._04_bs;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 13:13
 * @Version 1.0
 */
public class BasicBinarySearch {
    public static boolean contains(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static boolean containsR(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        return contains(nums, 0, nums.length - 1, target);
    }

    private static boolean contains(int[] nums, int left, int right, int target) {
        if (left > right) return false;
        int mid = left + (right - left) / 2;
        if (target == nums[mid]) return true;
        if (target < nums[mid]) {
            return contains(nums, left, mid - 1, target);
        } else {
            return contains(nums, mid + 1, right, target);
        }
    }

    public static void main(String[] args) {
        test1();
        System.out.println("===============");
        test2();
    }

    public static void test1() {
        int[] arr = {2, 4, 6, 9, 12, 15, 23, 30, 32, 36, 40};
        System.out.println(contains(arr, 1));  // false
        System.out.println(contains(arr, 15)); // true
        System.out.println(contains(arr, 24)); // false
        System.out.println(contains(arr, 30)); // true
        System.out.println(contains(arr, 41)); // false
    }

    public static void test2() {
        int[] arr = {2, 4, 6, 9, 12, 15, 23, 30, 32, 36, 40};
        System.out.println(containsR(arr, 1));  // false
        System.out.println(containsR(arr, 15)); // true
        System.out.println(containsR(arr, 24)); // false
        System.out.println(containsR(arr, 30)); // true
        System.out.println(containsR(arr, 41)); // false
    }
}

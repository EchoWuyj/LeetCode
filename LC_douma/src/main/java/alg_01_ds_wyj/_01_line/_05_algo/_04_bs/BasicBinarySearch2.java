package alg_01_ds_wyj._01_line._05_algo._04_bs;

/**
 * @Author Wuyj
 * @DateTime 2023-09-30 15:18
 * @Version 1.0
 */
public class BasicBinarySearch2 {
    private static int searchFirstTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    public static int searchLastTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    private static int searchFirstGETargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int searchLastETTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public static void test1() {
        int[] arr = {1, 2, 3, 3, 4, 5};
        int target = 3;
        // 查找第一个'等于'目标元素的下标
        System.out.println(searchFirstTargetIndex(arr, target)); // 2
        // 查找最后一个'等于'目标元素的下标
        System.out.println(searchLastTargetIndex(arr, target)); // 3
    }

    public static void test2() {
        int[] arr = {1, 2, 3, 3, 4, 5};
        int target = 3;
        // 查找第一个'等于'目标元素的下标
        System.out.println(searchFirstTargetIndex(arr, target)); // 2
        // 查找最后一个'等于'目标元素的下标
        System.out.println(searchLastTargetIndex(arr, target)); // 3
    }

    public static void main(String[] args) {
        System.out.println("=== test1 ===");
        test1();
        System.out.println("=== test2 ===");
        test2();
    }
}

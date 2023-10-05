package alg_01_ds_dm._01_line._05_algo._04_bs_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-06 12:34
 * @Version 1.0
 */
public class _02_Advanced_BinarySearch2 {

    // KeyPoint 思路二：在循环体中排除一定不存在目标元素的区间 => 重点掌握

    // KeyPoint 核心三要素
    // 1.不等号方向 > 或 <
    // 2.从左往右，还是 从右往左 的逼近方向
    // 3.以及求解问题：第一个 还是 最后一个
    //   => 这三者是相互绑定，不要搞混了

    // KeyPoint 第一个'等于 & '最后一个'等于

    // 本题：求'第一个'等于 target 元素
    // 1. >
    // 2.从右往左
    // 3.第一个
    private static int searchFirstTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                // target <= nums[mid]，right 从右向左向 mid 逼近，直到就剩最后一个元素
                // 即为第一个等于目标元素下标，跳出 while 循环，进行后续比较
                right = mid;
            }
        }
        // 第一个 '大于等于' target 的 nums[left]
        // 再附加 '等于'判断
        // => 第一个等于 target 的 nums[left]

        // KeyPoint 最后一个元素，若是等于 target，则一定是第一个等于 target
        if (nums[left] == target) return left;
        // 最后一个元素，若是不于 target，区间没有 target，返回 -1 即可
        return -1;
    }

    // 本题：求'最后一个'等于 target 元素
    // 1. <
    // 2.从左往右
    // 3.最后一个
    // 4.额外补充：计算 mid 得 +1
    private static int searchLastTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 计算中间值 mid 需要靠右，否则会有死循环
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                // target < nums[mid] => target 必然不在 [mid,right]
                right = mid - 1;
            } else {
                // 1.target >= nums[mid] => target 必然不在 [left,mid-1]
                //   最差情况 target == nums[mid]，故 left 右移到 mid
                // 2.left 从左往右向 mid 位置向右逼近，直到就剩最后一个元素
                //   即为最后一个等于目标元素下标，跳出 while 循环，进行后续比较
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // KeyPoint 第一个'大于等于' & 最后一个'小于等于'

    // 本题：第一个'大于等于'目标元素的下标
    // 1. >
    // 2.从右往左
    // 3.第一个
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

    // 本题：最后一个'小于等于'目标元素的下标
    // 1. <
    // 2.从左往右
    // 3.最后一个
    // 4.额外补充：计算 mid 得 +1
    private static int searchLastETTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 计算中间值 mid 需要靠右，否则会有死循环
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

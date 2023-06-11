package alg_01_ds_dm._01_line._05_algo._04_bs;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 10:44
 * @Version 1.0
 */
public class _01_Basic_BinarySearch {

    // KeyPoint 方法一 二分查找(迭代) => 推荐使用
    // 时间复杂度：O(logn)
    // 空间复杂度：O(1)
    public static boolean contains(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        // 1 当 left = right 搜索区间只有一个元素，此时也是需要执行 while 循环
        //   mid 移动 Left 和 right 位置，再去进行比较
        // 2 当 left > right，即搜索区间没有元素才会退出 while 循环
        while (left <= right) {
            // bug：left + right 可能会溢出，整数的最大值：2^31 - 1 = 2147483647
            // 这个 mid 计算公式 => 奇数个元素 mid 为中间位置，偶数个元素 mid 为偏左位置
            // 推荐使用这种方式
            int mid = left + (right - left) / 2;

            // 其余解决方案(看别人这样写能看懂即可)
            // int mid = left + ((right - left) >> 1) ;
            // >> 运算优先级低于 +，使用 >> 需要使用 () 提高优先级，其实性能差不多

            // int mid = (left + right) >>> 1;
            // >>> 无符号右移，高位补 0 右移

            if (target == nums[mid]) {
                return true;
                // target 比中间值 nums[mid] 要小，下一次搜索区间 [left...mid - 1]，移动 right
                // target | mid 相对位置，想要找到 target，right 只能左移，一眼就清楚
            } else if (target < nums[mid]) {
                right = mid - 1;
                // target 比中间值 nums[mid] 要大，下一次搜索区间 [mid + 1...right]，移动 left
            } else {
                left = mid + 1;
            }
        }

        // left > right：没有元素
        return false;
    }

    // KeyPoint 方法二 二分查找(递归)
    // 时间复杂度：O(logn) => 取决于 target 比较的次数
    // 空间复杂度：O(logn)
    public static boolean containsR(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        return contains(nums, 0, nums.length - 1, target);
    }

    // 递归函数功能:在一个子数组中，判断是否存在 target
    // 这种区间判断的，递归函数一般是有左右边界 left 和 right
    private static boolean contains(int[] nums, int left, int right, int target) {
        // 搜索区间不成立
        if (left > right) return false;

        // 单层处理逻辑
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return true;

        if (target < nums[mid]) {
            // 递归调用过程，涉及系统调用栈，调用的次数为递归树的高度，O(logn)
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

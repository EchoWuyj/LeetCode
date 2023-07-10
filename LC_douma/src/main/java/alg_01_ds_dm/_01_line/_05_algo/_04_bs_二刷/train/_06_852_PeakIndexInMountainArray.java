package alg_01_ds_dm._01_line._05_algo._04_bs_二刷.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 14:51
 * @Version 1.0
 */
public class _06_852_PeakIndexInMountainArray {

    /*
        852. 山脉数组的峰顶索引
        符合下列属性的数组 arr 称为 山脉数组 ：
        1.arr.length >= 3
        2.存在 i（0 < i < arr.length - 1）使得：
          2.1 arr[0] < arr[1] < ... arr[i-1] < arr[i]
          2.2 arr[i] > arr[i+1] > ... > arr[arr.length - 1]

        给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i]
         > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i

        示例 1：
        输入：arr = [0,1,0]
        输出：1

        示例 2：
        输入：arr = [0,2,1,0]
        输出：1

        示例 3：
        输入：arr = [0,10,5,2]
        输出：1

        提示：
        3 <= arr.length <= 105
        0 <= arr[i] <= 106
        题目数据保证 arr 是一个山脉数组

     */

    // KeyPoint 方法一 暴力方法
    public int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return i;
        }
        //
        return arr.length - 1;
    }

    // KeyPoint 方法一 暴力方法 另外一种方式
    public int peakIndexInMountainArray1(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        for (int i = 1; i < arr.length; i++) {
            // 只要后一个元素 arr[i] 比前一个元素 arr[i-1] 要小，则返回 i-1 索引
            if (arr[i] < arr[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }

    // KeyPoint 方法二 二分方法
    // KeyPoint 山脉数组不是严格有序，峰顶之前升序 ↗，峰顶之后降序 ↘  => 二分方法
    public int peakIndexInMountainArray2(int[] arr) {
        // 山脉数组长度 > 3，判空条件可以省略
        if (arr == null || arr.length == 0) return -1;
        int left = 0;
        int right = arr.length - 1;

        // 不断在循环体内，排除一定不存在目标元素的区间，最后只剩一个元素该元素就是峰顶
        // 关键：找到排除一半不存在目标元素的区间 对应的 if 条件 => 本题：arr[mid] < arr[mid + 1]
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 因为峰顶值并不知道的，故没有目标值 target，故比较的条件，使用 [mid] 和 [mid+1]
            // arr[mid] < arr[mid + 1] => 上坡，但找的是峰值，故排除 mid 以左，包括 mid
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                // arr[mid] >= arr[mid + 1]
                // 注意：本题山脉数组中，不存在元素相等的情况
                // => arr[mid] > arr[mid+1] => 下坡
                // 最多将 mid+1 往后，数值递减元素排除，但是 arr[mid] 不能排除
                // 即 right 最多移动到 mid，arr[mid] 可能是最大值
                right = mid;

                // KeyPoint 排除一半区间代码模板
                // 一般都是 if 判断条件中
                // 一个分支严格排除：left = mid + 1;
                // 另外一个分支非严格排除： right = mid;
            }
        }
        // 明确：最后返回 数组索引 index 还是 数组值 value
        // 本题：返回 index，不是 value
        return left;

        // KeyPoint 经验技巧
        // 在力扣网页写代码，多使用自动的代码提示，提高写代码的效率，同时减少 bug
    }
}

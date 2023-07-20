package alg_02_train_dm._02_day_一维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 12:00
 * @Version 1.0
 */
public class _01_941_valid_mountain_array {

    /*
        941. 有效的山脉数组
        给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。

        让我们回顾一下，如果 arr 满足下述条件，那么它是一个山脉数组：
        1 arr.length >= 3
        2 在 0 < i < arr.length - 1 条件下，存在 i 使得：
          2.1 arr[0] < arr[1] < ... arr[i-1] < arr[i]  => 严格递增
          2.2 arr[i] > arr[i+1] > ... > arr[arr.length - 1] => 严格递减

        提示
        1 <= arr.length <= 104
        0 <= arr[i] <= 104

        输入：arr = [0,3,7,8,6,5,2,1]
        输出：true

        输入：arr = [3,5,5]
        输出：false

        输入：arr = [0,3,2,1]
        输出：true

        提示
        1 <= arr.length <= 10^4 => 至少 O(n^2)
        0 <= arr[i] <= 10^4  => 保证元素不会越界

     */

    public boolean validMountainArray(int[] arr) {

        int n = arr.length;
        // 指针 i 从 0开始，通过 i+1 和 i 进行比较，循环需要对索引 i 限制，避免越界
        int i = 0;

        // 1.找到最高点
        // 需要保证 i < n - 1，若 i = n - 1，则 arr[i + 1] 越界
        while (i < n - 1 && arr[i] < arr[i + 1]) i++;

        // 2.对于最高点进行边界判断
        // 对于最高点进行判断，最高点不能是第一个或者最后一个元素
        // 否则：属于单调增或者单调减数组情况
        if (i == 0 || i == n - 1) return false;

        // 3.从最高点往后递减扫描
        while (i < n - 1 && arr[i] > arr[i + 1]) i++;

        // 4.如果 i 指向数组最后一个元素，则返回 true，否则返回 false
        // 保证不是因为 arr[i] <= arr[i + 1]，而停止 while 循环的，符合山脉数组定义
        return i == n - 1;
    }
}

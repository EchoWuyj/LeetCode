package alg_02_train_dm._02_day_一维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-19 19:09
 * @Version 1.0
 */
public class _02_189_rotate_array3 {
    // KeyPoint 方法三：数组旋转 => 推荐掌握
    // 时间复杂度 O(n)
    public void rotate(int[] nums, int k) {

        // nums：1 2 3 4 5 6 7，k = 3
        //       1 2 3 4 | 5 6 7
        // 目标：5 6 7 1 2 3 4

        // 1.反转数组： 7 6 5 4 3 2 1
        // 2.反转 [0,k-1]：5 6 7 | 4 3 2 1
        // 3.反转 [k,n-1]：5 6 7 | 1 2 3 4

        int n = nums.length;
        // k 的范围没有明说，但是最好处理下，避免越界
        k = k % n;
        reverse(nums, 0, n - 1);
        // 0 ~ k-1 保证 k 个
        reverse(nums, 0, k - 1);
        // 剩余的元素
        reverse(nums, k, n - 1);
    }

    // 数组反转
    private void reverse(int[] nums, int start, int end) {
        // start == end 跳出循环
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}

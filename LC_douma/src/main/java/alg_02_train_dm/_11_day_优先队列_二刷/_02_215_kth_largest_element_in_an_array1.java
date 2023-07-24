package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 23:41
 * @Version 1.0
 */
public class _02_215_kth_largest_element_in_an_array1 {
    /*
        215 号算法题：数组中的第 K 个最大元素
        在未排序的数组中找到第 k 个最大的元素。
        请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

        输入: [3,2,1,5,6,4] 和 k = 2
        输出: 5

        输入: [3,2,3,1,2,4,5,5,6] 和 k = 4  => 5，5 相同元素属于两个不同最大元素
        输出: 4

        你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

        提示：
        1 <= k <= nums.length <= 10^5
        -104 <= nums[i] <= 104
     */

    // KeyPoint 方法一 排序解法
    // 时间复杂度：O(nlogn)
    // KeyPoint O(nlogn) 对应数据集
    // => 数据规模 10^5，log(10^5) < 20，nlogn = 20 * 10^5 = 2 * 10^6，故 O(nlogn) 可以通过
    // => 注意：log n 表示以 2 为底的对数，其中 log2(10^5) ≈ 16.6094
    // => 详见 02_Note_Data_Scale
    // 空间复杂度：快排 O(logn)，归并排序 O(n)
    // => 数据规模很大，使用归并排序，否则使用快排
    public int findKthLargest1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        // 倒数第 1 个大元素 nums[n-1]
        // => 倒数第 k 个大元素 nums[n-k]
        return nums[n - k];
    }

    // KeyPoint 通过代码来计算 log2(10^5)
    public static void main(String[] args) {

        // Math.log() => 以 e 为底的自然对数，除以 Math.log(2) 来将其转换为以 2 为底的对数
        double res1 = Math.log(1000_00) / Math.log(2);
        System.out.println(res1); // 16.609640474436812

        double res2 = Math.log(1000_000) / Math.log(2);
        System.out.println(res2); // 19.931568569324174
    }
}

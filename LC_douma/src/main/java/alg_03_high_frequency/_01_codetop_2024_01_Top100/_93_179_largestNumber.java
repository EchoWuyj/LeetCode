package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-19 10:23
 * @Version 1.0
 */
public class _93_179_largestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        int n = nums.length;
        sort(nums, 0, n - 1);

        StringBuilder builder = new StringBuilder();
        for (int num : nums) {
            builder.append(num);
        }
        if (builder.charAt(0) == '0') return "0";
        return builder.toString();
    }

    public void sort(int[] nums, int low, int high) {
        // 一个元素无序排序，直接返回
        if (low >= high) return;
        int pivot = nums[high];
        int less = low;
        int greater = high;
        int i = less;

        while (i <= greater) {
            // 注意：比较的是 nums[i]，而不是索引 i
            String xy = nums[i] + "" + pivot;
            String yx = pivot + "" + nums[i];

            if (xy.compareTo(yx) > 0) {
                swap(nums, i, less);
                i++;
                less++;
            } else if (xy.compareTo(yx) < 0) {
                swap(nums, i, greater);
                greater--;
            } else {
                i++;
            }
        }
        sort(nums, low, less - 1);
        sort(nums, greater + 1, high);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

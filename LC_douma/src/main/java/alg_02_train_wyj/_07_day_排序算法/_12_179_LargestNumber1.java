package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 16:09
 * @Version 1.0
 */
public class _12_179_LargestNumber1 {

    public String largestNumber1(int[] nums) {
        int n = nums.length;
        sort(nums, 0, n - 1);
        if (nums[0] == 0) return "0";
        StringBuilder sb = new StringBuilder();

        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) return;

        int i = low;
        int less = low;
        int great = high;
        int pivot = nums[high];

        while (i <= great) {

            String xy = "" + nums[i] + pivot;
            String yx = "" + pivot + nums[i];
            if (xy.compareTo(yx) > 0) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (xy.compareTo(yx) < 0) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(nums, low, less - 1);
        sort(nums, great + 1, high);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}






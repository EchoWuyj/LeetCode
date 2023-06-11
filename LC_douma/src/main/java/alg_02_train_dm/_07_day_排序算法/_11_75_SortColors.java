package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:15
 * @Version 1.0
 */
public class _11_75_SortColors {

     /*
        75. 颜色分类
        给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
        原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
        我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
        必须在不使用库内置的 sort 函数的情况下解决这个问题。

        示例 1：
        输入：nums = [2,0,2,1,1,0]
        输出：[0,0,1,1,2,2]

        示例 2：
        输入：nums = [2,0,1]
        输出：[0,1,2]

        提示：
        n == nums.length
        1 <= n <= 300
        nums[i] 为 0、1 或 2

     */

    // KeyPoint 方法一 计数排序 (两趟扫描)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void sortColors1(int[] nums) {

        if (nums == null) return;
        // 1. 计数
        // 0,1,2 => 数组大小为 3
        int[] count = new int[3];
        for (int num : nums) {
            // 对 0,1,2 进行计数
            // 0,1,2 => 索引
            // count[num] => 个数
            count[num]++;
        }

        // 2. 排序
        int index = 0;

        // KeyPoint 注意点
        // 因为 nums 数组比较特殊，nums 中只有 0，1，2 这 3 个元素，连续且中间不存在间断，
        // 故可以直接根据 count[i] 对原数组 nums 进行赋值操作即可，注意：一般数组不能这样做
        // 根据 0，1，2 这 3 个元素个数进行赋值
        for (int i = 0; i <= 2; i++) {
            int num = count[i];
            // 根据计数 num 个数，对 num 进行重赋值操作
            for (int j = 1; j <= num; j++) {
                nums[index++] = i;
            }
        }
    }

    // KeyPoint 方法二 三路快排 (一趟扫描)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void sortColors(int[] nums) {
        int zero = 0;
        int two = nums.length - 1;

        int i = 0;
        while (i <= two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, two);
                two--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:15
 * @Version 1.0
 */

// KeyPoint 快速排序应用
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

        进阶：
        你能想出一个仅使用常数空间的一趟扫描算法吗? => 空间复杂度 O(1)

     */

    // KeyPoint 方法一 计数排序 (两趟扫描)
    // => 类似于：1122 数组的相对排序
    // nums[i] 为 0、1 或 2 => 数据范围比较小，可以使用计数排序
    // 时间复杂度：O(n)
    // 空间复杂度： O(logn)
    public void sortColors(int[] nums) {

        // 计数排序 基本步骤
        // 1.计数
        // 2.计数累加
        // 3.计算每个数字存储位置
        // 4.拷贝

        if (nums == null) return;
        // 1.计数
        // 0,1,2 => 数组大小为 3
        int[] count = new int[3];
        for (int num : nums) {
            // 对 0,1,2 进行计数
            // 0,1,2 => 索引
            // count[num] => 个数
            count[num]++;
        }

        // KeyPoint 结合具体题目数据限制条件，从而简化计数排序
        // 因为 nums 数组比较特殊，nums 中只有 0，1，2 这 3 个元素，数字连续且不存在间断
        // 故可以直接根据 count[i] 对原数组 nums 进行赋值操作即可，简化计数排序操作
        // 省略：计数累加，申请额外数组 output
        // 注意：一般情况下，没有数据范围限定的数组 nums，不能这样做操作

        // 2.排序
        int index = 0;
        // 根据 0，1，2 这 3 个元素个数进行赋值
        for (int i = 0; i <= 2; i++) {
            int num = count[i];
            // 根据计数 num 个数，对 num 进行重赋值操作
            for (int j = 1; j <= num; j++) {
                nums[index++] = i;
            }
        }
    }

    // KeyPoint 方法二 三路快排 (一趟扫描) => 推荐使用

    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void sortColors1(int[] nums) {

        // 颜色分类之后，数组分成了 3 段，0 0 1 1 2 2 => 三路快排

        // 分区逻辑 => 背下来
        //  |  < pivot   |  = pivot | 未处理  | > pivot |
        // low         less         i      great      high

        // 类比推导
        //  | == 0 | == 1 | 未处理  | == 2 |
        // low   less     i      great    high

        int less = 0;
        int great = nums.length - 1;
        int i = 0;

        while (i <= great) {
            // 替换比较条件
            if (nums[i] == 0) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }
    }

    // 常规快排，使用递归
    // 空间复杂度 O(logn) => 不满足题目要求
    public void sortColors2(int[] nums) {
        if (nums == null) return;
        int n = nums.length;
        sort(nums, 0, n - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) return;

        int pivot = nums[high];
        int less = low;
        int great = high;
        int i = low;

        while (i <= great) {
            if (nums[i] < pivot) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }
        sort(nums, low, less - 1);
        sort(nums, great + 1, high);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

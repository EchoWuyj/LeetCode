package alg_02_train_dm._07_day_排序算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 10:47
 * @Version 1.0
 */
public class _06_315_count_of_smaller_numbers_after_self {

    /*
        315. 计算右侧小于当前元素的个数 => 类似于逆序对(54 => 5|4)
        给你一个整数数组 nums ，按要求返回一个新数组 counts 。
        数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

        示例 1：
        输入：nums = [5,2,6,1]
        输出：[2,1,1,0]

        解释：
        5 的右侧有 2 个更小的元素 (2 和 1)
        2 的右侧仅有 1 个更小的元素 (1)
        6 的右侧有 1 个更小的元素 (1)
        1 的右侧有 0 个更小的元素

        原始状态
        元素 5 2 6 1
        索引 0 1 2 3

        归并之后
        元素 1 2 5 6
        索引 3 1 0 2


        提示：
        1 <= nums.length <= 10^5
        -104 <= nums[i] <= 10^4

     */

    private static int[] indexes;
    private static int[] tmpIndexes;
    private static int[] count;

    public static List<Integer> countSmaller(int[] nums) {

        List<Integer> res = new ArrayList<>();
        if (nums == null) return res;

        int n = nums.length;
        // 记录原始数组每个元素的索引信息，方便在合并时，通过原始索引确定对应元素进行 count 计算

        indexes = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) indexes[i] = i;

        int[] tmp = new int[n];
        tmpIndexes = new int[n];
        count = new int[n];

        mergeSort(nums, 0, n - 1, tmp);

//        System.out.println(Arrays.toString(tmpIndexes)); // [1, 0, 3, 2]
//        System.out.println(Arrays.toString(count)); // [2, 1, 1, 0]

        for (int num : count) {
            res.add(num);
        }
        return res;
    }

    // 归并排序
    private static void mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        // KeyPoint 遇到递归函数，不断递进下一层，即每次都是 mergeSort 函数重新开始
        mergeSort(nums, left, mid, tmp);
        mergeSort(nums, mid + 1, right, tmp);
        merge(nums, left, mid, right, tmp);
    }

    // KeyPoint 合并 => 一般使用这种 merge 方法
    private static void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
            tmpIndexes[i] = indexes[i];
        }
        int i = left;
        int j = mid + 1;
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                // 数组排序，对应索引跟着一起排序
                nums[index] = tmp[j];
                indexes[index] = tmpIndexes[j];
                j++;
            } else if (j == right + 1) {
                nums[index] = tmp[i];
                indexes[index] = tmpIndexes[i];
                // j 到 right+1 位置，但是 i 没有越界，即 i <= mid，这种情况也是包括在内的
                count[tmpIndexes[i]] += (j - mid - 1);
                i++;
                // KeyPoint 注意：细节点
                // 一定是 [i] <= [j]，一定包括取等情况，保证'等于'时，移动 i 指针
                // 这样才能保证，[i] < [j] 时，[j] 的右侧一定是严格小于 [i]，否则会有相等值，不满足右侧小于当前元素
            } else if (tmp[i] <= tmp[j]) {
                nums[index] = tmp[i];
                indexes[index] = tmpIndexes[i];
                // 功能：计算比当前元素小的后面元素的个数
                // 通过 i 指针指向元素对应的原始索引 tmpIndexes 来进行计数
                // 如：count[3] = x，count[0] = y ...
                // 最后遍历 count 时，i = 0 到 n-1，表示索引，[i] 表示个数
                // 即使数组元素在 merge 过程中变成有序的，不影响 count[] 数组
                count[tmpIndexes[i]] += (j - mid - 1);
                i++;

                // KeyPoint 2 种 count 计数的方式，使用第二种方式，效率更高
                // 方式一 => 不推荐
                //      left  mid mid+1 right
                //       ↓     ↓   ↓     ↓
                // 计数  0 2 3 3   0 0 0 2
                // 元素 -9 3 5 6   2 4 8 9
                //         ↑       ↑
                //         i       j
                // [i] > [j]，i 到 mid 对应的 count 都要累加 1，这种方式又得 for循环一轮，增大时间复杂度

                // 方式二 => 推荐
                //      left  mid mid+1 right
                //       ↓     ↓   ↓     ↓
                // 计数  0 2 3 3   0 0 0 2
                // 元素 -9 3 5 6   2 4 8 9
                //         ↑       ↑ ↑
                //         i       j j'
                // [i] > [j]，不处理 => i 到 mid 暂时不累加 1，j 后移为 j'
                // [i] < [j']，需要处理， => j' 前面有 j'-mid-1 个比 [i] 小，更新 count[i]，加 1
                // 注意，只是计算 i 指针位置 count，后面的元素 5，6 暂时不管，后面会进行处理

            } else {
                nums[index] = tmp[j];
                indexes[index] = tmpIndexes[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 6, 1};
        System.out.println(countSmaller(array));
    }
}

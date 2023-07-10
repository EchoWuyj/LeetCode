package alg_02_train_dm._07_day_排序算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 10:47
 * @Version 1.0
 */

// KeyPoint 归并排序应用
public class _06_315_count_of_smaller_numbers_after_self1 {

    /*
        315. 计算 右侧 小于 当前元素的个数
        给你一个整数数组 nums ，按要求返回一个新数组 counts 。
        数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 nums[i] 的元素的数量。

        示例 1：
        输入：nums = [5,2,6,1]
        输出：[2,1,1,0]

        解释：
        5 的右侧有 2 个更小的元素 (2 和 1)
        2 的右侧仅有 1 个更小的元素 (1)
        6 的右侧有 1 个更小的元素 (1)
        1 的右侧有 0 个更小的元素

        分析：
        本质：类似于逆序对(54 => 5|4)

        提示：
        1 <= nums.length <= 10^5
        -104 <= nums[i] <= 10^4

     */

    // 记录原始数组每个元素对应索引信息
    // => 方便在合并时，通过原始索引确定对应元素进行 count 计算
    private static int[] indexes;
    private static int[] tmpIndexes;
    private static int[] count;

    public static List<Integer> countSmaller(int[] nums) {

        // KeyPoint 归并之前

        // nums 数组
        // 索引 0 1 2 3
        // 元素 5 2 6 1 → key

        // indexes 数组
        // 索引 0 1 2 3
        // 元素 0 1 2 3 → value

        // KeyPoint 归并之后

        // nums 数组
        // 索引 0 1 2 3
        // 元素 1 2 5 6 => num 索引对应元素 nums[index] 发生改变
        //              => key

        // indexes 数组
        // 索引 0 1 2 3
        // 元素 3 1 0 2 → value

        // 映射关系没有变化
        // 元素  索引
        //  5 -> 0
        //  2 -> 1
        //  6 -> 2
        //  1 -> 3

        List<Integer> res = new ArrayList<>();
        if (nums == null) return res;

        int n = nums.length;
        // 初始化
        indexes = new int[n];
        for (int i = 0; i < n; i++) indexes[i] = i;
        // indexes 临时数组
        tmpIndexes = new int[n];

        int[] tmp = new int[n];
        count = new int[n];
        sort(nums, 0, n - 1, tmp);

        // 一开始使用数组，方便操作，再去根据题目要求转成 List
        for (int num : count) {
            res.add(num);
        }
        return res;
    }

    // 归并排序
    private static void sort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;

        // KeyPoint 深刻理解递归
        // 若每次从 mergeSort 函数开始位置，遇到递归函数 mergeSort
        // 则不断递进下一层，再次从 mergeSort 函数重新开始，直到递归边界
        sort(nums, left, mid, tmp);
        sort(nums, mid + 1, right, tmp);
        merge(nums, left, mid, right, tmp);
    }

    // 合并排序应用 => 一般使用 merge 实现二
    private static void merge(int[] nums, int left, int mid, int right, int[] tmp) {

        // 核心：分治

        // 一一比较
        // 5 6 | 3 -9 | 2 9 | 4 8 |
        //       1
        // => 调整顺序
        // 5 6 | -9 3 | 2 9 | 4 8 |

        // 二二比较
        // 5 6  -9 3 | 2 9  4 8 |
        // 2 2           2
        // => 归并 merge 比较时，i 和 j 中选择最小值 min，使用 min 和另外一侧数组比较
        // => 调整顺序
        // -9 3 5 6  2 4 8 9

        // 四四比较
        // -9 3 5 6  2 4 8 9
        //    1 2 2

        // 所有比较累加
        // -9 3 5 6  2 4 8 9
        //    2 4 4        2  => 不是单纯求个数，需要每个索引位置，记录个数，故需要额外空间存储对应关系

        // => 调整顺序
        // -9 2 3 4 5 6 8 9

        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
            tmpIndexes[i] = indexes[i];
        }
        int i = left;
        int j = mid + 1;
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                // 数组排序，对应索引，跟着一起排序
                nums[index] = tmp[j];
                indexes[index] = tmpIndexes[j];
                j++;
            } else if (j == right + 1) {
                nums[index] = tmp[i];
                indexes[index] = tmpIndexes[i];
                // KeyPoint 需要考虑特殊边界情况
                // j 到 right+1 位置，但是 i 没有越界，即 i <= mid，
                // 此时，右侧数组全部 < nums[i]，故也是需要累加 count 的
                count[tmpIndexes[i]] += (j - mid - 1);
                i++;
            } else if (tmp[i] <= tmp[j]) {
                // KeyPoint 细节点：包括取等
                // 一定是 [i] <= [j]，包括取等情况，即 当 [i] = [j] 时，也是触发累加 count 的
                // 本质：保证 tmp[i] > tmp[j] 不含等号，保证严格小于，从而满足右侧小于当前元素
                nums[index] = tmp[i];
                indexes[index] = tmpIndexes[i];
                // num 元素 => 原始索引 tmpIndexes[i]
                // 通过 count[tmpIndexes[i]] 来计数
                count[tmpIndexes[i]] += (j - mid - 1);
                i++;
            } else {
                // tmp[i] > tmp[j] => 右侧小于当前元素 => 严格小于
                nums[index] = tmp[j];
                indexes[index] = tmpIndexes[j];
                j++;
            }
        }

        // KeyPoint 详细分析
        // 计算 右侧 小于 当前元素 的个数，有两种 count 计数的方式
        // 推荐使用第二种方式，效率更高

        // KeyPoint 方式一 => 不推荐

        //      left  mid  mid+1 right
        //       ↓     ↓    ↓     ↓
        // 计数  0 2 3 3    0 0 0 2
        // 元素 -9 3 5 6    2 4 8 9
        //         ↑        ↑
        //         i        j
        // [i] <= [j] 不满足条件，不用计算
        // [i] > [j]，满足条件，需要计算，即 [i,mid] 对应的 count 都要累加 1，这种方式得 for 循环一轮，增大时间复杂度

        // KeyPoint 方式二 => 推荐

        //      left  mid  mid+1 right
        //       ↓     ↓    ↓     ↓
        // 计数  0 2 3 3    0 0 0 2
        // 元素 -9 3 5 6    2 4 8 9
        //         ↑        ↑ ↑
        //         i        j j'
        // [i] > [j]，先不处理 => [i,mid] 对应的 count 暂时不累加 1，j 后移为 j'
        // [i] <= [j']，需要处理 => j' 前面有 j'-(mid+1) 个比 [i] 小，更新 count[i]，count[i] 加 1
        //             注意：只是计算 i 指针位置 count，后面的元素 5，6 暂时不管，后面 i 指针会进行处理

        // 注意：[i] <= [j] 情况同 [i] <= [j']，使用 j 和 j'，只是区别不同位置的 j

        //      left  mid  mid+1 right
        //       ↓     ↓    ↓     ↓
        // 计数  0 2 3 3    0 0 0 2
        // 元素 -9 3 5 6    2 4 8 9
        //           ↑        ↑ ↑
        //           i        j j'
        // => 处理方式同上

        // 总结：
        // 这种方式，只要 i 和 j 分别遍历一遍左右数组，即实现了 count 统计
        // 从而节省了多次 for 循环遍历，所以性能更好
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 6, 1};
        System.out.println(countSmaller(array));
    }
}

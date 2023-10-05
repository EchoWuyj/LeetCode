package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 15:47
 * @Version 1.0
 */
public class _05_15_ThreeSum3_推荐 {

    // KeyPoint 方法三 消除 set 转 ArrayList，new ArrayList<>(set)，从而提高性能
    public List<List<Integer>> threeSum3(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        // 数据预处理
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            // KeyPoint 手动去重-1
            // 这种方式去重前提条件：数组是有序的，相等的元素靠在一起
            // 当前 [i] 和 [i-1] 相同，则跳过，原因如下
            // 1.i-1 在前，在 i-1 这轮循环中，已经所有可能结果 result 获取
            // 2.i 在后，在 i 轮轮循环中，所有可能结果 result' 只是 result 的子集，必然存在重复三元组
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 在 i+1 ... n-1 中查找相加等于 -nums[i] 的两个数
            int target = -nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 找到一组之后，继续往后遍历
                    // KeyPoint 手动去重-2
                    // 这种方式去重前提条件：数组是有序的，相等的元素靠在一起
                    // 相邻且相等的元素，只记录一次后续再有再相等元素，通过移动 left 和 right 指针，跳过，从而实现去重
                    // KeyPoint 特别注意
                    // 经过 while 循环后，left 和 right 已经到正确去重后的位置上了，
                    // 不用在 while 循环体中，再去执行 left++ 和 right --
                    while (left < right && nums[left] == nums[++left]) ;
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}

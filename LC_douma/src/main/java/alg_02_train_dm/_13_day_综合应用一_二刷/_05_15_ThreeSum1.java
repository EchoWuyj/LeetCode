package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 21:50
 * @Version 1.0
 */
public class _05_15_ThreeSum1 {

    /*
        15. 三数之和
        给你一个整数数组 nums，判断是否存在三元组 [nums[i], nums[j], nums[k]]
        满足 i != j、i != k 且 j != k，同时还满足 nums[i] + nums[j] + nums[k] == 0
        请你返回所有和为 0 且 不重复 的三元组。注意：答案中不可以包含重复的三元组。

        示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
        解释：
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0  => [-1,0,1]
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0  => [0,1,-1]
        注意：[-1,0,1] 和 [0,1,-1] 是重复的三元组，将其舍弃

        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 => [-1,2,1]
        不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
        注意，输出的顺序和三元组的顺序并不重要。

        提示：
        3 <= nums.length <= 3000
        -10^5 <= nums[i] <= 10^5

     */

    // KeyPoint 方法一 暴力 => 超时
    // 时间复杂度 O(n^3)
    public List<List<Integer>> threeSum1(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        // 使用 set 去重
        Set<List<Integer>> set = new HashSet<>();

        // 注意：本题返回数组元素值，不是元素索引，故可以先排序，且只需要一次排序即可
        // 排序之后，相同的元素紧靠在一起
        Arrays.sort(nums); // O(nlogn)
        // O(n^3)
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // KeyPoint Arrays 数组集合类，数组转集合 => asList
                        // 在 ArrayList 集合中：元素顺序，元素值，元素个数，三者都相等
                        // => ArrayList 才算相同，这样才能通过 set 去重
                        // KeyPoint 注意：asList 方法，返回值类型 List<T>
                        // => 定义 Set<List<Integer>> 里面得是 List<Integer>，不能是具体的 ArrayList
                        set.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        // 将 set 转成 ArrayList
        return new ArrayList<>(set);
    }

    public static void test1() {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 3, 4, 5};
        // KeyPoint Set 中 equals 方法
        // Java 中的 Set 在判断元素是否重复时，使用的是对象的 equals 方法来进行比较
        // 默认情况下：equals 方法比较的是对象的引用而不是内容
        Set<int[]> set = new HashSet<>();
        set.add(array1);
        set.add(array2);
        for (int[] arr : set) {
            System.out.println(Arrays.toString(arr));
            // [1, 2, 3, 4, 5]
            // [1, 2, 3, 4, 5]
        }
    }

    public static void test2() {
        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(1, 2, 3));
        set.add(Arrays.asList(1, 2, 3));
        System.out.println("size1 => " + set.size());
        set.add(Arrays.asList(2, 1, 3));
        System.out.println("size2 => " + set.size());
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}

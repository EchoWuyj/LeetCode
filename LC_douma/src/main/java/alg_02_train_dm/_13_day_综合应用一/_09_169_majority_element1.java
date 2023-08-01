package alg_02_train_dm._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 19:12
 * @Version 1.0
 */
public class _09_169_majority_element1 {

    /*
        169. 多数元素(求众数)
        给定一个大小为 n 的数组，找到其中的多数元素。
        多数元素是指在数组中出现次数 大于 ⌊n/2⌋ 的元素。

        你可以假设数组是非空的，并且给定的数组总是存在多数元素。

        输入：[3,2,3]
        输出：3

        输入：[2,2,1,1,1,2,2]
        输出：2

        提示：
        n == nums.length
        1 <= n <= 5 * 104
        -109 <= nums[i] <= 109

        进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */

    // KeyPoint 方法一 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            // 每次计数加 1，否则 cnt 没有变化
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > n / 2) return num;
            map.put(num, cnt);
        }
        return -1;
    }

    // KeyPoint 方法二 排序查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    public static int majorityElement2(int[] nums) {
        // 既然存在众数 num，则其出现次数 count 必然大于 ⌊n/2⌋
        // 1.若 n 为奇数，n=7，n/2=3，=> count >= 4
        // 2.若 n 为偶数，n=6，n/2=3，=> count >= 4
        // 若将数组排序(全局排序)，则中间元素必然是众数
        Arrays.sort(nums);
        int n = nums.length;
        // 索引 n/2，对应元素个数 n/2 +1
        return nums[n / 2];
    }

    // for test
    public static void test1() {
        // 注意：array 是不含有众数，并不符合题意
        int[] array = {1, 1, 1, 2, 3, 3, 3};
        System.out.println(majorityElement2(array)); // 2
    }

    // KeyPoint 方法三 堆查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public static int majorityElement3(int[] nums) {

        // 局部排序，找 n / 2 小的元素 => 大顶堆

        // 注意区别：第 k 小 和 第 k 大
        // 1.第 k 小 => 从数组头开始计数
        //   [1,2,3,4,5]，第 2 小，指的是 2
        // 2.第 k 大 => 从数组尾开始计数
        //   [1,2,3,4,5]，第 2 大，指的是 4

        // k 一定是数组中点偏右的位置
        int n = nums.length;
        int k = n / 2 + 1;
        // 查找第 k 小元素 => 大顶堆
        // 类比：02_215_kth_largest_element_in_an_array2
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k + 1, (a, b) -> b - a);
        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) maxHeap.remove();
        }
        System.out.println("size：" + maxHeap.size()); // 4
        return maxHeap.peek();
    }

    // for test
    public static void test2() {
        int[] array = {2, 2, 1, 1, 1, 2, 2}; // 7
        System.out.println(majorityElement3(array));
    }

    // for test
    public static void main(String[] args) {
        test1();
        System.out.println("===");
        test2();
    }

    // 堆查找 => 另外一种实现
    public int majorityElement4(int[] nums) {
        int n = nums.length;
        int size = n / 2 + 1;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(size, (a, b) -> b - a);
        for (int num : nums) {
            if (maxHeap.size() < size) {
                maxHeap.add(num);
            } else {
                if (num < maxHeap.peek()) {
                    maxHeap.remove();
                    maxHeap.add(num);
                }
            }
        }
        // 返回大顶堆第 k+1 个元素
        return maxHeap.peek();
    }
}

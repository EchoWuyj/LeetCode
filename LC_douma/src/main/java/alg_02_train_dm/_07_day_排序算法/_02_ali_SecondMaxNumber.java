package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 19:44
 * @Version 1.0
 */
public class _02_ali_SecondMaxNumber {

    /*
        阿里面试题
        在一个很长很长的数组中，如何快速找到第二大的元素
        注意时间复杂度和空间复杂度
        => 时间复杂度，空间复杂度，尽可能的低，最好都是 O(1)

        输入：nums=[1，2，3，4]
        输出：3
     */
    public static int getSecondMax(int[] nums) {

        // 最直接的想法：先排序，降序排列，返回第二大的元素，该解法虽然可以
        // 但是时间复杂度 O(nlogn)，空间复杂度 O(n)，不满足题目要求

        // 为了降低时间和空间复杂度，联想冒泡排序，一轮冒泡能确定最大值
        // => 使用两个变量 first 和 second，存储最大值和次大值
        // 时间复杂度 O(n)，空间复杂度 O(1) => 最优解
        // 注意：必然需要遍历一遍数组才能知道第二大的元素，所以 时间复杂度 >= O(n)

        // 求的最大值，赋初值为 Integer.MIN_VALUE
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : nums) {
            // if 判断从 first 依次往下递减判断
            if (num > first) {
                // 原来最大值赋值给第二大值
                second = first;
                // 新的最大值
                first = num;
            } else if (num > second) {
                // second < num <= first，
                // 即：新的第二大值赋值给 second
                second = num;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(getSecondMax(array)); // 4

        int[] array1 = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(getSecondMax(array1)); // 5
    }
}

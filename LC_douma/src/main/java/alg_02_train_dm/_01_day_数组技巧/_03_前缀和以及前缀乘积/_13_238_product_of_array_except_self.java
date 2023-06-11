package alg_02_train_dm._01_day_数组技巧._03_前缀和以及前缀乘积;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 10:28
 * @Version 1.0
 */
public class _13_238_product_of_array_except_self {
    /*
        238. 除自身以外数组的乘积
        给你一个整数数组 nums，返回 数组 answer
        其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积

        题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
        请不要使用除法，且在 O(n) 时间复杂度内完成此题。

        提示
        2 <= nums.length <= 105
        -30 <= nums[i] <= 30

        输入: nums = [1,2,3,4]
        输出: [24,12,8,6]

        输入: nums = [-1,1,0,-3,3]
        输出: [0,0,9,0,0]

     */

    // KeyPoint 方法一 暴力解法
    // O(n^2) 超出时间限制
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            // 求左边数的乘积
            int leftProduct = 1;
            for (int j = 0; j < i; j++) {
                leftProduct *= nums[j];
            }
            // 求右边数的乘积
            int rightProduct = 1;

            // 统一 for 中的变量，左中右位置都是为 j，不能有 j 又有 i，肯定是存在问题的
            for (int j = i + 1; j < n; j++) {
                rightProduct *= nums[j];
            }

            // 将左边和右边乘积再相乘
            output[i] = leftProduct * rightProduct;
        }

        return output;
    }

    // KeyPoint 方法二 前缀积
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;

        // leftProducts[i] 表示索引 i 左侧所有元素的乘积
        int[] leftProducts = new int[n];
        // 第一个元素左边没有元素，将左侧前缀积定义为 1
        leftProducts[0] = 1;
        // i 从 1 开始，[i-1]
        for (int i = 1; i < n; i++) {
            // 左侧前缀积 leftProducts[i] 是不包括 i 元素的
            // nums[0] * num[1] ... * num[i-2] * num[i -1] = leftProducts[i]
            // nums[0] * num[1] ... * num[i-2] => leftProducts[i-1]
            // => leftProducts[i-1] * nums[i-1] = leftProducts[i]
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        // rightProducts[i] 为索引 i 右侧所有元素的乘积
        int[] rightProducts = new int[n];
        // 最后一个元素右侧没有元素，将右侧前缀积定义为 1
        rightProducts[n - 1] = 1;
        // 从右往左遍历，i 从 n-2 开始
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        int[] output = new int[n];
        for (int i = 0; i < n; i++) {
            output[i] = leftProducts[i] * rightProducts[i];
        }

        return output;
    }

    // 优化：先将中间临时结果，存储在结果数组上，后续遍历过程计算的最终结果，再去覆盖结果数组
    // => 减少循环，减少额外存储空间
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // 先将每个元素的左边全部元素的乘积存储在 output 中
        int[] output = new int[n];
        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        // 每个元素的右边所有元素的乘积存储在一个变量中
        int rightProduct = 1;
        // KeyPoint 总结：若从左往右遍历数组时，不好解决当前问题，可以尝试换个思维，从右往左遍历数组
        for (int i = n - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 output[i]，右边的乘积为 rightProduct
            output[i] = output[i] * rightProduct;
            // 更新右边乘积
            rightProduct = rightProduct * nums[i];
        }
        return output;
    }
}

package alg_02_train_dm._01_day_数组技巧_二刷._03_技巧三_前缀和以及前缀乘积;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 10:28
 * @Version 1.0
 */
public class _13_238_product_of_array_except_self_推荐 {

    /*
        238. 除自身以外数组的乘积
        给你一个整数数组 nums，返回 数组 answer
        其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积

        题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
        请不要使用除法，且在 O(n) 时间复杂度内完成此题。

        输入: nums = [1,2,3,4]
        输出: [24,12,8,6]

        输入: nums = [-1,1,0,-3,3]
        输出: [0,0,9,0,0]

        提示
        2 <= nums.length <= 105
        -30 <= nums[i] <= 30

     */

    // KeyPoint 方法一 暴力解法
    // O(n^2) 超出时间限制
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            // 1.求左边数的乘积
            int leftProduct = 1;
            for (int j = 0; j < i; j++) {
                leftProduct *= nums[j];
            }
            // 2.求右边数的乘积
            int rightProduct = 1;

            // KeyPoint 易错点：统一 for 中的循环变量
            // for 循环中，左中右位置都是为 j，不能有 j 又有 i，否则肯定是存在问题的
            for (int j = i + 1; j < n; j++) {
                rightProduct *= nums[j];
            }

            // 3.将左边和右边乘积再相乘
            res[i] = leftProduct * rightProduct;
        }

        return res;
    }

    // KeyPoint 方法二 前缀积法 => 推荐
    // 优化：将暴力解法中，两层 for 循环拆分，形成并行独立的 for 循环，从而减少时间复杂度
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;

        // KeyPoint 数据预处理
        // leftProducts[i] 表示索引 i 左侧(不包括 nums[i])所有元素的乘积
        int[] leftProducts = new int[n];
        // 第一个元素左边没有元素，将左侧前缀积定义为 1
        leftProducts[0] = 1;
        // i 从 1 开始，[i-1]
        for (int i = 1; i < n; i++) {
            // 左侧前缀积 leftProducts[i] 是不包括 nums[i] 元素的
            // 推导过程：
            // leftProducts[i] = nums[0] * num[1] ... * num[i-2] * num[i -1]
            // leftProducts[i-1] = nums[0] * num[1] ... * num[i-2]
            // => leftProducts[i] = leftProducts[i-1] * nums[i-1]
            // 记忆：由前推后：i-1 => i
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        // rightProducts[i] 为索引 i 右侧(不包括 nums[i])所有元素的乘积
        int[] rightProducts = new int[n];
        // 最后一个元素右侧没有元素，将右侧前缀积定义为 1
        rightProducts[n - 1] = 1;
        // 从右往左遍历，i 从 n-2 开始往前遍历
        for (int i = n - 2; i >= 0; i--) {
            // 同理，右侧前缀积 rightProducts[i] 是不包括 nums[i] 元素的
            // 推导过程：
            // rightProducts[i] = nums[i+1] * nums[i+2] * ... * nums[n-1]
            // rightProducts[i+1] = nums[i+2] * ... * nums[n-1]
            // => rightProducts[i] = rightProducts[i+1] * nums[i+1]
            // 记忆：由后推前：i+1 => i
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = leftProducts[i] * rightProducts[i];
        }
        return res;
    }

    // KeyPoint 方法二 前缀积法 => 优化
    // 先将中间临时结果，存储在 res 数组上，后续遍历过程计算的最终结果，再去覆盖结果数组
    // => 减少循环，降低时间复杂度
    // => 减少额外存储空间 leftProducts 和 rightProducts，降低空间复杂度
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // 先将每个元素的左边全部元素的乘积存储在 res 中
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // 每个元素的右边所有元素的乘积存储在一个变量中
        int rightProduct = 1;
        // KeyPoint 数组遍历小技巧
        // 若从左往右遍历数组时，不好解决当前问题，可以尝试换个思维，从右往左遍历数组
        for (int i = n - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 res[i]，右边的乘积为 rightProduct
            res[i] = res[i] * rightProduct;
            // 更新右边乘积
            // KeyPoint 解释说明：索引为什么是 i，而不是 i+1
            // 当 i = n-1，rightProduct = 1，执行 res[i] = res[i] * rightProduct
            // 之后更新 rightProduct = nums[n-1]，即在 i = n-2，res[i] = res[i] * rightProduct 之前
            // 更新了 rightProduct，正好满足题意
            rightProduct = rightProduct * nums[i];
        }
        return res;
    }
}

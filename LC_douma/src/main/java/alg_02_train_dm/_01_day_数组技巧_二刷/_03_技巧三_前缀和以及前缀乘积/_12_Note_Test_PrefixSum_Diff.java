package alg_02_train_dm._01_day_数组技巧_二刷._03_技巧三_前缀和以及前缀乘积;

/**
 * @Author Wuyj
 * @DateTime 2023-07-19 16:06
 * @Version 1.0
 */
public class _12_Note_Test_PrefixSum_Diff {

    // KeyPoint 前缀和 => 实现一 => 好像存在 bug，一般不使用，了解即可
    // prefixSum[i] = prefixSum[i-1] + nums[i];

    // index      0  1  2  3
    // nums       1  2  3  4
    // prefixSum  1  3  6  10

    // 第一组两个数，没法使用 prefixSum[i + k] - prefixSum[i] 计算，
    // 第一组两个数 prefixSum[k-1] => prefixSum[1] => [0]+[1]
    // 因为第一组两个数，不使用 prefixSum[i + k] - prefixSum[i]，循环次数减 1，即为 nums.length - k

    // prefixSum[3] - prefixSum[2] => nums[3]
    // => nums[0] + nums[1] + nums[2] + nums[3] -(nums[0] + nums[1] + nums[2]) = nums[3]

    // prefixSum[2] - prefixSum[0] => nums[1] + nums[2]
    // => nums[0] + nums[1] + nums[2] - nums[0] => nums[1] + nums[2]

    // KeyPoint 前缀和 => 实现二 => 作差 => 推荐使用
    // prefixSum[i] = prefixSum[i-1] + nums[i-1];

    // index      0  1  2  3  4
    // nums       1  2  3  4
    // prefixSum  0  1  3  6  10

    // KeyPoint 推论一
    // prefixSum[i] - prefixSum[i-1] = num[i-1]

    // 解释：
    // prefixSum[i] - prefixSum[i-1]
    // = prefixSum[i-1] + num[i-1] - prefixSum[i-1] = num[i-1]

    // 比如：
    // prefixSum[2] - prefixSum[1] = nums[1]
    // => nums[0] + nums[1] - nums[0] = nums[1]
    // => 3 - 1 = 2

    // KeyPoint 推论二 => 牢记
    // prefixSum[i+k] - prefixSum[i] = num[i+k-1] + num[i+k-2] + ... + num[i]
    // => 记忆：起始位置 i，终止位置 i+k-1

    // KeyPoint 推广 => 牢记
    // prefixSum[j] - prefixSum[i] = num[j-1] + ... +num[i]

    // 解释：
    // prefixSum[i+k] - prefixSum[i]
    // = prefixSum[i+k-1] + num[i+k-1] - prefixSum[i]
    // = prefixSum[i+k-2] + num[i+k-2] +num[i+k-1] - prefixSum[i]
    // = ...
    // =  prefixSum[i] + num[i] + ... + num[i+k-2] + num[i+k-1] - prefixSum[i]
    // num[i] + ... + num[i+k-2] + num[i+k-1]

    // 比如：
    // prefixSum[4] - prefixSum[2] => nums[3] + nums[2]
    // => nums[0] + nums[1] + nums[2] + nums[3] - (nums[0] +nums[1]) => nums[3] + nums[2]
    // => 10 - 3 = 7
}

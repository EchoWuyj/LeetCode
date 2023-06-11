package alg_02_train_dm._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:04
 * @Version 1.0
 */
public class _18_164_maximum_gap {

    /*
        164. 最大间距
        给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。
        如果数组元素个数小于 2，则返回 0 。
        您必须编写一个在「线性时间 O(n)」内运行并使用「线性额外空间 O(n)」的算法。

        示例 1:
        输入: nums = [3,6,9,1]
        输出: 3
        解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。

        示例 2:
        输入: nums = [10]
        输出: 0
        解释: 数组元素个数小于 2，因此返回 0。

        提示：
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^9

     */

    // KeyPoint 方法一 Java 内置排序
    public int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        // 默认升序排列 O(nlogn)
        Arrays.sort(nums);

        // 最大间距
        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }

        return maxGap;
    }

    // KeyPoint 方法三：手动实现排序 => 基数排序
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int maximumGap1(int[] nums) {

        if (nums == null || nums.length < 2) return 0;

        // 基数排序
        radixSort(nums);

        // 最大间距计算
        int maxGap = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }
        return maxGap;
    }

    // KeyPoint 基数排序 => 电话号码形式数据
    public void radixSort(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(nums, exp);
        }
    }

    // KeyPoint 计数排序 => 比赛评分，0-10 分 => 数据范围不大场景
    public void countSort(int[] data, int exp) {
        int[] count = new int[10];
        int n = data.length;
        for (int i = 0; i < n; i++) {
            int digit = (data[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int digit = (data[i] / exp) % 10;
            int k = count[digit] - 1;
            res[k] = data[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            data[i] = res[i];
        }
    }

    // KeyPoint 方法三：手动实现排序 => 桶排序
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        // 1. 找到最大最小值
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 所有元素都想等，间隔为 0，直接返回
        // 测试用例 [1,1,1,1]，
        // 间隔为 0，对应 (num - min) / gap 是不能除 gap 的
        // 显示报错：java.lang.ArithmeticException: / by zero
        if (max == min) return 0;

        // 计算 gap 逻辑
        // KeyPoint max 和 min 最开始为 nums[0]，经过一轮循环之后，max 和 min 已经改变了
        // max - min => 最大间隔
        // nums.length - 1 => 最大间隔数
        // gap = ⌈(max - min) / (nums.length - 1) ⌉ => 最大间距的最小值
        // KeyPoint 经典错误 => / nums.length -1
        // (nums.length - 1) => 整体，需要加上括号的，否则 / 优先级更高，先 / nums.length，再去减1
        int gap = (int) Math.ceil((double) (max - min) / (nums.length - 1));

        // 2. 初始化桶数组
        int bucketNum = nums.length;
        // 通过 Bucket 类，而不是 ArrayList，ArrayList 对象太重了，没有自定义的 Bucket 类更加轻量级
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }

        // KeyPoint 错误写法
        // 在增强 for 循环中，变量被隐式声明为 final，因此不能被赋值
        // bucket = new Bucket() 不会修改原始的 buckets 数组或集合
        // 使用普通的 for 循环来迭代数组或集合，并使用索引或迭代器来访问每个元素并进行修改
//        for (Bucket bucket : buckets) {
//            bucket = new Bucket();
//        }

        // 3. 将所有元素添加到对应的桶中
        for (int num : nums) {

            // 最大间距的最小值 gap，而 (num - min) / gap 将间距小于 gap 的数据放在同一个桶内了
            // 求 bucketId 和求 gap 的逻辑需要保持一致
            // 这里的 gap 是通过 max - min 求出来的，所以在求 bucketId 的时候，也需要将 num - min
            // 如果直接使用 num / gap 的话，会导致求出来的 bucketId 大于桶的个数
            int bucketId = (num - min) / gap;
            // bucketId 计算逻辑如何理解，请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I498BD

            buckets[bucketId].hasData = true;
            // KeyPoint 只要求出每个 bucket 中，最小值和最大值即可，不需要排序
            // min 和 max 中比较的都是，当前桶的 min 和 max，而不是 nums 中的 min 和 max
            // min(buckets[bucketId].min, num) √
            // min(min, num) ×
            buckets[bucketId].min = Math.min(buckets[bucketId].min, num);
            buckets[bucketId].max = Math.max(buckets[bucketId].max, num);
        }

        // 4. 计算最大间隔 => 计算桶与桶之间的间隔
        int maxGap = 0;
        int prevBucketMax = min;
        for (Bucket bucket : buckets) {
            if (!bucket.hasData) continue;
            maxGap = Math.max(maxGap, bucket.min - prevBucketMax);
            prevBucketMax = bucket.max;
        }

        return maxGap;
    }
    // KeyPoint 总结：写完代码，还是写 code view 一下，排除基本性错误，之后再出现 bug 通过 debug 方式来解决，提高效率

    // 定义 Bucket 类
    private class Bucket {
        // 是否有数据，boolean 默认是 false
        public boolean hasData = false;
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }
}

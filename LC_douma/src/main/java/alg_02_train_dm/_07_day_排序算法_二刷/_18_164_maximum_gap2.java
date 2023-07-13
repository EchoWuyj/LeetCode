package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-11 18:31
 * @Version 1.0
 */
public class _18_164_maximum_gap2 {

    // 提示：
    //  1 <= nums.length <= 10^5
    //  0 <= nums[i] <= 10^9

    // KeyPoint 方法三：手动实现排序 => 桶排序
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    // 执行用时：14 ms, 在所有 Java 提交中击败了 87.47% 的用户
    public int maximumGap(int[] nums) {

        // KeyPoint 桶排序思路

        // 桶排序 gap 计算逻辑
        // gap = ⌈(max-min)/(n-1)⌉ = 最大间距的最小值
        // max-min => 最大间隔
        // n-1 => 最大间隔数，n 为 num 长度

        // 将数值中元素间距小于 gap 所有元素放在一个桶中，且桶与桶之间是有序的
        // 优化：只要求出每个 bucket 中，最小值和最大值即可，不需要排序
        // 间隔：后一个桶最小值 - 前一个桶最大值
        // 最大间隔：出现在桶与桶之间的间隔

        // nums
        // 5 3 6 9 1 8 24
        // => (24-1)/6 = 3.8333 => 4
        // => 最大间距肯定不会小于 4

        //  3 1       5 6 8      9        24
        // bucket1   bucket2  bucket3  bucket4
        // min 1      min 5    min 9    min 24
        // max 3      max 8    max 9    max 24

        if (nums == null || nums.length < 2)
            return 0;
        // 1. 找到最大最小值
        // min 和 max 直接使用 nums[0]
        int min = nums[0];
        int max = nums[0];
        // max 和 min 最开始为 nums[0]，经过 for 循环之后，
        // max 和 min 已经成为数组 nums 的 max 和 min
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 若最小值和最大值相等，则所有元素都想等，间隔为 0，直接返回
        // 测试用例 [1,1,1,1] => gap 为 0，公式 (num - min) / gap 抛异常
        // 显示报错：java.lang.ArithmeticException: / by zero
        if (max == min) return 0;

        // 计算 gap 逻辑
        int n = nums.length;
        // KeyPoint 易错点
        // / nums.length -1 => (n- 1) => 整体作为除数，需要加上括号的
        // 否则 / 优先级更高，先 / nums.length，再去减1
        // 注意：ceil(double) 需要将 int 转成 double
        int gap = (int) Math.ceil((double) (max - min) / (n - 1));

        // 2. 初始化桶数组
        // 1 <= nums.length <= 10^5
        // 最差情况，每个元素之间恰好是 gap，故有多少个元素，初始化多少个桶
        int bucketNum = nums.length;

        // 通过 Bucket 类，而不是 ArrayList，因为 ArrayList 对象太重了，没有自定义的 Bucket 类更加轻量级
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }

        // KeyPoint 特别注意：增强 for 循环中的变量不能被赋值
        // 在增强 for 循环中，变量被隐式声明为 final，因此不能被赋值，使用普通的 for 循环

//        for (Bucket bucket : buckets) {
//            // 不会修改原始的 buckets 数组或集合
//            bucket = new Bucket();
//        }

        // 3.将所有元素添加到对应的桶中
        for (int num : nums) {

            // 本题的 gap 是通过 max - min 求出来的，故在求 bucketId 时，也需要将 num - min
            // 若直接使用 num，会导致求出来的 bucketId 大于桶的个数
            // (num - min) / gap 将间距小于 gap 的数据放在同一个桶内了
            // => bucketId 和求 gap 的逻辑需要保持一致
            int bucketId = (num - min) / gap;

            // bucketId 计算逻辑如何理解，请参考 issue：
            // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I498BD

            // 标记桶有数据
            buckets[bucketId].hasData = true;

            // 每个桶中的最大和最小值
            // KeyPoint 优化
            // 只要求出每个 bucket 中，最小值和最大值即可，故不需要排序
            // 1.通过 num 确定 bucketId，确定该 num 属于那个桶，
            // 2.再通过 bucketId 获取 buckets 中 min 和 max，再去和 num 进行比较 =>  min(buckets[bucketId].min, num) √
            // 3.而不是数组 nums 的 min 和 max 比较 =>  min(min, num) ×
            buckets[bucketId].min = Math.min(buckets[bucketId].min, num);
            buckets[bucketId].max = Math.max(buckets[bucketId].max, num);
        }

        // 4.计算最大间隔 => 计算桶与桶之间的间隔
        int maxGap = 0;
        // min 已经成数组最小值，必然在第一个桶中，为第一个桶的最小值 min
        int prevBucketMax = min;
        for (Bucket bucket : buckets) {
            // 没有数据，直接跳过
            if (!bucket.hasData) continue;
            maxGap = Math.max(maxGap, bucket.min - prevBucketMax);
            prevBucketMax = bucket.max;
        }
        return maxGap;
    }

    // KeyPoint code view 好习惯
    // 写完代码，还是写 code view 一下，排除基本性错误
    // 之后再出现 bug 通过 debug 方式来解决，提高效率

    // 定义 Bucket 类
    private class Bucket {
        // 属性设置为 public，方便后续直接调用
        // 是否有数据，boolean 默认是 false
        // => 调用桶时，对其进行判断是否为空，若桶为空，则直接跳过
        public boolean hasData = false;
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }
}

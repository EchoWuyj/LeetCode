package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-07-11 20:56
 * @Version 1.0
 */
public class _18_164_maximum_gap2 {

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (max == min) return 0;
        int n = nums.length;
        int gap = (int) Math.ceil((double) (max - min) / (n - 1));
        int bucketNum = nums.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }

        for (int num : nums) {
            int buckedId = (num - min) / gap;
            buckets[buckedId].hasNum = true;
            buckets[buckedId].min = Math.min(buckets[buckedId].min, num);
            buckets[buckedId].max = Math.max(buckets[buckedId].max, num);
        }
        int maxGap = 0;
        int preBucketMax = min;
        for (Bucket bucket : buckets) {
            if (!bucket.hasNum) continue;
            maxGap = Math.max(maxGap, bucket.min - preBucketMax);
            preBucketMax = bucket.max;
        }
        return maxGap;
    }

    private class Bucket {
        public boolean hasNum = false;
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }
}

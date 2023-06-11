package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:51
 * @Version 1.0
 */
public class _15_CutWood {
    /*
        给定一个数组 nums，每个元素代表一个木头的长度
        木头可以任意截断，从这堆木头中截出至少 k 个长度为 m 的木块。
        已知 k，求 m 的最大值

        输入：k = 5，nums = [4，7，2，10，5]
        输出：4
        解释：最多可以把数组分成 5 段长度为4的木头：4，4+3，2，4+4+2，4+1
             可以看出有 5 个 4
     */

    // KeyPoint 方法一 暴力法 => 没有头绪，直接模拟，按照题目描述硬解
    //                因为根本不知道 m 为多少算作 m 最大，只能一个一个尝试
    // 时间复杂度：O(maxValue * n)
    // 空间复杂度：O(1)
    public static int cutWood1(int k, int[] nums) {

        // 输入 nums = [4,7,2,10,5]，k = 5

        // 分析
        // m = 1，k = 27 > 5，m 可以继续增大
        // m = 2，k = 13 > 5，m 可以继续增大
        // m = 3，k = 7 > 5，m 可以继续增大
        // m = 4，k = 5 >= 5，m 可以继续增大
        // m = 5，k = 4 < 5，m 不满足题目要求
        // ...
        // m = 10，k = 1

        // 将 1 到 '最大元素值' 作为木头的长度，针对每个长度，做下面的事情
        // 1. 计算所有元素可以截取多少个这个长度的木头
        // 2. 如果截取这个长度的木头数比 k 大，则更新最大的 m 值

        // 找到数组中元素的最大值 maxValue，从而知道尝试的范围
        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        // 记录 m 的最大值
        int maxM = 0;
        // 从 1 尝试到 maxValue
        for (int m = 1; m <= maxValue; m++) {
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                // 每个元素能截取长度为 m 木块个数的总和
                cnt += nums[i] / m;
            }
            // 获取 m 的最大值
            if (cnt >= k) maxM = Math.max(maxM, m);
        }

        return maxM;
    }

    // KeyPoint 方法二 二分查找
    // 时间复杂度：O(nlog(maxValue))
    // 空间复杂度：O(1)
    public static int cutWood(int k, int[] nums) {

        // 优化思路
        // 暴力解法是在区间 [1...maxValue] 中，针对每个长度来计算能截取的木头数，即为外层 for 循环
        // 区间 [1...maxValue] 严格升序，线性查找 => 二分查找

        // 我们可以先计算 [1...maxValue] 的中间值 mid，然后计算 nums 中可以截取长度为 mid 的木头数量 cnt
        // 如果 cnt >= k 的话，说明我们可以去看看截取长度比 mid 大的情况，也就是将区间缩小一半至 [mid...maxValue]
        // 否则，我们可以看左半部分区间，即 [1...mid - 1]

        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        int left = 1, right = maxValue;
        while (left < right) {
            // 保证 m 最大 => '最后一个'，left 从左往右向 mid 移动，不用这个的话，会死循环
            int mid = left + (right - left + 1) / 2;

//            if (calWoodCnt(mid, nums) >= k) left = mid;
            // calWoodCnt(mid, nums) < k;
//            else right = mid - 1;

            // KeyPoint if 条件判断需要根据具体题目来确定
            // 找到最后一个 cnt >= k 的长度 len
            if (calWoodCnt(mid, nums) < k) { // 写成标准形式
                right = mid - 1;
            } else { // calWoodCnt(mid, nums) >= k
                left = mid;
            }
        }
        return left;
    }

    // 计算 nums 数组中截取长度为 mid 木块个数的和
    private static int calWoodCnt(int mid, int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += nums[i] / mid;
        }
        return cnt;
    }

    public static void main(String[] args) {
        test1();
        System.out.println("============");
        test2();
    }

    private static void test2() {
        int[] nums = {4, 7, 2, 10, 5, 16, 12};
        System.out.println(cutWood1(3, nums)); // 10
        System.out.println(cutWood(3, nums)); // 10
    }

    private static void test1() {
        int[] nums = {4, 7, 2, 10, 5};
        System.out.println(cutWood1(5, nums)); // 4
        System.out.println(cutWood(5, nums)); // 4
    }
}

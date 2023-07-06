package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:51
 * @Version 1.0
 */
public class _15_Byte_Dance_CutWood {

    /*
        字节跳动面试题：
        给定一个数组 nums，每个元素代表一个木头的长度
        木头可以任意截断，从这堆木头中截出至少 k 个长度为 m 的木块。
        已知 k，求 m 的最大值

        输入：k = 5，nums = [4，7，2，10，5]
        输出：4
        解释：最多可以把数组分成 5 段长度为4的木头：4，4+3，2，4+4+2，4+1
             可以看出有 5 个 4
     */

    // KeyPoint 方法一 暴力法
    // 时间复杂度：O(maxValue * n) => 时间复杂度挺高
    // 空间复杂度：O(1)
    public static int cutWood1(int k, int[] nums) {

        // 基本思路：
        // 没有头绪，直接模拟，按照题目描述硬解
        // 因为根本不知道 m 为多少算作 m 最大，只能一个一个尝试

        // 输入 nums = [4,7,2,10,5]，k = 5

        // 分析：长度为 m，个数为 k
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

        // 找到数组中元素的最大值 maxValue，从而知道 m 的尝试范围
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
            // 获取 m 的最大值 => 更新的是 m，不是 cnt，cnt 只是 if 判断条件
            if (cnt >= k) maxM = Math.max(maxM, m);
        }

        return maxM;
    }

    // KeyPoint 方法二 二分查找
    // 时间复杂度：O(nlog(maxValue))
    // 空间复杂度：O(1)
    public static int cutWood(int k, int[] nums) {

        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        // 优化思路
        // 暴力解法：在区间 [1 ... maxValue] 中，针对每个长度来计算能截取的木头数，
        // 即为外层 for 循环区间 [1 ... maxValue] 严格升序，线性查找 => 二分查找

        // 目标：找到最后一个截取的木头数量 cnt >= k 的长度 len
        // 1.先计算 [1 ... maxValue] 的中间值 mid，然后计算 nums 中可以截取长度为 mid 的木头数量 cnt
        // 2.若 k 大于 calWoodCnt(mid, nums) ，说明截取的木头数量小于 k，此时我们需要将截取长度的上界缩小，
        //   即将搜索区间缩小为左半部分 [left ... mid-1]。因此，执行 right = mid - 1。
        // 3.若 k <= calWoodCnt(mid, nums)，即截取的木头数量大于等于 k，说明当前的截取长度 mid
        //   可能是满足条件的解，但不一定是最后一个满足条件的解。为了找到最后一个满足条件的解，
        //   需要继续搜索右半部分的区间 [mid ... right]，因此保持 left = mid。

        // KeyPoint => 代码根据具体题目要求进行了调整，if 条件判断需要根据具体题目来确定

        int left = 1, right = maxValue;
        while (left < right) {

            // KeyPoint 注意事项
            // 在某些问题中，在搜索过程中，保持右边界的位置不变，而左边界向右靠拢，以便缩小搜索区间。
            // 这种情况下，我们可以使用 int mid = left + (right - left + 1) / 2 的形式来计算中间值，
            // 使得 mid 在取整时向右靠拢

            // KeyPoint 一般是这种结构的代码
//            if ( ... ) {
//                right = mid - 1;
//            } else {
//                left = mid;
//            }

            // 保证 m 最大 => '最后一个'=> 从左往右逼近，不用这个的话，会死循环
            int mid = left + (right - left + 1) / 2;

            // 原始代码
//            if (calWoodCnt(mid, nums) >= k) left = mid;
            // calWoodCnt(mid, nums) < k;
//            else right = mid - 1;

            // 写成标准形式
            if (k > calWoodCnt(mid, nums)) {
                right = mid - 1;
            } else {
                // calWoodCnt(mid, nums) >= k
                // => 保证 cnt 尽可能大，右侧可能还有更大，所以 left 右移
                left = mid;
            }
        }
        return left;
    }

    // 计算 nums 数组中截取长度为 mid 木块个数的和
    // 时间复杂度 O(n)
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

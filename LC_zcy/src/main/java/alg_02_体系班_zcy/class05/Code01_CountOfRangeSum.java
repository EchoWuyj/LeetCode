package alg_02_体系班_zcy.class05;

/**
 * @Author Wuyj
 * @DateTime 2022-09-16 20:36
 * @Version 1.0
 */

// 测试链接 https://leetcode.cn/problems/count-of-range-sum/

public class Code01_CountOfRangeSum {

    // 给定一个数组arr,两个整数lower和upper,
    // 返回arr中有多少个子数组的累加和在[lower,upper]范围上

    // 子数组:一个或连续多个数组中元素组成一个子数组
    // [1,-1,-2,3]
    //  0,1,2,3

    // 子数组
    // [0-0][0-1]0-2[0-3]  0~N-1
    // [1-1][1-2][1-3]  1~N-1
    // ....

    // 本题使用暴力的方法,时间复杂度是O(N^3)
    // 子数组的数量呈现等差数列规律,故子数组的数量的时间复杂度O(N^2),
    // 再去遍历验证每一个子数组,故遍历的代价是O(N),故总的时间复杂度是O(N^3)

    //--------------------------------------------------------------------

    // 思路
    // 1)常规是数组索引位置开始进行情况讨论的,如[0-0][0-1]0-2[0-3]
    //   但是这里以数组索引为位置结尾所达标数量 ([0-0],[1-1][0-1],[2-2][2-1][2-0]...)
    //   总的情况是一定的
    // 2)Sum[i,j]在[lower,upper]上,等价于preSum[0,j]-preSum[0,i-1]在[lower,upper]上
    // 3)假设0~i整体(preSum[i])的累加和是x,求必须以i位置结尾的子数组,有多少个在[lower,upper]上,
    //   等价于:求i之前的所有前缀和中有多少个前缀和在[x-up,x-lower]上(互补关系)
    // 4)将原问题的arr[]处理成前缀和数组sum[](sum[]全是前缀和数组,其中每个数都是前缀和),
    //   求sum数组中每个x之前,有多少数落在[x-up,x-lower]

    //-------------------------------------------------------------------

    // 总结
    // 1)前缀和知识铺垫
    // 2)原[lower,upper]->前缀和数组中每个X,求的[X-up,X-lower]
    // 3)前缀和数组sum[],之前有多少数是落在[X-up,X-lower]上的
    // 4)归并排序中求解
    // 5)merge左右,不回退,所以O(N)

    //----------------------------------------------------------------------

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 创建前缀和数组
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    // arr[L..R]已经不传进来了,只是传进来sum(前缀和数组)
    // 在原始的arr[L..R]中有多少子数组累加和在[lower,upper]上
    public static int process(long[] sum, int L, int R, int lower, int upper) {
        // 原始数组中的子数组arr[0..L]的前缀和为sum[L],判断其是否达标,即是否在[lower,upper]范围内
        if (L == R) {
            // 满足给定条件,返回1个
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        // KeyPoint 只有最开始时,数组的范围是从0开始的,后面都是给定的LR,所以起始点不再是0
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    // merge方法
    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        // 不merge,但是对于右组中每个数X,求左组中有多少个数,位于[X-upper,X-lower]之间
        int ans = 0;
        int windowL = L;
        int windowR = L;

        // for循环遍历的对象是右组的对象
        // 这里虽然for循环里面,有两个while循环,但是时间复杂度还是O(N)
        // 因为窗口的左,右边界只会往右,不会往左,做到的是不回退的统计个数
        // 故while循环发生的总次数和windowL,windowR绑定
        for (int i = M + 1; i <= R; i++) {
            // 对于右组中每个数都去计算[X-upper,X-lower]
            // arr[i]等价preSum[i],i只是索引值,而preSum[i]才是索引对应的数值
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            // windowR需要小于边界M
            // 注意arr[windowR]=max时,windowR还得往前移动一个,此时索引位置偏大1位,所以是windowR)
            // 此时windowR - windowL,得到才是两个索引之间相差的个数
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            // 通过<min的方式,
            // 实现[windowL, windowR),这里不能<=min,若等于min,windowL还要前移,这样实际索引位置就比min位置大1了
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            // [windowL, windowR)
            // [X,X)表示一个数也没有
            ans += windowR - windowL;
        }

        // 正常merge返回(计算和merge是分开写)
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}

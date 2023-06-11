package alg_02_体系班_zcy.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 11:49
 * @Version 1.0
 */
public class Code08_GetMax {

    // 从思想上理解递归:
    //      将一件事分解掉,将大事分解成若干件小事,将每件小事的答案求解出来
    //      通过决策过程将小事的答案得大事的答案
    // 推荐:画出递归的思维导图

    //-----------------------------------------------------

    // 求数组arr[L..R]中的最大值,怎么用递归方法实现
    //  1)将[L..R]范围分成左右两半,左[L..Mid] 右[Mid+1..R]
    //  2)左部分求最大值,右部分求最大值
    //  3)[L..R]范围上的最大值,是max{左部分最大值,右部分最大值}
    //注意：2)是个递归过程,当范围上只有一个数,就可以不用再递归了

    // [4,6,3,7]
    //  0,1,2,3

    //         f(0,3)
    //          ↙ ↘ max
    //      f(0,1)   f(2,3)
    //       ↙↘ max   ↙↘ max
    // f(0,0) f(1,1) f(2,2) f(3,3)

    // ----------------------------------------------------

    // 递归函数通过Master公式来确定时间复杂度
    // 公式:T(N)=aT(N/b)+O(N^d),其中a,b,d是常数
    //     每个子问题的规模都一样N/b
    //     每个子问题调用了a次
    //     除了子问题调用之外,剩下的时间复杂度为O(N^d)

    // 1)如果log(b,a) > d –> 时间复杂度为:O(N^log(b,a))
    // 2)如果log(b,a) = d –> 时间复杂度为:O(N^d*logN)
    // 3)如果log(b,a) < d –> 时间杂度为:O(N^d)

    // 下面例题:T(N)=2T(N/2)+O(N^0)
    // log(2,2)=1>0 即满足1)的情况
    // 复杂度为O(N^log(2,2))=O(N)

    //---------------------------------------------------

    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr, int L, int R) {
        // arr[L..R]范围上只有一个数,直接返回,base case
        if (L == R) {
            return arr[L];
        }
        // L...R 不只一个数
        // mid = (L + R) / 2
        int mid = L + ((R - L) >> 1); // 中点

        // 分别在两个不同的区间上调用递归,不一定需要将中点传入到process中
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}

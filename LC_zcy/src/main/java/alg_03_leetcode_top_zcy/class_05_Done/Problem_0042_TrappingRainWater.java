package alg_03_leetcode_top_zcy.class_05_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-19 9:43
 * @Version 1.0
 */

// 接雨水
public class Problem_0042_TrappingRainWater {
    /*
        思路:i位置上能留下几格水,这是由i左右两侧的最大值中的较小值(瓶颈)决定的

         [i]表示i位置的高度
         10 | i | 20  => 最多留下10格水([i]=0)
         水量[i]=min{max{不包括i位置左侧},max{不包括i位置右侧}}-[i]
         优化:避免i位置的高度值[i]都是大于左右两侧的max,若按照上面的公式计算,则会出现负的水量,此时应该是0格水
         max{0,水量[i]=min{max{不包括i位置左侧},max{不包括i位置右侧}}-[i]}

         KeyPoint 解法一:预处理,辅助数组

          0    i-1 i i+1  N-1
            max         max

          原数组                   3 2 5 2 6 2 2 9 2

          left数组(从左往右遍历)   3 3 5 5 6 6 6 9 9   表示0-i-1位置的max

          right数组(从右往左遍历)  9 9 9 9 9 9 9 9 2   表示i+1-N-1位置的max

         KeyPoint 解法二:最优解
         1) 0和N-1位置不管是什么值都是没有水的
         2) L左侧(不包括L)已经遍历过的最大值max和R(不包括R)右侧已经遍历过的最大值,那侧小则结算那侧(相等则那侧都是可以结算的)
            故这里先结算左侧的水量.因为对于1位置,左侧的最大值是真实的,右侧的最大值可能不是真实的(若存在>10的情况)
            所以1位置的瓶颈是7,因此可以结算1位置的水量.

            7 13                   10
            0 1 2              N-2 N-1
              L                 R
              7-13=-6 优化,应该是0格水

         3) L++,移动到2位置,结算N-2位置的水量

             7 13                    10
             0 1 2              N-2  N-1
                 L               R

     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int n = height.length;
        int l = 1;
        int leftMax = height[0];
        int r = n - 2;
        int rightMax = height[n - 1];
        int water = 0;

        // l和r重合,则只剩该位置没有结算
        // 10  3  7
        // 0   1  2
        //  l和r重合
        while (l <= r) {
            // leftMax=rightMax并入左侧结算
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax - height[l]);
                // 左侧结算后,需要更新leftMax,同时l右移
                leftMax = Math.max(leftMax, height[l++]);
            } else {
                water += Math.max(0, rightMax - height[r]);
                rightMax = Math.max(rightMax, height[r--]);
            }
        }
        return water;
    }
}

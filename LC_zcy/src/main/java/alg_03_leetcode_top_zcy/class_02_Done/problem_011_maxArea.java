package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-15 12:59
 * @Version 1.0
 */

// 盛最多水的容器
public class problem_011_maxArea {
    public int maxArea(int[] height) {

        // 左右两侧谁小结算谁,每个杆求的可能不是该杆的最优解,而是尝试找寻可能的更大的解
        // 即使不是最优解也没事,因为最优解之前已经求过了

        //  8       10 7 7 7 7 7    数值
        //  0       i         i+5   索引
        // 0位置结算,此时水量8*(i-0),此时该水量不是0位置的最优解
        // 0位置最优解:8*(i+5-0),之前在i+5位置已经考虑过了,并不会更新max值

        int max = 0;
        int l = 0;
        int r = height.length - 1;

        // 左右两侧双指针
        // l和r重合,则没法接水,while停止
        while (l < r) {
            // max值只要变大,则更新
            // [l]和[r]谁低谁就是瓶颈,则以其为标准
            // l和r之间的距离,r-l,排除l断点之外的长度
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }
}

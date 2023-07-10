package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 13:55
 * @Version 1.0
 */
public class _02_888_fair_candy_swap {
     /*
        888 号算法题：公平的糖果棒交换
        Alice 和 Bob 有 不同大小 的糖果棒：
        A[i] 是爱丽丝拥有的 第 i 根糖果棒的大小，
        B[j] 是鲍勃拥有的 第 j 根糖果棒的大小。

        因为他们是朋友，所以他们想交换一根糖果棒，
        这样交换后，他们都有相同的糖果总量 (一个人拥有的糖果总量：是他们拥有的糖果棒大小的总和)

        返回一个整数数组 ans，其中
        ans[0] 是 Alice 必须交换的糖果棒的大小，
        ans[1] 是 Bob 必须交换的糖果棒的大小。

        如果有多个答案，你可以返回其中任何一个。保证答案存在。

        输入：A = [1,2], B = [2,3]
             交换一根糖果棒 => [2,2], B = [1,3] => 相同的糖果总量
        输出： [1,2]

        输入：A = [1,2,5], B = [2,4]
             交换一根糖果棒 => [1,2,4], B = [2,5] => 相同的糖果总量
        输出：[5,4]

        1 <= A.length <= 10000
        1 <= B.length <= 10000
        1 <= A[i] <= 100000
        1 <= B[i] <= 100000
        保证爱丽丝与鲍勃的糖果总量不同。
        答案肯定存在。

        KeyPoint 分析
        思路：
        爱丽丝糖果总数：sumA
        鲍勃糖果总数：sumB
        爱丽丝给 x 大小糖果给鲍勃，鲍勃给 y 大小糖果给爱丽丝
        有公式 sumA - x + y = sumB - y + x
               => x = y + (sumA - sumB) / 2

        对于 B 中任意 y，只要 A 中存在一个数 x
        满足 x = y + (sumA - sumB) / 2，那么 (x，y) 就是一个解
     */

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        for (int a : A) sumA += a;
        int sumB = 0;
        for (int b : B) sumB += b;

        // 固定值，计算一次即可，避免多次重复计算，从而影响性能
        int delta = (sumA - sumB) / 2;

        // 高效查找，使用哈希表来查找
        Set<Integer> set = new HashSet<>();
        for (int num : A) set.add(num);

        int[] ans = new int[2];
        // 注意：字符对应关系
        // Alice => A => x
        // Bob => B => y

        // 遍历 B 中每个元素 y，通过公式计算 x，x = y + (sumA - sumB) / 2
        // 再判断 A 是否存在 x，若存在将 x 和 y 分别记录 res[0] 和 res[1]
        for (int y : B) {
            int x = y + delta;
            if (set.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
}

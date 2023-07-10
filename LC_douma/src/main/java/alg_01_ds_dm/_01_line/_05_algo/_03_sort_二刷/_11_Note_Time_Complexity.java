package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 14:56
 * @Version 1.0
 */
public class _11_Note_Time_Complexity {
    /*

        1.加法规则(多项相加，只保留最高阶的项，且系数变为1)
            T(n) = T1(n) + T2(n) = O(f(n)) + O(g(n)) = O(max(f(n) , g(n)))

        2.乘法规则(多项相乘，都保留)
            T(n) = T1(n) × T2(n) = O(f(n)) × O(g(n)) = O(f(n) × g(n))

        1、顺序执行代码中语句只会影响 常数项，可以忽略
        2、循环语句中，只需挑循环中的一个基本操作分析它的执行次数和 n 的关系即可；
        3、如果有多层嵌套循环，只需关注最深层循环循环了几次

        O(1) < O(log2n) < O(n) < O(nlog2n) < O(n2) 对应图像要牢记

        参考博客
        https://blog.csdn.net/qq_43290883/article/details/127172599
        https://blog.csdn.net/zhangt766/article/details/126611255

            
     */

    // KeyPoint 相同的数据规模，则取最大时间复杂度
    // 时间复杂度 O(n^2)
    public static long test1(int n) {

        int sum1 = 0;
        // O(1)
        for (int i = 1; i <= 10000; i++) {
            sum1 += i;
        }

        int sum2 = 0;
        // O(n)
        for (int i = 1; i <= n; i++) {
            sum1 += i;
        }

        int sum3 = 0;
        // O(n^2)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum3 += i;
            }
        }
        return sum1 + sum2 + sum3;
    }

    // KeyPoint 不同的数据规模，则时间复杂度相加
    // 时间复杂度 O(n+m)
    public static long test2(int n, int m) {
        int sum1 = 0;
        // O(1)
        for (int i = 1; i <= 10000; i++) {
            sum1 += i;
        }

        int sum2 = 0;
        // O(n)
        for (int i = 1; i <= n; i++) {
            sum1 += i;
        }

        int sum3 = 0;
        // O(m)
        for (int i = 1; i <= m; i++) {
            sum1 += i;
        }

        return sum1 + sum2 + sum3;
    }
}

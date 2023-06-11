package alg_02_体系班_zcy.class27;

import javax.sql.rowset.serial.SQLOutputImpl;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 21:45
 * @Version 1.0
 */
public class Code04_ClimbingStairs {
    /*
        假设你正在爬楼梯,需要n阶你才能到达楼顶
        每次你可以爬1或2个台阶,你有多少种不同的方法可以爬到楼顶呢？

        第n个台阶只能从第n-1或者n-2个上来
        在n层台阶上,要么在n-1层台阶,迈1步上来
                   要么在n-2层台阶,迈2步上来

        => 到第n-1个台阶的走法 + 第n-2个台阶的走法 = 到第n个台阶的走法
        F(N)=F(N-1)+F(N-2)
        F(N)=3F(N-1)-7F(N-2)形如斐波那契数列严格递推式,都是存在O(logN)解
    */
    public static int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        // KeyPoint 初始值和传统斐波那契数列的初始值不一样
        //      需要将F2和F1单独判断
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        // res = 矩阵中的1
        int[][] tmp = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    // 若是没有时间复杂度要求,就使用这种写法,比较好理解
    // 借鉴斐波那契数列迭达方式的写法,但是具体实现上还是有不一样的
    public static int climbStairs1(int n) {
        // 将索引下标和F(n)的n对应好,将数组多申请一个单位,空出0位置
        int[] nums = new int[n + 1];
        // i直接从1开始,空出0位置
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == 2) {
                nums[i] = i;
            } else {
                nums[i] = nums[i - 1] + nums[i - 2];
            }
        }
        return nums[n];
    }

    public static int climbStairs2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return n;
        }

        int res = 2;
        int pre = 1;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(10));
        System.out.println(climbStairs1(10));
    }
}

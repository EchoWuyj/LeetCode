package alg_02_体系班_zcy.class25;

import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 15:59
 * @Version 1.0
 */
public class Code03_GasStation {

    /*
           加油站的良好出发点问题
           N个加油站组成一个环形,给定两个长度都是N的非负数组gas和cost(N>1),
           gas[i]代表第i个加油站存的油可以跑多少千米
           cost[i]代表第i个加油站到环中下一个加油站相隔多少千米。

           假设你有一辆油箱足够大的车,初始时车里没有油。
           如果车从第i个加油站出发,最终可以回到这个加油站,那么第i个加油站就算良好出发点,否则就不算。
           请返回长度为N的boolean型数组res,res[i]代表第i个加油站是不是良好出发点

           规定只能按照顺时针走,也就是i只能走到i+1,N只能走到1

           gas [ 1 1 3 1 ]
           cost[ 2 2 1 1 ]
                 a b c d

           a ×
           b ×
           c √
           d ×

           使用gas-cos加工一个数组
           arr [-1 -1 2 0]
           原问题 => 从a出发中途(每个节点)累加和,只要低于0(严格)以下,则车熄火


           arr [-2 -3 4 -2 6 -1]

           暴力解:从每个位置(N)出发遍历一圈(N)累和,时间复杂度O(N^2)

           int[] arr = {1, 2, 3, 4, 5};
           int n = arr.length;
           int k = 2; // 假设要向右移动 2 个位置

           for (int i = 0; i < n; i++) {
               // 计算移动后的位置,通过%来实现循环
               int j = (i + k) % n;
               System.out.println(arr[j]);
            }


          时间复杂度O(N)解

     */

    // 这个方法的时间复杂度O(N),额外空间复杂度O(N)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }
}

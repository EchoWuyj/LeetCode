package alg_02_体系班_zcy.class20_DP_Done;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 11:02
 * @Version 1.0
 */
public class Code03_Coffee {

    /*
        (京东原题)
        数组arr:表示几个咖啡机,这几个咖啡机生产一杯咖啡所需要的时间就是数组中的值,
                arr=[2,3,7]
                表示第一台咖啡机生产一杯咖啡需要2单位时间,第二台需要3单位时间,第三台需要7单位时间
        int N:表示有N个人需要用咖啡机制作咖啡(并发=>同时要),每人一杯,同时假设制作完咖啡后,喝咖啡时间为0,一口闷
        int a:表示用洗碗机洗一个咖啡杯需要的时间(串行运行)
        int b:表示咖啡杯也可以不洗,自然晾干的时间(并行运行)
              => 咖啡杯要么洗,要么晾干

        现在,请你求出这N个人从开始用咖啡杯制作咖啡,到杯子洗好或者晾干的最少时间？
             => 即从第一个人开始冲咖啡计时,到最后一个人喝完咖啡,并把杯子全部洗干净,最少需要多少时间？

        整体大致分为两个部分:
          1)最早喝完咖啡时间点
          2)洗杯子

         1)需要计算出每个人最早喝完咖啡的时间点
           => N个人一开始是无差别的,但尽量保证每个人能尽早喝完咖啡
           => 通过'小根堆'模拟排队过程,从而实现每个人最优排队策略

             咖啡机 arr [3,1,7]
                        0 1 2

             小根堆 => timePoint 冲一个咖啡花费的时间
                       workTime 当前咖啡机在什么时间才是空闲

                   => timePoint + workTime 进行排序
                       当前堆顶的咖啡机就是最佳的选择

            构建小根堆
             [0,1]  0号时间点可以用,泡咖啡需要1分钟
             [0,3]
             [0,7]

             N个人一起来,每个人依次选择最优咖啡机排队,泡咖啡

              0号人 拿出[0,1] (0+1)号时间喝完  放回[1,1] -> 小根堆
                                                          [1,1]
                                                          [0,3]
                                                          [0,7]

              1号人 拿出[1,1] (1+1)号时间喝完  放回[2,1] -> 小根堆
                                                          [2,1]
                                                          [0,3]
                                                          [0,7]

              2号人 拿出[2,1] (2+1)号时间喝完  放回[3,1] -> 小根堆
                                                          [0,3]
                                                          [3,1]
                                                          [0,7]

              3号人 拿出[0,3] (0+3)号时间喝完  放回[3,3] -> 小根堆
                                                          [3,1]
                                                          [3,3]
                                                          [0,7]

                 1              3            7
               0号人  1点     3号人  3点
               1号人  2点
               2号人  3点


          2)根据每个人冲好咖啡的时间点,开始洗杯子(因为咖啡是一口喝完的,冲好咖啡的时间点,也就是洗杯子的时间点)

            drinks [ 2 4 9 ] 喝完咖啡时间(最优解)
                     0 1 2     人
                     
            对于每个杯子来说,只有两种选择,要么是机器洗,要么就是自然晾干
            我们先用递归的方法来解,然后在递归的基础之上,再改进为动态规划
     */

    // 定义节点
    public static class Machine {
        // 冲一个咖啡花费的时间
        public int timePoint;
        // 当前咖啡机在什么时间才是空闲
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    // 定义比较器(oj需要记住)
    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            // 将这两个参数加起来作为参数,来组织成小根堆
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    // KeyPoint 方法一:暴力递归
    // a:表示用洗碗机洗一个咖啡杯需要的时间(串行运行)
    // b:表示咖啡杯也可以不洗,自然晾干的时间(并行运行)
    public static int minTime1(int[] arr, int n, int a, int b) {

        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        // 将所有咖啡机打包放入堆中
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        // n是总人数
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            // 咖啡泡好时间
            cur.timePoint += cur.workTime;
            // 咖啡泡好时间 => i号人喝完的时间
            drinks[i] = cur.timePoint;
            // 压到小根堆中
            heap.add(cur);
        }
        return bestTime(drinks, a, b, 0, 0);
    }

    /**
     * @param drinks 所有杯子可以开始洗的时间
     * @param wash   单杯洗干净的时间(串行)
     * @param air    挥发干净的时间(并行)
     * @param index  当前index号人的咖啡杯
     * @param free   洗碗机什么时候可用
     * @return drinks[index..]都变干净, 最早的结束时间
     */
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }

        // KeyPoint 往后考虑index号咖啡杯处理逻辑时,将其当做任意时刻下x号咖啡杯,思考其处理逻辑
        //      对于每个杯子来说,只有两种选择,要么是机器洗,要么就是自然晾干

        // 1)index号杯子 => 决定洗
        // 取max是因为:只有index号咖啡喝完,同时洗的机器可用,此时才能洗
        int selfClean1 = Math.max(drinks[index], free) + wash;
        // index+1号咖啡杯的free是要等index号咖啡杯的selfClean1
        int restClean1 = bestTime(drinks, wash, air, index + 1, selfClean1);
        // 木桶原理(所有杯子都变干净的时间)
        int p1 = Math.max(selfClean1, restClean1);

        // 2)index号杯子 => 决定挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);

        return Math.min(p1, p2);
    }

    // KeyPoint 方法二:dp(贪心+优良尝试)
    public static int minTime2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return bestTimeDp(drinks, a, b);
    }

    // 可变参数index和free
    // index:0~N
    // free:不能直观得到范围(业务限制模型),通过人为想限制,将free的大小估计出来
    //   => 最坏的情况:所有的杯子都是用机器洗,那么free最大的取值范围就是机器洗完所有杯子的时间
    // KeyPoint 可变参数index和free在dp的填表中,所以要将不可变参数传入
    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int N = drinks.length;
        int maxFree = 0;
        // 估算maxFree值
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
            // 涉及咖啡是否喝完,洗碗机是否空闲,所以maxFree不是简单 N*wash
            //  [1      50      51      70      72]  洗一杯咖啡3分钟
            //   0      1       2       3       4
            //   4      53      56      73     76     maxFree
            //          ↑等咖啡喝完      ↑等咖啡喝完
            //                  ↑等洗碗机空闲    ↑等洗碗机空闲
            //  +wash表示加上洗的时间
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        // dp[N][..]=0;
        // 一般位置依赖关系是:index->index+1
        for (int index = N - 1; index >= 0; index--) {
            //  KeyPoint 可以取到maxFree的,不能遗漏
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + wash;

                // KeyPoint 越界情况,该状态不会被递归所调用,即后面也就不用填了(类似:l>r不用填的情况)
                if (selfClean1 > maxFree) {
                    // 双层for循环,在内层循环中当满足某些条件时使用了break,只结束内层循环(就近原则)
                    break;
                }

                // KeyPoint 以后凡是dp[x][y]的索引,需要考虑x,y是否是越界的

                // index号杯子 => 决定洗
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);

                // index号杯子 => 决定挥发
                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    // KeyPoint 方法三:验证方法
    // 彻底的暴力,很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一:当前index号咖啡杯,选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二:当前index号咖啡杯,选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

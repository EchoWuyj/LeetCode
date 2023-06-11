package alg_02_体系班_zcy.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 0:05
 * @Version 1.0
 */
public class Code01_CoverMax {

    // 线段最大重合问题(用堆实现)
    // 给定很多线段,每个线段都有两个整数[start,end]
    // 表示线段开始位置和结束位置,左右都是闭区间
    // 规定：
    // 1)线段的开始和结束位置一定都是整数值
    // 2)线段重合区域的长度必须>=1
    // 返回线段最多重合区域中包含了几条线段
    // (只是需要确定几条线段,并不需要确定线段范围)

    // 实现一(暴力方法)
    // 1)找出所有线段中的最大值和最小值
    // 2)在最大值和最小值中每隔0.5进行判断,判断该点是否在线段中
    public static int maxCover1(int[][] lines) {
        // 记住这是常用的处理方式!
        // min对应MAX_VALUE,max=MIN_VALUE
        // 一定是min->MAX_VALUE,max->MIN_VALUE
        // 如果min->MIN_VALUE,永远不会有值比MIN_VALUE更小,max也是同样
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 二维数组,一维数组中存着一维数组
        for (int i = 0; i < lines.length; i++) {
            // start的最小值
            min = Math.min(min, lines[i][0]);
            // end的最大值
            max = Math.max(max, lines[i][1]);
        }

        int cover = 0;
        // O((max-min)*N)
        // 计算某个点在所有的线段中,那先线段是满足的
        for (double p = min + 0.5; p < max; p += 1) {
            // 记录单个节点满足线段的次数
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    // 实现二
    // 1)线段的开始位置进行排序(比较器),从小到大
    // 2)准备小根堆,依次按顺序,对每个线段判断
    //  [1,7],<=1的数从小根堆中弹出,再将7加入小根堆,此时小根堆中有几个数,就是该线段的答案,1(7)
    //        1代表:重合区域以1为左边界,有多少条线段越过左边界往右
    //  [2,3],<=2的数从小根堆中弹出,再将3加入小根堆,此时小根堆中有几个数,就是该线段的答案,2(3,7)
    //        2代表:重合区域以2为左边界,有多少条线段越过左边界往右
    //  [4,6],<=4的数从小根堆中弹出,故3弹出,再将6加入小根堆,此时小根堆中有几个数,就是该线段的答案,2(6,7)
    //  [4,5],<=4的数从小根堆中弹出,再将5加入小根堆,此时小根堆中有几个数,就是该线段的答案,3(5,6,7)
    //   ...
    // 3)最后比较大小,取max
    public static int maxCover2(int[][] m) {

        // 定义线段数组
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            // 数据封装
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // O(N*logN)
        Arrays.sort(lines, new StartComparator());

        // 小根堆(默认小根堆),存储每一条线段的结尾数值
        // 使用小根堆进行动态调整
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        // for循环,遍历线段数组,每个线段一次,可以认为N个
        for (int i = 0; i < lines.length; i++) {
            // lines[i]->cur,在黑盒中,把<=cur.start东西都弹出
            // 线段结尾最多进一次,最多出一次小根堆,进出的代价都是O(logN)
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            // 更新全局max,O(1)
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // 定义线段类
    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    //---------------------------------------------------

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

        // 演示
        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构,heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        //-----------------------------------------------

        // for test
        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}

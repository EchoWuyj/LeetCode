package alg_02_体系班_zcy.class25;

import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 15:53
 * @Version 1.0
 */
public class Code01_SlidingWindowMaxArray {


    /*
        滑动窗口(窗口内最大值/最小值结构)

             数值  6 5 2 1 7
             索引  0 1 2 3 4
        L(R)

        原则:L不能跑到R的右侧
          1)任何时刻,R++(R不能回退),R右动,数会从右侧进入窗口
          2)任何时刻,L++(R不能回退),R右动,数会从左侧进入窗口

         窗口内最大值更新结构(双端队列)
         双端队列首尾都可以进行进出,时间复杂度O(1)
         需要保证双端队列从头到尾是按照从大到小排序(必须严格大小,即使相同的数也是不留的)

        窗口内最大值更新结构
        1) 加数规则
             [6 4 2 3 5 7 0 5]
              0 1 2 3 4 5 6 7
            ======================
            L R
              6从尾进双端队列
              窗口内最大值:双端队列获取头部值
            ======================
            L   R
                4从尾进双端队列
            ======================
            L     R
                  2从尾进双端队列
            ======================
            L      R
                   a) 3不能直接从尾进双端队列
                   b) 2从尾进双端队列弹出,凡是弹出的元素都不用管了
                      =>3对应索引位置比2对应索引位置要大,说明3更完过期,且数值3更大,故弹出的2可以不用管
                   c) 4>3直接从尾进双端队列(必须严格从大到小关系,相等也不留)

             --------------
                  6 4 3
                  0 1 3
             --------------
                  双端队列

            双端队列意义:假设此时若让窗口依次缩小(只能L++,R不能回退),那些位置的数会依次成为窗口内最大值

        2) 减数规则
           L右扩,则判断双端队列的头部下标是否过期?
                 若头部下标已经过期,从头部弹出0位置的6,此时双端队列就更新好了


        时间复杂度分析:
        假设L和R会划过数组中所有数字(N),对于i位置x,最多进一次,最多出一次
        窗口在运动中,双端队列更新的总代价O(N),均摊到每个数的代价是O(1)

        ---------------------------------------------------------------------------------

        题目描述:
        假设一个固定大小为W的窗口,依次划过arr,返回每一次滑出状况的最大值
        例如 arr = [4,3,5,4,3,3,6,7], W=3
        返回:[5,5,5,4,6,7]

     */

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        // 返回结果数组大小
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            // 通过遍历方式获取最大值
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        // 有效性判断
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // qmax窗口最大值的更新结构
        // qmax里面放下标,方便检查过期
        LinkedList<Integer> qmax = new LinkedList<Integer>();

        // 返回结果数组大小
        int[] res = new int[arr.length - w + 1];
        // index给结果数组专用
        int index = 0;

        // R从0开始,表示0位置数需要进行入窗口
        for (int R = 0; R < arr.length; R++) {

            // qmax不为空,同时只要qmax尾部的值<=arr[R],直接弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);

            // 判断双端队列头部下标是否为过期下标(R在不断后移中,也就是窗口不断后移,所以有这样的可能)
            // R-w窗口过期下标,R=0,w=3,R-w=-3,比如-3 -2 -1 0 则-3位置数过期了
            if (qmax.peekFirst() == R - w) {
                qmax.pollFirst();
            }
            // 一开始窗口没有形成,直接跳过,只有窗口形成之后才去收集答案
            if (R >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

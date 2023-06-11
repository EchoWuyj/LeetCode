package alg_02_体系班_zcy.class25;

import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 15:58
 * @Version 1.0
 */
public class Code02_AllLessNumSubArray {

    /*
        给定一个整型数组arr,和一个整数num,某个arr中的子数组sub
        如果想达标,必须满足:sub中最大值–sub中最小值<=num,返回arr中达标子数组的数量
        注意:子数组是连续的


        结论1:对于[L...R]范围达标,那么[L...R]上的子数组都达标
              [L...R]max-[L...R]min <= num
               => 子数组:[L'...R']max <= [L...R]max
                         [L'...R']min >= [L...R]min
               => [L'...R']max-[L'...R']min <= num

        结论2:对于[L...R]范围不达标,那么L往左扩和R往右扩后的[L'...R']也不达标
               扩展后[L'...R']min <= [L...R]min
                     [L'...R']max >= [L...R]max
                     [L'...R']max-[L'...R']min 必然不会<= num

        思路:
          1)建立两个双端队列,一个是窗口最大值双端队列,一个是窗口最小值双端队列(从头部到尾部,按照从小到大顺序排列)
            使用两个双端队列来维持一个窗口的最大值和最小值
          2)窗口从0开始,R一直扩,每次扩一个位置,qmax和qmin都会更新,判断是否仍然达标,达标继续扩展,不达标就停止
            因为此时R再往后扩,其结果也是不达标的,所以直接停止即可(结论2),故可以得到本次子数组(以0开头)的达标数(结论1)
          3)缩小窗口L加1,qmax和qmin都会更新,R继续往右一直扩,周而复始,因为窗口滑动(L,R)不会回退,整体O(N)
     */

    // 暴力的对数器方法 O(n^3)
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        // 枚举所有子数组
        // [0,0],[0,1]...[0,N-1]
        // [1,1],[1,2]...[0,N-1]
        // ...
        // [N-2,N-1]
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                // 遍历找最大值和最小值
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        // R不回退
        int R = 0;
        // 依次尝试窗口[0..],[1..]开头
        // 窗口[L,R) => [0,0)=> 说明窗口内没有数
        for (int L = 0; L < N; L++) {

            // R往右扩,但是不能越界超过N
            while (R < N) {

                // 最大值更新结构
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);

                // 最小值更新结构
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);

                // [L..R]初次不达标,然后停止
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    // [L..R]依旧达标,然后R++,右扩
                    R++;
                }
            }
            // 一共有R-L个达标,R是首次不达标位置
            count += R - L;
            // max和min检查过期,若L即将过期,将其清除
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }

            //     7  6  2  0
            //     0  1  2  3
            //     L        R     max-min<=5

            // max 7  6  2  0
            //     0  1  2  3
            // min          3
            //     0  1  2  3

            // 当R到3位置,就是首次不达标位置,7-0>5,故这轮达标个数3-0=3
            // L到1位置,此时7已经过期了,再去检查max和min,发现max需要变化

            // max    6  2  0
            //        1  2  3
            // min          3
            //     0  1  2  3
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

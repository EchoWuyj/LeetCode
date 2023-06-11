package alg_01_新手班_zcy.class03;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-08-31 17:24
 * @Version 1.0
 */
public class Code01_BSExist {

    // 二分搜索,arr保证有序
    // 注意这里是返回是否存在,有时是返回对应的索引
    public static boolean find(int[] arr, int num) {

        // KeyPoint 边界条件没有考虑
        if (arr == null || arr.length == 0) {
            return false;
        }

        // 只有一个元素的数组不能直接返回true
//        if (arr.length < 2) {
//            return true;
//        }

        int L = 0;
        int R = arr.length - 1;

        // L<=R是有效范围
        //   L和R指针重合正好指向数组中某个元素
        //   该数可能是需要搜索的数,所以while循环中可以取等
        // L>R则跳出while循环

        while (L <= R) {
            // 除法向下取整 9/2=4
            int mid = (L + R) / 2;
            if (arr[mid] == num) {
                return true;
                // 变化的都是L和R指针,确定左右范围
                // KeyPoint 说明num在右侧,必然是修改L的起始范围
            } else if (arr[mid] < num) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // maxSize=9 ; maxSize+1=10 ;
        // 10*Math.random() 的取值范围[0,10)->实际范围[0,9],一共1可选10个数
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // 随机产生一个数值,后面的是具体的随机算法
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        // 定义一个Flag
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            // 随机产生一个数值,后面的是具体的随机算法
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != find(arr, value)) {
                System.out.println("出错了！");
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}

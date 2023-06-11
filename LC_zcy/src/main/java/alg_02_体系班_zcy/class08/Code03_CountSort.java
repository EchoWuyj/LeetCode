package alg_02_体系班_zcy.class08;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 18:15
 * @Version 1.0
 */

public class Code03_CountSort {

    // 计数排序
    // 1)被排序的数有范围的,即数据范围特殊
    // 2)计数排序是一个非基于比较的排序算法,元素从未排序状态变为已排序状态的过程,
    //   是由额外空间的辅助和元素本身的值决定的

    // 桶排序核心思想
    // 1)将要排序的数据,分到几个有序的桶里,每个桶在分别进行排序
    // 2)每个桶排序完成后,再把每个桶里的数据按照顺序依次取出,组成新的序列,该序列就是排好序的序列

    // 基于比较的排序
    //    冒泡排序,选择排序,堆排序,快速排序,归并排序,SHELL排序
    //    使用范围很宽泛
    //    最好到极限O(N*logN)

    // 非基于比较的排序
    //    计数排序
    //    不基于比较的排序,数据范围要求特殊,使用范围比较窄
    //    最好到极限O(N)

    //------------------------------------------------------------------------------

    // only for 0~200 value
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        // 辅助数组,0~max的数组(索引范围),数组大小为:max+1
        // 保证最大值有相应的索引与其对应即可
        int[] bucket = new int[max + 1];

        // 统计数组元素出现的频率
        // arr[i]数组值,在bucket中对应索引
        for (int i = 0; i < arr.length; i++) {
            // 索引值和元素值相等
            bucket[arr[i]]++;
        }

        // 重置原数组是从i开始
        int i = 0;
        // 遍历辅助数组,根据词频将原数组生成好
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                // 这里需要++,原数组赋值之后,需要向后引动,不能停在原地
                arr[i++] = j;
            }
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        countSort(arr);
        printArray(arr);
    }
}

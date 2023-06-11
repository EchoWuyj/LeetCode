package alg_01_新手班_zcy.class03;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-08-31 22:29
 * @Version 1.0
 */
public class Code02_BSNearLeft {

    // KeyPoint 题目1 arr有序的,求>=num最左的位置
    public static int mostLeftNoLessNumIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            // -1表示没有符合的情况
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;

        // 使用临时变量ans记录比较靠左>=num位置索引
        // 该变量还得随着while循环的判断,更新更靠左位置索引
        // 最开始需要初始化(养成好习惯),-1表示没有找到的情况
        int ans = -1;

        while (L <= R) {
            // 求中点另外一种表示(推荐使用)
            // 使用()提高优先级
            // int mid = L +((R - L) >> 1);
            int mid = (L + R) / 2; // 存在越界的风险

            // 中点位置>=num,其左边有可能>=num,需要while循环判断
            // 若arr[mid] >= num,则[mid,R]必然比num大,则>=num的最左位置从mid左侧找
            if (arr[mid] >= num) {
                // 临时记下
                ans = mid;
                // 缩小二分的判断区域是都是修改都是左右指针实现的
                // 所以等号的左侧都是R和L变量
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
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

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != mostLeftNoLessNumIndex(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(mostLeftNoLessNumIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
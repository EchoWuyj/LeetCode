package alg_02_体系班_zcy.class01;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-09 22:45
 * @Version 1.0
 */
public class Code04_BSExist {

    // 二分判断某个数是否存在
    public static boolean exist(int[] sortedArr, int num) {
        // null和0是两种不同情况
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }


        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;

        // L..R 至少两个数的时候(不是很推荐)
        while (L < R) {
            // 补充:
            // N*2+1等价于 (N<<1)|1
            // N<<1最低位是使用0来补充,再去或1,表示加1
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        // 关于while(L<R)说明
        // target 6
        // 数值  5  6
        // 索引  3  4
        //       L  R
        // mid+(3+4)/2=3
        // arr[mid]=5 < target=6
        // 此时还要执行L=mid+1=4,while循环不满足,跳出
        // 但是,最后返回时,还有判断sortedArr[L]是否和num相等
        // sortedArr[4]=6,return true 相当于将漏比较的值补上!
        return sortedArr[L] == num;
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
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}

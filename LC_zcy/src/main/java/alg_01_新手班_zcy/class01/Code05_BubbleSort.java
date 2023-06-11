package alg_01_新手班_zcy.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-08-30 11:47
 * @Version 1.0
 */
public class Code05_BubbleSort {
    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 7, 9};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    // 交换arr的i和j位置上的值
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 外层for循环确定比较的数组范围
        // 冒泡一次,得到最大值,最后一个位置确定
        // 随着内层for循环一次遍历确定一个元素的位置,数组范围不断减小
        // 从数组的索引最后位置不断前移
        for (int end = N - 1; end >= 0; end--) {
            // 内层for循环,在确定的数组范围内,两两比较

            // KeyPoint 方式一
            //  1)索引从1开始,遍历到最后索引位置end,其中end是外层for循环的变量,
            //    该值是动态变换的,不是固定的N-1,只是end最大为N-1
            //  2)for循环中间使用;进行分隔,而不是,进行分隔
            for (int second = 1; second <= end; second++) {
                // 前一个数比后面一个数要大,即大的交换,排序后的顺序则是从小到大
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }

            // KeyPoint 方式二
            // 索引从0开始,i < end,即遍历到最后索引位置end-1,
            // 而end最大为N-1,故最后一组的比较[N-2,N-1],所以不存在数组下标越界的情况

//            for (int i = 0; i < end; i++) {
//                if (arr[i] > arr[i + 1]) {
//                    swap(arr, i, i + 1);
//                }
//            }

        }
    }
}

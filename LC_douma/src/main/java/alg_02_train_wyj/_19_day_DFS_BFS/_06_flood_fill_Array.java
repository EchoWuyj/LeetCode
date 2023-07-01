package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 16:14
 * @Version 1.0
 */
public class _06_flood_fill_Array {

    private static int[] twoDimConvertOneDim(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[] res = new int[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                res[index] = arr[i][j];
            }
        }
        return res;
    }

    public static void test1() {
        int[][] data = {
                {4, 2, 5, 11},
                {3, 7, 1, 9},
                {32, 22, 13, 8}
        };

        System.out.println("========== test1 =============");
        // Arrays.toString 可以将数组直接打印
        System.out.println(Arrays.toString(twoDimConvertOneDim(data)));
        // [4, 2, 5, 11, 3, 7, 1, 9, 32, 22, 13, 8]
        System.out.println("========== test1 =============");
    }

    private static int[][] oneDimConvertTwoDim(int[] arr, int rows, int cols) {
        int[][] res = new int[rows][cols];
        for (int index = 0; index < arr.length; index++) {
            int i = index / cols;
            int j = index % cols;
            res[i][j] = arr[index];
        }
        return res;
    }

    public static void test2() {
        int[] arr = {4, 2, 5, 11, 3, 7, 1, 9, 32, 22, 13, 8};
        System.out.println("========== test2 =============");
        // 对二维数组进行遍历打印，其中一维数组，通过 Arrays.toString 输出打印
        for (int[] a : oneDimConvertTwoDim(arr, 3, 4)) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("========== test2 =============");

        /*
            [4, 2, 5, 11]
            [3, 7, 1, 9]
            [32, 22, 13, 8]
         */

    }

    private static void printAdj(int[][] arr, int i, int j) {
        int rows = arr.length;
        int cols = arr[0].length;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

        int count = 0;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (nexti >= 0 && nexti < rows && nextj >= 0 && nextj < cols) {
                System.out.print(arr[nexti][nextj] + " ");
            }
            count++;
            if (count == 4) {
                System.out.println();
            }
        }
    }

    public static void test3() {
        int[][] data = {
                {4, 2, 5, 11},
                {3, 7, 1, 9},
                {32, 22, 13, 8}
        };

        System.out.println("========== test3 =============");
        printAdj(data, 1, 2);
        System.out.println();
        System.out.println("========== test3 =============");
        /*
            [4, 2, 5,11]
            [3, 7, 1, 9]
            [32,22,13,8]

            二维索引 (1,2) 对应元素 1
            上 下 左 右 5 13 7 9
            左上 右上 左下 右下 2 11 22 8
         */
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}

package alg_01_ds_dm._04_graph._03_floodfill;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 21:42
 * @Version 1.0
 */
public class ArrayTest {

    // KeyPoint 1 二维数组索引 => 一维数组索引
    public static int[] twoDimConvertOneDim(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        // 一维数组
        int[] res = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 二维数组索引 => 一维数组索引
                // i 表示元素的行索引，j 表示元素的列索引，cols 表示二维数组的列数
                int index = i * cols + j;
                // 一维数组 <= 二维数组
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

    // KeyPoint 2 一维数组索引 => 二维数组索引
    public static int[][] oneDimConvertTwoDim(int[] arr, int rows, int cols) {
        // 行和列，从外部传入
        int[][] res = new int[rows][cols];
        for (int index = 0; index < arr.length; index++) {
            // 二维数组索引 (i,j) 计算，都是只和 cols 有关
            // 行坐标 i = index / cols
            // 列坐标 j = index % cols
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

    // KeyPoint 3 四连通和八连通
    public static void printAdj(int[][] arr, int i, int j) {
        int rows = arr.length;
        int cols = arr[0].length;

        // 将多个移动的方向(x,y)，整理二维数组的方式
        int[][] dirs = {
                // '上下左右' 元素位置
                // x 控制上下，当前元素 (1,2)，- 则上 (0,2)，+ 则下 (2,2)
                // y 控制左右，当前元素 (1,2)，- 则左 (1,1)，+ 则右 (1,3)
                // 通过方向(上，下，左上，右下...)，即可确定 (x,y) 的正负
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // 上 下 左 右  => (±1,0) 和 (0,±1)
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // 左上 右上 左下 右下 => (-1,±1) 和 (1,±1) 上下为基准，分为左右
        };

        // 控制输出 4 个元素一行输出
        int count = 0;

        // 二维数组中每个元素即为一维数组(x,y)
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];

            // 并不是每个元素都是存在四连通和八连通
            // => 需要判断索引是否越界，将索引限定在一个区域内
            // 注意：nexti 和 nextj 可以取 0，多次犯错了！
            if (nexti < rows && nextj < cols && nexti >= 0 && nextj >= 0) {
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

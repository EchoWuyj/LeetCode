package alg_01_ds_wyj._04_graph._03_floodfill;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 16:07
 * @Version 1.0
 */
public class ArrayTest {
    // 2 -> 1
    public static int[] twoDimConvertOneDim(int[][] arr) {
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

    public static int[][] oneDimConvertTwoDim(int[] arr, int rows, int cols) {
        int[][] res = new int[rows][cols];
        for (int index = 0; index < arr.length; index++) {
            int i = index / cols;
            int j = index % cols;
            res[i][j] = arr[index];
        }
        return res;
    }

    public static void printAdj(int[][] arr, int i, int j) {
        int rows = arr.length;
        int cols = arr[0].length;

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        int count = 0;
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row < rows && col < cols && row >= 0 && col >= 0) {
                System.out.print(arr[row][col] + " ");
            }
            count++;
            if (count == 4) System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {4, 2, 5, 11},
                {3, 7, 1, 9},
                {32, 22, 13, 8}
        };
        System.out.println(Arrays.toString(twoDimConvertOneDim(data)));

        System.out.println("=========================");

        int[] arr = {4, 2, 5, 11, 3, 7, 1, 9, 32, 22, 13, 8};
        for (int[] a : oneDimConvertTwoDim(arr, 3, 4)) {
            System.out.println(Arrays.toString(a));
        }

        System.out.println("=========================");

        printAdj(data, 1, 2);
    }
}

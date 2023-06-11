package alg_01_新手班_wyj.class02;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-07 23:02
 * @Version 1.0
 */
public class Code03_Comp {
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        int length = (int) (Math.random() * maxLen);
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * maxValue);
        }
        return array;
    }

    public static int[] copyArray(int[] arr) {
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public static boolean isSorted(int[] arr) {
        if (arr.length < 2) {
            return true;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max > arr[i]) {
                return false;
            }
            max = arr[i];
        }
        return true;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] result = lenRandomValueRandom(100, 100);
//            for (int num : result) {
//                System.out.print(num + " ");
//            }
            System.out.println();
            System.out.println("===============");

            Arrays.sort(result);
//            for (int num : result) {
//                System.out.print(num + " ");
//            }
            System.out.println(isSorted(result));
        }
    }
}

package alg_01_新手班_wyj.class08;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-12 9:55
 * @Version 1.0
 */
public class Code02_MergeSort {
    // 归并排序 -> 递归实现
    public static void mergeSort1(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;

        // 定义两指针
        int p = L;
        int q = M + 1;

        while (p <= M && q <= R) {
            help[i++] = (arr[p] <= arr[q]) ? arr[p++] : arr[q++];
        }

        while (p <= M) {
            help[i++] = arr[p++];
        }

        while (q <= R) {
            help[i++] = arr[q++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    // ---------------------------------------------------------

    // 非递归实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int step = 1;
        int N = arr.length;

        while (step < N) {

            int L = 0;
            while (L < N) {
                int M = 0;

                if (N - L >= step) {
                    M = L + step - 1;
                } else {
                    M = N - 1;
                }

                if (M == N - 1) {
                    break;
                }

                int R = 0;
                if (N - 1 - M >= step) {
                    R = M + step;
                } else {
                    R = N - 1;
                }

                // 划分好左右组,调用merge函数
                merge(arr, L, M, R);

                // 边界判断
                if (R == N - 1) {
                    break;
                } else {
                    L = R + 1;
                }
            }
            if (step > N / 2) {
                break;
            }
            step *= 2;
        }
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

    public static void main(String[] args) {
        int[] array = {3, 5, 3, 2, 8, 1, 4, 6, 7};
        printArray(array);

        // KeyPoint 关于值传递和引用传递
        //      1)基本数据类型的值传递,不改变原值,因为方法调用后就会弹栈,局部变量随之消失
        //      2)引用数据类型的值传递,改变原值,因为即使方法弹栈,但是堆内存数组对象还在,可以通过地址继续访问
        //  Java中到底是传值还是传址?(面试)
        //      1)既是传值,也是传地址,基本数据类型传递的是值,引用数据类型传递的是地址
        //      2)Java中只有传值,因为地址值也是值(出去面试都说这种,支持者都是高司令(Java之父))

        // Arrays.sort将array数组的地址传入,通过地址值修改了堆中的数组元素的位置,
        // 故在原数组的基础上进行排序操作
        Arrays.sort(array);
        System.out.println("=========================");
        printArray(array);

        System.out.println("=========================");

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2); // 随机
                break;
            }
        }
        System.out.println("测试结束");
    }
}

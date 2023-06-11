package alg_02_体系班_zcy.class08;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 18:15
 * @Version 1.0
 */

// 基数排序
public class Code04_RadixSort {

    // only for no-negative value
    // 数据范围限制:非负,能够表达成10进制

    // 基数排序的流程
    // 103,13,27,25,17,9
    // 1)先找最大值:103,10进制是3位,故不够3位的使用0补齐
    //   103,013,027,025,017,009
    // 2)准备10个桶,每个桶上标记一个数字,数字范围0-9
    //   所有数字从左往右,根据其个位数字进桶,若个位数字相同,进桶有先后次序,全部进桶
    // 3)再从0号桶依次往外倒数字,先进先出,依次排序,此时是根据个位数字进行排序的
    //   个位排好序:103,013,025,027,017,009
    // 4)所有数字从左往右,根据其十位数字进桶,若十位数字相同,进桶有先后次序,全部进桶
    //   再从0号桶依次往外倒数字,先进先出,依次排序,此时是根据十位数字进行排序的
    //   注意:个位的顺序已经排序好,就不会改变
    //   十位排好序:103,009,013,017,025,027
    // 5)最后百位进桶,重复以上操作;

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 重载方法
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // 计算最大值的十进制的位数
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // 提取数字个位/十位/百位函数(形参d决定是什么位)
    public static int getDigit(int x, int d) {
        // 个位 130 / 1 = 130 % 10 = 0
        // 十位 130 / 10 = 13 % 10 = 3
        // 百位 130 / 100 =1 % 10 = 1
        return (x / (int) Math.pow(10, d - 1)) % 10;
    }

    // arr[L..R]排序
    // 数组元素中最大值的十进制的位数digit(固定不变),如130,位数是3位
    // L,R是索引的有效范围,不存在R-1的情况
    public static void radixSort(int[] arr, int L, int R, int digit) {

        // 优化后,不使用10个队列的实现

        // 1)桶排序
        //   021,010,111,022,011,012
        //   按照个位进桶,再出桶后的顺序
        //   010,021,111,011,022,012

        // 2)优化实现
        //  a.准备两个数组count[]和count'[]
        //    统计数组count[0~9],个位数字是X有几个
        //    count'[]是累加和,表示个位数字<=X一共有几个
        //  b.使用 021,010,111,022,011,012,从左往右判断个位的情况
        //    count [1,3,2,0,0,0...]  数值
        //           0,1,2,3,4,5...   索引
        //    count'[1,4,6,6,6,6...]  数值
        //           0,1,2,3,4,5...   索引
        //  c.021,010,111,022,011,012 ← 从右往左判断
        //    i.012,个位数字<=2,一共有6个,个位数字<=2的区域0~5
        //      012也是6个数中最右的,故应该填5位置,同时将2索引对应的数值6-1=5
        //   ii.011,个位数字<=1,一共有4个,个位数字<=1的区域0~3,
        //      011也是4个数中最右的,故应该填3位置,同时将1索引对应的数值4-1=3
        //      ...

        //-------------------------------------------------------------

        final int radix = 10;

        // 多个for需要使用,将i,j提取出来
        // 每次在使用时,将i和j重新赋值即可,避免重复定义i
        int i = 0, j = 0;

        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        // 有多少位就进出桶几次
        for (int d = 1; d <= digit; d++) {

            // count[0..9],10个空间
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            // 还是定义在进出桶的for循环里面,保证每次新的进出桶,不受历史值影响
            int[] count = new int[radix];

            //    count [1,3,2,0,0,0...]  数值
            //           0,1,2,3,4,5...   索引
            // 数组从L~R元素,传入的L,R是有效的范围,不存在R-1的情况
            for (i = L; i <= R; i++) {
                // 提取数字个位/十位/百位
                j = getDigit(arr[i], d);
                // 个位/十位/百位词频++
                count[j]++;
            }

            //    count'[1,4,6,6,6,6...]  数值
            //           0,1,2,3,4,5...   索引
            // 累加和
            for (i = 1; i < radix; i++) {
                // count[0]还是count[0],累加是从count[1]开始的
                count[i] = count[i] + count[i - 1];
            }

            // 021,010,111,022,011,012 ←
            // 从右往左遍历
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                // 012也是6个数中最右的,故应该填5位置
                // count[j]是个位数字的词频,再去减1即为应该在位置
                help[count[j] - 1] = arr[i];
                // 词频减1
                count[j]--;
            }

            // 将help数组中元素copy回原数组,继续下一轮
            for (i = L, j = 0; i <= R; i++, j++) {
                // KeyPoint 注意事项
                //  for循环中i都是变化的,一般不会出现固定索引
                //  切记for中不能将索引值固定,一般使用变量值i
                arr[i] = help[j];
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
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
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
        radixSort(arr);
        printArray(arr);
    }
}

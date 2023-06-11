package alg_02_体系班_zcy.class04;

public class Code02_SmallSum {
    
    /*
    用常见面试题再深入理解一下归并排序的精髓

    在一个数组中,一个数左边比它小的数的总和,叫数的小和
    所有数的小和累加起来,叫数组小数和,求数组小和

    例子：[1,3,4,2,5]
    1左边比1小的数：没有
    3左边比3小的数：1
    4左边比4小的数：1,3
    2左边比2小的数：1
    5左边比5小的数：1,3,4,2
    所以数组的小和为1+1+3+1+1+3+4+2=16

    换思路
    原问题等价于:1的右边有多少个数比1大,则一定产生相应数量的小和
    (因为每个比1大的数num,在统计比num小的数都是会将1统计一次的)

     */

    public static int smallSum(int[] arr) {
        // 只有一个数的数组,不在存在小数和,故小数和为0
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // 利用递归实现,操作整体是套用mergeSort,再其基础上进行修改
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序,也要求小和返回(返回值为int)
    public static int process(int[] arr, int l, int r) {
        // 数组只有一个数,没有小和
        if (l == r) {
            return 0;
        }

        // l < r
        int mid = l + ((r - l) >> 1); // 左移1位,不是左移2位
        // 所有merge时产生的小和累加
        // 左侧排序(merge);右侧排序(merge);左右侧排好序后整体的merge
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    // ---------------------------------------------------

    // 在merge过程中求出小和
    //  在原数组[1,3,4,2,5]划分范围:1,3|4,2,5
    //  再去划分1|3,merge过程中产生小和
    // 从左往右merge,左右组指针都在左边
    //  1)左组数<右组数,先copy左组数到help数组,产生小和(根据右组元素个数计算小和的个数),左指针右移
    //  2)左组数=右组数,先copy右组数到help数组,不产生小和,右指针右移
    //  3)左组数>右组数,先copy右组数到help数组,不产生小和,右指针右移
    public static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        // 记录小和的结果
        int res = 0;

        while (p1 <= m && p2 <= r) {
            // KeyPoint 两行代码的位置关系不能调换,关键在p1索引后移,会影响arr[p1]的值
            // 产生小和,根据右组元素个数计算小和的个数
            // r是右组的最右侧,则当前p2指针到r位置,元素个数r-p2+1
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            // 左组数<右组数,先copy左组数到help数组
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 左组或者右组其中一个越界,将另外一组直接copy到help数组即可
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            // KeyPoint 一定需要小心,并不是从索引i=0开始赋值
            arr[L + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}

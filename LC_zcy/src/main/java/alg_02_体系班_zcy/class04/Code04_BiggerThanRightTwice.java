package alg_02_体系班_zcy.class04;

public class Code04_BiggerThanRightTwice {

    // 在一个数组中,对于任何一个数num的右边,求有多少个的数*2依然<num,返回总个数
    // 等价于:等价于求一个数,右边多少个数*2比它小,此时该数是左组姿态

    // 比如:[3,1,7,0,2]
    //  3的后面有:1,0
    //  1的后面有:0
    //  7的后面有:0,2
    //  0的后面没有
    //  2的后面没有
    //  所以总共有5个

    public static int biggerTwice(int[] arr) {
        // 数组只有一个元素,自然是不满足
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        // 给定范围数组中只有一个元素
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int m, int r) {

        // [L..M] [M+1. .R]

        // 先计算出满足要求的数有多少个(右边多少个数*2比它小),计算完之后再去merge
        // 整个求解的过程是O(N),左右指针分别是从左往右,移动的过程不回退
        // 故两部分代码都是O(N),并没有增加时间复杂度

        int ans = 0;
        // 目前囊括进来的数,是从[M+1, windowR)
        int windowR = m + 1;
        // for循环遍历的对象是左组中的元素
        // for循环从左往右依次考察左组中的每个数,看windowR能滑动到那里?
        for (int i = L; i <= m; i++) {
            // 满足条件arr[i]>(arr[windowR]*2)前移1位,直到在不满足条件的索引位置停止,
            // 这样在计算有多少个数相减时,不用考虑多减1的情况
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                // windowR一直是往右移动,并且是不回退的
                // 每次向前移动到一个满足条件新的位置,计算个数,都是直接从mid+1开始计算的
                windowR++;
            }
            // 当前windowR减去开始位置m+1,得到的就是满足条件的个数
            ans += windowR - (m + 1);
        }

        //--------------------------------

        // 单独merge
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }


    // -----------------------------------------------------------

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

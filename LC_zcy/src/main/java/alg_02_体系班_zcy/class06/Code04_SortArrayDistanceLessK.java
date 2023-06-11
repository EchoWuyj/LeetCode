package alg_02_体系班_zcy.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-18 18:14
 * @Version 1.0
 */
public class Code04_SortArrayDistanceLessK {

    // 已知一个几乎有序的数组几乎有序,是指如果把数组排好顺序的话
    // 每个元素移动的距离一定不超过k,k相对于数组长度来说是比较小的
    // 请选择一个合适的排序策略,对这个数组进行排序

    // 由于数组是几乎有序的,假如在第k+1个数之后存在最小值,那么元素移动到第一个位置需要移动超过k次,
    // 不符合,所以最小值必在前k+1个数里。

    // 可以选择k+1大小的小根堆进行排序,先扫描数组的前k+1个数构建小根堆。
    // 然后取出堆顶元素放在数组的第1个位置,并把数组的第k+2个数插入小根堆
    // 再取出堆顶元素放在数组的第2个位置,并把数组的第k+3个数插入小根堆
    // ……依次类推,扫描完数组后,把小根堆依次出堆即可

    // [1,2,3,4,5] k=2
    // [3,4,1,2,5]

    // 等价于0-K个索引原始数字,其中最小值为0位置的索引
    // 将0-K索引范围的数字放到一个堆里面(小根堆),弹出最小值放在0位置,则一定是对的
    // 再将(K+1)位置的数,放到小根堆里面去,弹出一个最小值放到1位置,...后面依次反复

    // 一共弹出N个数,小根堆最多(K+1)个数,时间复杂度O(N*log(K+1))
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            // k=0,则直接是有序
            return;
        }

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 将index提取出来成为全局公共变量
        // 两个for循环都是需要使用到的
        int index = 0;

        // KeyPoint 同一数组不同部分,被不同的for循环进行处理

        // 1)0~K元素处理
        // K的值有可能比arr.length-1大,所以需要判断一下,否则有可能索引越界
        for (; index <= Math.min(arr.length - 1, k); index++) {
            // 堆的大小为(K+1),将arr[0]~arr[K]元素放入
            heap.add(arr[index]);
        }

        // 2)K+1往后元素处理
        int i = 0;
        for (; index < arr.length; i++, index++) {
            // 先将k+1元素放入,同时堆里面弹出一个元素
            heap.add(arr[index]);
            // 将数组从0开始设置相应的最小值
            arr[i] = heap.poll();
        }

        // 进入一个元素,弹出一个元素,这样的方式将第二个
        // for循环将数组中元素遍历完了,此时heap中还有剩余的元素
        // 将其设置到相应的位置上
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换,但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
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
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 1000;
        int maxValue = 1000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}

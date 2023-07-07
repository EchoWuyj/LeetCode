package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 15:45
 * @Version 1.0
 */
public class _10_BucketSorter {

    // 桶排序
    // 思路：
    // 1.将要排序的数组分散到几个有序的桶中，保证桶与桶之间是有序的，后面一个桶最小值大于前一个桶最大值
    // 2.再对每个桶中数据进行单独排序(快排,归并)
    // 3.桶内排序结束，再将每个桶内数据依次取出，保证最后数组元素是有序的

    // 时间复杂度 O(n*log(n/m)
    // 当桶的个数 m 接近数据个数 n 时，log(n/m) 就是一个非常小的常量
    // 这个时候桶排序的时间复杂度接近 O(n)
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 创建几个空的 bucket
        int maxValue = data[0];
        // 获取数组最大值，通过最大值计算桶的个数
        for (int num : data) {
            maxValue = Math.max(maxValue, num);
        }

        /*
        这里为什么是除以 10 呢？ 在视频中，我们讲过，假设数组中的元素的大小是 0 到 39
        然后我们想划分成 4 个桶，所以 39 / 10 + 1 = 4
        这里需要注意的是：bucketNum 的计算是根据场景确定的，不同的场景 bucketNum 的计算方式是不同的
        所以 bucketNum 的计算之前，需要确定桶排序使用的场景
         */

        int bucketNum = maxValue / 10 + 1; // 39 / 10 + 1 = 4
        // 每个桶中元素个数不确定，使用动态数组存储
        // 这里只是申明 bucketNum 个桶，实际上并没有创建桶，后续进行初始化
        // KeyPoint 空间复杂度是 O(m)，m 表示桶的个数
        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        // 2. 将所有的元素添加进对应的 bucket
        for (int i = 0; i < data.length; i++) {
            // 计算当前元素应该被分配到哪一个桶里
            int index = data[i] / 10;
            // 若桶没有初始化，则先初始化
            if (buckets[index] == null) {
                buckets[index] = new ArrayList<>();
            }
            // KeyPoint ArrayList add 报错，多半是导包错误
            buckets[index].add(data[i]);
        }

        // 3. 对每一个 bucket 中的元素进行排序
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucketData = buckets[i];
            if (bucketData != null) {
                // KeyPoint 集合转数组，只是引用类型数组，不能是基本数据类型(int)
                // Integer[] dataArr = bucketData.toArray(new Integer[bucketData.size()]);
                // 对 Integer 数据类型进行排序，调用 _09_IntegerArrayQuickSorter 中的 sort 方法
                // KeyPoint 不是原地排序算法，sort 方法，申请了新的数组空间，即不是原地算法
                new _09_IntegerArrayQuickSorter().sort(bucketData);
            }
        }

        // 4. 从 buckets 中拿到排序后的数组
        // 遍历 data 数组
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucketData = buckets[i];
            if (bucketData != null) {
                for (int j = 0; j < bucketData.size(); j++) {
                    data[index++] = bucketData.get(j);
                }
            }
        }

        // KeyPoint 补充说明
        // 桶排序应用 -> 外部排序 -> 详细见 PPT
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _10_BucketSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}

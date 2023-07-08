package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 15:45
 * @Version 1.0
 */
public class _10_BucketSorter {

    // 线性时间复杂度排序算法
    // 1.桶排序
    // 2.计数排序
    // 3.基数排序

    // 桶排序思路：
    // 1.将要排序的数组分散到几个有序的桶中，保证桶与桶之间是有序的，后面一个桶最小值大于前一个桶最大值
    // 2.再对每个桶中数据进行单独排序(快排,归并)
    // 3.桶内排序结束，再将每个桶内数据依次取出，保证最后数组元素是有序的

    // 应用场景
    // 数组中元素的大小是从 0 到 39
    // 创建 4 个桶
    //   0-9     10-19    20-29    30-39
    // bucket   bucket   bucket   bucket
    //   3 5    11 13     23 24    32 35
    //   8 9    16 19      27      37 37
    // 最终排序 3 5 8 9 11 13 16 19 23 24 27 32 35 37 37

    // 时间复杂度 O(n*log(n/m))
    // 假设有 n 个元素，有 m 个桶，每个桶中元素个数：c=n/m，对每个桶进行排序(快排,归并)
    // 单个桶的时间复杂度 O(clog(c))，总的时间复杂度 O(m*n/m*log(n/m)) => O(n*log(n/m))
    // 当桶的个数 m 接近数据个数 n 时，log(n/m) 就是一个非常小的常量
    // 这个时候桶排序的时间复杂度接近 O(n)
    public void sort(int[] data) {

        // 桶排序基本步骤
        // 1.创建几个空的 bucket
        // 2.将所有的元素添加进对应的 bucket
        // 3.对每一个 bucket 中的元素进行排序
        // 4.从 buckets 中拿到排序后的数组

        if (data == null || data.length <= 1) return;

        // 1.创建几个空的 bucket
        int maxValue = data[0];
        // 获取数组最大值，通过最大值计算桶的个数
        for (int num : data) {
            maxValue = Math.max(maxValue, num);
        }

        int bucketNum = maxValue / 10 + 1; // 39 / 10 + 1 = 4

        // 补充说明：
        // 这里为什么是除以 10 呢？ 假设数组中的元素的大小是 0 到 39
        // 然后我们想划分成 4 个桶，所以 39 / 10 + 1 = 4

        // 注意
        // bucketNum 的计算是根据场景确定的，不同的场景 bucketNum 的计算方式是不同的
        // 所以 bucketNum 的计算之前，需要确定桶排序使用的场景

        // 1.因为每个桶中元素个数不确定，使用 ArrayList 存储，且是 ArrayList 构成的数组 buckets
        // 2.注意：这里只是申明 bucketNum 个桶，实际上并没有创建桶，后续需要进行初始化
        // 3.空间复杂度是 O(m)，m 表示桶的个数
        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        // 2.将所有的元素添加进对应的 bucket
        int n = data.length;
        for (int i = 0; i < n; i++) {
            // 计算当前元素应该被分配到哪一个桶里
            int index = data[i] / 10;
            // 若桶没有初始化，则先初始化
            if (buckets[index] == null) {
                buckets[index] = new ArrayList<>();
            }
            // KeyPoint 将 if 和 else 中共有的代码，抽取到 if 判断外面
            // 若 ArrayList add 报错，多半是导包错误，使用了自定义 ArrayList
            buckets[index].add(data[i]);

            /*
                KeyPoint 不要将 if 判断中的 buckets[index].add(data[i]) 遗漏了
                         创建 ArrayList 后，同时加入元素，而不只是创建 ArrayList
                if (buckets[index] == null) {
                    buckets[index] = new ArrayList<>();
                    buckets[index].add(data[i]);
                } else {
                    buckets[index].add(data[i]);
                }
            */
        }

        // 3.对每一个 bucket 中的元素进行排序
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucket = buckets[i];
            if (bucket != null) {
                // 注意：不是原地排序算法，调用 sort 方法，申请了新的数组空间
                IntegerArrayQuickSorter.sort(bucket);
            }
        }

        // 4.从 buckets 中拿到排序后的数组
        // index 用于遍历 data 数组
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucket = buckets[i];
            // 保证桶里有数据，
            if (bucket != null) {
                int size = bucket.size();
                for (int j = 0; j < size; j++) {
                    data[index++] = bucket.get(j);
                }
            }
        }

        // KeyPoint 桶排序特点
        // 1.空间复杂度是 O(m)，m 表示桶的个数
        //   不是原地排序算法，调用 sort 方法，申请了新的数组空间
        // 2.桶排序是不是稳定排序算法，取决于对桶内元奈排序的算法
        // 3.桶排序对数据要求非常苛刻
        //    3.1 桶与桶之间必须是有序的
        //    3.2 待排序数据最好均匀的分配到每个桶中
        //        否则在数据倾斜情况下，桶排序时间复杂度退化成 O(nlogn)

        // KeyPoint 补充说明
        // 外部排序：对存储在磁盘中的数据进行排序
        // 外部排序特点:数据量比较大，内存有限，无法将数据全部加载到内存中。

        // 外部排序 => 应用桶排序
        // 将大文件切分成若干个小文件，每个小文件相当于一个桶，再去加载到内存中进行排序，排序后再将结果写导大文件中
        // 注意：小文件与小文件之前有序，单个小文件内是无序的，需要进行排序

        // 文件 10 G，最小值：1，最大值：10 万
        // 将所有数，划分到 100 个桶中，1~1000，...
        // 若数据量仍然不均匀，则进一步划分成更小的文件，关键：保证小文件数据量差不多
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _10_BucketSorter().sort(data);
        System.out.println(Arrays.toString(data));
        // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}

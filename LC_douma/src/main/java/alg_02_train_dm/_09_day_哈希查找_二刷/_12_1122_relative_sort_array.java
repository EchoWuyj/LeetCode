package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:10
 * @Version 1.0
 */
public class _12_1122_relative_sort_array {
    /*
        1122 数组的相对排序
        给你两个数组：arr1 和 arr2
        1. arr2 中的元素各不相同
        2. arr2 中的每个元素都出现在 arr1 中

        对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
        未在 arr2 中出现过的元素，需要按照升序放在 arr1 的末尾。

        输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19,8]
              arr2 = [2,1,4,3,9,6] => 元素各不相同
                                   => 每个元素都出现在 arr1 中
        输出：[2,2,2,1,4,3,3,9,6,7,8,19]

        KeyPoint 解释
        arr2   2,1,4,3,9,6
        arr1   2,2,2,1,4,3,3,9,6,| 7,8,19
               按照 arr2 相对位置排序 | 剩余元素升序排列
               arr1 和 arr2 相对顺序一致，未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾

        提示
         1 <= arr1.length, arr2.length <= 1000
         0 <= arr1[i], arr2[i] <= 1000
         arr2 中的元素 arr2[i] 各不相同
         arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */

    // KeyPoint 方法一 自定义排序
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        // 将数组 arr2 中 num 和 index 映射成 map2
        // key =>  num
        // value => index
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map2.put(arr2[i], i);
        }

        // 数组转集合，为了后面使用：自定义排序 Collections.sort()
        List<Integer> list1 = new ArrayList<>();
        for (int num : arr1) list1.add(num);

        // 自定义排序
        // KeyPoint 核心：只需要定义好两数 x，y 比较规则即可，剩下的调用系统排序算法即可
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                // 因为 arr1 并不是所有元素都在 arr2 中，故还需要进行判断是否存在
                // 其中，map2 为 arr2 集合，list1 为 arr1 结合
                // 1.若 x 中元素在 arr2 集合中
                if (map2.containsKey(x)) {
                    // 1.1 若 x 和 y 都出现在两个数组中，那么返回 x 和 y 在 arr2 索引的差
                    // int diff = map2.get(x) - map2.get(y) 表示索引差，不是 x 和 y 数值差
                    // KeyPoint 特别注意：以下 x 和 y 指的是 x 索引 和 y 索引，不是数值 x 和 数值 y
                    //                   注意：索引差代表原来的相对顺序
                    // 1) 若差等于 0，那么 x 和 y 在 arr1 的顺序不变
                    // 2) 若差等于负数，x - y < 0 =>  x < y，在 arr2 中， x 在 y 的前面
                    //    => 在 arr1 中， x 和 y 就升序排列 => 即 x 在 y 前面
                    // 3) 若差等于正数，x - y > 0 =>  x > y，在 arr2 中， x 在 y 的后面
                    //    => 在 arr1 中， x 和 y 就降序排列 => 即 y 在 x 前面
                    // 1.2 若 x 在 arr2 中，但是 y 不在 arr2 中，那么返回负数 -1
                    //     => x 和 y 在 arr1 中升序排列 => 即 x 在 y 前面 => 注意：真实的数值相减，x-y，不是索引注意：真实的数值相减，x-y，不是索引
                    return map2.containsKey(y) ? map2.get(x) - map2.get(y) : -1;
                } else {
                    // 2.若 x 不在 arr2 集合中
                    // 2.1 若 x 不在 arr2 中，但 y 在 arr2 中，则返回正数 1
                    //      => x 和 y 在 arr1 中降序排列 => 即 y 在 x 的前面
                    // 2.2 若 x 和 y 都不在 arr2 中 => 属于 arr1 中独有的元素
                    //     => 则 x 和 y 就按照 x 和 y 的大小进行升序排列
                    return map2.containsKey(y) ? 1 : x - y;
                }
            }
        });

        for (int i = 0; i < arr1.length; i++) arr1[i] = list1.get(i);
        return arr1;
    }

    // KeyPoint 注意：0 <= 数据范围 <=1000 => 数据量不大，可以使用计数排序
    // KeyPoint 方法二 计数排序 (特殊桶排序)
    // 最差情况时间复杂度 O(mn)
    // 1.arr1 中所有元素都是同一个元素，从而导致 count[num] 为 n
    // 2.外层 for 循环为 m，故总体时间复杂度为 O(m*n)
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;
        int[] count = new int[1001];
        for (int num : arr1) count[num]++;

        // arr1： 2 3 1 3 2 4 6 7 9 2 8
        // count
        // index  0 1 2 3 4 5 6 7 8 9
        // value  0 1 3 2 1 0 1 1 1 1

        // arr2： 2 1 4 3 9 6

        int index = 0;
        for (int num : arr2) { // O(m)
            for (int i = 0; i < count[num]; i++) { // O(n) => 最差情况
                arr1[index++] = num;
            }
            // KeyPoint 注意：count[num] 需要置 0
            // 1.在 count 过程，arr1 中所有元素都计数，通过 arr2 的 num，从而确定 num 值
            //   再根据 count[num] 表示 num 个数，for 循环对 arr1 赋值
            // 2.但是 arr1 中有 arr2 中没有元素 num，count[num] != 0
            //   为了可以区分出已经排好序 和 还没排序的元素，将已经排好序的元素出现的次数清 0
            count[num] = 0;
        }
        // 清 0 后，在 for 循环中就不用处理了，只要处理在 arr2 中没出现的元素了
        for (int num = 0; num < 1001; num++) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
        }
        return arr1;
    }
}

package alg_02_train_dm._09_day_哈希查找;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:10
 * @Version 1.0
 */
public class _12_1122_relative_sort_array {
    /*
        1122 号算法题：数组的相对排序
        给你两个数组，arr1 和 arr2
            1. arr2 中的元素各不相同
            2. arr2 中的每个元素都出现在 arr1 中

        对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
        未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

        输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19,8], arr2 = [2,1,4,3,9,6]
        输出：[2,2,2,1,4,3,3,9,6,7,8,19]

        KeyPoint 解释
        arr2 2,1,4,3,9,6
        arr1 2,2,2,1,4,3,3,9,6,| 7,8,19
              按照 arr2 相对位置排序 | 剩余元素升序排列
        => arr1 和 arr2 相对顺序一致，未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾

        提示
        - 1 <= arr1.length, arr2.length <= 1000
        - 0 <= arr1[i], arr2[i] <= 1000
        - arr2 中的元素 arr2[i] 各不相同
        - arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */

    // KeyPoint 方法一 自定义排序
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        // 将数组 arr2 中 num 和 index 映射成 map
        // key =>  num； value => index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        // 数组转集合，为了后面使用 Collections.sort()
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) list.add(num);

        // 自定义排序
        // KeyPoint 核心：只需要定义好两数 x，y 比较规则即可，剩下的调用系统排序算法即可
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                // arr1 并不是所有元素都在 arr2 中，故还需要进行判断是否存在
                // 其中 map 为 arr2 集合，
                if (map.containsKey(x)) {
                    // 若 x 和 y 都出现在两个数组中，那么返回 x 和 y 在 arr2 索引的差
                    //  1.若差等于 0，那么 x 和 y 在 arr1 的顺序不变
                    //  2.若差等于负数，那么说明在 arr2 中， x 在 y 的前面，那么在 arr1 中， x 和 y 就升序排列(即 x 在 y 前面)
                    //  3.若差等于正数，那么说明在 arr2 中， x 在 y 的后面，那么在 arr1 中， x 和 y 就降序排列(即 y 在 x 的前面)
                    // 若 x 在 arr2 中，但是 y 不在 arr2 中，那么返回负数 (-1)，那么 x 和 y 在 arr1 中升序排列(即 x 在 y 前面)
                    return map.containsKey(y) ? map.get(x) - map.get(y) : -1;
                } else {
                    // 若 x 不在 arr2 中，但是 y 在 arr2 中，那么返回正数(即 1)，那么 x 和 y 在 arr1 中降序排列(即 y 在 x 的前面)
                    // 若 x 和 y 都在 arr2 中，那么 x 和 y 就按照 x 和 y 的大小进行升序排列
                    return map.containsKey(y) ? 1 : x - y;
                }
            }
        });

        for (int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }

    // KeyPoint 方法二 计数排序 => 特殊桶排序
    // 0 <= 数据范围 <=1000 => 数据量不大，可以使用计数排序
    // 最差情况时间复杂度 O(mn)
    // 1. arr1 中所有元素都是同一个元素，从而导致 count[num] 为 n
    // 2. 外层 for 循环为 m，故总体时间复杂度为 O(m*n)
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;
        int[] count = new int[1001];
        for (int num : arr1) count[num]++;

        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
            // count[num] 需要置 0，因为在计数过程，arr1 中等于 num 的所有的元素都放在一起了
            // 也就是 num 排好序了，为了可以区分出已经排好序，和还没排序的元素，我们将排好序的元素出现的次数清 0
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

package alg_02_train_wyj._09_day_哈希查找;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-19 16:01
 * @Version 1.0
 */
public class _12_1122_relative_sort_array {

    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : arr1) list.add(num);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (map.containsKey(x)) {
                    return map.containsKey(y) ? map.get(x) - map.get(y) : -1;
                } else {
                    return map.containsKey(y) ? 1 : x - y;
                }
            }
        });

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = list.get(i);
        }
        return arr1;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;
        int[] count = new int[1001];
        for (int num : arr1) count[num]++;

        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
            count[num] = 0;
        }

        for (int num = 0; num < 1001; num++) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
        }
        return arr1;
    }
}

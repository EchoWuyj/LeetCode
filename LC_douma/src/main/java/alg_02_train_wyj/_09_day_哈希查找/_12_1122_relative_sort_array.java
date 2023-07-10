package alg_02_train_wyj._09_day_哈希查找;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-05-19 16:01
 * @Version 1.0
 */
public class _12_1122_relative_sort_array {

    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) map2.put(arr2[i], i);
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int num : arr1) list1.add(num);
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (map2.containsKey(x)) {
                    return map2.containsKey(y) ? map2.get(x) - map2.get(y) : -1;
                } else {
                    return map2.containsKey(y) ? 1 : x - y;
                }
            }
        });
        for (int i = 0; i < list1.size(); i++) {
            arr1[i] = list1.get(i);
        }
        return arr1;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] count = new int[1001];
        for (int num : arr1) {
            count[num]++;
        }

        int index = 0;
        for (int num2 : arr2) {
            for (int i = 0; i < count[num2]; i++) {
                arr1[index++] = num2;
            }
            count[num2] = 0;
        }

        int n = count.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr1[index++] = i;
            }
        }
        return arr1;
    }
}

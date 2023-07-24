package alg_02_train_wyj._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:59
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream2 {

    class kthLargest {
        List<Integer> list;
        int k;

        public kthLargest(int k, int[] nums) {
            list = new ArrayList<>();
            this.k = k;
            for (int num : nums) list.add(num);
            Collections.sort(list);
        }

        public int add(int val) {
            if (list.isEmpty()) {
                list.add(val);
            } else {
                int size = list.size();
                list.add(Integer.MIN_VALUE);
                int j = size;
                for (; j > 0; j--) {
                    if (val < list.get(j - 1)) {
                        list.set(j, list.get(j - 1));
                    } else {
                        break;
                    }
                }
                list.set(j, val);
            }
            return list.get(list.size() - k);
        }
    }
}

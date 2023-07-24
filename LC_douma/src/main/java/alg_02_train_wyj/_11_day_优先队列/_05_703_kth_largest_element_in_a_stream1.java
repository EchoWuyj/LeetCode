package alg_02_train_wyj._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 18:12
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream1 {
    class kthLargest {
        List<Integer> list;
        int k;

        public kthLargest(int k, int[] nums) {
            list = new ArrayList<>();
            for (int num : nums) list.add(num);
            this.k = k;
        }

        public int add(int val) {
            list.add(val);
            Collections.sort(list);
            int size = list.size();
            return list.get(size - k);
        }
    }
}









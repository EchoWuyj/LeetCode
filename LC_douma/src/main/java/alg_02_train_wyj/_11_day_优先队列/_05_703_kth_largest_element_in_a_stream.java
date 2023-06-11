package alg_02_train_wyj._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 18:12
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream {

}

class KthLargest1 {
    private List<Integer> data;
    private int k;

    public KthLargest1(int k, int[] nums) {
        data = new ArrayList<>();
        this.k = k;
        for (int num : nums) data.add(num);
    }

    public int add(int val) {
        data.add(val);
        Collections.sort(data);
        return data.get(data.size() - k);
    }
}

class KthLargest2 {
    private List<Integer> data;
    private int k;

    public KthLargest2(int k, int[] nums) {
        this.data = new ArrayList<>();
        this.k = k;
        for (int num : nums) data.add(num);
        Collections.sort(data);
    }

    public int add(int val) {
        if (data.isEmpty()) {
            data.add(val);
        } else {
            int n = data.size();
            data.add(Integer.MIN_VALUE);
            int j = n;
            for (; j > 0; j--) {
                if (val < data.get(j - 1)) {
                    data.set(j, data.get(j - 1));
                } else {
                    break;
                }
            }
            data.set(j, val);
        }
        return data.get(data.size() - k);
    }
}

class KthLargest3 {
    private PriorityQueue<Integer> data;
    private int k;

    public KthLargest3(int k, int[] nums) {
        data = new PriorityQueue<>(k);
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (data.size() < k) {
            data.add(val);
        } else if (val > data.peek()) {
            data.remove();
            data.add(val);
        }
        return data.peek();
    }
}








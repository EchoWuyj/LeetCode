package alg_01_ds_dm._03_high_level._01_heap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-15 14:27
 * @Version 1.0
 */
public class DataStream2 {
    private MaxHeap<Integer> maxHeap;

    public DataStream2() {
        maxHeap = new MaxHeap<>();
    }

    // O(logn)
    public void add(int val) {
        maxHeap.add(val);
    }

    // O(logn)
    public int removeMax() {
        return maxHeap.removeMax();
    }
}

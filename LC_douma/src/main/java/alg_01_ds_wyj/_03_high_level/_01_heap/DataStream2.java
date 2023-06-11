package alg_01_ds_wyj._03_high_level._01_heap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 20:58
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

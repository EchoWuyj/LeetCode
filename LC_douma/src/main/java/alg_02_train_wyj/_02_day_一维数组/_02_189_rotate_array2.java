package alg_02_train_wyj._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-07-19 19:59
 * @Version 1.0
 */
public class _02_189_rotate_array2 {
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int cur = start;
            int pre = nums[start];
            do {
                int next = (cur + k) % n;
                int tmp = nums[next];
                nums[next] = pre;
                pre = tmp;

                cur = next;
                count++;
            } while (cur != start);
        }
    }
}

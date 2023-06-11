package alg_02_train_wyj._21_day_综合应用二;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 11:01
 * @Version 1.0
 */
public class _11_440_k_th_smallest_in_lexicographical_order {
    public int findKthNumber1(int n, int k) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }

        Collections.sort(list);
        return Integer.parseInt(list.get(k - 1));
    }

    public int findKthNumber2(int n, int k) {
        int cur = 1;
        k -= 1;
        while (k > 0) {
            int nodes = calNodes(n, cur, cur + 1);
            if (nodes <= k) {
                k -= nodes;
                cur += 1;
            } else {
                k -= 1;
                cur *= 10;
            }
        }
        return cur;
    }

    public int calNodes(int n, long cur, long next) {
        int nodes = 0;
        while (cur <= n) {
            nodes += (Math.min(next, n + 1) - cur);
            cur *= 10;
            next *= 10;
        }
        return nodes;
    }
}

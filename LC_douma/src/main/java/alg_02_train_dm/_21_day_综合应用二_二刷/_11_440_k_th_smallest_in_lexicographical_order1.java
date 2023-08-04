package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 7:49
 * @Version 1.0
 */
public class _11_440_k_th_smallest_in_lexicographical_order1 {

     /*
        440. 字典序 第 K 小数字
        给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。

        输入:
        n: 13   k: 2
        输出: 10

        解释：
        自然排序：1,2,3,4,5,6,7,8,9,10,11,12,13
        字典序：1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9

        字典序比较
        10 < 2
        12 < 3
        110 < 2
        110 < 201
        110 < 120

        提示
        1 ≤ k ≤ n ≤ 10^9。
     */

    // KeyPoint 方法一 排序 => 空间复杂度 O(n) => 超出内存限制
    // 注：字典序和字符串排序一样 "10" < "2"
    // 将每个数字转成字符串，对字符串进行排序，从而获取第 k 小
    public int findKthNumber1(int n, int k) {
        // 1. 将每个数字转成字符串
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }
        // 2. 对字符串进行排序
        Collections.sort(list);
        // 获取第 k 小的元素
        // 通过 Integer.parseInt 将 String 转成 int，从而符合返回值 int
        return Integer.parseInt(list.get(k - 1));
    }
}

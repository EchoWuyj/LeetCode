package alg_02_train_dm._05_day_数学;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:50
 * @Version 1.0
 */
public class _03_989_add_to_array_form_of_integer {
    /*

        989. 数组形式的整数加法
        整数的 数组形式 num 是按照从左到右的顺序表示其数字的数组。
        例如，对于 num = 1321 ，数组形式是 [1,3,2,1] 。
        给定 num ，整数的 数组形式 ，和整数 k ，返回 整数 num + k 的 数组形式 。

        提示：
        1 <= num.length <= 10^4
        0 <= num[i] <= 9
        num 不包含任何前导零，除了零本身
        1 <= k <= 10^4

        示例 1：
        输入：num = [1,2,0,0], k = 34
        输出：[1,2,3,4]
        解释：1200 + 34 = 1234

        示例 2：
        输入：num = [2,7,4], k = 181
        输出：[4,5,5]
        解释：274 + 181 = 455
     */

    // 套用模板代码
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        int l1 = num.length - 1;
        while (l1 >= 0 || k != 0) {
            int x = l1 < 0 ? 0 : num[l1];
            int y = k == 0 ? 0 : k % 10;

            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;

            l1--;
            // 将个位数去掉
            // 188 -> 18 -> 1
            k = k / 10;
        }
        if (carry != 0) res.add(carry);
        Collections.reverse(res);
        return res;
    }
}

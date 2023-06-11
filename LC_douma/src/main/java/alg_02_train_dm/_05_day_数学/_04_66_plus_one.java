package alg_02_train_dm._05_day_数学;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:51
 * @Version 1.0
 */
public class _04_66_plus_one {
    /*

        66. 加一
        给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
        最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
        你可以假设除了整数 0 之外，这个整数不会以零开头。

        提示：
        1 <= digits.length <= 100
        0 <= digits[i] <= 9

        示例 1：
        输入：digits = [1,2,3]
        输出：[1,2,4]
        解释：输入数组表示数字 123。

        示例 2：
        输入：digits = [4,3,2,1]
        输出：[4,3,2,2]
        解释：输入数组表示数字 4321。

     */

    // KeyPoint 方法一 套用 989 解法
    public int[] plusOne1(int[] digits) {
        List<Integer> temp = new ArrayList<>();
        int carry = 0;
        int l1 = digits.length - 1;
        int k = 1;
        while (l1 >= 0 || k != 0) {
            int x = l1 < 0 ? 0 : digits[l1];
            int y = k == 0 ? 0 : k % 10;

            int sum = x + y + carry;
            temp.add(sum % 10);
            carry = sum / 10;

            l1--;
            k = k / 10;
        }
        if (carry != 0) temp.add(carry);
        int size = temp.size();
        int[] res = new int[size];
        int index = 0;
        for (int i = size - 1; i >= 0; i--) {
            res[index++] = temp.get(i);
        }
        return res;
    }

    // KeyPoint 方法二 特殊题目，存在特殊解法，直接模拟
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        // 从右往左遍历
        for (int i = length - 1; i >= 0; i--) {
            digits[i]++;
            // 不管是否有进位都取模
            digits[i] = digits[i] % 10;
            // digits[i] 没有进位，则则直接返回，不需要进位，提前结束
            if (digits[i] != 0) return digits;
        }

        // for 循环结束都没有 return，则说明一直有进位，直到最高位，需要重新创建一个数组
        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }
}

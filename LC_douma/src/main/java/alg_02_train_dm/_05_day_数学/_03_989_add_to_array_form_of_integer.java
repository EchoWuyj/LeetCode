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
        整数的 数组形式 num 是按照 从左到右的顺序 表示其数字的数组。
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

    // 完整套用模板代码
    public static List<Integer> addToArrayForm(int[] num, int k) {

        // KeyPoint 将 int k 转成 int[] nums2
        // 若 k = 34
        String str = String.valueOf(k);
        int n = str.length();
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            // KeyPoint 字符 Ascii 码
            // (int) str.charAt(i) => 输出 '3' 对应的 Ascii 码 51，不是数字 3
            // 想要获取数字 3 => str.charAt(i) - '0'，且不需要数据类型转换
            nums2[i] = str.charAt(i) - '0';
        }
        // 没有减去 '0'，则输出 3，4 对应的 Ascii 码
        // System.out.println(Arrays.toString(nums2));  // [51, 52]
        int l1 = num.length - 1;
        int l2 = nums2.length - 1;
        int carry = 0;
        List<Integer> res = new ArrayList<>();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? num[l1] : 0;
            int y = l2 >= 0 ? nums2[l2] : 0;
            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;
            l1--;
            l2--;
        }
        if (carry != 0) res.add(carry);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 0};
        int k = 34;
        addToArrayForm(arr, k);
    }

    public List<Integer> addToArrayForm1(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        // KeyPoint 一定不要忘了减 1 => 记忆：l1 对应：-1
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

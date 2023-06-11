package alg_03_leetcode_top_zcy.class_09;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 12:59
 * @Version 1.0
 */

// 加一
public class Problem_0066_PlusOne {

    /*
        给定一个由整数组成的非空数组所表示的非负整数在该数的基础上加一
        最高位数字存放在数组的首位,数组中每个元素只存储单个数字
        你可以假设除了整数0之外,这个整数不会以零开头

        [1,2,3] -> [1,2,4]
        [4,3,2,1] -> [4,3,2,2]
        虽然给的是数组,但是将其认为是数字

     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        // 从最低位开始
        for (int i = n - 1; i >= 0; i--) {
            // 判断是否digits[i]<9,考虑进位问题
            if (digits[i] < 9) {
                digits[i]++;
                // 直接return,后面代码不会执行
                return digits;
            }
            // 原来个位是9,加1进位
            // 加1操作在下次for循环里面digits[i]++执行
            digits[i] = 0;
        }
        // 每位执行for循环之后都没有return,则说明每位都是9,
        // 即999的情况,进位后会多一位,需要一个新数组装
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}

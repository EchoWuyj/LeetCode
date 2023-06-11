package alg_02_train_dm._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 11:32
 * @Version 1.0
 */
public class _03_738_monotone_increasing_digits {
      /*
        738. 单调递增的数字
        给定一个非负整数 N，找出小于或等于 N 的最大的整数，
        同时这个整数需要满足其各个位数上的数字是单调递增。
        （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的）

        例如：1233 单调递增的数字 √
              12332 单调递增的数字 ×

        示例 1:
        输入: N = 10
        输出: 9

        示例 2:
        输入: N = 1234
        输出: 1234

        示例 3:
        输入: N = 332
        输出: 299

        说明: N是在[0, 10^9]范围内的一个整数。
     */

    // KeyPoint 贪心思路
    // 1.遇到递增数字，尽量保证高位不变
    // 2.遇到两个非递增数字，将高位数字减 1，高位减 1 完之后，要 check 一下，是否比更高位数字要小，
    //   若 check 通过，则需要将更高位数字减 1，依次循环，直到比更高位数字要'大于等于'
    // 3.将最后修改的高位后的所有数字都修改成 9
    public int monotoneIncreasingDigits(int n) {
        // 将 int 转成 String，再转成 char 数组
        char[] strN = String.valueOf(n).toCharArray();

        // 1. 找到第一个递减的位
        int i = 1;
        // while 循环相对更加优雅
        // KeyPoint  while 设置执行循环操作成立时的条件
        // strN[i - 1] <= strN[i] 则 i 不断往前走
        while (i < strN.length && strN[i - 1] <= strN[i]) i++;

        // 此时 i 为单调递减的位置，非递增数字中'低位位置'
        // 判断 i 位置，若 i 在 strN.length 内部，则 n 不是单调递增的数字
        if (i < strN.length) {
            // 不断将前一个数字 -1，直到 strN[i - 1] <= strN[i]，保证是单调递增
            // i 为非递增数字中'低位位置'
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i--;
            }
            // i先加 1，为了将 i 后面的数字都设置为 9
            i++;
            while (i < strN.length) {
                strN[i++] = '9';
            }
            return Integer.parseInt(new String(strN));
        } else {
            // n 本身就是单调递增的数字
            return n;
        }
    }
}

package alg_02_train_dm._25_day_贪心算法二_二刷;

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
        (当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的)

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

        提示：
         N是在[0, 10^9]范围内的一个整数。
     */

    // KeyPoint 贪心思路
    public int monotoneIncreasingDigits(int n) {

        // KeyPoint 从简单数字(简单的情况)，开始模拟找规律

        // 6 2
        // => 5 9

        // 4 6 2
        // => 4 5 9

        // 8 6 2
        // => 7 9 9

        //  1  2  3  6  4  3  1
        // i-1 i

        //     1  2  3  6  4  3  1
        //             i-1 i
        //  => 1  2  3  5  9  9  9
        //             i-1 i

        //     2  3  3  3  3  3  2
        //                    i-1 i
        //  => 2  2  9  9  9  9  9
        //    i-1 i

        // KeyPoint 代码步骤
        // 1.遇到递增数字，尽量保证高位不变
        // 2.遇到两个非递增数字，将高位数字减 1，高位减 1 完之后，要 check 一下，是否比更高位数字要小
        //   若 check 通过，则需要将更高位数字减 1，依次循环，直到比更高位数字要'大于等于'
        // 3.将最后修改的高位后的所有数字都修改成 9

        // 将 int 转成 String，再转成 char 数组
        char[] charsN = String.valueOf(n).toCharArray();

        // 1.找到第一个递减的位
        int i = 1;
        int len = charsN.length;

        // 推荐使用 while 循环，代码相对更加优雅
        // while 执行循环的条件：charsN[i-1] <= charsN[i] 则 i 不断往前走
        while (i < len && charsN[i - 1] <= charsN[i]) i++;

        // 此时 i 为单调递减的位置，非递增数字中'低位位置'
        // 判断 i 位置，若 i 在 charsN 内部，则 n 不是单调递增的数字
        if (i < len) {
            // 不断将前一个数字 -1，直到 charsN[i-1] <= charsN[i]，保证是单调递增
            // i 位置为最后减 1 的位置，需要将 i+1 及其往后都设置为 9
            // KeyPoint 利用 while 循环来移动指针到特定的位置
            while (i > 0 && charsN[i - 1] > charsN[i]) {
                // KeyPoint 通过 -= 符号，巧妙避开了：字符 'num'-1 存在的问题
                charsN[i - 1] -= 1;
                i--;
            }
            // 举例说明
            //     2  3  3  3  3  3  2
            //                   i-1 i
            //  => 2  2  9  9  9  9  9
            //    i-1 i

            // 将 i+1 (包括 i+1) 往后的位置的数值都设置成 9
            i++;
            while (i < len) {
                // KeyPoint 使用 '9' 来设置，而不是数字 9
                charsN[i++] = '9';
            }
            return Integer.parseInt(new String(charsN));
        } else {
            // i = len => n 本身就是单调递增的数字
            return n;
        }
    }

    // KeyPoint char 字符 和 数字运算
    public static void main(String[] args) {
        int n = 1;
        char[] charsN = String.valueOf(n).toCharArray();
        int len = charsN.length;
        for (int i = 0; i < len; i++) {
            // 字符 '1' + 1 => 使用 ascii 码值进行计算
            // '1' ascii 为 49，'1' + 1 => 50
            System.out.println(charsN[i] + 1); // 50

            // 使用 += 符号，正常计算 1 + 1 =2
            System.out.println(charsN[i] += 1); // 2
        }
    }
}

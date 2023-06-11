package alg_02_train_dm._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 14:54
 * @Version 1.0
 */
public class _07_670_maximum_swap {
      /*
        670. 最大交换
        给定一个非负整数，你至多可以'交换一次'数字中的任意两位。返回你能得到的最大值。

        示例 1 :
        输入: 2736
        输出: 7236
        解释: 交换数字2和数字7。

        2736 -> 3726 不是最大值
        2736 -> 7623 最大值

        示例 2 :
        输入: 9973
        输出: 9973
        解释: 不需要交换。

        注意:
        给定数字的范围是 [0, 10^8]

     */

    // KeyPoint 方法一暴力 => 尝试将数字的任意两位都进行交换，从而获取最大值
    public int maximumSwap1(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int len = chars.length;

        int max = num;
        // 给定的 num 范围 [0, 10^8]，但是 10^8 的 len 长度也就 9，
        // 即整数的位数不是很多，故 O(n^2) 时间复杂度代码是可以通过的
        for (int i = 0; i < len; i++) {
            // i 和 i+1 进行交换，故 j 从 i+1 开始
            for (int j = i + 1; j < len; j++) {
                swap(chars, i, j);
                max = Math.max(max, Integer.parseInt(new String(chars)));
                // 交换回来
                swap(chars, i, j);
            }
        }
        return max;
    }

    // 交换数组两个元素
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // KeyPoint 方法二 贪心
    // 贪心策略
    // 1.若最高位后面有比其值大的值，取后面最大的那个值，和最高位进行两者交换
    // 2.若没有比最高位的值还大的值，则按照这种方式处理次高位上的数值
    // 7263 -> 7623
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();

        // 记录字符数组中每个数字最后出现的位置索引
        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            // 相同的数字字符，后面出现的索引 i 会覆盖前面出现的索引，从而保证是最后出现的位置索引
            // index 索引 => 数字
            // last[index] => 位置
            last[chars[i] - '0'] = i;
        }

        // chars 从前往后判断 => 高位到低位
        // i 表示字符位置
        for (int i = 0; i < chars.length; i++) {
            // 对于当前位的字符，找比其大且是最大的字符，故从 9 开始递减判断
            // chars[i] - '0' 当前字符数字值，比其小的不用考虑交换
            for (int digit = 9; digit > chars[i] - '0'; digit--) {
                // digit 位置 > i 位置
                if (last[digit] > i) {
                    // 交换
                    char tmp = chars[i];
                    chars[i] = chars[last[digit]];
                    chars[last[digit]] = tmp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }

        // 执行外层 for 循环，但不执行内层 for 循环中的 return 语句
        // 则说明 num 中数字是从大到小降序排列的，故不交换，则直接返回 num
        return num;
    }
}

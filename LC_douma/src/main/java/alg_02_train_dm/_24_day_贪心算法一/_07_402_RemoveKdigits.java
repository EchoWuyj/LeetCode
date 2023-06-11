package alg_02_train_dm._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 20:23
 * @Version 1.0
 */
public class _07_402_RemoveKdigits {

    /*
        给定一个以字符串表示的非负整数 num
        移除这个数中的 k 位数字，使得剩下的数字最小

        输入：num = "1432219", k = 3
        输出："1219"
        解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219

        输入：num = "10200", k = 1
        输出："200"
        解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。

        分析:
        贪心策略:每次删除前面比较大的数字
        删除一个数字的贪心策略：[D0,D1,D2,D3,...,Dn-1]
        从左往右找到第一个位置 i（i>0），使得 Di < D(i-1)，删除 D(i-1)
        如果不存在 Di < D(i-1)，说明序列递增，删除最后一个字符
        例如：num 2349，删除最后一个字符 9
        例如：num 439 k = 1，
             若删除 4，num = 39
             若删除 3，num = 49
     */

    // 贪心策略：每次删除前面比较大的数字
    // KeyPoint O(k*n) 超出时间限制
    // 1 <= k <= num.length <= 10^5
    public String removeKdigits(String num, int k) {
        // 将 String 转成 StringBuilder，方便删除操作
        // new StringBuilder(num) 注意需要将 num 传入形参中，别搞忘了
        // KeyPoint
        //  1.定义变量名避免混淆，numStr 和 num，尤其是在 idea 中使用代码提示，更是容易出错
        //  2.找 bug 看代码中有那些部分是"浅色"，大概率是 bug 源头
        StringBuilder builder = new StringBuilder(num);
        // 循环删除 k 次
        for (int count = 0; count < k; count++) { // O(k)
            // 标记是否有删除数字
            boolean hasDeleted = false;
            // 涉及 [i-1]，i 从 1 开始
            // 每次外层 for 循环都要遍历一遍 builder，消耗性能，是否能使用空间换时间，提高性能
            for (int i = 1; i < builder.length(); i++) { // O(n)
                // 如果 [i-1] >[i]，即前面的数字大，则删除 [i-1]
                if (builder.charAt(i) < builder.charAt(i - 1)) {
                    builder.deleteCharAt(i - 1);
                    hasDeleted = true;
                    // 结束本次内层 for 循环，进行下一次外层 for 循环
                    break;
                }
            }
            // 没有删除数字，则说明序列是递增的，那么删除最后一个字符
            if (!hasDeleted) builder.deleteCharAt(builder.length() - 1);
        }

        // 删除前面是 0 的字符
        int len = builder.length();
        while (len != 0) {
            // charAt 返回类型 char，字符之间的比较
            // 注意:使用 numStr 不是 num
            if (builder.charAt(0) > '0') break;
            builder.deleteCharAt(0);
            // len 重新赋值，循环判断，可能有不止一个 0
            len = builder.length();
        }
        // builder.length() == 0，即从原数字移除所有的数字，剩余为空就是 0
        // KeyPoint 一开始想不到没有关系，可以根据测试用例来对代码进行调整
        return builder.length() == 0 ? "0" : builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _07_402_RemoveKdigits().removeKdigits("10200", 1));
        // 200
    }
}

package alg_02_train_dm._24_day_贪心算法一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 20:23
 * @Version 1.0
 */
public class _07_402_RemoveKdigits {

    /*
        给定一个以字符串表示的 非负整数 num
        移除这个数中的 k 位数字，使得剩下的数字最小

        输入：num = "1432219", k = 3
        输出："1219"
        解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219

        输入：num = "10200", k = 1
        输出："200"
        解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。

        提示：
        1 <= k <= num.length <= 105
        num 仅由若干位数字（0-9）组成
        除了 0 本身之外，num 不含任何前导零

     */

    // KeyPoint 方法一 贪心 => 超时
    // 贪心策略：每次删除前面比较大的数字
    // O(k*n) => O(n^2)
    // 1 <= k <= num.length <= 10^5
    public String removeKdigits(String num, int k) {

        // 1 3 9 9 9
        // 1 5 0 0 0
        //   ↑
        //   i

        // 对于两个相同长度的数字序列
        // 最左边不同的数字决定了这两个数字的大小

        // KeyPoint 贪心策略：每次删除前面比较大的数字
        // 删除一个数字的贪心策略
        // 数字串 [D0,D1,D2,D3,...,Dn-1]，从左往右找到第一个位置 i（i>0），使得 Di < D(i-1)，删除 D(i-1)
        // 若不存在 Di < D(i-1)，说明序列递增，删除最后一个字符 => num 2349，删除最后一个字符 9

        // num  4     3    9
        //      ↑     ↑
        //    D(i-1)  Di

        // num 439 k = 1
        // 若删除 4，num = 39
        // 若删除 3，num = 49
        // 故删除 4，保留 3

        // KeyPoint 手动模拟流程

        // num： 1  4  3  2  2  3  9
        //          ↑  ↑
        //         i-1 i
        //          ↑
        //        delete

        // num： 1  3  2  2  3  9
        //          ↑  ↑
        //         i-1 i
        //          ↑
        //        delete

        // num： 1  2  2  3  9
        //                   ↑  ↑
        //                  i-1 i
        //                   ↑
        //                 delete

        // KeyPoint 代码经验
        // 1.定义变量名尽量避免混淆，numStr 和 num，尤其是在 idea 中使用代码提示，更是容易出错
        // 2.找 bug 看代码中有那些部分是"浅色"，大概率是 bug 源头

        // 将 String 转成 StringBuilder，方便删除操作
        // =>?new StringBuilder(num) 注意需要将 num 传入形参中，别搞忘了
        StringBuilder sb = new StringBuilder(num);

        // 循环删除 k 次
        for (int count = 0; count < k; count++) { // O(k)
            // 标记是否有删除数字
            boolean hasDeleted = false;
            // 涉及 [i-1]，i 从 1 开始
            // 优化：每次外层 for 循环都要遍历一遍 sb，消耗性能，是否能使用空间换时间，提高性能
            for (int i = 1; i < sb.length(); i++) { // O(n)
                // 如果 [i-1] >[i]，即前面的数字大，则删除 [i-1]
                if (sb.charAt(i - 1) > sb.charAt(i)) {
                    sb.deleteCharAt(i - 1);
                    hasDeleted = true;
                    // 删除了一个数字，结束本次内层 for 循环，执行下一次外层 for 循环
                    break;
                }
            }
            // 没有删除数字，则说明序列是递增的，那么删除最后一个字符
            if (!hasDeleted) sb.deleteCharAt(sb.length() - 1);
        }

        // 删除前面是 0 的字符 => 删除前导零
        int len = sb.length();
        while (len != 0) {
            // charAt 返回类型 char，字符之间的比较
            if (sb.charAt(0) > '0') break;
            sb.deleteCharAt(0);
            // len 重新赋值，循环判断，可能有不止一个 0
            len = sb.length();
        }
        // sb.length() == 0，即从原数字移除所有的数字，剩余为空就是 "0"
        // 一开始想不到没有关系，可以根据测试用例来对代码进行调整
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _07_402_RemoveKdigits().removeKdigits("10200", 1));
        // 200
    }
}

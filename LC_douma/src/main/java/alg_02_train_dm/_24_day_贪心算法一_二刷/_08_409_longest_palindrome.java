package alg_02_train_dm._24_day_贪心算法一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 20:59
 * @Version 1.0
 */
public class _08_409_longest_palindrome {
      /*
           409. 最长回文串
           给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
           在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

           示例 1:
           输入："abccccdd"
           输出： 7
           解释：对输入的 "abccccdd"，可以重新排序，在满足回文串要求基础上，保证其为最长
                 => dccaccd
                       ↑
                    对称中心

           输入:"ababbca"
           输出  baaab

           提示:
           1 <= s.length <= 2000
           s 只由小写 和/或 大写英文字母组成

       */

    public int longestPalindrome(String s) {

        // KeyPoint 贪心策略
        // 1.每次将这个字母出现次数 count / 2，放在回文串的左右两侧，从而保证回文串更长
        //   该步骤 count 不用区分奇偶，若 count 为偶数自然没有问题，若 count 为奇数'步骤2'处理
        // 2.若 count 为奇数，需要根据回文串长度奇偶，从而决定是否将其加入
        //   2.1 回文串长度为偶数，将'多余一个字符'加入回文串
        //       => 回文串 bb，新增 aaa，结果 ababa
        //   2.2 回文串长度为奇数，将'多余一个字符'不加入回文串
        //       => 回文串 bbb，新增 aaa，结果 bababa，abbbaa 不管怎么构造，都有多余的 a

        // ASCII 码表中，大写字母和小写字母，在字符表中不是相连的，中间存在其他字符
        // 但常见字符总的个数是 128，包含了大写字母和小写字母

        // counts 对每个字符计数
        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            // 注意：不用 counts[c -'a']
            // => 因为在 int[128] 中，每个字符都有自己位置，互不冲突
            // char 隐式转换成 int
            counts[c]++;
        }

        // 回文串长度
        int res = 0;
        for (int count : counts) {
            // 贪心：每次都将 count 一半，放在回文串的左右两侧，从而保证回文串更长
            // 1.若 count = 1 => res += (1 / 2) * 2 = 0
            // 2.若 count = 2 => res += (2 / 2) * 2 = 2
            res += (count / 2) * 2;
            // 回文串中间只能有一个出现奇数次的字符
            //  => count 为奇数 => count % 2 == 1
            //  => 回文串为偶数 => res % 2 == 0
            // 将 count 对应字符放在回文串中间，即 res++;
            // 注意：该操作只会执行一次，res 为奇数后，res 后面就一直为奇数了，因为后续都是左右两边对称添加元素的
            if (count % 2 == 1 && res % 2 == 0) {
                res++;
            }
        }
        return res;
    }
}

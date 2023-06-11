package alg_02_train_dm._24_day_贪心算法一;

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
        注意:假设字符串的长度不会超过 1010。

        示例 1:
        输入:"abccccdd"
        输出: 7 => dccaccd => 字符可以重新排序

        输入:"ababbca"
        输出  baaab
         */

    // 贪心策略：每次将这个字母的 v(字母出现次数) / 2 放在回文串的左右两侧，从而保证回文串更长
    // 计算每个字符个数，通过判断:当前字符出现次数奇偶，回文串长度奇偶，确定是否将'出现奇数次字符'放到回文串中
    public int longestPalindrome(String s) {

        // 注意:ASCII 码表中，大写字母和小写字母，在字符表中不是相连的，中间存在其他字符
        //      但所有字符总的个数是 128，包含了大写字母和小写字母

        // counter 对每个字符计数
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            // 不用 counter[c -'a']，因为在 int[128] 中，每个字符都有自己位置，互不冲突
            // char 隐式转换成 int
            counter[c]++;
        }

        // 回文串长度
        int ans = 0;
        for (int v : counter) {
            // KeyPoint 贪心:每次都将 v 一半，放在回文串的左右两侧，从而保证回文串更长
            // ans += 3 / 2 * 2 = 2
            ans += v / 2 * 2;
            // 结论:回文串中间只能有一个出现奇数次的字符
            //  => v 为奇数， 回文串为偶数，将 v 对应字符放在回文串中间
            //  => 该操作只会执行一次， ans 为奇数后，ans 后面就一直为奇数了，因为后续都是左右两边对称添加元素的
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}

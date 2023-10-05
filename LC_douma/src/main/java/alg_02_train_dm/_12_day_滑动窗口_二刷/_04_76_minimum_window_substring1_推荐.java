package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:07
 * @Version 1.0
 */
public class _04_76_minimum_window_substring1_推荐 {
    /*
        76. 最小覆盖子串
        给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
        如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

        注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

        示例 1：
        输入：s = "ADOBECODEBANC", t = "ABC"
        输出："BANC"

        示例 2：
        输入：s = "a", t = "a"
        输出："a"

        提示：
        1 <= s.length, t.length <= 10^5
        s 和 t 由英文字母组成

        进阶：你能设计一个在 O(n) 时间内解决此问题的算法吗？

     */

    // KeyPoint 暴力解法 => 超时
    // 枚举所有区间，判断区间是否覆盖 t 中所有字符，并从中找到最小区间
    // 具体代码省略

    // KeyPoint 一般枚举所有区间 => 使用滑动窗口优化，避免重复计算
    public String minWindow1(String s, String t) {

        //            right
        //              ↓
        //    0 1 2 3 4 5 6 7
        // S：A B A A C B A B
        //          ↑
        //        left

        // T：A B C
        // minLen = 3
        // 返回索引 [3,5] 对应字符，而不是 minLen

        int n = s.length();
        // 统计字符串 t 中每个字符出现的次数
        // s 和 t 由英文字母组成
        // => 大小写英文字符之间并不是连续的，存在 8 个其他字符，故数组大小定义为 60(26+26+8)
        // KeyPoint 想要简单，直接定义 int[128]，不用减，直接存字符
        int[] cntT = new int[60];

        // 统计 t 中不同的字符数
        // => 为了在后面每个窗口判断相同字符的数量
        int uniqueCharsInT = 0;

        for (char c : t.toCharArray()) {
            // A 65，a 97
            // A 对应 ASCII 最值，故以其为基准
            if (cntT[c - 'A'] == 0) uniqueCharsInT++;
            cntT[c - 'A']++;
        }

        int left = 0, right = 0;
        // 窗口中每个字符出现的次数
        int[] windowCntS = new int[60];

        // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
        int matchedChars = 0;

        // 通过数组 res 记录信息，方便后期返回结果
        // KeyPoint 后期优化：定义成 class

        // 定义 res 数组
        // [0] => right-left+1 最小长度
        // [1] => left 左边界
        // [2] => right 右边界
        int[] res = {-1, 0, 0};

        while (right < n) {
            // right 字符
            char rightChar = s.charAt(right);
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;

            // 一个字符出现次数相同，则该字符匹配上了
            // rightCharIndex => 字符匹配
            // [rightCharIndex] => 次数匹配
            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }

            // while 循环逻辑
            // 1.若滑动窗口不满足 matchedChars == uniqueCharsInT，则 right 一直右移，直到窗口满足为止
            // 2.多次尝试缩减窗口，找到最短符合条件的子串

            //          right
            //            ↓
            //    0 1 2 3 4 5 6 7
            // S：A B A A C B A B
            //    ↑
            //   left

            // T：A B C

            //          right
            //            ↓
            //    0 1 2 3 4 5 6 7
            // S：A B A A C B A B
            //      ↑
            //    left

            // T：A B C

            // 避免索引越界：left <= right
            while (left <= right && matchedChars == uniqueCharsInT) {
                // 记录满足条件的最短子串
                // res[0] == -1 表示最开始
                // right-left+1 < res[0] 表示 minLen 更小
                // 注意： res[0]， res[1]， res[2] 三者是同步更新
                //       不能使用 res[2] - res[1] + 1 更新  res[0]
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }
                // left 字符
                char leftChar = s.charAt(left);
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                // KeyPoint 后期优化：left 一个字符，一个字符，往前移动，其实性能很差，
                left++;
            }
            // 右移 right 扩大窗口
            right++;
        }
        // 若没有最小覆盖子串，返回空串
        // 注意：substring [start,end)，故需要额外加 1
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    // KeyPoint cntStrT 和 windowCnt 直接定义 128 数组，简化运算 => 推荐
    public String minWindow2(String s, String t) {
        int n = s.length();
        int[] cntStrT = new int[128];
        int uniqueCnt = 0;

        for (char c : t.toCharArray()) {
            if (cntStrT[c] == 0) uniqueCnt++;
            cntStrT[c]++;
        }

        int[] windowCnt = new int[128];
        int matchCnt = 0;
        int[] res = {-1, 0, 0};

        int left = 0, right = 0;
        while (right < n) {
            char rightChar = s.charAt(right);
            windowCnt[rightChar]++;
            if (windowCnt[rightChar] == cntStrT[rightChar]) {
                matchCnt++;
            }

            while (left <= right && matchCnt == uniqueCnt) {
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }
                char leftChar = s.charAt(left);
                windowCnt[leftChar]--;
                if (windowCnt[leftChar] < cntStrT[leftChar]) {
                    matchCnt--;
                }
                left++;
            }

            right++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }
}

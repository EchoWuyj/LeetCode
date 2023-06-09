package alg_02_train_dm._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:07
 * @Version 1.0
 */
public class _04_76_minimum_window_substring {
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

    public String minWindow1(String s, String t) {
        int length = s.length();
        // 统计字符串 t 中每个字符出现的次数
        // s 和 t 由英文字母组成
        // => 大小写英文字符之间存在 8 个其他字符，故数组大小定义为 60(26+26+8)
        int[] cntT = new int[60];

        // 统计 t 中不同的字符数
        // => 为了在后面每个窗口判断相同字符的数量
        int uniqueCharsInT = 0;

        for (char c : t.toCharArray()) {
            // A 65
            // a 97
            if (cntT[c - 'A'] == 0) uniqueCharsInT++;
            cntT[c - 'A']++;
        }

        int left = 0, right = 0;
        // 窗口中每个字符出现的次数
        int[] windowCntS = new int[60];

        // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
        int matchedChars = 0;

        // [0] => right -left+1 最小长度
        // [1] => left 左边界
        // [2] => right 右边界
        int[] ans = {-1, 0, 0};
        while (right < length) {
            // right 字符
            char rightChar = s.charAt(right);
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;

            // 一个字符出现次数相同，则该字符匹配上了
            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }

            // 1.若窗口不满足 matchedChars == uniqueCharsInT，则 right 一直右移，直到窗口满足为止
            // 2.尝试缩减窗口，因为我们想找到最短符合条件的子串
            while (left <= right && matchedChars == uniqueCharsInT) {
                // 记录满足条件的最短子串
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // left 字符
                char leftChar = s.charAt(left);
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                // left 一个字符，一个字符，往前移动，其实性能很差，可以优化
                left++;
            }
            right++;
        }
        // 若没有最小覆盖子串，返回空串
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    // KeyPoint 优化
    // left 只关心出现在 T 中的字符，其他字符并不关心，因此 left
    // 每次移动直接移动到 T 中出现的字符，而不是每次移动一个字符 => 减少窗口移动次数
    // S: W W E E A B C B
    // T: A B C
    public String minWindow2(String s, String t) {

        // 统计字符串 t 中每个字符出现的次数
        int[] cntT = new int[60];
        // 统计 t 中不同的字符数
        int uniqueCharsInT = 0;
        for (char c : t.toCharArray()) {
            if (cntT[c - 'A'] == 0) uniqueCharsInT++;
            cntT[c - 'A']++;
        }

        // 在 s 中拿到是 t 中的字符，及其在 s 中的位置
        // Pair 自定义泛型， key => 索引，value => s和t都出现的字符
        // => 后续操作基于 filteredS 进行滑动窗口
        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cntT[c - 'A'] > 0) {
                // s 和 t 交集相对顺序没有发生改变
                filteredS.add(new Pair<>(i, c));
            }
        }

        int left = 0, right = 0;
        // 窗口中每个字符出现的次数
        int[] windowCntS = new int[60];
        // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
        int matchedChars = 0;
        int[] ans = {-1, 0, 0};
        // 基于 filteredS(ArrayList) 进行滑动窗口
        while (right < filteredS.size()) {

            char rightChar = filteredS.get(right).getValue();
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;

            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }

            // 尝试缩减窗口，因为我们想找到最短符合条件的子串
            while (left <= right && matchedChars == uniqueCharsInT) {
                // left -> start
                // right -> end
                int end = filteredS.get(right).getKey();
                int start = filteredS.get(left).getKey();

                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                char leftChar = filteredS.get(left).getValue();
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    // Pair 类，KV 结构，自定义泛型
    private class Pair<K, V> {
        // 将 K 和 V 当做 int 数据类型来理解
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}

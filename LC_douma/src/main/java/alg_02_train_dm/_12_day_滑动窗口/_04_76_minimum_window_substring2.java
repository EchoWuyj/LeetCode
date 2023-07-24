package alg_02_train_dm._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 20:59
 * @Version 1.0
 */
public class _04_76_minimum_window_substring2 {

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

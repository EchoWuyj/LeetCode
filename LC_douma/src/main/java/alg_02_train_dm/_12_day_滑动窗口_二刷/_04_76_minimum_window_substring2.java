package alg_02_train_dm._12_day_滑动窗口_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 20:59
 * @Version 1.0
 */
public class _04_76_minimum_window_substring2 {

    // KeyPoint 优化
    // left 只关心出现在 T 中的字符，其他字符并不关心
    // 因此 left 每次移动直接移动到 T 中出现的字符，而不是每次移动一个字符
    // => 减少窗口移动次数，提高性能

    //              right
    //                ↓
    // S: W W E E A B C B
    //    ↑
    //   left
    // T: A B C

    //              right
    //                ↓
    // S: W W E E A B C B
    //            ↑
    //           left
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

        // KeyPoint 优化
        // 在 S 中拿到是 T 中的字符，及其在 S 中的位置
        // => 对 S 中字符做了一个层过滤
        // => 后续操作基于 filteredS 进行滑动窗口，而不是使用 S
        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cntT[c - 'A'] > 0) {
                // S 和 T 交集相对顺序没有发生改变
                filteredS.add(new Pair<>(i, c));
            }
        }

        int left = 0, right = 0;
        // 窗口中每个字符出现的次数
        int[] windowCntS = new int[60];
        // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
        int matchedChars = 0;
        int[] res = {-1, 0, 0};
        // KeyPoint 基于 filteredS (ArrayList) 进行滑动窗口
        while (right < filteredS.size()) {
            char rightChar = filteredS.get(right).getValue();
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;
            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }

            // 尝试缩减窗口，因为我们想找到最短符合条件的子串
            while (left <= right && matchedChars == uniqueCharsInT) {
                // KeyPoint 注意事项
                // left -> start，right -> end
                // => start，end 本质：对应还是索引
                int end = filteredS.get(right).getKey();
                int start = filteredS.get(left).getKey();

                if (res[0] == -1 || end - start + 1 < res[0]) {
                    res[0] = end - start + 1;
                    res[1] = start;
                    res[2] = end;
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
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    // Pair 类
    // KeyPoint 自定义泛型 => 类中得声明 KV 结构
    private class Pair<K, V> {

        // Pair 自定义泛型
        // key => 索引
        // value => 字符 => S 和 T 中共有

        // KeyPoint 加强对泛型理解
        // 将 K 和 V 当做 int 数据类型来理解

        // KeyPoint 修饰符
        // 若是将属性定义成 public，直接通过 .key 或者 .value 获取，可以省略 get 和 set方法
        public K key;
        public V value;

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

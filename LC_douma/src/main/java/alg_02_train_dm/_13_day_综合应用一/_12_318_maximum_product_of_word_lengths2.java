package alg_02_train_dm._13_day_综合应用一;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 16:52
 * @Version 1.0
 */
public class _12_318_maximum_product_of_word_lengths2 {

    // KeyPoint 方法二 位运算 + 预计算 => 推荐掌握
    // 时间复杂度：O((m + n)* n)
    // 空间复杂度：O(n)
    public int maxProduct2(String[] words) {

        // for {
        //    for {
        //       调用 hasSameChar
        // } }

        // 暴力解法，在 for 循环过程中，调用 hasSameChar，嵌套在一起
        // 优化
        // => 将嵌套操作分离开来，hasSameChar 先计算好，之后再去 for 循环
        // => 通过预计算，提前将每个单词的 bitMask 计算出来

        // O(mn)
        int n = words.length;
        int[] bitMasks = new int[n];
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                bitMasks[i] |= (1 << (c - 'a'));
            }
        }

        // O(n^2)
        int ans = 0;
        for (int i = 0; i < n; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];
                // 相与为 0，说明对位分别为 1 和 0
                if ((bitMasks[i] & bitMasks[j]) == 0) {
                    ans = Math.max(ans, word1.length() * word2.length());
                }
            }
        }
        return ans;
    }

    // KeyPoint 方法三 位运算 + 预计算
    // 时间复杂度：O((m + n)* n)
    // 空间复杂度：O(n)
    public int maxProduct(String[] words) {
        // O(mn)
        Map<Integer, Integer> map = new HashMap<>();
        int n = words.length;
        // 不需要定义 bitMasks 数组，只要一个 int bitMask 即可
        for (int i = 0; i < n; i++) {
            int bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            // 不同的单词，可能有相同的 bitmask，但是只取最长的单词，从而减少比较次数
            // 如：ab and aabb
            // 相同的 bitmask 对应的 int 值一样，可以作为 map 的 key
            map.put(bitMask, Math.max(map.getOrDefault(bitMask, 0), words[i].length()));
        }

        // O(n^2)
        // 时间复杂度没有降下来，但是实际上比较的次数有可能会降低
        // KeyPoint 在 OJ 上测试，时间效果并不理想，主要使用了 Map，影响了性能，同时数据集规模不大，测试不来算法性能
        int res = 0;
        for (int x : map.keySet()) {
            for (int y : map.keySet()) {
                // KeyPoint (x & y) 先加上括号，再去判断和 0 是否相等
                if ((x & y) == 0) {
                    res = Math.max(res, map.get(x) * map.get(y));
                }
            }
        }
        return res;
    }
}

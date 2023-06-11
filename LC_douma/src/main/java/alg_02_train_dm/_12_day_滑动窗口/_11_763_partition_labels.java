package alg_02_train_dm._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 16:38
 * @Version 1.0
 */
public class _11_763_partition_labels {

      /*
       leetcode 763. 划分字母区间
       字符串 S 由小写字母组成。
       我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
       返回一个表示每个字符串片段的长度的列表。

       示例：
       输入：S = "ababcbacadefegdehijhklij"
       输出：[9,7,8]
       解释：
       划分结果为 "ababcbaca", "defegde", "hijhklij"。
       每个字母最多出现在一个片段中。
       像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。

        提示：
        S的长度在[1, 500]之间。
        S只包含小写字母 'a' 到 'z'
     */

    // 思路：每个片段可以看成一个窗口，该窗口中包含的字符，不会出现其他窗口中出现，则该窗口就是一个片段
    public List<Integer> partitionLabels(String s) {

        // 计算并存储每个字符在数组中所在的最大索引
        int[] c2Index = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // i 表示字符在原数组中远近位置
            // 同一个字符，以在数组中最后位置为准
            c2Index[s.charAt(i) - 'a'] = i;
        }

        // 存储最终结果
        List<Integer> res = new ArrayList<>();

        // 维护窗口
        int left = 0;
        int right = 0;
        // 每个字符都需要遍历，判断每个字符最远位置
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            right = Math.max(right, c2Index[c - 'a']);

            // i 指针，不断后移，当 i == right，该窗口内最远距离为 right，即为一个片段
            if (i == right) {
                res.add(right - left + 1);
                // left 下个片段开始位置
                left = right + 1;
            }
        }
        return res;
    }
}

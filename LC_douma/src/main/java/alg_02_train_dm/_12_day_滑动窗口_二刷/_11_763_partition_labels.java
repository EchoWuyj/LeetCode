package alg_02_train_dm._12_day_滑动窗口_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 16:38
 * @Version 1.0
 */
public class _11_763_partition_labels {

      /*
           763 划分字母区间
           字符串 S 由小写字母组成。
           我们要把这个字符串划分为 尽可能多的片段，同一字母最多出现在一个片段中。
           返回一个表示每个字符串片段的长度的列表。

           示例：
           输入：S = "ababcbacadefegdehijhklij"
           输出：[9,7,8]
           解释：
           "ababcbaca defegde hijhklij"
           划分结果为 "ababcbaca", "defegde", "hijhklij"。
           每个字母最多出现在一个片段中。
           像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。

           提示：
           S的长度在[1, 500]之间。
           S只包含小写字母 'a' 到 'z'
     */

    public List<Integer> partitionLabels1(String s) {

        // 滑动窗口
        // 思路：每个片段可以看成一个窗口，该窗口中包含的字符，不会出现其他窗口中出现，则该窗口就是一个片段

        //            right
        //              ↓
        // "a b a b c b a c a d e f e g d e h i j h k l i j"
        //  ↑
        // left

        //                           right right'
        //                              ↓  ↓
        // "a b a b c b a c a d e f e g d  e h i j h k l i j"
        //                ↑
        //               left

        //                                           right right'
        //                                              ↓   ↓
        // "a b a b c b a c a d e f e g d e h i j h k l i   j"
        //                                    ↑
        //                                   left

        // KeyPoint 经验积累
        // 1.数据预处理，为解题创造前置条件
        // 2.尤其没法直接一步实现的情况下，更需要数据预处理

        // KeyPoint 数据预处理
        // 计算并存储，每个字符在数组中所在的最大索引
        int[] map = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // i 表示该字符在原字符串 s 中索引位置
            // 同一个字符，后面的 i 会覆盖前面的 i，即以最靠后的位置为准
            map[s.charAt(i) - 'a'] = i;
        }

        // 存储最终结果
        List<Integer> res = new ArrayList<>();

        // 维护窗口
        int left = 0, right = 0;
        // for 循环写法
        // 每个字符都需要遍历，判断每个字符最远位置
        // => 和之前滑动窗口不太一样
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            right = Math.max(right, map[c - 'a']);

            // i 指针不断后移，当 i == right，该窗口内最远距离为 right，即为一个片段
            if (i == right) {
                // 片段长度，left 和 right 两端都需要包括，故需要 +1
                res.add(right - left + 1);
                // left 下个片段开始位置
                left = right + 1;
            }
        }
        return res;
    }

    public List<Integer> partitionLabels2(String s) {

        int[] map = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            map[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        int i = 0;
        // 使用 while 循环，替换 for 循环
        // => 更加符合标准滑动窗口的写法
        while (i < n) {
            char c = s.charAt(i);
            right = Math.max(right, map[c - 'a']);
            // KeyPoint 易错点
            // 注意：不是任何情况都适用，使用 while 替换 if 的，不要想当然！
            // 本题：while (i == right) 循环体中，i 和  right 都没有更新，导致 while 死循环
            // => 提交力扣 OJ 显示：超出内存限制
            if (i == right) {
                res.add(right - left + 1);
                // left 下个片段开始位置
                left = right + 1;
            }
            i++;
        }
        return res;
    }
}

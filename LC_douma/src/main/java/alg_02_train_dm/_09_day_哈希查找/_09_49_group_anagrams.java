package alg_02_train_dm._09_day_哈希查找;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:24
 * @Version 1.0
 */
public class _09_49_group_anagrams {
     /*
        leetcode 49 号算法题：字母异位词分组
        给定一个字符串数组，将字母异位词组合在一起。
        字母异位词指字母相同，但排列不同的字符串。

        输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出:
        [
            ["bat"],              --> abt
            ["eat", "tea", ate"], --> aet
            ["tan", "nat"]        --> ant
        ]

        - 所有输入均为小写字母。
        - 不考虑答案输出的顺序。
     */

    // KeyPoint 方法一 排序
    public List<List<String>> groupAnagrams1(String[] strs) {
        // key 字母异位词排序后的字符串
        // value 装字母异位词的集合
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            // 排序后，将字符数组转成 String，作为 key
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            // 将 key 和 字符串 s 绑定
            map.get(key).add(s);
        }

        // 注意:根据题目要求的返回类型 List<List<String>>，在外面包一层 ArrayList，而不是直接返回 map.values()
        return new ArrayList<>(map.values());
    }

    // KeyPoint 方法二 数组 => 所有输入均为小写字母
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            int[] count = new int[26];
            for (char c : chars) count[c - 'a']++;
            // Arrays 数组工具类，toString 将 int 类型数组转成 String，
            // 同一组字母异位词对应的 key 是相同的
            String key = Arrays.toString(count);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());

//        int[] test = {97, 98, 99};
//        System.out.println(Arrays.toString(test));
        // [97, 98, 99]
    }
}

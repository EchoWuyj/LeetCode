package alg_02_train_dm._09_day_哈希查找;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:24
 * @Version 1.0
 */
public class _09_49_group_anagrams {
     /*
        49 号算法题：字母异位词 分组
        给定一个字符串数组，将字母异位词组合在一起。
        字母异位词指字母相同，但排列不同的字符串。

        输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

        解释:
        [                           排序操作，之后作为 key
            ["bat"],              --> abt
            ["eat", "tea", ate"], --> aet
            ["tan", "nat"]        --> ant
        ]

        提示：
        1 <= strs.length <= 104
        0 <= strs[i].length <= 100
        strs[i] 仅包含小写字母
     */

    // KeyPoint 方法一 排序
    public List<List<String>> groupAnagrams1(String[] strs) {

        // 建立 map 映射
        // key 字母异位词排序后的字符串 => 保证排序后，相同字符串作为 key
        // value 装字母异位词的集合
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            // 排序后，将字符数组转成 String，作为 key
            Arrays.sort(chars);
            // 将字符数组转成字符串
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            // 将 key 和 字符串 s 绑定
            map.get(key).add(s);
        }

        // 注意:根据题目要求的返回类型 List<List<String>>
        //      需要在外面包一层 ArrayList，而不是直接返回 map.values()
        return new ArrayList<>(map.values());
    }

    // KeyPoint 方法二 数组 => 所有输入均为小写字母
    //               => int 数组转 字符串，比较影响性能，不推荐
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            // 词频数组
            int[] count = new int[26];
            for (char c : chars) count[c - 'a']++;
            // 将词频数组作为 key => 同一组字母异位词 对应的词频数组相同，即 key 相同
            // Arrays 数组工具类，toString 将 int 类型数组转成 String
            String key = Arrays.toString(count);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());

        // int 类型数组
//        int[] test = {97, 98, 99};
//        System.out.println(Arrays.toString(test));
        // 字符串
        // [97, 98, 99]
    }
}

package algorithm._04_sliding_windows;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-03-15 23:00
 * @Version 1.0
 */
public class LeetCode_438_FindAllAnagrams {
    // 方法一:暴力法,枚举所有的长度为p.length()的子串
    // 基本思路:
    //  统计p中所有字符频次,然后再去遍历所有的字串,统计字串所有出现的频次
    //  再去进行比对,找到一个匹配的就去输出起始点的索引位置
    public List<Integer> findAnagrams01(String s, String p) {
        // 定义一个结果列表
        ArrayList<Integer> result = new ArrayList<>();

        // 1.统计p中所有字符频次
        // 通过整形数组来统计,当前只能取到26个字母,所以使用长度为26整形数组来对应每个字母
        //  从而统计每个字母出现的频次,每个索引位置给定其含义,相当于自己实现了Hash一样
        int[] pCharCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            // 通过这样的方式:p.charAt(i)-'a'来确定其索引
            pCharCounts[p.charAt(i) - 'a']++;
        }

        // 2.遍历s,以每一个字符作为起始,考察长度为p.length()的子串
        // s.length()-1和p.length()-1中的减1相抵消了,从而为s.length() - p.length()
        for (int i = 0; i <= s.length() - p.length(); i++) {
            // 3.判断当前子串是否为p的字母异位词
            boolean isMatched = true;

            // 定义一个数组,统计子串中所有字符频次
            int[] subStrCharCounts = new int[26];

            for (int j = i; j < i + p.length(); j++) {
                subStrCharCounts[s.charAt(j) - 'a']++;

                // 判断当前字符频次,如果超过了p中的频次,就一定不符合要求
                //  因为字串的长度和p的长度相同,多一个频次必然导致另外一个地方少个频次
                if (subStrCharCounts[s.charAt(j) - 'a'] > pCharCounts[s.charAt(j) - 'a']) {
                    isMatched = false;
                    // 如果有一个不满足,直接使用break跳出for循环
                    break;
                }
            }
            // 当前循环正常执行结束表示isMatched为true
            if (isMatched) {
                result.add(i);
            }
        }
        return result;
    }

    // 方法二:滑动窗口法,分别移动起始和结束位置
    // 优化思路:
    //  窗口内部遍历每个元素进行比对,对于滑动窗口而言,窗口内大多数元素其实重复的
    //  完全可以只考虑滑动之后增加和减少的元素
    public List<Integer> findAnagrams(String s, String p) {
        // 定义一个结果列表
        ArrayList<Integer> result = new ArrayList<>();

        // 1.统计p中所有字符频次
        int[] pCharCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }

        // 统计子串中所有字符出现频次的数组
        int[] subStrCharCounts = new int[26];

        // 定义双指针,指向窗口的起始(包含)和结束位置(不包含)
        int start = 0, end = 1;

        // KeyPoint 结束位置的指针向后移动,扩充字串导致长度不一样,则肯定不匹配
        //  故需要从起始位置开始向后一直移动直到抵消掉新增字符的影响,才能满足这样的要求

        // 2.移动指针,总是截取字符出现频次全部小于等于p中字符频次的子串
        //  end是不包含在内的,所以可以等于s.length()
        while (end <= s.length()) {
            // 当前新增字符
            char newChar = s.charAt(end - 1);
            subStrCharCounts[newChar - 'a']++;

            // 3.判断当前子串是否符合要求
            // 如果新增字符导致子串中频次超出了p中频次,那么移动start,消除新增字符的影响
            while (subStrCharCounts[newChar - 'a'] > pCharCounts[newChar - 'a'] && start < end) {
                // 频次减少
                char removedChar = s.charAt(start);
                subStrCharCounts[removedChar - 'a']--;
                start++;
            }

            // 如果当前子串长度等于p的长度,那么就是一个字母异位词
            if (end - start == p.length())
                result.add(start);

            // 截取字符出现频次全部小于p中字符频次的子串并且长度不等于p.length(),则是结束指针向后移动
            end++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        LeetCode_438_FindAllAnagrams findAllAnagrams = new LeetCode_438_FindAllAnagrams();
        List<Integer> result = findAllAnagrams.findAnagrams01(s, p);

        for (int index : result) {
            System.out.print(index + "\t");
        }
        System.out.println();
    }
}

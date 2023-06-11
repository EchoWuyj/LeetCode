package alg_03_leetcode_top_zcy.class_03_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-15 21:29
 * @Version 1.0
 */

// 最长公共前缀
public class problem_014_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {

        /*
            abcde
            abcdk
            abcdef
            abkst
            最长公共前缀:ab

           1)使用第一个字符串abcde,此时最长公共前缀为其长度5
           2)下面每个字符串遍历,遍历到不一样的字符停止,每次对abcde进行截取,直到最后一个字符串,
           3)再观察abcde截取成什么了,就是最后的答案

         */

        if (strs == null || strs.length == 0) {
            return "";
        }

        // 第一个字符串的字符数组
        char[] chars = strs[0].toCharArray();

        // KeyPoint min初值设置一般都是最大值,若一开始就是 Integer.MIN_VALUE,则没有比其更小的值了
        int min = Integer.MAX_VALUE;


        // 字符串数组
        for (String str : strs) {
            // 每个字符串转字符数组
            char[] temp = str.toCharArray();
            int index = 0;
            while (index < temp.length && index < chars.length) {
                // index 表示不同字符的位置
                if (chars[index] != temp[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(index, min);
        }

        if (min == 0) {
            return "";
        }

        // 因为index表示不同字符的位置,所以min不需要+1,因为substring也是不包括min位置
        return strs[0].substring(0, min);
    }
}

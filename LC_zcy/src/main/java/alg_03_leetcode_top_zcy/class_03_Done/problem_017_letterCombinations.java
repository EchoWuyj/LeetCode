package alg_03_leetcode_top_zcy.class_03_Done;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-16 14:37
 * @Version 1.0
 */

// 电话号码的字母组合
public class problem_017_letterCombinations {

    // 全局变量
    // 创建一个char类型二维数组
    public static char[][] phone = {
            {'a', 'b', 'c'}, //  2 按键    0 索引
            {'d', 'e', 'f'}, //  3         1
            {'g', 'h', 'i'}, //  4         2
            {'j', 'k', 'l'}, //  5         3
            {'m', 'n', 'o'}, //  6
            {'p', 'q', 'r', 's'}, // 7
            {'t', 'u', 'v'},   // 8
            {'w', 'x', 'y', 'z'}, // 9
    };

    //  digits "23"
    public List<String> letterCombinations(String digits) {

        // 暴力递归
        // 2: a,b,c
        // 3: d,e,f

        /*          2
                a   b   c
                3
            d  e  f
          ad  ae af
         */

        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            // 返回ans,ans为空
            // KeyPoint 最好不要返回null,而是想这种方式,先创建一个对象,判空没过,则返回该对象
            return ans;
        }

        // digits "23" -> ['2','3']
        char[] chars = digits.toCharArray();

        // chars长度和沿途按出的所有结果组合(path)长度一样
        // ['2','3'] 长度2,且2对应字符a,b,c;3对应的字符d,e,f,其组合长度也是2(ad,ae...)
        char[] path = new char[chars.length];
        process(chars, 0, path, ans);
        return ans;
    }

    /**
     * @param chars chars = ['2','3']
     * @param index index 表示按到那一位,指的是索引位置('2'->0索引 '3'->1索引)
     * @param path  str[...index-1] 沿途按出的结果都在path里
     * @param ans   str[index...] 按完之后,有哪些组合,放入到ans里
     */
    public void process(char[] chars, int index, char[] path, List<String> ans) {
        // 递归边界
        // index越界,表示chars中的字符都已经遍历最后结束了
        if (index == chars.length) {
            // 此时可以将一个path结果['a','b']加入到ans中,再去返回递归的上层加入['a','e']
            // String.valueOf(new char[]{'a', 'b'}) 结果为 ab
            ans.add(String.valueOf(path));
        } else {
            // KeyPoint 这段代码必须在else中,不能在没有else的if下面,否则该段是不管if是否成立
            //      一定会执行的,这样逻辑就错误了
            // '2'对应的数组 {'a','b','c'}
            // chars是'2','3'数组
            // chars[index] -'2' = '2'-'2' = 0 索引位置
            char[] cands = phone[chars[index] - '2'];
            // 在 {'a','b','c'} 中依次选择每个字符,先从'a'开始
            for (char cur : cands) {
                // 将'a'加入到 path 中 ['a',?],剩余的?在下轮递归中求得
                path[index] = cur;
                process(chars, index + 1, path, ans);
                // 再其递归,path =['a','d'],直到递归边界,加入ans,返回上层,再去加入['a','e'],依次类推
            }
        }
    }
}

package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 16:37
 * @Version 1.0
 */
public class _11_17_LetterCombinationsOfAPhoneNumber2_推荐 {
    private Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    // "234"
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        // 最开始传入空字符串 ""
        findCombination(digits, 0, "", res);
        return res;
    }

    // 字符串拼接另外一种方式：字符串 String 可以直接拼接
    private void findCombination(String digits, int index, String combination, List<String> res) {
        if (index == digits.length()) {
            res.add(combination);
            return;
        }
        char numChar = digits.charAt(index);
        // String => 转成 char[]
        char[] letters = map.get(numChar).toCharArray();
        for (char c : letters) {
            findCombination(digits, index + 1, combination + c, res);

            // 字符串拼接另外一种方式：
            // 1.递归调用的形参中，进行字符串拼接，则回溯中不需删除拼接的字符串
            //   因为在递归返回时，返回的是 combination，而不是 combination + c
            //   而 combination + c 只是作为下层递归函数 dfs 的 combination
            // 2.若字符串长且多，还是使用 StringBuilder，性能更好，力扣上实测
            //   字符串拼接，执行用时：5 ms ,在所有 Java 提交中击败了 15.84% 的用户
            //   StringBuilder 速度更快，执行用时：0 ms ,在所有 Java 提交中击败了 100.00% 的用户
        }
    }
}

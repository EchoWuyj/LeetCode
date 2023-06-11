package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 16:37
 * @Version 1.0
 */
public class _11_17_LetterCombinationsOfAPhoneNumber2 {
    private Map<Character, String> phone = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    private List<String> res;

    // “234”
    public List<String> letterCombinations(String digits) {
        // bug 修复：需要先初始化 res，要不然当 digits 为空的时候，
        // 返回的就是 null ，而不是空的数组
        res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        // 一开始是空字符串
        findCombination(digits, 0, "");
        return res;
    }

    // 字符串 String 可以直接拼接
    private void findCombination(String digits, int index, String combination) {
        if (index == digits.length()) {
            res.add(combination);
            return;
        }
        char numChar = digits.charAt(index);
        char[] letters = phone.get(numChar).toCharArray();
        for (char c : letters) {
            // 递归中拼接字符串，回溯中不需删除拼接的字符串
            // 即:递归返回时，返回的是 combination，而不是 combination + c
            //    combination + c 只是作为下层递归函数的 combination
            // KeyPoint 字符串拼接 => 推荐这种方式
            // 若字符串长且多，还是使用 StringBuilder，性能更好一点
            // 力扣上实测，StringBuilder 速度更快一点
            findCombination(digits, index + 1, combination + c);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _11_17_LetterCombinationsOfAPhoneNumber2()
                .letterCombinations("234"));
    }
}

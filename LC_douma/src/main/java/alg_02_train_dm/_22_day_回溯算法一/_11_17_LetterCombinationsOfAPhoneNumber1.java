package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 16:35
 * @Version 1.0
 */
public class _11_17_LetterCombinationsOfAPhoneNumber1 {
    // 根据题意构建映射 => 初始化同时，构建了映射
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

    // 定义全局变量形式(换种写法)
    private List<String> res;

    // "234"
    public List<String> letterCombinations(String digits) {
        // String 判空的形式
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        res = new ArrayList<>();
        findCombination(digits, 0, new StringBuilder());
        return res;
    }

    private void findCombination(String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        // 通过索引位置获取'数字'字符
        char numChar = digits.charAt(index);
        // 通过'数字'字符 => '字母'字符数组
        char[] letters = phone.get(numChar).toCharArray();
        for (char c : letters) {
            // 字符拼接，在拼接之后 sb 本身发生变化了，故回溯需要删除拼接的字符
            sb.append(c);
            // i+1 下一个'数字'字符
            findCombination(digits, index + 1, sb);
            // 删除最后一个字符，StringBuilder 使用的 length()，不是 size()
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _11_17_LetterCombinationsOfAPhoneNumber1()
                .letterCombinations("234"));

        // [adg, adh, adi, aeg, aeh, aei, afg, afh, afi, bdg, bdh, bdi, beg,
        //  beh, bei, bfg, bfh, bfi, cdg, cdh, cdi, ceg, ceh, cei, cfg, cfh, cfi]
    }
}

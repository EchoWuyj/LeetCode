package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 18:12
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

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return res;
        dfs(digits, 0, "", res);
        return res;
    }

    private void dfs(String digits, int index, String combination, List<String> res) {
        if (index == digits.length()) {
            res.add(combination);
            return;
        }
        char numChar = digits.charAt(index);
        char[] letter = phone.get(numChar).toCharArray();
        for (char c : letter) {
            dfs(digits, index + 1, combination + c, res);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _11_17_LetterCombinationsOfAPhoneNumber1()
                .letterCombinations("234"));

        // [adg, adh, adi, aeg, aeh, aei, afg, afh, afi, bdg, bdh, bdi, beg,
        // beh, bei, bfg, bfh, bfi, cdg, cdh, cdi, ceg, ceh, cei, cfg, cfh, cfi]
    }
}

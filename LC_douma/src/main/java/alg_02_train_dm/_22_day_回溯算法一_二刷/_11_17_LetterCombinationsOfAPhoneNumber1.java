package alg_02_train_dm._22_day_回溯算法一_二刷;

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
    /*
        17. 电话号码的字母组合
        给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
        给出数字到字母的映射如下(与电话按键相同)。注意 1 不对应任何字母。

        示例 1：
        输入：digits = "23"
        输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

        示例 2：
        输入：digits = ""
        输出：[]

        示例 3：
        输入：digits = "2"
        输出：["a","b","c"]

        提示：
        0 <= digits.length <= 4
        digits[i] 是范围 ['2', '9'] 的一个数字。

     */

    // 定义全局变量形式
    // 根据题意构建映射 => 初始化同时，构建了映射
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
        // String 判空的形式
        // 先判断是否为 null，再判读是否为空 => 逻辑递进判断

        if (digits == null || digits.isEmpty()) {
            return res;
        }

        // 字符串判空使用 equals 方法
        // if (digits.equals("") || digits.length() == 0) return res;
        dfs(digits, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        // 通过索引位置获取'数字'字符
        char num = digits.charAt(index);
        // 通过'数字'字符 => '字母'字符数组
        // letters 复数，记得加 s
        char[] letters = map.get(num).toCharArray();
        for (char c : letters) {
            // 字符拼接，在拼接之后 sb 本身发生变化了，故回溯需要删除拼接的字符
            sb.append(c);
            // i+1 下一个'数字'字符
            dfs(digits, index + 1, sb, res);
            // 删除最后一个字符 => deleteCharAt，不是 remove
            // sb 长度 length()，不是 size()
            sb.deleteCharAt(sb.length() - 1);
        }

        // KeyPoint 区别：两种 for 循环分支的写法
        // 排列，组合，子集 => 分支为数组中元素，故以数组为中心，进行 for 循环
        // for (int i = start; i < nums.length; i++) {}

        // 每个 num 对应的 letter，以其为中心进行分支选择
        // for (char c : letters) {}

        // 不以 digits 为中心，进行分支选择
        // for (int i = index; i < digits.length(); i++)

    }

    public static void main(String[] args) {
        System.out.println(new _11_17_LetterCombinationsOfAPhoneNumber1()
                .letterCombinations("234"));

        // [adg, adh, adi, aeg, aeh, aei, afg, afh, afi, bdg, bdh, bdi, beg,
        //  beh, bei, bfg, bfh, bfi, cdg, cdh, cdi, ceg, ceh, cei, cfg, cfh, cfi]
    }
}

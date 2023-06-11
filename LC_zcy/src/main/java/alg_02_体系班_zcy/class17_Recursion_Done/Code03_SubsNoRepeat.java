package alg_02_体系班_zcy.class17_Recursion_Done;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 14:49
 * @Version 1.0
 */

public class Code03_SubsNoRepeat {

    // 打印一个字符串的全部子序列,要求不要出现重复字面值的子序列
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        // 将收集集合变成set,通过set去重
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        // 最终要求的是返回值是List集合,所以没法直接使用set返回
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        process2(str, index + 1, set, path);
        String yes = path + str[index];
        process2(str, index + 1, set, yes);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans = subsNoRepeat(test);
        for (String str : ans) {
            System.out.println(str);
        }
    }
}

package alg_02_体系班_zcy.class17_Recursion_Done;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 15:49
 * @Version 1.0
 */

// 打印一个字符串的全部排列,要求不要出现重复的排列
public class Code04_PrintAllPermutationsNoRepeat {
    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process(str, 0, ans);
        return ans;
    }

    public static void process(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            // 中文ASCII码值的范围 0～127,扩展ASCII码128～255
            // 在for循环外面创建visited数组
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    // KeyPoint 给访问过的字符打上标记,注意是字符不是索引
                    visited[str[i]] = true;
                    swap(str, index, i);
                    process(str, index + 1, ans);
                    swap(str, index, i);

                    // a b a a c d a
                    // 0 1 2 3 4 5 6
                    // 相同字符a(0位置和2位置)直接跳过,不用交换
                    // 因为交换之后也是相同的字符,所以直接跳过
                }
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans3 = permutation(s);
        for (String str : ans3) {
            System.out.println(str);
        }
    }
}

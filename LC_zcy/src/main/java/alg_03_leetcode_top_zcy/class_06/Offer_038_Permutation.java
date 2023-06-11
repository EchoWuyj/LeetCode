package alg_03_leetcode_top_zcy.class_06;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 20:43
 * @Version 1.0
 */

// 剑指Offer 38 字符串的排列(无重复)
public class Offer_038_Permutation {
    /*
        输入一个字符串,打印出该字符串中字符的所有排列
        你可以以任意顺序返回这个字符串数组,但里面不能有重复元素
     */

    public static String[] permutation(String s) {
        HashSet<String> ans = new HashSet<>();
        if (s == null || s.length() == 0) {
            return ans.toArray(new String[0]);
        }

        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (char c : str) {
            rest.add(c);
        }
        String path = "";
        process(rest, path, ans);
        return ans.toArray(new String[rest.size()]);
    }

    public static void process(ArrayList<Character> rest, String path, HashSet<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int size = rest.size();
            for (int i = 0; i < size; i++) {
                Character cur = rest.get(i);
                rest.remove(i);
                process(rest, path + cur, ans);
                rest.add(i, cur);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        String[] ans1 = permutation(s);
        for (String str : ans1) {
            System.out.println(str);
        }
    }
}


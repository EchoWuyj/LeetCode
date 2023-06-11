package alg_02_体系班_wyj.class17;

import alg_03_leetcode_top_wyj.class_08.AddDivideNum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 18:54
 * @Version 1.0
 */
public class Code03_SubsNoRepeat {
    public static List<String> subsNoRepeat(String string) {
        char[] str = string.toCharArray();
        HashSet<String> set = new HashSet<>();
        String path = "";
        process(str, 0, set, path);
        ArrayList<String> res = new ArrayList<>();
        for (String s : set) {
            res.add(s);
        }
        return res;
    }

    public static void process(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
        } else {
            process(str, index + 1, set, path);
            process(str, index + 1, set, path + str[index]);
        }
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans = subsNoRepeat(test);
        for (String str : ans) {
            System.out.println(str);
        }
    }
}

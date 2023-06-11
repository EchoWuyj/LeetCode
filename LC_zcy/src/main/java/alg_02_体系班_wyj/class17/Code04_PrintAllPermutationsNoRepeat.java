package alg_02_体系班_wyj.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 23:45
 * @Version 1.0
 */
public class Code04_PrintAllPermutationsNoRepeat {
    public static List<String> permutation(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        char[] str = s.toCharArray();
        process(str, 0, res);
        return res;
    }

    public static void process(char[] str, int index, ArrayList<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    process(str, index + 1, res);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans3 = permutation(s);
        for (String str : ans3) {
            System.out.println(str);
        }
    }
}

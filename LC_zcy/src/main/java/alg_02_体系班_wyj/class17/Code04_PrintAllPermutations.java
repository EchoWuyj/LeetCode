package alg_02_体系班_wyj.class17;

import com.sun.media.sound.SoftTuning;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 19:19
 * @Version 1.0
 */
public class Code04_PrintAllPermutations {
    public static String[] permutation1(String s) {
        ArrayList<String> ans = new ArrayList<>();
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

    public static void process(ArrayList<Character> rest, String path, ArrayList<String> ans) {
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

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }

    public static void process2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                process2(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        String s = "abc";
        String[] ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }

        System.out.println("==========================");

        List<String> list = permutation2(s);
        for (String str : list) {
            System.out.println(str);
        }
    }
}

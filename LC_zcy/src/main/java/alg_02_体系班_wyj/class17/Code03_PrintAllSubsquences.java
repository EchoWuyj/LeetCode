package alg_02_体系班_wyj.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 17:15
 * @Version 1.0
 */
public class Code03_PrintAllSubsquences {
    public static List<String> subs(String string) {
        char[] str = string.toCharArray();
        String path = "";
        ArrayList<String> res = new ArrayList<>();
        process(str, 0, res, path);
        return res;
    }

    private static void process(char[] str, int index, ArrayList<String> res, String path) {
        if (index == str.length) {
            res.add(path);
        } else {
            process(str, index + 1, res, path);
            process(str, index + 1, res, path + str[index]);
        }
    }

    public static void main(String[] args) {
        List<String> res = subs("abc");
        for (String str : res) {
            System.out.println(str);
        }
    }
}

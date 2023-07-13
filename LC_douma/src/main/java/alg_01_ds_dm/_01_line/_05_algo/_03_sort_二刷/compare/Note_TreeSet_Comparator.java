package alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-07-11 12:01
 * @Version 1.0
 */
public class Note_TreeSet_Comparator {

    public void test() {
        TreeSet<String> ts = new TreeSet<>(new CompareByLen());
        ts.add("aaaaaaaa");
        ts.add("z");
        ts.add("wc");
        ts.add("nba");
        ts.add("cba");
        System.out.println(ts);
    }

    // 比较器
    class CompareByLen implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int num = s1.length() - s2.length();        // 按照字符串的长度比较 => 长度为主要条件，内容为次要条件
            return num == 0 ? s1.compareTo(s2) : num;    // 即：先比较长度，再比较内容
        }
    }

    // 具体流程：
    // 一开始为 "aaaaaa" 根元素存入，后面谁调用 compare 方法谁就是 s1，集合中的元素为 s2

    //   "aaaaaaaa"
    //     ↙
    //   "z"
    //     ↘
    //     "wc"
    //       ↘
    //       "nba"
    //      ↙
    //  "cba"
}

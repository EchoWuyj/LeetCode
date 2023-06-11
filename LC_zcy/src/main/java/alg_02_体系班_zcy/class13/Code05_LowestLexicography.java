package alg_02_体系班_zcy.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2022-09-26 15:49
 * @Version 1.0
 */
public class Code05_LowestLexicography {

    // 贪心算法
    // 1)最自然智慧的算法
    //   a.根据某种标准,在当前状况选择最优,然后再去看看集合(要考虑的东西)发生什么变化
    //   b.在剩下的集合中,再根据这个标准进行选择,依次下去
    //   c.每一步贪心都是贪的局部最优解,但局部最优解未必是全局最优解,不是则无效贪心,是则有效贪心
    // 2)用一种局部最功利的标准,总是做出在当前看来是最好的选择
    // 3)难点在于证明局部最功利的标准可以得到全局最优解
    // 4)对于贪心算法的学习主要以增加阅历和经验为主 -> 贪心就是考经验的! -> 多做题,直到遇到原题!

    // 贪心本质:根据某种策略建立一种标准,排序(比较器),堆(谁在堆顶,比较器)
    // 贪心关键:在于标准是否能建立正确

    //---------------------------------------------------------------------------

    // 从头到尾讲一道利用贪心算法求解的题目
    // 给定一个由字符串组成的数组strs,必须把所有的字符串拼接起来,返回所有可能的拼接结果中,字典序最小的结果
    // 如:"ab","cd","ef",拼接成"abcdef"最小,其他方式都比它大

    // 思路
    // 1)到这里可能想到排序再拼接,首先想到:按照最开始的单个字符进行排序,排序后是"b","ba",
    //   拼接后是"bba",但是按照"bab"拼接的话更小,这种贪心策略是失败的
    // 2)对str1,str2排序
    //   排序1:str1<=str2,str1放前,否则str2放前 -> 错误
    //   排序2:str1+str2<=str2+str1,str1放前,否则str2放前 -> 正确

    // 证明过程
    // 1)证明排序策略有传递性,即证明在该种排序策略下结果是唯一的
    //    a.b <= b.a
    //    b.c <= c.b
    // -> a.c <= c.a  该等式证明,则排序策略有传递性

    //    a.b表示a后拼接b,拼接后可以看成a在高位,b在低位,a.b表示a是向左移动了b的长度个位数再加上b
    //    a.b可以看成是k进制的拼接
    //    a*K^(b长度)+b,记K^(b长度)=m(b),故a.b=a*m(b)+b

    //    a.b <= b.a => a*m(b)+b <= b*m(a)+a  (1)
    //    b.c <= c.b => b*m(c)+c <= c*m(b)+b  (2)
    // -> a.c <= c.a => a*m(c)+c <= c*m(a)+a  (3)

    // 将(1)式减b再乘c(c是非负数)
    //    a*m(b)*c<=(b*m(a)+a-b)*c (4)
    // 将(2)式减b再乘a(a是非负数)
    //   (b*m(c)+c-b)*a<=c*m(b)*a  (5)
    // 由(4)+(5)两式得:
    // (b*m(a)+a-b)*c<=(b*m(c)+c-b)*a,化简可得:a*m(c)+c<=c*m(a)+a,得证

    // 2)序列为什么整体拼接后,其字典序是最小的?
    //   证明:在排完序拼接好的字符串,任意两个字符串值交换,字典序只增不减
    //     ...a,m1,m2,b...
    //     ...b,m1,m2,a...
    //   即证下面的字典序比上面的字典序要高

    //  ...a,m1,m2,b...
    //         <=
    //  ...m1,a,m2,b...  (a.m1<=m1.a)
    //         <=
    //  ...m1,m2,a,b... (a.m2=m2.a)
    //
    //  由:数学归纳法,得交换a和b,只会字典序上升,不会下降

    // ------------------------------------------------------------------

    // 总结:
    // 1)提出贪心策略,先使用实验的方式进行验证正确,通过暴力的方法作为对数器
    // 2)数学证明在后面,因为贪心策略从数学上的证明往往比较复杂

    // 方法一(暴力)
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    // strs中所有字符串全排列,返回所有可能的结果
    public static TreeSet<String> process(String[] strs) {
        // 直接使用TreeSet,自动从小到大排序
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            // 递归边界,需要加上""空字符串,方便后续的拼接
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {

            // compareTo方法:指定对象与方法的参数进行比较
            //  1)指定的数与参数相等返回 0
            //  2)指定的数小于参数返回 -1
            //  3)指定的数大于参数返回 1

            // 判断比较a,b拼接字符串的大小返回不同值
            // a,b两个字符串通过返回值-1,0,1,进行排序
            return (a + b).compareTo(b + a);
        }
    }

    // 方法二(贪心)
    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());
        // 推荐使用StringBuilder
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            res.append(strs[i]);
        }
        return res.toString();
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

package alg_02_体系班_zcy.class17_Recursion_Done;

import java.util.ArrayList;
import java.util.List;

// 打印一个字符串的全部排列
public class Code04_PrintAllPermutations {

	/*
	    字符串的全部排列是指对于一个给定的字符串,其中每个排列方式都由原始字符串中的字符按照不同的顺序排列而成,
	    每个字符都在每个排列方式中只出现一次.得到所有可能的不同排列方式的集合
	    例如,对于字符串"abc",它的全部排列包括"abc"、"acb"、"bac"、"bca"、"cab"和"cba".

	    打印一个字符串的全部排列
	    本题涉及深度优先遍历常见的技巧:恢复现场
		字符串"abcd"
		0位置,4个字符选择一个
	    1位置,3个字符选择一个
	    2位置,2个字符选择一个
	    3位置,1个字符选择一个
	 */

    // KeyPoint 方法一(原始)
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    /**
     * @param rest 字符串剩下字符
     * @param path 之前做的决定
     * @param ans  生成所有全排列加入ans中
     */
    public static void f(ArrayList<Character> rest, String path, List<String> ans) {
        // rest为空,没有剩余字符可以自由选择加入path
        // path已经决定完成,递归结束
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                char cur = rest.get(i);
                // 该字符已经使用,需要将rest中去掉该字符
                // 调用 remove(int index) 方法删除指定索引位置的元素
                rest.remove(i);
                f(rest, path + cur, ans);
                // 恢复现场
                //   a   a调用递归函数后,对bc经过全排列,
                // bc    当bc也进行全排列后,返回a层,需要将a重新加入rest集合,否则a字符就丢失了
                //  b
                // ac
                rest.add(i, cur);
            }
        }
    }

    // KeyPoint 方法二(改进)
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    public static void g1(char[] str, int index, List<String> ans) {
        /*
         KeyPoint
         递归函数另外一种形参定义方式
         1)递归最讲究就是可变参数的设计,动态规划的好坏完全取决于递归函数设计
         2)若递归函数参数设计很好,动态规划则能很顺利地搞出来.
         3)递归过程只是在原数组上进行调整,没有使用path,保存每次到达递归边界的结果

        [a,b,c]
         0 1 2

        KeyPoint 流程图
        0⇋0 -> 1⇋1 -> 2⇋2 abc
                1⇋2 acb -> 2⇋2 acb
                abc 2⇋1 <= 还原现场

        再次从abc开始交换
        0⇋1 bac -> 1⇋1 -> 2⇋2  bac
                    1⇋2 bca 2⇋2 bca
        ...

        KeyPoint 代码层面(学习zcy递归书写格式)
        分析递归函数时,一定注意递归函数入口和出口
        index=0,0位置字符,0和0交换,str="abc"; => 1-g1入口
        index=1,1位置字符,1和1交换,str="abc"; => 2-g1入口
        index=2,2位置字符,2和2交换,str="abc"; => 3-g1入口

        此时到递归边界,ans加入"abc";返回到递归函数3-g1层出口,恢复现场swap(str,index,i);
        KeyPoint 循环变量要i++,i=3,for循环条件判断,i<str.length条件不满足,for循环结束,该层结束;
        返回2-g1层出口,执行恢复现场swap,此时i=index=1,i++,i=2,i<str.length条件满足
        1和2交换,str="acb",再进入2-g1层入口进行递归

        本质:类似于二叉树深度优先遍历(递归决策树)
             a
           b   c
         d  e f  g

        关键:d节点结束,返回b后,再去执行e节点,并不是直接返回到a的

         */

        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            //  每步都选择谁做index字符,自己可以,index后面字符可以
            //  最开始i=index,故是字符自身交换;i++之后,index才和字符右边位置字符交换
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                g1(str, index + 1, ans);
                // KeyPoint 一定要恢复现场,保证是在原数组上得到新的情况,
                //      若是不恢复现场,则做的修改会保留下来,此时会有脏数据
                swap(str, index, i);
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }

        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
    }
}

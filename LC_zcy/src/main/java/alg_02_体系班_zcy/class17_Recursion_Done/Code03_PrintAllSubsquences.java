package alg_02_体系班_zcy.class17_Recursion_Done;

import java.util.ArrayList;
import java.util.List;

// 打印一个字符串的全部子序列
public class Code03_PrintAllSubsquences {

    /*
		1,2,3,4 子序列
	    子序列可以不连续,但是必须从前往后取(但是相对次序不能乱)
        1,2
        1,3
        1,2,4
        ...
              ×  1
             ×  2
           ×  3
       使用深度优先遍历
     */

    // s -> "abc" -> 打印全部子序列
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        // 刚开始还没有字符,path为空串
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    /**
     * @param str   给定字符数组,固定参数,一直不变化
     * @param index index是位置,str[index]字符位置
     * @param path  str[0...index-1]已经走过了,之前决定都在path上
     * @param ans   之前决定已经确定,不能改变了,就是path;str[index....]还能决定,后面自由选择
     *              把所有生成的子序列,放入到ans里去
     */
    public static void process1(char[] str, int index, List<String> ans, String path) {
        // index越界,递归停止,将一种确定的path加入ans中
        if (index == str.length) {
            ans.add(path);
            // KeyPoint 单独一个if判断时,需要加上return,保证递归函数能够结束
            return;
        }

        // KeyPoint 通过是否拼接str[index],从而体现不要该字符和要该字符两个分支,从而将所有path都走了
        // path没有变化,则说明没有要index位置的字符,即""的情况不要忘记考虑
        process1(str, index + 1, ans, path);
        // path变化了,则说明要了index位置的字符
        process1(str, index + 1, ans, path + str[index]);

        /*
        if (index == str.length) {
            res.add(path);
        } else {
            process(str, index + 1, res, path);
            process(str, index + 1, res, path + str[index]);
        }
         KeyPoint 注意点
          1) if else "多选一" 一旦执行if条件语句,则else语句不会执行,
             所以对于if来说,相当于结尾是有个return的
          2) 若是单独一个if条件判断,即使满足 index == str.length 条件,执行完ans.add(path)
             程序并不会停止,继续执行后面的process1递归函数,这样就无线递归了,最终栈溢出错误
             所以需要加上return,手动停止程序
         */
    }

    public static void main(String[] args) {
        String test = "123";
        List<String> ans = subs(test);

        for (String str : ans) {
            System.out.println(str);
        }
    }
}

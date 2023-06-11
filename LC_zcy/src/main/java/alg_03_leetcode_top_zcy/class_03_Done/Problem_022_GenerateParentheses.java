package alg_03_leetcode_top_zcy.class_03_Done;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-16 19:13
 * @Version 1.0
 */

// 括号生成
public class Problem_022_GenerateParentheses {
    /*
    数字n代表生成括号的对数,请你设计一个函数,用于能够生成所有可能的并且有效的括号组合
     */
    public static List<String> generateParenthesis(int n) {

        // n对括号,则有2n个括号,n个'(',n个')',中途的决策填入path中
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();

        // 一开始从0位置开始做决定的
        // leftMinusRight=0一开始什么决定都没有做,即0个左括号-0个右括号,所以填的是0
        // leftRest 表示一共可以有n个左括号可以使用
        process(path, 0, 0, n, ans);
        return ans;
    }

    /**
     * @param path           依次在path上填写决定,即选择'(',还是')'
     * @param index          做决定的位置
     *                       ( ( ) ) ( ) ...
     *                       0 1 2 3 4 5
     * @param leftMinusRight 已经做过的决定区域,左括号-右括号的值,其中path[0...index-1] 决定已经做完了
     *                       (((     leftNum = 3
     *                       ((()    leftNum = 2
     *                       ((())   leftNum = 1
     *                       ((()))  leftNum = 0 => 此时不能在做右括号的决定,因为会非法
     * @param leftRest       还剩几个左括号可以填的数量
     * @param ans            保存答案集合
     */
    public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {

        // KeyPoint 剪枝
        //  在递归中将约束补上,在递归的过程中进行剪枝,提高效率
        //  剪枝就是去掉那些不必要的递归,从而提高执行效率
        //  执行用时0ms,在所有 Java 提交中击败了 100.00% 的用户

        // 递归边界
        // index到越界位置,之前path数组中索引位置都已经做好决定了
        if (index == path.length) {
            // KeyPoint 返回值为void,代码中可以没有return
            //      如果函数执行结束后不需要返回任何值，则可以省略return语句
            ans.add(String.valueOf(path));
        } else {
            // KeyPoint
            //  思路:尽可能先做左括号,做到底,实在不行再跳出,再做右括号
            //  同时通过leftMinusRight和leftRest参数限制不符合条件的情况
            //  如n=2,"((()"就不会发生,因为最开始leftRest=2,减小到0,就去执行右括号了

            // 做左括号的决定,需要保证剩余左括号得有
            if (leftRest > 0) {
                // KeyPoint 测试用例 ["\u0000\u0000\u0000\u0000.... 表示结果里面都是空格
                // 到index+1位置做决定了
                // 深度优先
                process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
            }
            // 做右括号的决定,需要保证leftMinusRight>0
            if (leftMinusRight > 0) {
                path[index] = ')';
                process(path, index + 1, leftMinusRight - 1, leftRest, ans);
            }
        }
    }

    // KeyPoint 不做剪枝(暴力递归)
    //      执行用时 2 ms,在所有 Java 提交中击败了24.49%的用户
    public static List<String> generateParenthesis2(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process2(path, 0, ans);
        return ans;
    }

    // 递归的过程没有剪枝,在一个path完成之后,再去进行有效性检验
    // 因此,在递归的过程中一开始可能已经存在不合法情况,却一直在执行,直到最后才去检验
    // 这就说明递归函数设计的不好！
    public static void process2(char[] path, int index, List<String> ans) {
        if (index == path.length) {
            if (isValid(path)) {
                ans.add(String.valueOf(path));
            }
            // KeyPoint 注意else分支的位置,是对应外侧if,而不是对应内侧if
        } else {
            path[index] = '(';
            process2(path, index + 1, ans);
            path[index] = ')';
            process2(path, index + 1, ans);
        }
    }

    public static boolean isValid(char[] path) {
        int count = 0;
        for (char cha : path) {
            if (cha == '(') {
                count++;
            } else {
                count--;
            }
            // 在遍历字符的中途count<0就是不合法的
            // 不能在for之后再去判断,这样就失效了,因为最终'('和')'个数都是相等的
            if (count < 0) {
                return false;
            }
        }
        // 最后count=0也是合法的
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }
}

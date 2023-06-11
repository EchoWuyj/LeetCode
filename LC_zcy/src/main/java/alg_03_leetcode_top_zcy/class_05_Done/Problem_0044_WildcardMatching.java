package alg_03_leetcode_top_zcy.class_05_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-23 14:06
 * @Version 1.0
 */

// 通配符匹配
public class Problem_0044_WildcardMatching {
    /*
        s可能为空,且只包含从a-z的小写字母
        p可能为空,且只包含从a-z的小写字母,以及字符?和*
            '?'可以匹配任何单个字符
            '*'可以匹配任意字符串(包括空字符串)

        s:abcd
        p:*     √

        s:abcd
        p:?*    √

        s:abcd
        p:aa*   ×

     */
    public static boolean isMatch1(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        return process1(s, p, 0, 0);
    }

    // KeyPoint 方法一:暴力递归(提交上去显示超时)
    // s[si....] 能否被 p[pi....] 匹配出来
    public static boolean process1(char[] s, char[] p, int si, int pi) {

        // KeyPoint 递归边界
        if (si == s.length) { // s -> ""
            if (pi == p.length) { // p -> ""
                return true;
            } else {
                // p -> "..."
                // p[pi] == '*' && p[pi+1...] -> ""
                // KeyPoint si越界位置就代表为"",不会再往后移动了
                return p[pi] == '*' && process1(s, p, si, pi + 1);
                // ""
                // si
                // *  ...
                // pi pi+1
            }
        }

        if (pi == p.length) { // p -> "" s只能是""
            return si == s.length;
        }

        // KeyPoint 经过上if的讨论,必然是s从si出发....,p从pi出发...
        // s[si] -> 小写字母
        // p[pi] -> 小写、?、*
        if (p[pi] != '?' && p[pi] != '*') {
            return s[si] == p[pi] && process1(s, p, si + 1, pi + 1);
            //  a   ...
            //  si  si+1
            // [a-z] ...
            //  pi   pi+1
        }

        // si.. pi.. 此时pi 只能是'?'或者'*'
        // si.. pi.. 且pi为'?'
        if (p[pi] == '?') {
            return process1(s, p, si + 1, pi + 1);
            //  a   ...
            //  si  si+1
            //  ?   ...
            //  pi   pi+1
        }

        // si.. pi.. 且pi为'*'
        for (int len = 0; len <= s.length - si; len++) {
            if (process1(s, p, si + len, pi + 1)) {
                return true;
            }

            //  a    b  c  d
            //  si  si+1
            //  *   ...
            //  pi   pi+1
            // pattern中的 * -> "",...-> abcd, => *搞定si的0个长度
            // pattern中的 * -> 'a',... -> bcd, => *搞定si的1个长度(si不断后移的过程)
            // ...
            // 一直尝试从si到结尾位置,每个位置就是一个前缀,每个前缀进行逐个比较

            // 前缀个数计算
            // a  b  c  d  e 越界 str.length=5
            // 0  1  2  3  4  5
            //   si=1
            // 则有5-1=4个前缀串
            // KeyPoint 也可以理解str.length为越界位置,减去当前元素索引位置
            //      即为当前位置到最后的元素个数
        }
        return false;
    }

    // KeyPoint 方法二:优化
    //      通用思路: 尝试 -> 暴力递归 -> 动态规划 -> 斜率优化
    public static boolean isMatch2(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int N = s.length;
        int M = p.length;

        // KeyPoint 动态规划本质:将尝试行为变成确定的表结构依次填好
        // si和pi一旦确定,则返回值一定确定.dp可以装下所有的返回值
        // dp[si][pi] => process1(s,p,si,pi)返回结果
        // KeyPoint [N+1]表示数组大小,不是索引的最大值,索引范围0-N,一共N+1个数字
        boolean[][] dp = new boolean[N + 1][M + 1];

        // KeyPoint 通过 base case 来先填写二维表中的那些值

//        if (si == s.length) {
//            if (pi == p.length) {
//                return true;
//                KeyPoint si=N,pi=M => dp[N][M] = true;
//            } else {
//                return p[pi] == '*' && process1(s, p, si, pi + 1);
//                KeyPoint si=N时,依赖(si,pi+1)状态
//                     最后一行的表,任何一个位置都是依赖其右侧后一个位置
//                     故pi从右往左填写,因为M位置已经填好了,故pi从M-1开始
//            }
//        }

        // 填写最后一行N的其他位置,即除了(N,M)以外的位置
        dp[N][M] = true;
        for (int pi = M - 1; pi >= 0; pi--) { // pi从右往左填写
            // 根据递归代码进行修改
            // p[pi] == '*' && process1(s, p, si, pi + 1);
            dp[N][pi] = p[pi] == '*' && dp[N][pi + 1];
        }

//        if (pi == p.length) {
//            return si == s.length;
//             KeyPoint 只有si=N,pi=M,此时才是true,其他情况都是false
//        }

        // dp[...][M]中,只有dp[N][M]=true,d[其他位置][M]=false,
        // 因为二维表格在初始化时,就已经是设置好为false了,故这句递归代码不需要改成dp

//        // s[si] -> 小写字母
//        // p[pi] -> 小写、?、*
//        if (p[pi] != '?' && p[pi] != '*') {
//            return s[si] == p[pi] && process1(s, p, si + 1, pi + 1);
//            KeyPoint 将return改成dp[si][pi],并且将process1递归函数改成dp[si + 1][pi + 1];
//                 原来递归版本代码中存在return语句,因为return是需要结束,这里替换成continue
//        }
//
//        // si.. pi.. 此时pi 只能是'?'或者'*'
//        // si.. pi.. 且pi为'?'
//        if (p[pi] == '?') {
//            return process1(s, p, si + 1, pi + 1);
//        }
//
//        // si.. pi.. 且pi为'*'
//        for (int len = 0; len <= s.length - si; len++) {
//            if (process1(s, p, si + len, pi + 1)) {
//                return true;
//            }
//        }
//        return false;
//        KeyPoint 这里最后的return false可以不用在dp中设置false,因为dp[si][pi]默认就是false

        // KeyPoint 递归 -> DP
        //  1)不是base case的情况,需要修改一般位置(si,pi),需要分析递归函数中的依赖关系
        //    其中dp中N行和M列已经确定
        //  2)process1(s, p, si + 1, pi + 1) => (si,pi)依赖(si+1,pi+1)
        //    process1(s, p, si + len, pi + 1) => (si,pi)依赖(si+len,pi+1)
        //    => 以上两点可以推出:(si,pi)依赖齐高度后一列位置
        //  3)填表方式:已有位置信息=>新位置信息
        //    因此填表方式也是从已有位置信息出发,通过行或列向新位置信息移动
        //    本题是从(N-1,M-1)位置右往左填,填满一行,再从下往上填,最终到(0,0)位置

        for (int si = N - 1; si >= 0; si--) {
            for (int pi = M - 1; pi >= 0; pi--) {
                // 这种依赖顺序,一定能保证任意(si,pi)所依赖的格信息都已经求解过了
                // if判断的逻辑基本不变化,主要是将递归部分改成DP
                if (p[pi] != '?' && p[pi] != '*') {
                    dp[si][pi] = s[si] == p[pi] && dp[si + 1][pi + 1];
                    continue;
                }
                if (p[pi] == '?') {
                    dp[si][pi] = dp[si + 1][pi + 1];
                    continue;
                }

                // KeyPoint 方式一:最原始修改dp的代码
                //      没有进行斜率优化存在很多枚举的情况
                for (int len = 0; len <= s.length - si; len++) {
                    if (dp[si + len][pi + 1]) {
                        dp[si][pi] = true;
                        // KeyPoint 注意break位置,在if里面进行break,if外面break没有什么意义
                        break;
                    }
                }

                // KeyPoint 方式二:斜率优化后
                //      斜率优化:观察临近格子能不能将枚举替代掉,关键在于观察能力

                // 只有p[pi] == '*',才会存在枚举
                // 计算(4,7) -> (4,8)
                //              (5,8)
                //              (6,8)
                //              (7,8)
                // 斜率优化:观察临近格子(5,7)能不能将枚举替代掉
                // 计算(5,7) -> (5,8)
                //              (6,8)
                //              (7,8)
                // (5,7)一个状态可以替代(4,8)下面的全部
                // 注意:(4,7)位置的p[pi] == '*',同样(5,7)位置的p[pi] == '*'

                // KeyPoint 右边格子||下面的格子,只要其中有个为true,则此时dp[si][pi]=true
                //      ||(短路或),若前表达式为真,不执行后表达式,直接得TRUE
                // dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
            }
        }

        // 递归函数最开始尝试的位置是(0,0)位置
        return dp[0][0];
    }

    // 最终做的化简
    public static boolean isMatch3(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int N = s.length;
        int M = p.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int pi = M - 1; pi >= 0; pi--) {
            dp[N][pi] = p[pi] == '*' && dp[N][pi + 1];
        }
        for (int si = N - 1; si >= 0; si--) {
            for (int pi = M - 1; pi >= 0; pi--) {
                if (p[pi] != '*') {
                    dp[si][pi] = (p[pi] == '?' || s[si] == p[pi]) && dp[si + 1][pi + 1];
                } else {
                    dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
                }
            }
        }
        return dp[0][0];
    }
}

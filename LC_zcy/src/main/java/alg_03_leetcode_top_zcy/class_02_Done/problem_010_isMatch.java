package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-15 14:30
 * @Version 1.0
 */

// 正则表达式匹配
public class problem_010_isMatch {
    // KeyPoint 方法一 递归求解
    public boolean isMatch(String s, String p) {
        /*
            s只包含从a-的小写字母
            p只包含从a-z的小写字母,以及字符'.'和 '*'
               1) '.' 匹配任意单个字符
               2)'*' 匹配零个或多个前面的那一个元素
                 注意:'*'不能单独使用,得保证每次出现字符*时,前面都匹配到有效的字符
            所谓匹配是要涵盖整个字符串s的,而不是部分字符串

            s:abcd
            p:abcd* 匹配,d*->d

            s:abcd
            p:.* <=> ....,其中'.'可以为任意字符,故可以匹配

            s:abcd
            p:ab.cd  因为.不能作为空字符,故不能匹配

            s:abcd
            p:ab.*cd  让.*为0,则是可以匹配

            经典的猜尝试的方法
            一个样本作为行一个样本作为列的对应的模型(需要看之前的课)
         */

        // 判空条件先这样,若是测试用例中真的有""再进一步处理""
        if (s == null || p == null) {
            return false;
        }

        // 涉及转toCharArray,不能有null出现
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        // 主函数调用递归函数
        return isValid(str, pattern) && process(str, pattern, 0, 0);
    }

    // 有效性检查
    public boolean isValid(char[] str, char[] pattern) {

        // str,pattern 若是空字符串"",则for循环直接跳过
        // String test = "";
        // System.out.println(test.length()); 0

        // str中不能有'.'或者'*'
        for (char cha : str) {
            if (cha == '.' || cha == '*') {
                // 有效性不通过
                return false;
            }
        }

        // pattern中*不能单独使用,每次出现字符*时,得跟在前面不为*的字符联合使用
        // pattern中**不能在一起,.*也是不对,同时*也不能在开头
        for (int i = 0; i < pattern.length; i++) {
            // pattern[i] == '*' => 只有在pattern在*位置才检查其是否无效
            // pattern[i-1]并不会越界,只有在i==0不成立时,才会到 pattern[i-1]
            if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归函数含义:str[si...]能否被pattern[pi...]变出来
     * 规定:pattern[pi]!= '*'
     * 1) 主函数调用process时,经过有效性检验是pi位置没有'*'的
     * 2) 在递归调用中,若一开始pi位置若是'*'则没法匹配,因为不知道其含义是什么,'*'的确定需要看前面的字符
     * 这种递归不是最优解,两个可变参数的递归函数可以改成动态规划
     *
     * @param str
     * @param pattern
     * @param si
     * @param pi
     * @return
     */
    public boolean process(char[] str, char[] pattern, int si, int pi) {

        // KeyPoint 注意点
        //  1) 注意多个if判断并列关系时,if判断逻辑是传递的,在上面if逻辑不成立,再去执行后面的if逻辑
        //  2) 若是逻辑条件写漏了或者多写了,通过报错代码进行修改,不太容易一遍就能写通过的

        // KeyPoint 递归边界,分类讨论

        // KeyPoint 1) si越界
        // si在str越界位置,str为""
        if (si == str.length) {
            // pi在pattern越界位置,pattern为"",两者都是""
            if (pi == pattern.length) {
                return true;
            }
            // str为"",pattern为"a*b*c*d*"也能匹配
            // => pi不是终止位置(没有越界),pi+1为"*"
            // => 即a*可以变"",且剩余部分b*c*d*也能变""
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                // 保证剩余的pi+2部分也能变""
                // 因为pi+1位置为'*',则pi+2位置不为'*',两个*不相邻
                return process(str, pattern, si, pi + 2);
            }
            // 否则匹配不了
            return false;
        }

        // KeyPoint 2) si没越界,pi越界
        // pi来到越界位置,pattern为"",需要匹配,则str必须得是""
        if (pi == pattern.length) {
            return si == str.length;
        }

        // KeyPoint 一般情况,分类讨论

        // KeyPoint si没越界,pi没越界
        //      本题是用pattern匹配str,所以是以pattern为主体来进行讨论的
        //      1) pi+1位置不为"*"(将所有情况都包括了)
        // pi+1位置越界(没有字符)或者[pi+1]!='*'都属于pi+1位置不为"*"
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            // pi得和si字符匹对上或者pattern[pi]为'.'
            // KeyPoint 复杂逻辑判断需要加上(),规定执行的顺序,不然可能因为优先级顺序,从而导致代码出错
            return ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
                    // 保证字符串剩余部分也得匹配上
                    && process(str, pattern, si + 1, pi + 1);
            // a
            // si si+1 ...
            // a
            // pi pi+1 ...
            // si和pi匹配,同时...和...匹配,则整体是匹配的
        }

        // KeyPoint si没越界,pi没越界
        //      经过上面if的pattern[pi+1]!='*'逻辑,到现在的逻辑是pattern[pi+1]=='*',
        //      即默认[pi+1]='*',所以直接就没有判断了
        //      2) pi+1位置为"*",[pi]不可配[si]
        // [pi]不是'.',说明是一般字符,且[si]!=[pi]
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            // 只能让str的si位置和pattern的pi+2位置匹配
            return process(str, pattern, si, pi + 2);
            // a
            // si ...
            // b   *
            // pi pi+1 ...
            // => 此时b*只能变0个b,且pi+2得匹配出si整体
        }

        // KeyPoint si没越界,pi没越界
        //      3) pi+1位置为"*",[pi]可配[si],0个a
        if (process(str, pattern, si, pi + 2)) {
            return true;
            // a   a    a    b    c   d
            // si
            // a   *
            // pi pi+1 pi+2
            // => a*变0个a,pi+2位置和si匹配,=>该if判断代码

        }

        // KeyPoint  si没越界,pi没越界
        //       4) pi+1位置为"*",[pi]可配[si],多个a=>使用while进行循环判断,避免多个if逐个判断
        // 在while循环的迭代的过程中,还是需要考虑si不能越界的
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process(str, pattern, si + 1, pi + 2)) {
                return true;
            }
            // [si]和[pi]匹配不上,则跳出while循环
            si++;

            // a   a    a    b    c   d
            // si si+1
            // a   *
            // pi pi+1 pi+2
            // => a*变1个a,[si+1]和[pi+2]匹配

            // a   a    a    b    c   d
            //    si   si+1
            // a   *
            // pi pi+1 pi+2
            // => a*变2个a,[si+1]和[pi+2]匹配

            // ...
        }
        return false;
    }

    // KeyPoint 方法二:计划搜索(DP)
    // process2没有暴力执行,若之前计算过则直接返回的.只有在第一次计算时,才会将逻辑展开,并且在返回之前将缓存存好
    // 计划搜索和一般DP的区别,没有整理缓存表从简单推到复杂的过程,即没有整理二维表位置依赖的关系.若是整理了依赖关系,则是经典的DP
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        /*
             pi
            ----
        si |

        si可能性:str的0~N(N是越界位置); pi可能性:pattern的0~M(M是越界位置)
        => 所有可能性都在这个二维表中(二维数组作为缓存)
         */

        // 包括越界位置(0~N),故数组长度+1
        int[][] dp = new int[str.length + 1][pattern.length + 1];

        for (int si = 0; si <= str.length; si++) {
            for (int pi = 0; pi <= pattern.length; pi++) {
                dp[si][pi] = -1;
            }
        }
        // dp[si][pi] == -1 si pi 之前没有计算过
        // dp[si][pi] == 0 si pi false
        // dp[si][pi] == 1 si pi true
        return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
    }

    // str[si.....] 能否被 pattern[pi...] 变出来
    // 潜台词：pi位置，pattern[pi] != '*'
    public boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {

        // KeyPoint 使用缓存
        // [si][pi]之前已经计算过了
        if (dp[si][pi] != -1) {
            return dp[si][pi] == 1;
        }

        // KeyPoint 记录缓存
        // si pi 这个参数组合第一次算
        if (si == str.length) { // si越界了
            if (pi == pattern.length) {
                dp[si][pi] = 1;
                return true;
            }
            // (pi pi+1) pi+2 ....
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                boolean ans = process2(str, pattern, si, pi + 2, dp);
                // process2递归调用的返回值不知道,所以需要根据true/false来填写dp
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }
            dp[si][pi] = 0;
            return false;
        }
        // si 没越界
        if (pi == pattern.length) {
            boolean ans = si == str.length;
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        // si 没越界 pi 没越界
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            boolean ans = ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
                    && process2(str, pattern, si + 1, pi + 1, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        // si 没越界 pi 没越界 pi+1 *
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            boolean ans = process2(str, pattern, si, pi + 2, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        if (process2(str, pattern, si, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process2(str, pattern, si + 1, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            si++;
        }
        dp[si][pi] = 0;
        return false;
    }
}

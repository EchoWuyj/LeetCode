package alg_03_leetcode_top_zcy.class_05_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-18 16:01
 * @Version 1.0
 */

// 外观数列
public class Problem_0038_CountAndSay {

    /*

    「外观数列」是一个整数序列,从数字1开始,序列中的每一项都是对前一项的描述
        1.  1         1个1               记作 "11"
        2.  11        2个1               记作 "21"
        3.  21        1个2,1个1          记作 "1211"
        4.  1211      1个1,1个2,2个1     记作 "111221"
        5.  ...
     给定一个正整数n,输出外观数列的第n项

     3322251
        =>2个3,3个2,1个5,1个1
        =>23321511
     因此想要n项,则需要第(n-1)项,对(n-1)项进行判断,使用递归求解

     */
    public String countAndSay(int n) {
        if (n < 1) {
            // KeyPoint 返回数据类型为String,对应的空值应该是"",而不是null
            return "";
        }

        // 当n=1,执行到if判断条件就已经结束了,不会涉及递归函数调用
        // 不能直接越过该行代码,跳转到char[] last = countAndSay(n - 1).toCharArray();
        if (n == 1) {
            return "1";
        }

        // 得到上一个(n-1)序列
        // n=3时,3-1=2,两次递归函数连着,每次遇到递归函数便重新进入函数执行代码
        // 直到递归边界后,返回递归出口,再去执行递归过程中代码

        // KeyPoint 递归的递过程(深入)
        char[] last = countAndSay(n - 1).toCharArray();

        // KeyPoint 递归的归过程(折返)
        // 由 "11"=> "21";
        //    "21"=> "1211" 代码流程
        StringBuilder ans = new StringBuilder();
        // 数字至少出现一个
        int times = 1;
        // 对字符序列进行遍历
        // KeyPoint 涉及数组前后两个位置索引,for循环中起始位置,和判断条件是有讲究的,避免数组越界
        //      方式一
        // 从0位置开始遍历,判断0和1位置是否和其相等
        for (int i = 1; i < last.length; i++) {
            // 当前i-1位和i位比较是否相等,若相等则表示i位置和i-1位置元素相同
            // 之后append操作确实针对i-1位,for循环最后只能到i-2,遗留了i-1位置的元素,所以在for循环外补上
            if (last[i - 1] == last[i]) {
                times++;
            } else {
                // append追加可以直接添加数字,不需要String.valueOf(times)
                ans.append(times);
                ans.append(last[i - 1]);
                // 下个字符是,times重置
                times = 1;
            }
        }

        /*
         KeyPoint 涉及数组前后两个位置索引,for循环中起始位置,和判断条件是有讲究的,避免数组越界
              方式二
         for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                times++;
            } else {
                res.append(times);
                res.append(chars[i]);
                times = 1;
            }
         */

        // 填上i-1位,需要单独添加
        // 注意:i-1位和前面i-2位相同,times次数也是保存下来的
        ans.append(times);
        ans.append(last[last.length - 1]);
        return ans.toString();
    }
}

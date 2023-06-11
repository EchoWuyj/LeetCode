package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-14 19:16
 * @Version 1.0
 */

// 字符串转换整数
public class problem_008_myAtoi {
    public int myAtoi(String s) {

        /*
        请你来实现一个myAtoi(string s)函数,使其能将字符串转换成一个32位有符号整数
        函数myAtoi(string s)的算法如下：
          1)读入字符串并丢弃无用的前导空格
          2)检查下一个字符(假设还未到字符末尾)为正还是负号,读取该字符(如果有).确定最终结果是负数还是正数.如果两者都不存在,则假定结果为正.
          3)读入下一个字符,直到到达下一个非数字字符或到达输入的结尾.字符串的其余部分将被忽略.
          4)将前面步骤读入的这些数字转换为整数(即"123"->123,"0032"->32).如果没有读入数字,则整数为0.必要时更改符号(从步骤2开始).
          5)如果整数数超过32位有符号整数范围[−2^(31), 2^(31)−1],需要截断这个整数,使其保持在这个范围内.具体来说,小于−2^31的整数应该
            被固定为−2^(31) ,大于2^(31)−1 的整数应该被固定为2^(31)−1
          6)返回整数作为最终结果.
        注意：
          本题中的空白字符只包括空格字符' ' .
          除前导空格或数字后的其余字符串外,请勿忽略 任何其他字符.
          s由英文字母(大写和小写)、数字(0-9)、' '、'+'、'-' 和 '.' 组成
         */

        if (s == null || s.length() == 0) {
            // 测试用例要求
            return 0;
        }

        // 处理之后赋值到原来的s上
        s = removeHeadZero(s.trim());

        //removeHeadZero之后原来字符串可能为空串"",但是应该不会为null
        if (s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        // 再次进行有效性验证
        if (!isValid(chars)) {
            // 无效情况,直接返回0
            return 0;
        }

        // 经过前2个过滤条件后,chars中是符合日常书写的规范整数形式

        // 这里还是使用负数来接,因为负数的绝对值比正数的绝对值大一个
        // int 最小值 -2147483648; int 最大值 2147483647
        // "-2147483648",该字符串绝对值部分为2147483648,无法使用整数2147483647来接,数据已经溢出了,所以使用负数来接

        // int 最小值 1000 0000 0000 0000 0000 0000 0000 0000
        // 除符号位外值,剩余部分取反再+1,结果 1000 0000 0000 0000 0000 0000 0000 0000 =2147483648 = -2^31 =-2147483648

        // int 最大值 0111 1111 1111 1111 1111 1111 1111 1111
        // 除符号位外值为 0111 1111 1111 1111 1111 1111 1111 1111 = 2^31 = 2147483647

        // int 最小值的负数还是自己,因为一个数(不管正负)的负数就是取反再加1
        // 1000 0000 0000 0000 0000 0000 0000 0000 取反加1
        // 0111 1111 1111 1111 1111 1111 1111 1111 取反
        // 0111 1111 1111 1111 1111 1111 1111 1111 +1
        // 1000 0000 0000 0000 0000 0000 0000 0000 还是自己
        // => -Integer.MIN_VALUE = Integer.MIN_VALUE

        // 正数标记
        boolean posi = chars[0] != '-';

        // 避免数据溢出
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;

        /*
        System.out.println(Integer.MIN_VALUE); // -2147483648
        System.out.println(Integer.MIN_VALUE / 10); // -214748364
        System.out.println(Integer.MIN_VALUE % 10); // -8
         */

        int res = 0;
        int cur = 0;

        // 首个字符若是从-/+,则for循环从1开始
        // KeyPoint 需要判断首个字符是否为-/=,不能笼统的直接从0开始
        for (int i = (chars[0] == '-' || chars[0] == '+') ? 1 : 0; i < chars.length; i++) {

            // 不管正负数都用负数的形式将原来数字表示,32 -> -32
            // KeyPoint cur代码是放在判断数据溢出的上面的,上下顺序不能搞错了
            cur = '0' - chars[i];

            // 数据溢出的情况
            // res<minq说明,若res<minq,此时res * 10则必然 < Integer.MIN_VALUE
            if ((res < minq) || (res == minq && cur < minr)) {
                // 真的数据溢出
                // 正数返回 Integer.MAX_VALUE,负数返回 Integer.MIN_VALUE
                return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // 先处理的3变成-3,在第二轮中上轮的res=-3,此时res * 10=-30
            // res= -3*10+(-2)=-32
            res = res * 10 + cur;
        }

        // res使用负数的形式表示,存在特殊情况:
        // "-2147483648" √ 负数
        // "2147483647" √ 正数
        // 而"2147483648"其实该字符串是溢出的,应该返回int最大值2147483647
        // 但代码是不报错的,因为使用负数来接,数据没有溢出
        if (posi && res == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        // 因为res是负数形式,所以需要在符号变换
        return posi ? -res : res;
    }

    public String removeHeadZero(String str) {
        boolean r = (str.startsWith("+") || str.startsWith("-"));

        // 因为后面s需要使用,故需要单独提取出来,不能直接放在for循环中
        int s = r ? 1 : 0;

        // 可能存在 +000063 -> +63
        // 有+/-号则保留,跳过从下一位开始判断
        for (; s < str.length(); s++) {
            if (str.charAt(s) != '0') {
                break;
            }
        }

        // s 到了第一个不是'0'字符的位置,则前半部分处理完成

        // e 是从右往左找
        // (r?1:0)表示str最开始位置(不包括负号),因为不确定0/1,所以使用这种方式表示
        int e = -1;
        for (int i = str.length() - 1; i >= (r ? 1 : 0); i--) {
            // e 标记最左的不是数字字符的位置(循环遍历到底判断到最左位置)
            // +0063ab -> +63
            // +0063ab12 -> +63
            // - 0011a -> 返回负号,注意一定是最左不是数字字符的位置
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                e = i;
            }
        }

        // 此时e到了最左的不是数字字符的位置
        // 确定+/-拼接上str截取后的字符串
        // substring(start,end) [) 左闭右开
        // String s = 'a' + "a"; 字符可以字符串直接拼接
        return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? str.length() : e);
    }

    public boolean isValid(char[] chars) {

        // KeyPoint 这部分代码可以注掉,removeHeadZero方法中从右往左判断不是数字逻辑已经包括这种情况了
        // 开头不是-,也不是+,也不是数字字符 => 无效
        // 有可能 b+a0012,则开头字符为b
//        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9')) {
//            return false;
//        }

        // 单独-或+ => 无效
        if ((chars[0] == '-' || chars[0] == '+') && (chars.length == 1)) {
            return false;
        }

        // KeyPoint 这部分代码可以注掉,removeHeadZero方法中从右往左判断不是数字逻辑已经包括这种情况了
        // 0位置字符只可能:+,-,数字字符
        // 从1位置开始判断
//        for (int i = 1; i < chars.length; i++) {
//            if (chars[i] < '0' || chars[i] > '9') {
//                return false;
//            }
//        }

        return true;
    }
}

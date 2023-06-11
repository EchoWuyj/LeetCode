package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-13 20:41
 * @Version 1.0
 */

// manacher算法模型
public class problem_005_manacher {
    public static int manacher(String s) {

        // 回文:abcba,abccba
        // 镜像对称:即过了对称轴后逆序.即一开始从左往右正序,过了对称轴从右往左逆序
        //     ab|ba  abc|cba

        // KeyPoint 方法一:暴力解法
        // 思路:以每个字符为中心向左右两边扩,保证左右字符相同,直到扩不动为止,记录下每个字符对应能扩的范围大小
        // 比如:"AB1234321CD"这个字符串,以"4"字符为中心向左右两边能扩的位置最大,1234321 为最长回文子串

        // 有问题:长度为偶数的回文没办法得到(轴是虚的),只有奇数的回文能得到
        // 解决方法:在每两个字符之间加一个字符#,不一定要求原字符串中没出现过的字符
        //          因为实的跟实的比,虚的跟虚的比,不会出现实的和虚的比

        // 注意:最终答案需要/2,即将多添加的特殊字符去掉
        // 比如:    "1"       =>     "#1#"
        //      "1"位置回文长度1    "1"位置回文长度3,在3/2=1,因为/的过程存在向下取整,
        //      所以"#1#"可以看成"#1",最终答案需要/2

        // 时间复杂度:得以最差情况为结果,全部字符都相等
        // "aaa" => "# a # a # a # "
        //           0 1 2 3 2 1 0     往外扩的次数
        // 从左往右是等差数列,从右往左也是等差数列,故最终是O(N^2)的解

        // KeyPoint 方法二:Manacher算法 => 回文问题
        // Manacher算法和暴力解的大过程是一样的,针对处理后的串从0,1,2..位置往左右两边扩,但是在扩的过程中是存在加速的
        // 因此可用O(N)时间复杂度解决这个问题,同样的Manacher算法也需要对原始字符串进行上述的预处理过程

        // Manacher基本概念
        // 1) abcba 回文半径:3
        //          回文直径:5
        // 2) 回文半径数组 pArr[]:一个整型数组,长度和预处理串一样,存每个位置的最长回文半径是多少
        //                        使用pArr为了加速,即求i位置的最长回文子串,利用0-i-1位置的回文半径信息加速求解
        // 3) 回文最右边界 R:该数初始值为-1,可以理解成0位置还没有开始往外扩
        //                  不管是哪个位置扩的,只要扩的范围的右边界比当前R大,就更新该值
        //                  R代表最右的扩成功的位置
        //    比如:#0#0#1
        //    刚开始R=-1,0位置#,回文区域是0~0(索引范围),此时0>R=-1,则更新R=0
        // 4) 中心 C:初始值为-1,跟R同步更新,取得最右回文右边界时中心位置,即R和C是搭配使用的

        // KeyPoint 在i位置为中心往左右两侧扩,扩的时候考虑i,R,C三个变量之间的位置关系,有大的2种情况

        // 1) 情况一:i在R外(当前点的位置i不在最右回文右边界R的范围里)
        // 此时按照经典做法进行暴力扩,即看左右两侧是否一样,一样就继续扩,不一样就停止,没有任何优化
        // 比如: # 1 # 2 # 2 # 1 #
        //      0 1 2 3 4 5 6 7 8
        // R=-1,i=0,i还没有往左右两侧扩,则i在R外

        // 2) 情况二:i在R内(当前点的位置i在最右回文右边界R的范围里,存在优化)
        // 此时C一定在i的左侧,因为之前的C扩了比较远的距离R,导致刚到i位置,i就已经在R内了
        // 根据C与i可以做出i'(i的对称点),根据R与C可以做出L(回文半径),即拓扑关系 L[ i' C  i ]R
        // 注意:包括i与R重合的情况,拓扑关系 L(i') C (i)R

        // KeyPoint 根据i'位置扩出来的回文区域,对第二大类情况进行分类
        //      i'位置回文半径已经保存在回文半径数组pArr[]中,因此i'扩的范围可以获取到

        //  a) i'位置的回文区域整个都在L到R区域的内部,此时的i位置的回文区域大小跟i'一样,
        //     又因为i'的回文半径值存在pArr中,从而求得i'回文区域.因此i位置不用扩,直接求解,i和i'一样
        //     L[a,b,c,d,c,s,t,c,t,s,c,d,c,b,a]R
        //             i'      C       i
        //           [c,d,c]        [c,d,c]

        //  b) i'的回文区域一部分在L到R外面,此时i不用扩,直接求解,i的回文半径为i到R
        //     a,L[b,c,d,c,b,a,t,k,t,a,b,c,d,c,b]R,y
        //             i'        C         i
        //      [a,b,c,d,c,b,a]

        //  c) i'的回文区域的左边界与L重合,i的回文半径至少等于i'的回文半径,可能会变得更大,要看左右部分(小加速)
        //     x,L[a,b,c,t,c,b,a,t,a,b,c,t,c,b,a]R,?(?是不确定的字符)
        //               i'      C       i
        //        [a,b,c,t,c,b,a]  [a,b,c,t,c,b,a]其中该区域不需验证的,而是i再往左,再往右是否还能扩更远
        //                         若?=t,则扩的更远 [t,a,b,c,t,c,b,a,t]
        //                         若?=x,则没有扩   [a,b,c,t,c,b,a],和i'回文半径相等

        /*
        KeyPoint 伪代码
        for (int i = 0; i < str.length; i++) {
            if (i在R外部) {
                从i开始往两边暴力扩;R变大
            } else { i在R内部
                if (i'回文区域彻底在L..R内){
                    pArr[i] = pArr[i']  某个表达式,O(1)
                }else if (i'回文区域有一部分在L..R外){
                    pArr[i] = i到R距离  某个表达式,O(1)
                }else{
                    i'回文区域和L..R的左边界压线
                    从R之外的字符开始,往外扩,然后确定pArr[i]的答案;
                    若第一步扩失败了,R不变,否则R变大
                }
            }

        时间复杂度分析
        对于i位置为中心往外扩,必会失败一次,总的失败次数N次
        根据i和R两个变量来估计分析,i和R的最大值都是N,四个分支都在for循环里,每次只会执行一个,每个分支结束i都会++
            1)分支 扩成功 R↑
            2)分支 O(1)
            3)分支 O(1)
            4)分支 扩成功(i~R内部是不验证的,从R的下个位置开始验) R↑
        同时即i和R都是只增不退的,其中1)和4)分支扩的过程(成功次数)不会总体大于O(N),因此整个过程时间复杂度是O(N)
         */

        // 给你一个字符串s,找到s中最长的回文子串,注意求的是什么?
        // 本题求的是最长的回文子串的长度,LeetCode_005求的是最长的回文子串
        if (s == null || s.length() == 0) {
            return 0;
        }

        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // KeyPoint 回文半径的大小
        //      保证和处理后的字符串保持长度一致
        // 数组刚创建没有赋值,则数组中所有的元素值都是0
        int[] pArr = new int[str.length];
        int C = -1;

        // 讲述中:R代表最右的扩成功的位置
        // coding:最右的扩成功位置的再下一个位置,即扩失败的位置(第一个违规的位置)
        int R = -1;
        int max = Integer.MIN_VALUE;
        // 每个i位置为中心去扩
        for (int i = 0; i < str.length; i++) {
            // 总逻辑:将 i在R外,i在R内中的a,b,c一共4种情况中
            //       至少不需要验证的位置单独抽取,使用一行代码进行判断
            // KeyPoint 比较符号最好按照常规逻辑顺序来书写,比如i<R,这样比较好理解
            // 1) i<R 即i在R内,因为R第一个违规的位置
            //    2*C-i为i的对称点i'位置,由(i'+i)/2 = C 推得
            //    i'的回文半径和R-i谁小就取谁
            // 2) i>=R 因为R第一个违规的位置,即i在R外,i至少的回文半径也是1
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;

            // i在R内中a,b情况其实是不用验证的,为了精简代码,将其写到while,遇到这2种情况会直接break
            // i+pArr[i]:i位置加上不用验的区域,即再往外的右,不越界;i-pArr[i]同理
            // 注意:pArr回文半径是包括i位置的
            // 若i=0,pArr[i]=3,表示的位置是3位置,即需要判断b是否回文
            //  a a a b
            //  0 1 2 3
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // 考虑i在R外,i在R内的c的右侧的位置,往外扩是需要验证
                // 能左右扩,则回文半径+1
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    // 回文半径+1
                    pArr[i]++;
                } else {
                    // 停止扩
                    break;
                    /*
                    如果在for循环内部嵌套了一个while循环,并且在while循环中使用了break关键字
                    那么break只会跳出while循环，不会跳出for循环
                     */
                }
            }

            // R,C更新
            // 注意:在while循环外,但还是在for循环里面的
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                // 更新R值时的中心位置C
                C = i;
            }
            // 这里是求的是包含"#"的回文半径
            // "121" -> "#1#2#1#" 回文半径为4,对应原始串长度为4-1=3
            // "1221" -> "#1#2#2#1#" 回文半径为5,对应原始串长度为5-1=4
            // max = Math.max(max, pArr[i]);
            if (pArr[i] > max) {
                max = pArr[i];
            }
        }

        // 若求原始串的长度需要 max-1
        // pArr中记录的是处理后字符串的回文半径值
        // #1#2#1#,pArr的最大值是4,4-1对应原来的3,即原始字符串的长度
        // 2#1#减去一个#,剩余的#变成1,即原始121的长度(偶回文同理)
        return max - 1;
    }

    // 处理操作
    public static char[] manacherString(String string) {
        char[] oldChars = string.toCharArray();
        // KeyPoint char[] newChars = new char[(oldChars.length << 1) + 1];
        //      想要使用位运算最好加上(),以免运算的优先级搞错了
        char[] newChars = new char[string.length() * 2 + 1];
        // 单独使用index控制oldChars当前字符的位置
        int index = 0;
        for (int i = 0; i < newChars.length; i++) {
            // 位运算判偶数,直接关键和0比较,在偶数位置赋值为'#'
            // 注意是在newChars数组中进行赋值操作,同时oldChars中的index要++,表示索引后移
            newChars[i] = (i & 1) == 0 ? '#' : oldChars[index++];
        }
        return newChars;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {

        // 测试代码
//        int possibilities = 5;
//        int strSize = 20;
//        int testTimes = 5000000;
//        System.out.println("test begin");
//        for (int i = 0; i < testTimes; i++) {
//            String str = getRandomString(possibilities, strSize);
//            if (manacher(str) != right(str)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("test finish");

        // debug
        int res = manacher("aaab");
    }
}

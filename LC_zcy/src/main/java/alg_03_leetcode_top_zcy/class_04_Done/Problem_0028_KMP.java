package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 10:53
 * @Version 1.0
 */

// KMP 需要手撕(非常高频)
public class Problem_0028_KMP {
    public static int getIndexOf(String s1, String s2) {
        /*
        KMP算法解决字符串查找(匹配)算法
        字符串str1和str2,str1是否包含str2,如果包含返回str2在str1中开始的位置,并且要求做到时间复杂度为O(N)

            str1 "abc123def"     单个匹配子串
            str2 "123"           kmp返回结果索引3

            str1 "abc123def123"  多个匹配子串
            str2 "123"           kmp返回结果索引3(返回最开始的索引的位置)

        暴力解思路:循环str(N)和match(M),挨个对比,最差情况为O(NM) 时间复杂度为O(NM)

        KMP算法相关概念:
        对str记录,字符坐标前的前缀后缀最大匹配长度
        例如str=“abcabc|k”
          KeyPoint 注意:前缀和后缀都是从左往右的顺序,后缀顺序并不是从右往左
                    只是从后往前数几个字符长度是和前缀相同的,但是读取顺序还是正序的
          1) 对于k位置前的字符,前后缀长度取1时,前缀为"a",后缀为"c"不相等  ×
          2) 对于k位置前的字符,前后缀长度取2时,前缀为"ab",后缀为"bc"不相等 ×
          3) 对于k位置前的字符,前后缀长度取3时,前缀为"abc",后缀为"abc"相等 √
          4) 对于k位置前的字符,前后缀长度取4时,前缀为"abca",后缀为"cabc"不相等 ×
          5) 对于k位置前的字符,前后缀长度取5时,前缀为"abcab"后缀为"bcabc"不相等 ×

        前后缀长度不可取k位置前的整体长度6,那么此时k位置前的最大匹配长度为3
        例如 "aaaaaab","b"的指标为6,那么"b"坐标前的前后缀最大匹配长度为5
        
        对str2建立坐标前后缀最大匹配长度数组
          1) 概念不存在的设置为-1,如0位置前没有字符串,就为-1,
          2) 索引1位置前只有一个字符,前后缀无法取和坐标前字符串相等,规定为0
        例如 "aabaabc", nextArr[] 为 [-1,0,1,0,1,2,3]

        a  a  b  a  a  b c
        0 -1  0  0  1  2 3
        KeyPoint 针对倒数第二个b,前后缀最大匹配是aa=aa,不是aab,因为aab!=baa(后串逆序读取了)

        暴力劣势:
        暴力方法之所以慢,是因为每次比对,如果match(str2)的u位置前都和str匹配上了,但是match的u+1位置没匹配成功
        那么str(str1)会回退到第一次匹配的下一个位置,而match(str2)直接回退到0位置再次比对.关键在于:str和match
        回退的位置太多,之前已经匹配的信息全部作废,没有记录,因而没有起到加速字符串查找的效果

        str1 ...A...K X
                i   u u+1

        str2    A...K Y
                0   u u+1

        str1的i-u位置和str2的0-u位置都是能匹配上的,从u+1位置开始是不匹配的

        1) 利用nextArr[]改进,其中[...]是前后缀最大匹配长度位置范围

        str1 ...A......... [    ]  X
                i          j      u+1

        str2   [A     ]Z.. [    ]  Y
                0 前缀串   后缀串  u+1

        2) 将str2往右推,0位置和j位置相对应,使用X和Z进行验证

        str1 ...A......... [    ]  X
                i          j      u+1

        str2              [A    ] Z..[    ]  Y
                           0 前缀串   后缀串 u+1

        KMP优势
        a) 尝试使用j位置能否匹配出str2,str1的j位置的后缀串[ ]和str2的0位置的前缀串[ ]是相等的,不需要验证,
           需要验证u+1的位置,相当于是加速
        b) str1中i-j位置中,任意一个子串开头都无法配出str2,直接将其舍弃掉,来到肯能配出str2的下个位置,即跳到j位置,
           也是相当于加速

        KeyPoint 本质:加速了以下这种情况
        若match(str2)的u位置前都和str匹配上了,但是match的u+1位置没匹配成功,
        那么str(str1)会回退到第一次匹配的下一个位置,而match(str2)直接回退到0位置再次比对
         */

        if (s1 == null || s2 == null || s1.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        // str1当前比对位置(从头开始进行比对)
        int x = 0;
        // str2中当前比位置
        int y = 0;

        // O(M)
        // 前后缀最大匹配长度数组
        int[] next = getNextArray(str2);

        // x在str中不越界,y在match中不越界
        while (x < str1.length && y < str2.length) {
            // 如果比对成功,x和y共同往各自的下一个位置移动
            if (str1[x] == str2[y]) {
                x++;
                y++;
                // next[y]==-1 => y=0 表示y已经来到了0位置(最开始位置),表示str2已经不能往左跳
                // 说明str2开头元素和str1的位置不匹配,x++则str1换下一个位置进行比对
            } else if (next[y] == -1) {
                x++;
            } else {
                // y通过最大前后缀长度的数组进行了加速匹配,并没有挨个进行匹配
                // y最左能跳到str2的开始位置,即next[-1],此时在while循环中再去和if比较
                // 若还是不等,则需要将x移动到str1的下个位置
                y = next[y];

                /*
                  1)步骤一
                        0 1 2 3 4 5
                  str1  a a b a a t
                  str2  a a b a a b      nextArr[-1,0,1,0,1,2] 数值
                  -------------------             0 1 2 3 4 5  索引值
                                 i=5

                  str1[5] != str2[5],执行y=nextArr[5]=2,y跳到2索引位置
                  使用str2的2位置和str1的5位置进行比较

                  2)步骤二
                        0 1 2 3 4 5
                  str1  a a b a a t
                                         nextArr[-1,0,1,0,1,2]
                 --------------------             0 1 2 3 4 5  索引值
                  str2        a a b a a b

                  b之前的aa是已经匹配好的,str1[5] != str2[2],执行y=nextArr[2]=1
                  y跳到1索引位置,使用str2的1位置和str1的5位置进行比较

                  3)步骤三
                        0 1 2 3 4 5
                  str1  a a b a a t
                                         nextArr[-1,0,1,0,1,2]
                 --------------------             0 1 2 3 4 5  索引值
                  str2          a a b a a b

                  str1[5] != str2[1],执行y=nextArr[1]=0,y跳到0索引位置
                  使用str2的0位置和str1的5位置进行比较

                  4)步骤四
                        0 1 2 3 4 5
                  str1  a a b a a t
                                         nextArr[-1,0,1,0,1,2]
                 --------------------             0 1 2 3 4 5  索引值
                  str2            a a b a a b

                  str1[5] != str2[0],执行y=nextArr[0]=-1,y没有跳了,x++即str1后移一个字符
                 */
            }
        }

        //  a  b  c  d  e        str2 abcde
        // 17 18 19 20 21

        //  a  b  c  d 越界位置   str2 abcd
        //  0  1  2  3  4

        // y到越界位置,跳出while循环,str2已经匹配上了,此时4代表str2的长度
        // 从索引位置角度分析,21-4,表示21位置元素不要,要前面4个元素,即为str2的开始索引
        // 类比数字:4-1=3,表示从2到4,一共3个元素2,3,4,将1排除在外

        // while循环结束条件
        // 1 x越界,y没有越界,找不到,返回-1
        //   str1尝试了所有开头,都没有和str2匹配,即找不到,返回-1;
        // 2 x没越界,y越界
        //   只有在分支1中,匹配上之后,y才会++,直到越界(y==str2.length),越界则说明str2的每个字符都匹配上了
        //   此时str2已经在str1中配出
        // 3 x-y,str1此时所在的位置x,减去y位置(y位置即为str2的长度),就是str存在匹配的字符串的开始位置
        return y == str2.length ? x - y : -1;

        // KeyPoint 时间复杂度 O(N)
        // 通过估计while循环中分支1,2,3分别中了几次,来估计while总的执行次数,从而分析时间复杂度
        //   X 最大为str1的长度N
        //   Y 最大为str2的长度M(N>M)
        //   X,Y 两者都不可能超过N

        //          X(max=N)   X-Y(max=N)
        // 分支1       ↑        不变
        // 分支2       ↑         ↑
        // 分支3     不变        ↑

        // 这3个分支总的发生的次数<=2N,即时间复杂度为O(N)
        // 因为3种分支只会进入一个,while循环的总次数和3种分支的总次数是一码事
        // 所以可以使用3种分支的总次数的极限来估计while发生的次数

        // 数学技巧:
        // X-Y是数学上作差,总来衡量整体的变化情况
        // 若是单纯分析X和Y,则存在X变大,Y变小的情况,无法评估整体的变化幅度,不好进行复杂度分析
        // 所以使用数学方法进行优化

    }

    public static int[] getNextArray(char[] str2) {
        /*
          next[] 特殊位置
           0 1  位置
          -1 0  next[i]
           0位置之前没有字符串,人为规定next[0]=-1
           1位置由于不能取整体,则next[1]=0
           不管是什么样的str2,next[0],next[1]都是这样

          next[]是从左往右依次求好的,故来到i位置,之前i-1位置的next信息已经求解过了,
          希望通过i-1位置的next信息加速得到i位置的信息
          =>即求解next[i]时,当前cn位置的字符(不断前跳的位置)和i-1位置的字符比较

          next[i-1]双重含义
          若next[i-1]=7,字符[0~6]为i-1位置的前缀,即最长前后缀匹配长度
          同时next[i-1]=7还表示前缀的下个字符的索引,即[0,1...5,6]7

          执行流程
          1) i位置,i-1位置的字符为b,利用i-1位置的next[i-1]值,来计算next[i]的值
             next[i-1]=7,则最长的前缀/后缀的长度为7,关键看z字符
             若z位置为b,则i位置next[i]=8,即在next[i-1]=7又加了一个字符b,故为8
                  b         b
             [   ]z  [   ] i-1   i
               7       7    7 => 8

          2) 若z位置不为b,在z位置再往前跳,若k位置为b,则i的next[i]=next[z](?)+1
                 b     b
             [  ]k  [  ]z
                        ?
          3) 依次类推,不断地基于上次判断位置(k)往前判断
         */

        if (str2.length == 1) {
            // 任何str的0位置的next值都是-1;
            return new int[]{-1};
        }

        // int型数组元素的默认初始值为0
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;

        // 从2位置上求next数组的值
        int i = 2;

        // int cn = 0 一开始设置为0原因
        // 一开始i=2,i-1=1,此时只有0位置字符和1位置字符比较,故cn=0;
        //  0 1 2  索引
        // -1 0    next[i]
        //  a a 1  str1
        //  a b 0  str2
        // 若0,1位置字符相同,则next[2]=next[1]+1=0+1=1;
        // 若0,1位置字符不同,则next[2]=0;
        int cn = 0;

        while (i < next.length) {
            // cn位置和i-1位置的字符匹配成功时
            if (str2[i - 1] == str2[cn]) {

                // next[i++] = ++cn
                // 等价于下面代码
                // next[i] = cn+1;
                // i++;
                // cn++;

                // i位置next[i]=cn+1求解之后,i+1位置的next[i+1]需要使用next[i]=cn+1来求解,
                // 因此直接使用++cn,满足赋值cn+1的需要,同时自身值cn++,满足求解next[i+1]的需要
                next[i++] = ++cn;
            } else if (cn > 0) {
                // next[cn]表示cn位置的前缀的下个字符的索引位置([ ]z)
                // 只要cn>0,不断往左跳,依次判断cn位置和i-1位置的字符是否相等
                // 直到cn=next[1]=0,下轮while循环,if判断str[i-1]和str[0],若不等执行最后的else,next[i]=0,同时i后移
                cn = next[cn];
            } else {
                // cn不能往左跳了,此时没有字符与之匹配,next[i]=0,i++
                next[i++] = 0;
            }
        }
        return next;

        // KeyPoint 时间复杂度 O(M)
        // 方式同上
        //          i(max=M)   i-cn(max=M)
        // 分支1       ↑        不变
        // 分支2     不变         ↑
        // 分支3      ↑           ↑
        // 这3个分支总的发生的次数 <= 2M 即 O(M)
    }
}

package alg_03_leetcode_top_zcy.class_09;

/**
 * @Author Wuyj
 * @DateTime 2023-02-26 16:15
 * @Version 1.0
 */

// 最小覆盖子串
public class Problem_0076_MinimumWindowSubstring {
    /*
        给你一个字符串s,一个字符串t,返回s中涵盖t所有字符的最小子串
        如果s中不存在涵盖t所有字符的子串,则返回空字符串""

        注意:s中包含t的字符个数要全,不在乎中间有其他字符,也不在乎顺序

        输入:s="ADOBECODEBANC",t="ABC"
        输出:"BANC"
        解释:最小覆盖子串"BANC"包含来自字符串t的'A','B'和'C'

        思路:
           1)对于t字符串"aabbc"建立一张欠账表
                                            欠账表
                                             a 2
                                             b 2
                                             c 1
                                            all=5

           2)R依次往右移动,判断给的字符什么时候到all为0
             R是还款操作
                 字符  s a b s b b c c a
                 索引  0 1 2 3 4 5 6 7 8
                       L               R
             =>  R不断往右移,同时修改欠账表(注意无效还款,all不变),直到8位置,R停止
                 此时扩的窗口[L,R]含义:若子字串必须从以0开头,最短包含t的所有字符,就是[L,R]
                 因为R到达8位置是第一次不欠款,所以是最短的

                                            欠账表
                                             a 2->1->0
                                             b 2->1->0->-1 无效还款,all不变
                                             c 1->0->-1
                                             s -1->-2  s小于0,则表示不欠s,同时all不变
                                            all=5->4->1->0

            3)L依次往右移动,让前面的字符串依次出去
              L是欠款操作
                 字符  s a b s b b c c a
                 索引  0 1 2 3 4 5 6 7 8
                         L             R
                      => 若子字串必须从以1开头,最短包含s的所有字符,就是[L,R]
                           L
                      => 拿回a,则重新欠款.此时不知道以2开头,达标的最短包含s的所有字符
                         此时让R往后继续

                总结:L固定一个位置,如果欠款,让R往右扩(还款),扩到不欠款了
                     让L依次缩(欠款),缩的每一步都可以求一个答案,故所有开头的答案都可以求出

                                            欠账表
                                             a 0->-1
                                             b -1
                                             c -1
                                             s -2->-1 拿回s没有欠款
                                            all=0

            4)常数级别优化:优化操作不会减轻时间复杂度O(N),这些优化操作都是常数级别的优化
            5)本题中L和R是不回退的,L最多为N,R最多为N,所以时间复杂度是O(N)

     */
    public static String minWindow(String s, String t) {

        // 过滤条件
        if (s.length() < t.length()) {
            return "";
        }

        char[] str = s.toCharArray();
        char[] target = t.toCharArray();

        int[] map = new int[256];

        // 记录欠账表(+操作)
        // 在Java中可以将char类型的值,以ascll形式以存储在int类型的数组中
        for (char cha : target) {
            map[cha]++;
        }

        /*
            int[] a = new int[1];
            a[0] = 'a';
            System.out.println(a[0]); // 97
         */

        // 一共欠账数
        int all = target.length;

        // 左右窗口边界
        int L = 0;
        int R = 0;

        // minLen表示当前抓到最小长度,-1表示从来没找到过合法的
        int minLen = -1;

        // 当取得最小长度minLen时,使用ansl和ansr来记录此时L和R的位置,-1表示从来没找到过合法的
        int ansl = -1;
        int ansr = -1;

        // KeyPoint 推荐表示窗口使用[)的方式,简化窗口初始值的设置
        //      直接将初始值设置为0,[0,0)表示啥都没有
        // [L..R)->[0,0)
        // R就表示即将把该字符拉进窗口的位置
        //   s
        // L(R)
        // L和R重合,R表示即将把s字符拉进窗口位置
        while (R != str.length) {
            // R还款,则欠账表--
            map[str[R]]--;

            // 有效还款
            // KeyPoint 这里需要取等的,因为map[str[R]]--操作在前,
            //      在减完之后还能=0,则说明之前是1的
            if (map[str[R]] >= 0) {
                all--;
            }

            // all!=0,则表示没有还完,则跳过if判断
            // 直到将账都还完了,开始从L位置判断(尽可能消除无用字符)
            if (all == 0) {
                // 多给字符,将其拿回也不欠款
                // KeyPoint 小于0的判断,之前多给的字符,在欠账表记录时是负数
                while (map[str[L]] < 0) {
                    map[str[L]]++;
                    // L后移
                    L++;
                    // 等价 map[str[L++]]++; 不是很推荐
                }

                // L到了map[str[L]]=0位置,while停止因为再执行while,map[str[L]]++,则该字符就欠账了,此时到了临界情况

                // 2种情况下更新
                //  1)-1表示之前没有抓到过
                //  2)之前抓到的minLen没有现在的minLen好,
                if (minLen == -1 || minLen > R - L + 1) {
                    minLen = R - L + 1;
                    ansl = L;
                    ansr = R;
                }
                // 当前L在不欠账的位置,L到下个位置必然要欠账,all++
                all++;
                // 将L位置,拿回一个字符,重新变成欠账的状态,L右移
                map[str[L]]++;
                L++;
            }

            // 没有还完则R继续往右扩
            R++;
        }
        // substring[),所以ansr是需要+1的
        // KeyPoint 最后使用的ansl和ansr,不是原始的l和r
        return minLen == -1 ? "" : s.substring(ansl, ansr + 1);
    }
}

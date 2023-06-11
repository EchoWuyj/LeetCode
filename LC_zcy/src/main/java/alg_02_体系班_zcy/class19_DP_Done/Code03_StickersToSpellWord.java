package alg_02_体系班_zcy.class19_DP_Done;

import java.util.HashMap;

// 贴纸拼词
// 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word

public class Code03_StickersToSpellWord {
	
	/*
		给定一个字符串str,再给定一个字符串类型的数组arr,出现的字符都是小写英文,arr每一个字符串,
		代表一张贴纸(每个贴纸的数量是无限的),你可以把单个字符剪开使用,目的是拼出str来返回需要
		至少多少张贴纸可以完成这个任务?如果任务不可能,则返回-1

		例子：str= "babac",arr = {"ba","c","abcd"}
		      ba + ba + c => 3
			  abcd + abcd => 2  (贴纸剪碎,多余的可以不使用)
		      abcd + ba =>  2    因为2是最小的,所以返回2

		 KeyPoint 问题转化 => str和arr[...]的字符顺序不重要,至少有几张贴纸能将str的字符包含全

         思路
         将每张贴纸依次作为第一张去分解target,分解之后rest再去依次使用每张贴纸去分解,即暴力枚举
         若能完成,则从能完成任务中取最小值,否则任务不可能完成,返回-1;

         target="abc"     arr["ac","ka"]

                        abc
                     ↙ac  ↘ka
         系统最大↗  b         bc
         系统最大↗↙ac ↘ka ↙ac ↘ka ↖ 系统最大
                 系统最大↗ b ↖ 系统最大
                       ↙ac ↘ka

         1)若target在使用一层贴纸后,rest二层贴纸返回系统最大值,则一层贴纸也也应该返回系统最大值,表示任务无法完成
         2)若rest能被贴纸分解,且使用了x张贴纸,则target在返回时需要再加上1张贴纸,即(x+1)张贴纸

	 */

    // KeyPoint 方法一:暴力递归(超时)
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        // 返回-1表示任务不能完成的情况
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * @param stickers 所有贴纸stickers(每一种贴纸都有无穷张)
     * @param target   组成的目标
     * @return 最少张数
     */
    public static int process1(String[] stickers, String target) {
        // 之前决策已经将target分解完了,不需要贴纸了,return 0;
        // target每次被分解时,其长度是不断减少的,所以长度可能会等于0的
        if (target.length() == 0) {
            return 0;
        }

        // min为系统最大值,表示怎么弄都实现不了,上游主函数调用递归函数时再去判断
        // target "xyz",arr["abb","bbc","cd"] 这种就是怎么弄都实现不了
        int min = Integer.MAX_VALUE;

        // 每张贴纸作为第一张去分解target,剩下字符作为下一步
        for (String first : stickers) {
            // 剩下的字符rest
            String rest = minus(target, first);
            // 若rest长度和target长度一样,说明贴纸分解没有效果,if语句直接跳过
            if (rest.length() != target.length()) {
                // 使用stickers数组中的字符串继续分解rest
                // 在rest分解的所有能完成结果中选择最少张
                min = Math.min(min, process1(stickers, rest));
            }
        }

        // 后续分解的min有可能为系统最大,有可能为1,需要对这两种情况进行判断
        // 1)若min为Integer.MAX_VALUE,后续解无效,此时递归上层解也无效
        // 2)若min不为Integer.MAX_VALUE,后续解有效,此时递归上层解要加上本层一张贴纸,故+1
        // KeyPoint 递归函数绕不清楚,使用最简单的例子去梳理逻辑
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    // 使用贴纸分解target的过程,target长度不断减少
    // target ="abcd" ,stickers中"ab",经过minus,rest="cd"
    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 定义26字母长度的数组
        int[] count = new int[26];
        // target字符串
        for (char cha : str1) {
            // 'a'-'a'=0,0位置表示'a',count[0]=3,则表示有3个'a'
            count[cha - 'a']++;
        }
        // stickers数组中字符串
        for (char cha : str2) {
            count[cha - 'a']--;
        }
        // 生成好剩余的字符
        StringBuilder builder = new StringBuilder();
        // 26个位置,每个位置都判断
        for (int i = 0; i < 26; i++) {
            // i位置上存在字符
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    // KeyPoint 方法二:优化 => 贴纸数组转二维数组,将字母对应位置标记为词频次数(超时)
    // stickers数组   二维数组
    //   "acc"      [ [1 0 2 ...]
    //                 a b c
    //                 0 1 2
    //   "bbc"        [0 2 1  ] ]
    //                 a b c
    //                 0 1 2
    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化:用词频表替代贴纸数组
        int[][] counts = new int[N][26]; // 每行26个字母
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            // 遍历贴纸中每个字符串
            for (char cha : str) {
                // count[i]和每个贴纸一一对应
                counts[i][cha - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 每一种贴纸都有无穷张,返回搞定target的最少张数
    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        // target字符数组
        char[] target = t.toCharArray();

        // target的词频统计counts(每次递归target都可能发生变化)
        // target  aabbc  2 2 1..
        //                a b c..
        //                0 1 2..
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // sticker = stickers[i],sticker表示数组 =>当初i号贴纸的字符统计
            // 从第一张贴纸词频开始尝试
            int[] sticker = stickers[i];

            // 原来暴力递归在分解target时没有字符顺序要求,只是使用每张贴纸进行尝试,即所有的分支都走
            // 这样就导致正确答案可能因为次序不一样("X","Y","Z"|"Y","X","Z")出现好几回,但不同答案的最少张数却是一个解
            // 因此只要一组答案即可
            // => 最关键优化:剪枝,即所有分支中必须含有第一个字符的分支才跑后续流程(减少冤枉路)
            // 比如:target为"aaabbbck",贴纸"bbc","cck","kkb","bab",只有bab能第一次进来

            // 只有当前贴纸sticker关于target的0位置字符词频统计大于0,该贴纸才去执行后续的代码
            // 方法一中 if (rest.length() != target.length()) 就不需要了
            // KeyPoint 词频统计中'a'是比较的起始点,'a'-'a'=0,'b'-'a'=1...
            //      target[0]虽然不知道是什么字符,但是都是和'a'进行比较的
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                // 遍历26个位置
                for (int j = 0; j < 26; j++) {
                    // 只有target词频>0,才去进行后续判断
                    if (tcounts[j] > 0) {
                        // 每个位置上减字符,直接使用词频表相减,速度快很多
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    // KeyPoint 方法三:记忆化搜索(dp)
    public static int minStickers3(String[] stickers, String target) {

        // 重复过程判断
        // => target="aabbbbcccc",sticker数组["aa","aaa","aaaa"]
        //    aaa"和"aaaa"就是"aa"的重复解,此时若知道"bbbbcccc"最少张数,再遇到"aaa"和"aaaa"直接返回答案
        //

        // 由于target是String类型可变参数,无法确定target其变化范围
        // 因此不能将其变为严格表结构dp,只是记忆化搜索即可,注意区别int下标的dp
        //  => 能够做位置依赖的就做,做不了的直接做缓存

        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        // HashMap作为dp
        // key->字符串
        // value->计算结果
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        // 剩余字符串已经计算过了,直接返回结果
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        // 经过计算将结果存到dp表中
        dp.put(t, ans);
        return ans;
    }
}

package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.BM1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:02
 * @Version 1.0
 */

public class _03_BM2 {

    // KeyPoint 好后缀-第一种情况 => pattern 没有好后缀

    //    坏字符 好后缀
    //         ↓ ---
    // b c a c c b c b c b a c a b c
    // a b d c a b c

    // 判断 pattern 除了好后缀 map 外，是否有额外 map
    // 若没有，则将 pattern 整体移动到 map 后面

    //          好后缀
    //           ---
    // b c a c c b c | b c b a c a b c
    //               | a b d c a b c

    // 模式串中没有好后缀
    // 将整个模式串移动到bc后面

    // KeyPoint 好后缀-第二种情况 => pattern 有好后缀

    //    坏字符 好后缀
    //         ↓ ---
    // b c a c c b c b c b a c a b c
    // a b b c a b c
    //     ---

    //    坏字符 好后缀
    //         ↓ ---
    // b c a c c b c b c b a c a b c
    //       a b b c a b c
    //           ---

    // KeyPoint 好后缀-第三种情况 => pattern 前缀字符串和好后缀的后缀，有部分重叠

    //    坏字符 好后缀
    //         ↓ ---
    // b c a c c b c b c b a c a b c
    //             c b a c a b c
    //             -         ---
    //        模式串前缀    好后缀后缀

    // 虽然没有完整的好后缀 map，但是有其中一部分是能匹配上的
    // => 在模式串中，查找跟好后缀匹配的另一个子串
    // => 在好后缀的后缀子串中，查找最长、能跟模式串前缀子串匹配的后缀子串

    // 如何表达模式串的后缀子串，后缀子串都有可能成为好后缀
    // 模式串：c a b c a b
    // 后缀子串    长度    suffix数组
    //  b           1     suffix[1]=2，  1 => 后缀子串长度
    //                                   2 => 索引位置
    //  ab          2     suffix[2]=1
    //  cab         3     suffix[3]=0
    //  bcab        4     suffix[4]=-1 => 没有出现，设置为 -1
    //  abcab       5     suffix[5]=-1

    // suffix：记录每个后缀子串在模式串中出现的'其他位置'
    // '其他位置'：在模式串中，从前往后，找后缀子串出现的第一个位置 [多个匹配，选择靠后位置]
    // index 后缀子串长度
    // value 索引位置

    // KeyPoint 思考一个问题
    // 当 pattern 和 mainStr 在某个字符上不匹配时，
    // 使用'坏字符规则'还是'好后缀规则'来计算 pattern 移动的位数

    //           坏字符 好后缀
    //               ↓ -----
    // mainStr       a a a a a a a
    // pattern       b a a a
    //                 -----
    //               ↑     ↑
    //  坏字符规则： y=0   x=3  => i = i +(y-x) = -3; 向左移动

    //           坏字符 好后缀
    //               ↓ -----
    // mainStr       a a a a a a a
    // pattern               b a a a
    // 好后缀规则

    // KeyPoint 结论
    // 分别计算出'坏字符规则'下移动的位数和'好后缀规则'下移动的位数，并取两者的较大值，作为 pattern 移动的位数

    // KeyPoint 好处
    // 1.避免'坏字符规则'计算为负数
    // 2.符合 BM 核心思想：尽可能多的往后移动

    // KeyPoint 坏字符规则 + 好后缀规则 => BM
    // 一般都是使用 BM 算法来实现文本文档的搜索功能，性能很好
    // 时间复杂度：最坏情况下，BM 算法的比较次数上限是 5m => http://dl.acm.org/citation.cfm?id=1382431.1382552
    //            最坏情况下，BM 算法的比较次数上限是 3m => http://dl.acm.org/citation.cfm?id=127830
    // 空间复杂度：O(n)
    public int indexOf(String mainStr, String pattern) {

        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 数据(模式串)预处理, 构建坏字符哈希表
        Map<Character, Integer> map = getIndexMap(pattern);

        // 数据预处理：构建好后缀规则，需要准备两个数组 suffix 和 prefix
        // suffix 记录每个后缀子串在模式串中出现的位置
        int[] suffix = new int[n];
        // prefix 记录模式串中的后缀子串是否能匹配模式串的前缀子串
        boolean[] prefix = new boolean[n];
        genGoodSuffixArr(pattern.toCharArray(), suffix, prefix);

        int i = 0; // i 表示每次匹配的时候，主串的起始位置，初始化为 0
        while (i <= m - n) {
            // 1. 找到第一个坏字符
            int y;
            for (y = n - 1; y >= 0; y--) { // 从后往前匹配
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }

            // 2. 表示匹配成功
            if (y < 0) {
                // 返回主串和模式串第一个匹配字符串的位置
                return i;
            }

            // 3. 匹配不成功，模式串往后移动
            // 先计算坏字符在模式串中的位置 x
            char badChar = mainStr.charAt(i + y);
            int x = map.getOrDefault(badChar, -1);
            int badCharMoveSteps = y - x;

            // 根据好后缀规则，计算移动的位数
            int goodSuffixMoveSteps = 0;

            // y指向坏字符位置，且 y < n-1 ，即 y 不是模式串最后一个字符，则说明有好后缀，否则没有
            if (y < n - 1) {
                // y 表示坏字符对应的模式串中的字符位置
                // n 为模式串的长度
                goodSuffixMoveSteps = calMoveSteps(y, n, suffix, prefix);
            }

            // 4. 往后移动最大位数
            i = i + Math.max(badCharMoveSteps, goodSuffixMoveSteps);
        }

        return -1;
    }

    // KeyPoint 根据好后缀规则计算得到移动步数
    private int calMoveSteps(int y, int n, int[] suffix, boolean[] prefix) {
        // k 表示好后缀的长度
        int k = n - 1 - y;
        // 1 第二种情况：模式串中是否存在好后缀，通过 suffix[k] 判断
        if (suffix[k] != -1) return y - suffix[k] + 1;
        // 2 第三种情况：好后缀的后缀子串是否和模式串的前缀子串匹配
        //   通过 i 指针从好后缀的第 1 个位置 (y+1)，一次完后判断 prefix 是否为 true
        for (int i = y + 1; i < n; i++) {
            // KeyPoint 不能使用 y + 1 来，因为 for 循环中是变化的量，不能写死成 y + 1
            //          只能通过循环变量 i，来表示 y + 1 以及后续的位置
            //          n-(y+1) = n-1-y，表示 y+1 的位置，也是可以使用 i(y+1) 表示
            if (prefix[n - i]) {
                return i;
            }
        }
        // 3 第一种情况：模式串中没有好后缀，将整个模式串后移 n 位
        return n;
    }

    // KeyPoint 生成好后缀规则的两个数组 suffix 和 prefix
    private void genGoodSuffixArr(char[] pattern, int[] suffix, boolean[] prefix) {
        // suffix 默认是 -1，表示好后缀在模式串中不存在
        Arrays.fill(suffix, -1);
        int n = pattern.length;
        // i 不断向后遍历 pattern 每个字符位置，直到找到与最后一个位置字符匹配的索引记录为 j
        // KeyPoint i < n - 1 因为后缀字串的个数为 n-2 个，完整的模式串 cabcab 不能算后缀字串，最多为 abcab
        for (int i = 0; i < n - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && pattern[j] == pattern[n - 1 - k]) {
                // j 索引和 n-1-k 索引，一起往左移动，进行字符匹配判断
                k++;
                // k 表示好后缀的长度，j 表示对应索引位置
                suffix[k] = j;
                j--;
            }
            // 只有将上面的 while 执行完后，j 才能为 -1，若不执行 while 循环中代码
            // j 是不会减小的，故没有存在 prefix[k] = true 的情况
            if (j == -1) prefix[k] = true;
        }
    }

    private Map<Character, Integer> getIndexMap(String pattern) {
        char[] chars = pattern.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }
        return map;
    }

    public static void main(String[] args) {
        test1(); // 2
        test2(); // -1
    }

    private static void test2() {
        BM1 b = new BM1();
        String mainStr = "aaaaaaa";
        String patternStr = "baaa";
        System.out.println(b.indexOf(mainStr, patternStr));  // -1
    }

    private static void test1() {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));  // 2
    }
}

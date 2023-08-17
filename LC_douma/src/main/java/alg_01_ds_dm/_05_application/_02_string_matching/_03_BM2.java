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

    // --------------------------------------------------------

    // KeyPoint 好后缀：第一种情况 => pattern 没有好后缀

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

    // KeyPoint 好后缀：第二种情况 => pattern 有好后缀

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

    // KeyPoint 好后缀：第三种情况 => pattern 前缀字符串和好后缀的后缀，有部分重叠

    //    坏字符 好后缀
    //         ↓ ---
    // b c a c c b c b c b a c a b c
    //             c b a c a b c
    //             -         ---
    //        模式串前缀    好后缀后缀

    // 虽然没有完整的好后缀 map，但是有其中一部分是能匹配上的
    // => 在模式串中，查找跟好后缀匹配的另一个子串
    // => 在好后缀的后缀子串中，查找最长、能跟模式串前缀子串匹配的后缀子串

    // KeyPoint 如何表达模式串的后缀子串，后缀子串都有可能成为好后缀

    // 模式串：c a b c a b
    // 后缀子串    长度    suffix 数组                         prefix 数组
    //  b           1     suffix[1]=2，  1 => 后缀子串长度     prefix[1]=false
    //                                   2 => 索引位置
    //  ab          2     suffix[2]=1                        prefix[2]=false
    //  cab         3     suffix[3]=0                        prefix[3]=true  => cab = cab 都是从前往后看
    //  bcab        4     suffix[4]=-1 => 没有出现，设置为 -1  prefix[4]=false => cabc = bcab
    //  abcab       5     suffix[5]=-1                       prefix[5]=false

    // KeyPoint suffix 数组
    // suffix 数组：记录每个后缀子串在模式串中出现的'其他位置'
    // '其他位置'：在模式串中，从前往后，找后缀子串出现的第一个位置 [补充说明：若有多个位置匹配，选择靠后位置，避免滑动过头]

    // suffix
    // index 后缀子串长度
    // value 索引位置

    // KeyPoint prefix 数组
    // prefix 数组：记录模式串中的后缀子串是否能匹配模式串的前缀子串

    // prefix
    // index 后缀子串长度
    // value true / false

    // KeyPoint 计算 pattern 移动的位数
    // 当 pattern 和 mainStr 在某个字符上不匹配时
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

    // 结论
    // 分别计算出'坏字符规则'下移动的位数和'好后缀规则'下移动的位数
    // 并取两者的较大值，作为 pattern 移动的位数

    // 好处
    // 1.避免'坏字符规则'计算为负数
    // 2.符合 BM 核心思想：尽可能多的往后移动

    // --------------------------------------------------------

    // KeyPoint 坏字符规则 + 好后缀规则 => BM
    // 一般都是使用 BM 算法来实现文本文档的搜索功能，性能很好
    // 时间复杂度
    // 1.最坏情况下，BM 算法的比较次数上限是 5m，m 表示主串长度
    //    => http://dl.acm.org/citation.cfm?id=1382431.1382552
    // 2.最坏情况下，BM 算法的比较次数上限是 3m，m 表示主串长度
    //    => http://dl.acm.org/citation.cfm?id=127830
    // 空间复杂度：O(n)
    public int indexOf(String mainStr, String pattern) {

        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 数据(模式串)预处理, 构建坏字符哈希表
        Map<Character, Integer> map = getIndexMap(pattern);

        // KeyPoint 数据预处理
        // 为了构建好后缀规则，需要准备两个数组 suffix 和 prefix

        // 1.suffix 记录每个后缀子串在模式串中出现的位置
        int[] suffix = new int[n];
        // 2.prefix 记录 pattern 后缀子串，能否匹配 pattern 前缀子串
        boolean[] prefix = new boolean[n];

        // 生成好后缀规则所需要的 suffix 和 prefix 数组
        genGoodSuffixArr(pattern.toCharArray(), suffix, prefix);

        // i 表示每次匹配的时候，主串的起始位置，初始化为 0
        int i = 0;
        while (i <= m - n) {
            // 1.找到第一个坏字符
            int y;
            // mainStr 和 pattern 从后往前匹配
            for (y = n - 1; y >= 0; y--) {
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }

            // 跳出 for 循环，此时 y 为坏字符位置

            // 2.若 y < 0，表示匹配成功
            if (y < 0) {
                // 返回主串和模式串第一个匹配字符串的位置
                return i;
            }

            // 3.若 y > 0，匹配不成功，模式串往后移动
            // 坏字符是主串中的，i+y 位置
            char badChar = mainStr.charAt(i + y);

            // 主串中坏字符在模式串中的位置 x
            // 若模式串中没有主串坏字符，则 x = -1;
            int x = map.getOrDefault(badChar, -1);
            // 坏字符规则下需要移动的位数
            int badCharMoveSteps = y - x;

            // 根据好后缀规则，计算需要移动的位数
            // 默认设置 0，若没有'好后缀'也影响结果
            int goodSuffixMoveSteps = 0;

            // y 指向坏字符位置，且 y < n-1，即 y 不是模式串最后一个字符，则说明有好后缀，否则没有
            if (y < n - 1) {
                // y 表示模式串中坏字符的索引位置
                // n 为模式串的长度
                goodSuffixMoveSteps = calMoveSteps(y, n, suffix, prefix);
            }

            // 4. 根据两种规则下，往后移动最大位数
            i = i + Math.max(badCharMoveSteps, goodSuffixMoveSteps);
        }
        return -1;
    }

    // 函数功能：根据好后缀规则计算得到移动步数
    private int calMoveSteps(int y, int n, int[] suffix, boolean[] prefix) {

        // --------------------------------------------------------

        // KeyPoint 第二种情况 => 模式串中是否存在好后缀

        // 1.好后缀的长度
        //   mainStr b c a c c b c b c b a c a b c
        //   pattern a b b c a b c
        //                   ↑ ---
        //                   y k，k= n-1-y => 好后缀的长度

        //   a b b c a b c
        //           ↑   ↑
        //           y  n-1
        //             --- = n-1-y = k => 好后缀的长度

        // 2.移动位数
        //   mainStr b c a c c b c b c b a c a b c
        //   pattern a b b c a b c
        //               --- ↑ ---
        //               ↑   y k
        //          suffix[k]=2 => 模式串中存在好后缀

        //                   y 坏字符
        //                   ↓
        //   mainStr b c a c c b c b c b a c a b c
        //   pattern a b b c a b c
        //               ----- 移动位数：y-suffix[k]+1，数组两端都包括，故需要加 1

        // mainStr b c a c c b c b c b a c a b c
        // pattern       a b b c a b c
        //                   ---

        // KeyPoint 第三种情况 => 好后缀的后缀子串是否和模式串的前缀子串匹配

        // mainStr b c a c c b c b c b a c a b c
        //                   i i'
        //                   ↓ ↓
        // pattern c b a c a b c
        //                 ↑ ---
        //                 y k，k= n-1-y
        //                      suffix[2]=-1

        //                   i
        //                   ↓
        // pattern c b a c a b c _
        //                       ↑
        //                       n

        // n-i 表示 b c 长度，n 是 n-1 后面一位
        // prefix[n-i]=prefix[2]=false

        //                     i'
        //                     ↓
        // pattern c b a c a b c _
        //                       ↑
        //                       n

        // i 向后移动一位为 i'
        // prefix[n-i']=prefix[1]=true，表明 pattern '后缀子串' 和 pattern '前缀子串' 重合的
        // 直接返回 i'，表示移动 i' 位

        //                     i'
        //                     ↓
        // mainStr b c a c c b c b c b a c a b c
        // pattern             c b a c a b c
        //

        // --------------------------------------------------------

        // k 表示好后缀的长度
        int k = n - 1 - y;
        // 1.第二种情况：模式串中是否存在好后缀，通过 suffix[k] 判断
        // 若存在好后缀，再去计算需要移动的位数
        if (suffix[k] != -1) return y - suffix[k] + 1;

        // suffix[k] !== -1

        // 2.第三种情况：好后缀的后缀子串是否和模式串的前缀子串匹配
        //   通过 i 指针，从好后缀的第 1 个位置 (y+1)，依次往后判断 prefix 是否为 true
        for (int i = y + 1; i < n; i++) {
            // 不能使用固定值 y+1 来计算，因为 for 循环中是变化的量，不能写死成 y+1
            // 只能通过循环变量 i，i = y+1，表示 y+1 以及后续的位置
            if (prefix[n - i]) {
                return i;
            }
        }
        // 3.第一种情况：模式串中没有好后缀，直接移动模式串长度位，即移动 n 位
        // i+1 => i 向后移动 1 位
        // i+n => i 向后移动 n 位
        return n;
    }

    // 函数功能：生成好后缀规则所需要两个数组 suffix 和 prefix
    private void genGoodSuffixArr(char[] pattern, int[] suffix, boolean[] prefix) {

        // ----------------------------------

        // 模式串：c a b c a b
        //             ↑     -
        //             i        => suffix[1]=2
        //             -

        // 模式串：c a b c a b
        //           ↑ ↑
        //           j i   --   => suffix[2]=1
        //           --

        // 模式串：c a b c a b
        //         ↑   ↑
        //         j   i ---    => suffix[3]=0
        //          ---

        // 模式串：c a b c a b
        //       ↑     ↑
        //     j=-1    i ---   => prefix[3]=true

        // ----------------------------------

        // prefix 默认是 false，不用显示初始化
        // suffix 默认是 -1，表示好后缀在模式串中不存在
        Arrays.fill(suffix, -1);
        int n = pattern.length;

        // KeyPoint for 循环代码功能：填充 suffix 和 prefix 数组
        // 1.最开始，i 不断向后遍历 pattern 每个字符位置
        //   直到找到与最后一个位置字符 pattern[n-1] 匹配，记录索引为 j(i)
        // 2.for 循环条件 i < n-1，因为后缀字串的个数为 n-1 个
        //   完整的模式串 cabcab 不能算后缀子串，后缀子串最多为 c|abcab
        for (int i = 0; i < n - 1; i++) {
            int j = i;
            int k = 0;
            // 若 pattern[j] != pattern[n-1-k]，跳过 while 循环，执行下轮 for 循环
            // while 循环中 j 索引和 n-1-k 索引，一起往左移动，进行字符匹配判断
            while (j >= 0 && pattern[j] == pattern[n - 1 - k]) {
                k++;
                // k 表示好后缀的长度，j 表示对应索引位置
                suffix[k] = j;
                j--;
            }
            // 执行完上面 while 循环后，j = -1
            // 说明'后缀子串'匹配'前缀子串'，设置 prefix[k] = true
            if (j == -1) prefix[k] = true;
        }
    }

    private Map<Character, Integer> getIndexMap(String pattern) {
        char[] chars = pattern.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
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

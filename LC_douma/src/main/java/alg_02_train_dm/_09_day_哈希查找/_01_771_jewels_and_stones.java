package alg_02_train_dm._09_day_哈希查找;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 13:08
 * @Version 1.0
 */
public class _01_771_jewels_and_stones {
    /*
        leetcode 771 号算法题：宝石与石头
        给定字符串 J 代表石头中宝石的类型，和字符串 S 代表你拥有的石头。
        S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

        J 中的字母不重复，J 和 S中的所有字符都是字母。
        字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。

        输入: J = "aA", S = "aAAbbbb"
        输出: 3

        输入: J = "z", S = "ZZ"
        输出： 0

        - S 和 J 最多含有 50 个字母。
        - J 中的字符不重复。
    */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(1)
    // KeyPoint 因为 S 和 J 最多含有 50 个字母，所以该算法也能通过的
    public int numJewelsInStones1(String jewels, String stones) {
        int ans = 0;
        // 遍历 stones 每个字符，判断是否在 jewels 中
        for (char c : stones.toCharArray()) {
            // 线性查找 优化 哈希查找
            for (char j : jewels.toCharArray()) {
                if (c == j) ans++;
            }
        }
        return ans;
    }

    // KeyPoint 方法二 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(m) => 使用空间换时间
    public int numJewelsInStones2(String jewels, String stones) {
        // 通过 jewels 构造 HashSet 表，加速查找，类似字典效果
        Set<Character> set = new HashSet<>();
        for (char c : jewels.toCharArray()) set.add(c);

        int ans = 0;
        for (char c : stones.toCharArray()) {
            if (set.contains(c)) ans++;
        }

        return ans;
    }

    // KeyPoint 方法三 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int numJewelsInStones(String jewels, String stones) {

        // KeyPoint 若想使用数组替换 HashSet，需要对存储元素有限制，一般得限制其元素值范围
        // J 和 S 中的所有字符都是字母 => 26 小写字母 和 26 大写字母 => 数组 代替 HashSet，数组下标对应字符

        // 'A' 65 'Z' 90
        // 'a' 97 'z' 122
        // 大写字母和小写字母的 ASCII 不是连续的，中间有 6 个字符将其分隔开了
        // 使用字母对应的 ASCII 作为索引，故一定需要包含 65 到 122，即数组长度够长，不然 c - 'A' 索引值越界

        // count 中存储 A 到 Z 中的所有的字符，包含 58 个字符
        // 常规 '-' 是不包括一端的，而这里两端都要包括，故需要 +1
        int len = 'z' - 'A' + 1;
        int[] count = new int[len];
        // 将字典表初始化，宝石字符设置为 1
        for (char c : jewels.toCharArray()) count[c - 'A'] = 1;

        int ans = 0;
        for (char c : stones.toCharArray()) {
            // stones 中字符是宝石字符，则 ans ++
            if (count[c - 'A'] == 1) ans++;
        }

        return ans;
    }
}

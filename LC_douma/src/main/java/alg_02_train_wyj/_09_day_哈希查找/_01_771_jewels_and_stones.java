package alg_02_train_wyj._09_day_哈希查找;

import alg_01_ds_wyj._03_high_level._02_set.HashSet;
import alg_01_ds_wyj._03_high_level._02_set.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 13:22
 * @Version 1.0
 */
public class _01_771_jewels_and_stones {
    public int numJewelsInStones1(String jewels, String stones) {
        int res = 0;
        for (char s : stones.toCharArray()) {
            for (char j : jewels.toCharArray()) {
                if (s == j) {
                    res++;
                }
            }
        }
        return res;
    }

    public int numJewelsInStones2(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (char j : jewels.toCharArray()) set.add(j);
        for (char s : stones.toCharArray()) {
            if (set.contains(s)) res++;
        }
        return res;
    }

    public int numJewelsInStones(String jewels, String stones) {
        int len = 'z' - 'A' + 1;
        int[] count = new int[len];

        for (char j : jewels.toCharArray()) {
            count[j - 'A'] = 1;
        }
        int res = 0;
        for (char s : stones.toCharArray()) {
            if (count[s - 'A'] == 1) res++;
        }
        return res;
    }
}

package alg_02_train_wyj._09_day_哈希查找;

import alg_01_ds_wyj._03_high_level._02_set.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 13:22
 * @Version 1.0
 */
public class _01_771_jewels_and_stones {
    public int numJewelsInStones1(String jewels, String stones) {
        int ans = 0;
        for (char c : stones.toCharArray()) {
            for (char j : jewels.toCharArray()) {
                if (c == j) ans++;
            }
        }
        return ans;
    }

    public int numJewelsInStones2(String jewels, String stones) {
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        for (char c : jewels.toCharArray()) set.add(c);
        for (char c : stones.toCharArray()) {
            if (set.contains(c)) ans++;
        }
        return ans;
    }

    public int numJewelsInStones(String jewels, String stones) {
        int ans = 0;
        int len = 'z' - 'A' + 1;
        int[] count = new int[len];
        for (char c : jewels.toCharArray()) count[c - 'A'] = 1;
        for (char c : stones.toCharArray()) {
            if (count[c - 'A'] == 1) ans++;
        }
        return ans;
    }
}

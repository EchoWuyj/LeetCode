package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 15:28
 * @Version 1.0
 */
public class _05_767_reorganize_string {
    public String reorganizeString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] counts = new int[26];
        for (char c : chars) {
            counts[c - 'a']++;
            if (counts[c - 'a'] > (n + 1) / 2) return "";
        }

        int maxCountIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] > counts[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        char[] res = new char[n];
        int index = 0;
        while (counts[maxCountIndex] > 0) {
            res[index] = (char) (maxCountIndex + 'a');
            index += 2;
            counts[maxCountIndex]--;
        }

        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                if (index >= n) index = 1;
                res[index] = (char) (i + 'a');
                index += 2;
                counts[i]--;
            }
        }
        return new String(res);
    }
}

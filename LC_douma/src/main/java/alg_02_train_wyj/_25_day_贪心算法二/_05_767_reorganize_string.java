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
        int[] count = new int[26];
        for (char c : chars) {
            int index = c - 'a';
            count[index]++;
            if (count[index] > (n + 1) / 2) return "";
        }

        int maxCountIndex = 0;
        for (int i = 1; i < 26; i++) {
            if (count[i] > count[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        char[] res = new char[n];
        int index = 0;
        while (count[maxCountIndex] > 0) {
            res[index] = (char) (maxCountIndex + 'a');
            index += 2;
            count[maxCountIndex]--;
        }

        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                if (index >= n) index = 1;
                res[index] = (char) (i + 'a');
                index += 2;
                count[i]--;
            }
        }
        return new String(res);
    }
}

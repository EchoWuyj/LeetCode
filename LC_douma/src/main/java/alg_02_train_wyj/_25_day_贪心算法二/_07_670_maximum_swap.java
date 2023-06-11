package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 16:38
 * @Version 1.0
 */
public class _07_670_maximum_swap {
    public int maximumSwap1(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int len = chars.length;

        int max = num;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                swap(chars, i, j);
                max = Math.max(max, Integer.parseInt(new String(chars)));
                swap(chars, i, j);
            }
        }
        return max;
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - '0'] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            for (int digit = 9; digit > chars[i] - '0'; digit--) {
                if (last[digit] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[last[digit]];
                    chars[last[digit]] = tmp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }
        return num;
    }
}

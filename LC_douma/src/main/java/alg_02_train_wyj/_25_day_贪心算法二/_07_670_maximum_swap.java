package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 16:38
 * @Version 1.0
 */
public class _07_670_maximum_swap {
    public int maximumSwap1(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        int max = num;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(chars, i, j);
                max = Math.max(max, Integer.parseInt(new String(chars)));
                swap(chars, i, j);
            }
        }
        return max;
    }

    // 交换两个元素
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] lastIndex = new int[10];
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            lastIndex[chars[i] - '0'] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int digit = 9; digit > chars[i] - '0'; digit--) {
                if (lastIndex[digit] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[lastIndex[digit]];
                    chars[lastIndex[digit]] = tmp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }
        return num;
    }
}

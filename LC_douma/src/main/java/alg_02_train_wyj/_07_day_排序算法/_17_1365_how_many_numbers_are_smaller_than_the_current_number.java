package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-18 21:31
 * @Version 1.0
 */
public class _17_1365_how_many_numbers_are_smaller_than_the_current_number {
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length;
        int[][] map = new int[n][2];
        for (int i = 0; i < n; i++) {
            map[i][0] = nums[i];
            map[i][1] = i;
        }
        Arrays.sort(map, (o1, o2) -> o1[0] - o2[0]);
        int[] res = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || map[i][0] != map[i - 1][0]) {
                prev = i;
            }
            res[map[i][1]] = prev;
        }
        return res;
    }

    public int[] smallerNumbersThanCurrent3(int[] nums) {
        int n = nums.length;
        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i] == 0 ? 0 : count[nums[i] - 1];
        }
        return res;
    }
}

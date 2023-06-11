package alg_03_leetcode_top_wyj.class_02;

/**
 * @Author Wuyj
 * @DateTime 2023-02-21 23:24
 * @Version 1.0
 */
public class problem_013_romanToInt {
    public int romanToInt(String s) {
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }

        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            res = nums[i] < nums[i + 1] ? res - nums[i] : res + nums[i];
        }
        res += nums[nums.length - 1];
        return res;
    }
}

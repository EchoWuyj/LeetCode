package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-15 13:22
 * @Version 1.0
 */

// 罗马数字转整数
public class problem_013_romanToInt {
    public int romanToInt(String s) {

         /*
            输入:s="LVIII"
            输出:58
            解释:L=50,V=5,III=3.
         */

        // 罗马数字,后面数字-前面数字,则前面字符只有1位
        // 假设罗马数字序列 a b c d e f g
        // 若a<b,则为-a,否则为a,像这样一次遍历即可

        // 建立映射关系:LVIII -> 50 5 3
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            // LVIII
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    // KeyPoint break不能忘记
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

        // nums[] = {50,5,3}
        int sum = 0;
        // 根据前后的大小关系选择加还是减
        // 数组中前一个元素和后一个元素比较,for循环的写法
        //  1)使用nums.length-1避免nums[i + 1]数组越界
        //  2)nums.length-1=3-12 ,而i<2则i只能取到 1
        //  3)故最后需要再加上一个nums[nums.length -1],否则遗漏一个元素
        for (int i = 0; i < nums.length - 1; i++) {
            // 前面比后面要小,则是相减的关系
            // 例如:IV对应 nums[]= {1,5}
            if (nums[i] < nums[i + 1]) {
                sum -= nums[i];
            } else {
                sum += nums[i];
            }
            // 优化:res = nums[i] < nums[i + 1] ? res - nums[i] : res + nums[i];
        }
        return sum + nums[nums.length - 1];
    }
}

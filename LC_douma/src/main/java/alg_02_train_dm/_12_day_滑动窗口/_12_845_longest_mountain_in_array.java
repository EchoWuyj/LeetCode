package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 17:01
 * @Version 1.0
 */
public class _12_845_longest_mountain_in_array {
       /*
       
          845. 数组中的最长山脉
          我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
    
          B.length >= 3
          存在 0 < i < B.length - 1
          使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
          (注意：B 可以是 A 的任意子数组，包括整个数组 A。)

          KeyPoint 山脉一定是满足:元素值'先升后降'
    
          给出一个整数数组 A，返回最长 “山脉” 的长度。
    
          如果不含有 “山脉” 则返回 0。
    
          示例 1：
          输入：[2,1,4,7,3,2,5]
          输出：5
          解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
    
          示例 2：
          输入：[2,2,2]
          输出：0
          解释：不含 “山脉”。

          提示：
          0 <= A.length <= 10000
          0 <= A[i] <= 10000

     */

    // 思路：一个数组中，可能存在多个山脉数组，故可以将每个山脉看成一个窗口，且需要找到最长的一个窗口
    public int longestMountain(int[] arr) {
        if (arr == null) return 0;
        int n = arr.length;
        int ans = 0;
        int left = 0;
        // 通过 left + 2 判断滑动窗口是不是结束了
        // 其中 left 最大为 n-3，当 left = n-2，right = n-1，就构不成山脉数组
        while (left + 2 < n) {
            // right 从 left 后面一个位置判断
            int right = left + 1;
            // [left] < [right] 有可能有山脉
            if (arr[left] < arr[right]) {
                // 找到最高点
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    right++;
                }
                // while 循环结束，right 为最高点，若 [right] == [right+1]，则该窗口不是山脉数组
                // 因此，还需要再去判断 [right] 和 [right+1]
                if (right + 1 < n && arr[right] > arr[right + 1]) {
                    // 找到最低点
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        right++;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    // [right] == [right+1]，right 前移
                    right++;
                }
            }
            // "不存在山脉数组" 或者 "下轮山脉数组" 情况，都是将 left 移动 right 位置，接着判断
            left = right;
        }
        return ans;
    }
}

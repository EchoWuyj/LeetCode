package alg_02_train_dm._21_day_综合应用二;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-08-02 13:30
 * @Version 1.0
 */
public class _02_219_contains_duplicate_ii2 {

    // KeyPoint 方法三 滑动窗口
    // i 和 j 的差的 绝对值 至多为 k => i 和 j 位置关系限制在一个区间
    // => 联想滑动窗口
    // Math.abs(i-j) <= k，i 到 j 一共是 k+1 个元素
    // 使用窗口维护 k+1 个元素的即可，不需要使用哈希表维护 n 个元素 => 节省空间
    // 时间复杂度：O(n*min(n, k)) => 注意：k 有可能大于 n
    // 空间复杂度：O(1)
    public boolean containsNearbyDuplicate3(int[] nums, int k) {

        // nums：2 4 5 3 6 7 6 8
        //       ↑     ↑
        //      left  right

        if (nums == null) return false;
        int left = 0, right = 0;
        int n = nums.length;
        while (right < n) {
            // 取 max，保证 left 不会越界
            left = Math.max(0, right - k);
            // KeyPoint 窗口内 => 线性查找 => 本题中非常消耗时间
            // 多次循环移动 left，left 最后移动到 right -1 位置
            while (left < right) {
                if (nums[left] == nums[right]) return true;
                left++;
                // 执行 left = Math.max(0, right - k) 后 left 又往回退
            }
            // right 指针，每次都是需要右移的
            right++;
        }
        return false;
    }

    // KeyPoint 方法四 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyDuplicate4(int[] nums, int k) {
        if (nums == null) return false;
        int left = 0, right = 0;
        Set<Integer> window = new HashSet<>();
        int n = nums.length;
        while (right < n) {
            // 窗口内 => 哈希查找
            if (window.contains(nums[right])) return true;
            // 右侧新遍历到元素，将其加入窗口中
            window.add(nums[right]);
            // window 在 add 元素之后，窗口大小已经为 k+1，还没有找到相同元素
            // 将窗口左侧最久的元素，将其从窗口中移除
            if (window.size() >= k + 1) {
                window.remove(nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }
}

package alg_02_train_dm._08_day_二分查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 19:45
 * @Version 1.0
 */
public class _09_new_start_278_first_bad_version extends VersionControl {

    /*
        278. 第一个错误的版本
        你是产品经理，目前正在带领一个团队开发新的产品。
        不幸的是，你的产品的最新版本没有通过质量检测。
        由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

        假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
        你可以通过调用  bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
        实现一个函数来查找第一个错误的版本。
        你应该尽量减少对调用 API 的次数。
        
        
        示例 1：
        输入：n = 5, bad = 4
        输出：4
        解释：
        调用 isBadVersion(3) -> false 
        调用 isBadVersion(5) -> true
        调用 isBadVersion(4) -> true
        所以，4 是第一个错误的版本。

        KeyPoint 分析
        1 2 3 4 5 6 7 8，若从 6 开始往后都是错误版本，将 7 和 8都看成 6
        1 2 3 4 5 6 6 6 => 在一个升序的数组中，找到第一个等于 6 的位置
     */

    // KeyPoint 方法一 目标找第一错误版本
    //                => 思路一：不断在循环体内找到 第一错误版本
    public int firstBadVersion1(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                // 确保是第一个错误版本
                if (mid == 1 || !isBadVersion(mid - 1)) return mid;
                    // mid 之前存在错误版本
                else right = mid - 1;
            } else {
                // mid 不为 isBadVersion，往右找
                left = mid + 1;
            }
        }
        return -1;
    }

    // KeyPoint 方法二 目标找第一错误版本
    //                 => 思路二：不断在循环体内排除 '不存第一错误版本的区间'
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //'找第一' => 保证 right 从右往左向 mid 移动
            // mid 是错误版本，但不一定是第一个错误版本，right 移动到 mid 还需要继续判断
            if (!isBadVersion(mid)) {
                // 不是错误版本的区间，直接排除
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 最后 left 索引是需要 isBadVersion 判断 ，而不是直接返回 left
        return isBadVersion(left) ? left : -1;
    }
}

class VersionControl {
    // 来判断版本号 version 是否在单元测试中出错。
    boolean isBadVersion(int version) {
        // 这里面的逻辑：随机的返回 true 和 false
        return true;
    }
}

package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-07-02 16:30
 * @Version 1.0
 */
class _08_new_start_278_first_bad_version extends VersionControl {

    public int firstBadVersion1(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                if (mid == 1 || !isBadVersion(mid - 1)) return mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int firstBadVersion2(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return isBadVersion(left) ? left : -1;
    }
}

class VersionControl {
    // 来判断版本号 version 是否在单元测试中出错。
    boolean isBadVersion(int version) {
        // 这里面的逻辑是随机的返回 true 和 false
        return true;
    }
}


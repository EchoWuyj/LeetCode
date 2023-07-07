package alg_02_train_wyj._09_day_哈希查找;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 15:49
 * @Version 1.0
 */
public class _04_136_single_number {
    public int singleNumber1(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean isExist = true;
            for (int j = 0; j < n; j++) {
                if (i != j && nums[i] == nums[j]) {
                    isExist = false;
                    break;
                }
            }
            if (isExist) return nums[i];
        }
        return -1;
    }

    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i == 0 && nums[i] != nums[i + 1]) return nums[0];
            else if (i == n - 1 && nums[i] != nums[i - 1]) return nums[n - 1];
            else if (i > 0 && nums[i] != nums[i - 1] && i + 1 < n && nums[i] != nums[i + 1]) return nums[i];
        }
        return -1;
    }

    public int singleNumber3(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}

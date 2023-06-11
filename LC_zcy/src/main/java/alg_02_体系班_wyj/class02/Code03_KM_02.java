package alg_02_体系班_wyj.class02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 23:53
 * @Version 1.0
 */
public class Code03_KM_02 {

    public static int test(int[] arr, int k, int m) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }

        return -1;
    }

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static int onlyKTimesNum(int[] arr, int k, int m) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        if (map.size() == 0) {
            createMap(map);
        }

        int[] temp = new int[32];
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & (~num + 1);
                temp[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (temp[i] % m != 0) {
                if (temp[i] % m == k) {
                    ans |= (1 << i);
                } else {
                    return -1;
                }
            }
        }

        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0)
                    count++;
            }

            if (count != k) {
                return -1;
            }
        }

        return ans;
    }

    private static void createMap(HashMap<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }

    // for test
    public static int[] randomArray(int maxKinds, int range, int k, int m) {

        int ktimeNum = randomNumber(range);

        // 真命天子出现的次数
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);

        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数,我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // for test
    // [-range, +range]
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    // for test
    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            // (int) (Math.random() * max) 0~8
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }

            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimesNum(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}

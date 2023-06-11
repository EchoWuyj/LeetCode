package alg_02_体系班_zcy.class02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-09-13 23:32
 * @Version 1.0
 */
public class Code03_KM_02 {

    // 经典解:不同思路下的方法
    public static int test(int[] arr, int k, int m) {
        // 使用方法内的map
        HashMap<Integer, Integer> map = new HashMap<>();
        // 建立词频
        for (int num : arr) {
            // 包含情况
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

    // 定义一个全局的map,方便不同的方法可以共用
    public static HashMap<Integer, Integer> map = new HashMap<>();

    // 在一个arr中,若有一种数出现了K次,
    // 其他数都出现了M次,则将出现K次的数返回,没有则返回-1
    public static int onlyKTimes(int[] arr, int k, int m) {
        // 初始化之后,map就没有再变化了
        if (map.size() == 0) {
            mapCreater(map);
        }

        // 准备一个辅助固定长度32位数组,额外的空间复杂度为O(1)
        int[] t = new int[32];

        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            // KeyPoint
            while (num != 0) {
                // rightOne得到的10进制数值,还需要转成2进制位的位置
                int rightOne = num & (-num);
                // 由对应的rightOne的1所在的位置的值转成对应的0,1位
                t[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }

        // ans设置为0,为了通过异或来填答案
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 在第i位上是有1
            if (t[i] % m != 0) {
                // 保证是K次出现
                if (t[i] % m == k) {
                    // 将对应i位设置为1,只能使用|,有1则1
                    ans |= (1 << i);
                } else {
                    // 没有的情况
                    return -1;
                }
            }
        }

        // KeyPoint 特殊边界值处理
        // 处理实际出现的次数不是K次的数,但是这个数恰好是0的情况
        // 因为如果该数是0,则 if (t[i] % m != 0) 是不会成立的
        // 所以ans还是0,但是此时应该返回-1,因为实际出现的次数!=k次
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            // 使用for计算0真实出现的次数是否为k
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    // 初始化操作返回值为void
    public static void mapCreater(HashMap<Integer, Integer> map) {
        // 定义一个[2^i,i]结构
        int value = 1;
        // <32,不是<31,边界的判断特别小心
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
            int ans2 = onlyKTimes(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}

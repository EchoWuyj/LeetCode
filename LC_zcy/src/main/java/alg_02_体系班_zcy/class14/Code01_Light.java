package alg_02_体系班_zcy.class14;

import java.util.HashSet;

public class Code01_Light {

    // 给定一个字符串str,只由'X'和'.'两种字符构成
    // 'X'表示墙,一定不能放灯,可点亮,也可不点亮
    // '.'表示居民点,可以放灯,也可不放灯,一定需要点亮
    // 如果灯放在i位置,可以让i-1,i和i+1三个位置被点亮
    // 返回如果点亮str中所有需要点亮的位置,至少需要几盏灯?

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置,自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了,那些放了灯的位置,存在lights里
    // 要求选出能照亮所有.的方案,并且在这些有效的方案中,返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    // 思路:
    // 假设来到i位置, 从左往右依次考虑每个位置放灯与否?
    // 1) X
    //    i 不用放灯,直接去i+1位置考虑
    // 2) .      X
    //    i(灯) i+1  灯+1,从i+2位置考虑
    // 3) .   .      X
    //    i i+1(灯) i+2 灯在i或(i+1),灯+1,从i+3位置考虑
    // 4) .   .      .
    //    i i+1(灯) i+2 灯必在i+1(本身就是贪心的体现),灯+1,从i+3位置考虑

    // 方法二(贪心) 时间复杂度O(N)
    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < str.length) {
            // 不要放灯,直接跳过
            if (str[i] == 'X') {
                i++;
            } else {
                // 后续分支中,灯的数量都加1
                light++;
                // 不一定有i+1位置,没有直接break;
                if (i + 1 == str.length) {
                    break;
                    // 有i+1位置,i+1位置是X或者.
                } else {
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}

package alg_02_体系班_zcy.class14;

import java.util.Arrays;
import java.util.Comparator;

public class Code03_BestArrange {

    // 一些项目要占用一个会议室宣讲,会议室不能同时容纳两个项目的宣讲,
    // 给你每一个项目开始的时间和结束的时间,你来安排宣讲的日程
    // 要求会议室进行的宣讲的场次最多,返回最多的宣讲场次
    
    // 思路:
    // 1)如果按照开始时间最早,不成立:一个项目从开开会到结束
    // 2)如果按照会议时间最短,不成立:一个项目时间最短,但是恰巧卡到其它项目的开始和结束时间
    // 3)最佳思路:按照结束时间的早晚。找出最早结束的项目,然后过滤掉因该项目而不能举办的项目,依次循环

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 方法一:暴力！所有情况都尝试！
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     * 目前来到timeLine的时间点,已经安排了done多的会议,剩下的会议programs可以自由安排
     *
     * @param programs 还剩下的会议都放在programs里
     * @param done     之前已经安排了多少会议的数量
     * @param timeLine 目前来到的时间点是什么
     * @return 返回能安排的最多会议数量
     */
    public static int process(Program[] programs, int done, int timeLine) {
        // 一个会议都不剩下,之前安排的已经确定的了,不会发生变化了
        if (programs.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会,每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                // 相下递归
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    // programs中将i号会议删除
    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    // 方法二(递归实现)
    // 会议的开始时间和结束时间,都是数值,不会<0
    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        // 依次遍历每一个会议,结束时间早的会议先遍历
        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start) {
                result++;
                // 更新时间线
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            // 按照结束时间排序
            return o1.end - o2.end;
        }
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

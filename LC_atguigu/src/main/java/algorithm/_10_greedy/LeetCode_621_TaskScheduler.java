package algorithm._10_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-03-26 16:37
 * @Version 1.0
 */
public class LeetCode_621_TaskScheduler {
    //方法一:模拟法
    public int leastInterval01(char[] tasks, int n) {
        //用HashMap统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            //每遇到一个task都需要将其数量加1,如果没有则使用默认值为0
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }

        //任务种类数量
        int t = countMap.size();

        //KeyPoint 补充:
        // HashMap不保证插入顺序,但是循环遍历时,输出顺序是不会改变的
        // nCopies(int, T) 方法用于返回一个不可变列表组成的n个拷贝的指定对象

        //定义两个状态列表,这两个状态列表是去处HashMap中的key,即不管该任务叫什么
        //通过编号来确定,同时,这两个列表的编号是一一对应的

        //任务剩余次数
        ArrayList<Integer> restCount = new ArrayList<>(countMap.values());

        //每个任务下次可执行时间,刚开始每个任务没有冷却时间,所有任务都可以在第一时间单位执行,所以都给1即可
        ArrayList<Integer> nextAvailableTime = new ArrayList<>(Collections.nCopies(t, 1));

        //模拟CPU时钟
        int time = 0;

        //遍历任务选择执行
        for (int i = 0; i < tasks.length; i++) {
            //进来先推进时间
            time++;

            //定义变量用来存储可执行的最小时间
            int minNextAvailableTime = Integer.MAX_VALUE;

            //1.获取所有任务中最早可执行的时间
            //通过优化直接跳到能够执行的时间点,中间冷却时间不用等待了
            for (int j = 0; j < t; j++) {
                //取还没有做完的任务,
                if (restCount.get(j) != 0) {
                    minNextAvailableTime = Math.min(minNextAvailableTime, nextAvailableTime.get(j));
                }
            }

            //2.直接推进时间,执行任务
            time = Math.max(time, minNextAvailableTime);

            //3.选取可执行任务中,剩余次数最多的那个执行

            //保存要执行任务的索引
            int maxRestCountTask = -1;

            for (int j = 0; j < t; j++) {
                //
                if (restCount.get(j) > 0 && nextAvailableTime.get(j) <= time) {
                    //如果比之前保存的最大剩余任务数还大,就更新
                    if (maxRestCountTask == -1 || restCount.get(j) > restCount.get(maxRestCountTask)) {
                        maxRestCountTask = j;
                    }
                }
            }

            //4.执行任务,更新状态列表

        }

        return -1;
    }
}

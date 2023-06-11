package alg_01_ds_dm._05_application._01_system_design._1244_leaderboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 18:13
 * @Version 1.0
 */

public class _1244_Leaderboard {

    // KeyPoint
    //  1 若使用面向对象的思想解决，从题目描述中找名词和动词，将名词对应类，动词对应方法
    //  2 结合存储数据格式(map)，选择相应的数据结构存储，需要保证性能很好

    // key -> playerId
    // value -> score
    private Map<Integer, Integer> map;

    public _1244_Leaderboard() {
        this.map = new HashMap<>();
    }

    /**
     * 记录每个参赛者的分数，处理逻辑:
     * 1 假如参赛者已经在排行榜中，就给他的当前得分增加 score 分值并更新排行
     * 2 假如参赛者不在排行榜中，就把他添加到排行榜，并将分数设置为 score
     *
     * @param playerId 参赛者 Id，唯一标识一个参赛者
     * @param score    本次参赛者得分
     */
    public void addScore(int playerId, int score) { // O(1)
        if (map.containsKey(playerId)) {
            map.put(playerId, map.get(playerId) + score);
        } else {
            map.put(playerId, score);
        }
    }

    // 返回前 k 名参赛者的得分总和
    public int top(int k) {
        // 1. 按照分数降序排序
        // map.values() 返回值为 Collection，toArray 将其转成数组，里面还要传入相应数据类型
        Integer[] scores = map.values().toArray(new Integer[map.values().size()]);
        // 降序排列 O(nlogn) -> 性能瓶颈
        // KeyPoint 优化：没有必要在 top 操作，对整个数据进行排序，需要 TreeSet
        //               而是在 addScore 操作时，直接维护好顺序
        //          top 操作，直接使用维护好的数据，取值即可

        // 使用 Arrays.sort 对数组进行排序
        // 使用 Collections.sort 对集合进行排序
        Arrays.sort(scores, (o1, o2) -> o2 - o1);

        // 2. 拿到前 k 名的分数，累加
        // O(k)
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += scores[i];
        }
        return sum;
    }

    // 将指定参赛者的成绩清零
    public void reset(int playerId) { // O(1)
        if (map.containsKey(playerId)) {
            map.remove(playerId);
        }
    }

    public static void main(String[] args) {
        _1244_Leaderboard leaderboard = new _1244_Leaderboard();
        leaderboard.addScore(1, 20);
        leaderboard.addScore(2, 30);
        leaderboard.addScore(3, 16);
        leaderboard.addScore(4, 44);

        System.out.println(leaderboard.top(2)); // 74

        leaderboard.addScore(2, 34);
        leaderboard.addScore(3, 23);

        System.out.println(leaderboard.top(1)); // 64

        leaderboard.reset(2);
        leaderboard.reset(4);

        System.out.println(leaderboard.top(1)); // 39
    }
}

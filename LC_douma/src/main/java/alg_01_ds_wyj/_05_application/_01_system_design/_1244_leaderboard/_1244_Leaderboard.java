package alg_01_ds_wyj._05_application._01_system_design._1244_leaderboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 22:01
 * @Version 1.0
 */
public class _1244_Leaderboard {

    private Map<Integer, Integer> map;

    public _1244_Leaderboard() {
        map = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        if (map.containsKey(playerId)) {
            map.put(playerId, map.get(playerId) + score);
        } else {
            map.put(playerId, score);
        }
    }

    public int top(int k) {
        Integer[] scores = map.values().toArray(new Integer[map.values().size()]);
        Arrays.sort(scores, (o1, o2) -> o2 - o1);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += scores[i];
        }
        return sum;
    }

    public void reset(int playerId) {
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

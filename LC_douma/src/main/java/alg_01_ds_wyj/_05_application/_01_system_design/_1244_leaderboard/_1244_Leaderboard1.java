package alg_01_ds_wyj._05_application._01_system_design._1244_leaderboard;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 22:20
 * @Version 1.0
 */
public class _1244_Leaderboard1 {
    class Player {
        private int id;
        private int scores;

        public Player(int id, int scores) {
            this.id = id;
            this.scores = scores;
        }

        public int getId() {
            return id;
        }

        public int getScores() {
            return scores;
        }

        public void setScores(int scores) {
            this.scores = scores;
        }
    }

    private Map<Integer, Player> map;
    private TreeSet<Player> set;

    public _1244_Leaderboard1() {
        map = new HashMap<>();
        set = new TreeSet<>((p1, p2) -> p1.scores == p2.scores ?
                p1.id - p2.id : p2.scores - p1.scores);
    }

    public void addScore(int playerId, int score) {
        Player player = null;
        if (map.containsKey(playerId)) {
            player = map.get(playerId);
            set.remove(player);
            player.setScores(player.getScores() + score);
        } else {
            player = new Player(playerId, score);
            map.put(playerId, player);
        }
        set.add(player);
    }

    public int top(int k) {
        Iterator<Player> it = set.iterator();
        int sum = 0;
        while (it.hasNext() && k > 0) {
            sum += it.next().getScores();
            k--;
        }
        return sum;
    }

    public void reset(int playerId) {
        if (map.containsKey(playerId)) {
            set.remove(map.get(playerId));
            map.remove(playerId);
        }
    }

    public static void main(String[] args) {
        _1244_Leaderboard1 leaderboard = new _1244_Leaderboard1();
        leaderboard.addScore(1, 20);
        leaderboard.addScore(2, 30);
        leaderboard.addScore(3, 16);
        leaderboard.addScore(4, 44);

        System.out.println(leaderboard.top(2));  // 74

        leaderboard.addScore(2, 34);
        leaderboard.addScore(3, 23);

        System.out.println(leaderboard.top(1)); // 64

        leaderboard.reset(2);
        leaderboard.reset(4);

        System.out.println(leaderboard.top(1)); // 39
    }
}

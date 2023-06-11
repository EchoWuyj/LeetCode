package alg_01_ds_dm._05_application._01_system_design._1244_leaderboard;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 18:30
 * @Version 1.0
 */

public class _1244_Leaderboard1 {

    // KeyPoint 创建内部类 => 因为需要在 addScore 中利用 TreeSet，对数据进行排序，
    //              原来映射 Map<Integer, Integer> 关系，不方便存到 TreeSet 中
    //              故将其封装成 Player
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

    // 通过 Integer -> Player 所以还是使用 map 存储(HashMap 是无序存储)
    private Map<Integer, Player> map;
    // TreeSet 维持数据 Player 有序，时间复杂度是 O(logn)
    private TreeSet<Player> players;

    public _1244_Leaderboard1() {
        this.map = new HashMap<>();
        // KeyPoint 初始化过程定义好比较的规则
        players = new TreeSet<>((o1, o2) -> {
            // 排列规则：分数相等，按照 id 升序排列；分数不相等，按照分数降序排列
            //           => 避免分数相同，不存数据的情况
            return o1.getScores() == o2.getScores() ?
                    o1.getId() - o2.getId() :
                    o2.getScores() - o1.getScores();
        });

        // KeyPoint 更加简洁的形式
//        set = new TreeSet<>((p1, p2) -> p1.scores == p2.scores ?
//                p1.id - p2.id : p2.scores - p1.scores);
    }

    public void addScore(int playerId, int score) { // O(logn)
        Player player = null;
        if (map.containsKey(playerId)) {
            player = map.get(playerId);
            // KeyPoint：需要先从 TreeSet 中删除，等重新计算完 score 后再添加到 TreeSet 中
            //           因为这个 player 需要重新排序，而只有调用 TreeSet 的 add 方法，才能重新排序，否则顺序有问题
            players.remove(player); // O(logn)
            player.setScores(player.getScores() + score);
        } else {
            player = new Player(playerId, score);
            map.put(playerId, player);
        }
        // KeyPoint if else 中重复代码抽取出来，放在外边，因而变量 players 也是得抽取的
        players.add(player); // O(logn)
    }

    public int top(int k) { // O(k)
        // it 已经是有序的，不需要再去排序，通过 addScore 操作，从而降低了 top 操作的时间复杂度
        Iterator<Player> it = players.iterator();
        int sum = 0;
        // O(k)
        while (it.hasNext() && k > 0) {
            // it.next() 是 it 中存储的元素，即为 Player
            sum += it.next().getScores();
            k--;
        }
        return sum;
    }

    public void reset(int playerId) { // O(logn)
        if (map.containsKey(playerId)) {
            // 维护 TreeSet 和 Map
            players.remove(map.get(playerId));
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

        // Summary
        //  1 通过分摊方式提高某个操作的性能，一个类中查询操作 top 很费时间，需要 O(nlogn) 时间复杂度
        //    可以尝试通过其他操作 addScore 进行分摊，从而降低时间复杂度
        //  2 通过空间换时间，降低时间复杂度，提高性能
        //    => 这是降低时间复杂度思考的方向，养成这样的习惯
        //  3 类中定义的数据结构 Map 和 TreeSet 在进行增删改时，需要对结构的维护
    }
}

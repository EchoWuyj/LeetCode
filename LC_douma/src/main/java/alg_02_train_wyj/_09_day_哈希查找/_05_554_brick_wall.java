package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 19:43
 * @Version 1.0
 */
public class _05_554_brick_wall {
    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int size = wall.size();
        int maxCnt = 0;
        for (int i = 0; i < size; i++) {
            int edgePos = 0;
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                int curBrickLen = wall.get(i).get(j);
                edgePos += curBrickLen;
                map.put(edgePos, map.getOrDefault(edgePos, 0) + 1);
                maxCnt = Math.max(maxCnt, map.get(edgePos));
            }
        }
        return wall.size() - maxCnt;
    }
}

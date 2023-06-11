package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 19:43
 * @Version 1.0
 */
public class _05_554_brick_wall {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edgeFreq = new HashMap<>();
        int maxFreq = 0;
        for (int row = 0; row < wall.size(); row++) {
            int edgePosition = 0;
            for (int col = 0; col < wall.get(row).size() - 1; col++) {
                int curBrickLength = wall.get(row).get(col);
                edgePosition += curBrickLength;
                edgeFreq.put(edgePosition, edgeFreq.getOrDefault(edgePosition, 0) + 1);
                maxFreq = Math.max(maxFreq, edgeFreq.get(edgePosition));
            }
        }
        return wall.size() - maxFreq;
    }
}

package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 23:57
 * @Version 1.0
 */
public class _05_112_PathSum_Note {

    // KeyPoint 复用同一个 ArrayList 和 new ArrayList 区别
    public static void test1() {
        // 1.复用同一个 ArrayList
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            path.add(i);
            res.add(path);
        }
        System.out.println(res);
        // 因为每次都是复用同一个 ArrayList，导致最开始阶段性的结果被覆盖，都变成最终结果
        // [[0, 1, 2], [0, 1, 2], [0, 1, 2]
    }

    public static void test2() {
        // 2.new ArrayList
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            path.add(i);
            res.add(new ArrayList<>(path));
        }
        System.out.println(res);
        // 因为每次都是创建一个新的 ArrayList，每次 add 添加的结果相对独立，不收后面 add 的影响
        // [[0], [0, 1], [0, 1, 2]]
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}

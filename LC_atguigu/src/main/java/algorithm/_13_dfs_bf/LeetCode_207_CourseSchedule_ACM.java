package algorithm._13_dfs_bf;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-11-14 18:38
 * @Version 1.0
 */
public class LeetCode_207_CourseSchedule_ACM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int num = Integer.parseInt(in.nextLine());
            ArrayList<int[]> list = new ArrayList<>();

            // 输入 输出 处理
            String line = in.nextLine();
            int lineLen = line.length();
            if (lineLen == 3) {
                String[] innerStr = line.split(",");
                int num1 = Integer.parseInt(innerStr[0]);
                int num2 = Integer.parseInt(innerStr[1]);
                int[] tmp = {num1, num2};
                list.add(tmp);
            } else {
                String[] strs = line.split(" ");
                int len = strs.length;
                for (int i = 0; i < len; i++) {
                    String[] innerStr = strs[i].split(",");
                    int num1 = Integer.parseInt(innerStr[0]);
                    int num2 = Integer.parseInt(innerStr[1]);
                    int[] tmp = {num1, num2};
                    list.add(tmp);
                }
            }
            int[][] input = list.toArray(new int[list.size()][]);
            boolean res = method(num, input);
            System.out.println(res);
        }
    }

    public static boolean method(int num, int[][] input) {
        int[] in = new int[num];
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            in[input[i][0]]++;
            ArrayList<Integer> list =
                    map.getOrDefault(input[i][0], new ArrayList<Integer>());
            list.add(input[i][0]);
            map.put(input[i][1], list);
        }

        int finish = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < num; i++) {
            if (in[i] == 0) {
                queue.offer(in[i]);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            finish++;
            for (int next : map.getOrDefault(course, new ArrayList<Integer>())) {
                in[next]--;
                if (in[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return num == finish;
    }
}

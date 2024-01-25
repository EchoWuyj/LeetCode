package algorithm_wyj;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-10-03 16:47
 * @Version 1.0
 */
public class LeetCode_207_CourseSchedule {
    public boolean canFinish01(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();


        for (int[] pre : prerequisites) {
            in[pre[0]]++;
            ArrayList<Integer> followUp =
                    map.getOrDefault(pre[1], new ArrayList<>());
            followUp.add(pre[0]);
            map.put(pre[1], followUp);
        }

        int finished = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            finished++;
            for (int followUpCourse : map.getOrDefault(course, new ArrayList<>())) {
                in[followUpCourse]--;
                if (in[followUpCourse] == 0) {
                    queue.add(followUpCourse);
                }
            }
        }
        return finished == numCourses;
    }
}

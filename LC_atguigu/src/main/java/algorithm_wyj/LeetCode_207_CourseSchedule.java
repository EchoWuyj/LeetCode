package algorithm_wyj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-10-03 16:47
 * @Version 1.0
 */
public class LeetCode_207_CourseSchedule {
    public boolean canFinish01(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
            ArrayList<Integer> followUp = map.getOrDefault(prerequisite[1], new ArrayList<>());
            followUp.add(prerequisite[0]);
            map.put(prerequisite[1], followUp);
        }

        int finished = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            finished++;
            for (Integer followUpCourse : map.getOrDefault(course, new ArrayList<>())) {
                inDegrees[followUpCourse]--;
                if (inDegrees[followUpCourse] == 0) {
                    queue.offer(followUpCourse);
                }
            }
        }
        return finished == numCourses;
    }
}

package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 15:52
 * @Version 1.0
 */
public class _06_621_task_scheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        int maxCount = 0;
        int maxTask = 0;
        for (char task : tasks) {
            counts[task - 'A']++;
            if (counts[task - 'A'] == maxCount) {
                maxTask++;
            } else if (counts[task - 'A'] > maxCount) {
                maxCount = counts[task - 'A'];
                maxTask = 1;
            }
        }

        int partCount = maxCount - 1;
        int partLen = n - (maxTask - 1);
        int emptySlots = partCount * partLen;
        int availableTasks = tasks.length - maxCount * maxTask;
        int idles = Math.max(0, emptySlots - availableTasks);
        return tasks.length + idles;
    }
}

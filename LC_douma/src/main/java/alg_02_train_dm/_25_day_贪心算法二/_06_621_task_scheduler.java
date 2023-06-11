package alg_02_train_dm._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 14:06
 * @Version 1.0
 */
public class _06_621_task_scheduler {
      /* 
        621. 任务调度器
        给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。
        其中每个字母表示一种不同种类的任务。
        任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
        在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
        然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
        因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
        你需要计算完成所有任务所需要的 最短时间 。
    
        示例 1：
        输入：tasks = ["A","A","A","B","B","B"], n = 2
        输出：8
        解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
             在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，
             而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
    
        示例 2：
        输入：tasks = ["A","A","A","B","B","B"], n = 0
        输出：6
        解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
        ["A","A","A","B","B","B"]
        ["A","B","A","B","A","B"]
        ["B","B","B","A","A","A"]
        ...
        诸如此类
    
        示例 3：
        输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
        输出：16
        解释：一种可能的解决方案是：
             A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命)
             -> (待命) -> A -> (待命) -> (待命) -> A
             
        提示：
        1 <= task.length <= 10^4
        tasks[i] 是大写英文字母
        n 的取值范围为 [0, 100]

        KeyPoint 代码调用过程，从一般情况到特殊情况，不断去完善，
                 而不是一步到位，直接写出 bugFree 的代码(除非你是大佬)

     */

    // 贪心策略：每次先安排出现次数最多的任务 => 本题和重构字符串有点类似
    // 完成所有任务所需要的'最短时间' = 待命的任务数 + 所有的任务数
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        // 出现次数最多任务出现的'次数'
        int maxCount = 0;
        // 出现次数最多的'任务数'
        int maxTask = 0;
        for (char task : tasks) {
            count[task - 'A']++;
            // 对每个计数后字符进行判断
            if (count[task - 'A'] == maxCount) {
                maxTask++;
            } else if (count[task - 'A'] > maxCount) {
                maxCount = count[task - 'A'];
                maxTask = 1;
            } // count[task - 'A'] < maxCount 则什么都做
        }

        // KeyPoint 关键计算:待命的任务数，而不用纠结具体任务分配
        int partCount = maxCount - 1;
        // 出现次数最多任务有多个:'AAA'，'BBB'，'CCC'
        int partLength = n - (maxTask - 1);

        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - maxCount * maxTask;
        // 若:'出现次数最多任务的个数' > '冷却时间'
        // => 并不需要待命的任务就能将任务安排完，任务列表的长度即为'最短时间'
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}

package alg_02_train_dm._25_day_贪心算法二_二刷;

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
        然而，两个相同种类 的任务之间必须有长度为整数 n 的冷却时间，
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

        KeyPoint 代码经验
        代码调用过程，从一般情况到特殊情况，不断去完善，
        而不是一步到位直接写出 bugFree 的代码(除非你是大佬)

     */

    // KeyPoint 本题和重构字符串有点类似
    // 贪心策略：每次先安排出现次数最多的任务，后续其他任务依次插入冷却间隔中
    //          完成所有任务所需要的'最短时间' = 待命的任务数 + 所有的任务数
    public int leastInterval(char[] tasks, int n) {

        // index  0 1 2 3 4 5
        // char[] A A A B B C，n = 2

        // index  0 1 2 3 4 5 6 7 8 9 ...  无线数组长度
        //        A B C A B ? A
        //         |--|  |--| ↑
        //                最短时间：7 个时间单位

        // 出现次数最多任务有多个：'AAA','BBB','CCC'
        // partCount = counts(A)-1
        // emptySlots = partCount * partLen
        // availableTasks = tasks.length - maxCount * maxTask
        // idles = Math.max(0,emptySlots - availableTasks)
        // 返回：tasks.length + idles

        int[] counts = new int[26];
        // 出现次数最多任务出现的'次数'
        int maxCount = 0;
        // 出现次数为 maxCount 任务个数
        // 如：maxCount = 3，且 'AAA','BBB','CCC' => maxTask = 3
        int maxTask = 0;
        for (char task : tasks) {
            counts[task - 'A']++;
            // 对每个计数后字符进行判断
            if (counts[task - 'A'] == maxCount) {
                maxTask++;
            } else if (counts[task - 'A'] > maxCount) {
                maxCount = counts[task - 'A'];
                // maxTask 重新赋值为 1
                maxTask = 1;
            } // counts[task-'A'] < maxCount 则什么都做
        }

        int partCount = maxCount - 1;
        // 1.若 maxTask = 1，n 不用减，直接为 n
        // 2.若出现次数最多任务有多个:'AAA','BBB','CCC'，即 maxTask = 3
        //   则 partLen = n-(maxTask-1)
        int partLen = n - (maxTask - 1);
        // 内部空余的位置
        int emptySlots = partCount * partLen;
        // 剩余没有分配的 Task
        int availableTasks = tasks.length - maxCount * maxTask;
        // idles 待命状态[间隙]
        // 边界情况
        // => 出现次数最多任务的个数 maxTask > 冷却时间 n
        // => 此时不需要待命任务[间隙]，就能错开将任务安排完，任务列表的长度即为最短时间
        // KeyPoint 关键计算 => 待命任务数[间隙]，而不用纠结具体任务分配
        int idles = Math.max(0, emptySlots - availableTasks);
        return tasks.length + idles;

        // 如：A A A B B B C C C D D D E E，maxTask = 4，n = 2 => maxTask > n
        // partLen = n-(maxTask-1) = 2-(4-1) = -1
        // emptySlots = partCount * partLen < 0
        // availableTasks =  tasks.length - maxCount * maxTask = 14 - 3*4 = 2 (E,E)
        // emptySlots - availableTasks < 0
        // => A _ _ A _ _ A _ _
        // => A B C A B C A B C  ×
        // => A B C D E A B C D E A B C D  √
        // 此时 idles = 0，即任务数够多，能错开安排，且不需要待命任务数间隙
        // 故任务列表有多长，即为需要的时间，不要待命任务数[间隙]

    }
}

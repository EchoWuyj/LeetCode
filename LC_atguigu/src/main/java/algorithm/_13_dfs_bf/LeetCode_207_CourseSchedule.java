package algorithm._13_dfs_bf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-03-30 20:16
 * @Version 1.0
 */
public class LeetCode_207_CourseSchedule {
    //方法一:BFS
    //将搜索的过程分成不同的层级,考察入度为0的点,将这些点先进行处理,之后再去考察后面其他的点

    //判定修不完的情况:这种情况即返回false,修一门课就记录下,最后如果入度为0的点都找不到了
    //但是当前修完的课程还不够,还有课程没有修完,即返回false
    public boolean canFinish01(int numCourses, int[][] prerequisites) {
        //定义数组保存所有节点入度
        //节点的个数和课程数量保持一致,同时数组的索引对应课程的编号,其值保存表示该课程的入度
        int[] inDegrees = new int[numCourses];

        //保存每个课程的后继课程,即C1->C3,C8
        //定义HashMap存储邻接矩阵
        HashMap<Integer, ArrayList<Integer>> followUpCourses = new HashMap<>();

        //1.遍历先决条件,计算入度和后续节点
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;   //后修课程入度加1,如[1,0]中的1

            //获取先修课程的后续节点列表
            ArrayList<Integer> followUpCourseList = followUpCourses.getOrDefault(prerequisite[1], new ArrayList<>());
            followUpCourseList.add(prerequisite[0]); //后修课程加入列表
            followUpCourses.put(prerequisite[1], followUpCourseList); //更新followUpCourses
        }

        //接下来处理的过程,先扫描所有一遍所有的入度为0节点加入可学习的列表中(队列中),
        //不停地先将入度为0的课程找出来并且学习完再将其删除(出队),同时将其后继课程的入度减1
        //之后再去搜索新的入度为0的课程,加入可学习的列表中(队列)

        //定义队列保存当前可以学习的课程,入度为0的课程
        LinkedList<Integer> selectableCourses = new LinkedList<>();

        //2.启动BFS,将入度为0的所有课程入队
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                selectableCourses.offer(i);
            }
        }

        //用一个变量记录已学过的课程数量
        //一次出队,就将其值加1,表示学完一门课程
        int finishedCourseNum = 0;

        //3.不停地出队(学习课程),将后续课程入度减1,并将新的入度为0的课程入队
        while (!selectableCourses.isEmpty()) {
            int course = selectableCourses.poll(); //出队
            finishedCourseNum++;

            //遍历当前课程的后续课程,入度减1
            //followUpCourses这个HashMap中判断是否还有当前课程的后续课程,并对后续课程的ArrayList集合进行遍历
            //若当前课程没有后续课程的集合,则new一个新的ArrayList,此时for循环直接跳过
            for (Integer followUpCourse : followUpCourses.getOrDefault(course, new ArrayList<>())) {
                //后续课程的入度减1
                inDegrees[followUpCourse]--;
                //如果当前后续课程入度减成1,入队
                if (inDegrees[followUpCourse] == 0) {
                    selectableCourses.offer(followUpCourse);
                }
            }
        }

        //4.判断是否学完所有课程
        //在while循环结束之后,要不就是所有的课程都已经学完了,即finishedCoursesNum==numCourses
        return finishedCourseNum == numCourses;
    }

    //方法二:DFS
    public boolean canFinish02(int numCourses, int[][] prerequisites) {
        //定义HashMap存储邻接矩阵
        HashMap<Integer, ArrayList<Integer>> followUpCourses = new HashMap<>();
        //1.遍历先决条件,计算后续节点
        for (int[] prerequisite : prerequisites) {
            //获取先修课程的后续节点列表
            ArrayList<Integer> followUpCourseList = followUpCourses.getOrDefault(prerequisite[1], new ArrayList<>());
            followUpCourseList.add(prerequisite[0]);    //后修课程加入列表
            followUpCourses.put(prerequisite[1], followUpCourseList);
        }

        //定义一个栈,优先搜索最后要学习的课程
        Stack<Integer> lastCourses = new Stack<>();

        //需要判断是否有环,则需要定义一个数组,保存课程是否在当前搜索路径上出现过
        boolean[] isSearched = new boolean[numCourses];

        //在递归搜索的过程中,当前节点的状态会发生改变,需要定义一个boolean类型的变量
        boolean canFinish = true;

        //2.遍历每一个节点进行DFS
        //&&canFinish含义,遍历过程中只要一次出现false,则停止遍历,即图中一旦出现一个环,则表示该图不成功
        for (int i = 0; i < numCourses && canFinish; i++) {
            //若当前搜索的课程已经进栈,即为最后需要学习的课程,不用再去搜索了,即该出度为0
            //即不用再去搜以该节点为起点,再往后深度优先搜索了
            if (!lastCourses.contains(i)) {
                //不在栈内就搜索,当前搜索自己这个节点,搜索完之后还要继续往深找下一个节点,不停地找下一个节点
                //DFS最好使用一个递归方法,本身这个方法直接递归不合适,在外面重新定义一个递归方法,将相应
                //的参数都传递进去,其返回以当前节点为起点的路径是否有效
                canFinish = dfs(followUpCourses, lastCourses, isSearched, i);
            }
        }
        return canFinish;
    }

    //实现辅助DFS方法
    private boolean dfs(HashMap<Integer, ArrayList<Integer>> followUpCourses,
                        Stack<Integer> lastCourses, boolean[] isSearched, int i) {
        //当前节点在路径中出现
        isSearched[i] = true;

        //遍历所有后续课程,递归调用做深度搜索
        for (int followUpCourse : followUpCourses.getOrDefault(i, new ArrayList<>())) {
            //只要没有出现在当前的路径里面才去往后进行搜索,若后续课程已经出现了,则直接返回false
            if (isSearched[followUpCourse]) {
                return false;
            } else {
                //以当前下一个对应的课程作为起始,继续往下进行搜索,搜索的过程一直返回的结果都是true,
                //则当前为true不用处理,继续进行递归调用.如果中间搜索过程返回false,则当前页应该返回false
                if (!dfs(followUpCourses, lastCourses, isSearched, followUpCourse)) {
                    return false;
                }
            }
        }

        //后续节点处理完毕,当前节点入栈
        lastCourses.push(i);

        //状态回溯:当前节点已经处理完之后,需要将状态返回,当前节点不用判断在路径中出现过
        isSearched[i] = false;

        //当前节点已经入栈了,表示能从头到尾遍历成功,则返回true
        return true;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        int[][] prerequisites2 = {{1, 0}, {0, 1}};

        LeetCode_207_CourseSchedule courseSchedule = new LeetCode_207_CourseSchedule();
        System.out.println(courseSchedule.canFinish02(2, prerequisites));
        System.out.println(courseSchedule.canFinish02(2, prerequisites2));
    }
}

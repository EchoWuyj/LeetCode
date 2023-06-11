package alg_02_体系班_zcy.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-26 15:49
 * @Version 1.0
 */
public class Code04_MaxHappy {

    // 派对的最大快乐值
    // 这个公司现在要办party,你可以决定哪些员工来,哪些员工不来
    // 规则:
    //   1)如果某个员工来了,那么这个员工的所有直接下级都不能来
    //   2)派对的整体快乐值是所有到场员工快乐值的累加
    // 你的目标是让派对的整体快乐值尽量大
    // 给定一棵多叉树的头节点boss,请返回派对的最大快乐值

    public static class Employee {
        // 快乐值
        public int happy;
        // 直接下级
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur,
    // up表示cur的上级是否来,
    // 该函数含义:
    // 如果up为true,表示在cur上级已经确定来,的情况下,cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false,表示在cur上级已经确定不来,的情况下,cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话,cur没得选,只能不来
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话,cur可以选,可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    public static int maxHappy2(Employee head) {
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    // 1)第一种可能性:X结点来(和X有关),假设X有三个直接下级a,b,c
    // 快乐值就是:
    //    X自己的快乐值
    //    a不来情况下整颗子树的快乐值
    //    b不来情况下整颗子树的快乐值
    //    c不来情况下整颗子树的快乐值
    // 2)第二种可能性:X结点不来,假设X有三个直接下级a,b,c
    //  快乐值就是:
    //    X自己的快乐值0
    //    max{a来的情况下整颗树的快乐值,a不来的情况下整棵树的快乐值}
    //    max{b来的情况下整颗树的快乐值,b不来的情况下整棵树的快乐值}
    //    max{c来的情况下整颗树的快乐值,c不来的情况下整棵树的快乐值}
    // => Info
    //    X在,整颗子树的快乐值
    //    X不在,整颗子树的快乐值
    public static class Info {
        public int no;
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        // 不来情况
        int no = 0;
        // 来的情况
        int yes = x.happy;

        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.no, nextInfo.yes);
            yes += nextInfo.no;
        }
        return new Info(no, yes);
    }

    // for test
    public static Employee generateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        generateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void generateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            generateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }


    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = generateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

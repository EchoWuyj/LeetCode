package alg_02_体系班_zcy.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 本题为leetcode原题
// 测试链接:https://leetcode.cn/problems/number-of-islands-ii/
// 所有方法都可以直接通过

public class Code03_NumberOfIslandsII {

    // 假设你设计一个游戏,用一个m行n列的2D网格来存储你的游戏地图
    // 起始的时候,每个格子的地形都被默认标记为「水」
    // 我们可以通过使用addLand进行操作,将位置(row,col)的「水」变成「陆地」
    // 你将会被给定一个列表,来记录所有需要被操作的位置,然后你需要返回计算出来 每次 addLand 操作后岛屿的数量。
    // 注意:一个岛的定义是被「水」包围的「陆地」,通过水平方向或者垂直方向上相邻的陆地连接而成。
    // 你可以假设地图网格的四边均被无边无际的「水」所包围。

    // 示例:
    //  输入: m = 3,n = 3,
    //	positions = [[0,0],[0,1],[1,2],[2,1]]
    //  输出: [1,1,2,3] 每一步的addLand操作之后,全局的岛数量
    //解析:
    //起初，二维网格 grid 被全部注入「水」（0 代表「水」,1 代表「陆地」）
    // 0 0 0
    // 0 0 0
    // 0 0 0

    // 操作 #1:addLand(0,0) 将 grid[0][0] 的水变为陆地。
    // 1 0 0
    // 0 0 0   Number of islands = 1
    // 0 0 0

    // 操作 #2:addLand(0,1) 将 grid[0][1] 的水变为陆地。
    // 1 1 0
    // 0 0 0   岛屿的数量为 1
    // 0 0 0

    // 操作 #3:addLand(1,2) 将 grid[1][2] 的水变为陆地。
    // 1 1 0
    // 0 0 1   岛屿的数量为 2
    // 0 0 0

    // 操作 #4:addLand(2,1) 将 grid[2][1] 的水变为陆地。
    // 1 1 0
    // 0 0 1   岛屿的数量为 3
    // 0 1 0

    // 方法一(并查集)
    // 时间复杂度分析:
    //  1)空降k个1,每个小1都和上下左右相连,并查集操作O(1),总的为O(k)
    //  2)初始化是O(m*n)
    //  3)总的时间复杂度:O(m*n)+O(k)
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        // 记录每步操作的答案
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            // 每步addLand操作设置为1,[a,b] [c,d] [e,f]
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind1 {
        private int[] parent;
        // size[i]=1,合并之后挂在j的底下,size[j]正常变成大,同时size[i]=1不清零
        // 通过这样的方式标记,如果size[i]!=0,表示曾经被初始化过,size[i]=0表示没有被初始化
        // 即通过size[i]是否为0来表示i是否被初始化
        private int[] size;
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind1(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            // 保证不越界
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row
                    || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            // 两个其中有个一个没有初始化,直接返回
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        // 连接的方法:动态建立,动态连接
        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                // 是否能和上下左右连接
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }
    }

    // 课上讲的如果m*n比较大,会经历很重的初始化,而k比较小,怎么优化的方法
    // 即一开始不用准备很大空间,k很少就是动态来,动态加入
    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    //
    public static class UnionFind2 {
        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private ArrayList<String> help;
        private int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        private String find(String cur) {
            while (!cur.equals(parent.get(cur))) {
                help.add(cur);
                cur = parent.get(cur);
            }
            for (String str : help) {
                parent.put(str, cur);
            }
            help.clear();
            return cur;
        }

        private void union(String s1, String s2) {
            if (parent.containsKey(s1) && parent.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    String big = size1 >= size2 ? f1 : f2;
                    String small = big == f1 ? f2 : f1;
                    parent.put(small, big);
                    size.put(big, size1 + size2);
                    sets--;
                }
            }
        }

        public int connect(int r, int c) {
            // 空降到(17,1009)位置,使用"17_1009"代表
            String key = String.valueOf(r) + "_" + String.valueOf(c);
            if (!parent.containsKey(key)) {
                parent.put(key, key);
                size.put(key, 1);
                sets++;
                String up = String.valueOf(r - 1) + "_" + String.valueOf(c);
                String down = String.valueOf(r + 1) + "_" + String.valueOf(c);
                String left = String.valueOf(r) + "_" + String.valueOf(c - 1);
                String right = String.valueOf(r) + "_" + String.valueOf(c + 1);
                union(up, key);
                union(down, key);
                union(left, key);
                union(right, key);
            }
            return sets;
        }
    }
}

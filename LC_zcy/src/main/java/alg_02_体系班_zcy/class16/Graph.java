package alg_02_体系班_zcy.class16;

import java.util.HashMap;
import java.util.HashSet;

// 图的基本概念
// 1)由点的集合和边的集合构成
// 2)虽然存在有向图和无向图的概念,但实际上都可以用有向图来表达
// 3)边上可能带有权值

// 图结构的表达
// 1)邻接表法
// 2)邻接矩阵法
// 3)除此之外还有其他众多的方式

// 图的面试题如何搞定(方法论)
// 图的算法都不算难,只不过coding的代价比较高
// 1)先用自己最熟练的方式,实现图结构的表达
// 2)在自己熟悉的结构上,实现所有常用的图算法作为模板
// 3)把面试题提供的图结构转化为自己熟悉的图结构,再调用模板或改写即可

public class Graph {
    // 点集:数值转Node的映射
    // HashMap中的只能使用Integer,不能使用int
    public HashMap<Integer, Node> nodes;
    // 边集合
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}

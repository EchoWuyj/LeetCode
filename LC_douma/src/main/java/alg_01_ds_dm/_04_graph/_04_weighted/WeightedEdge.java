package alg_01_ds_dm._04_graph._04_weighted;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 16:31
 * @Version 1.0
 */

// 无向有权图的边抽象成 WeightedEdge
public class WeightedEdge implements Comparable<WeightedEdge> {
    private int v;
    private int w;
    private int weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {

        // KeyPoint 升序 / 降序
        //  TreeSet add 方法传入对象，就是 this => 新加入对象的和已经排好序的对象进行比较
        //  1 升序排列 compareTo 中的代码是: int num = this.age - o.age
        //  2 降序排列 compareTo 中的代码是: int num = o.age - this.age

        // 从小到大排序
        return this.weight - o.getWeight();
    }

    @Override
    public String toString() {
        // KeyPoint 字符串看着像多行，其实打印输出就一行
        return " WeightedEdge {" +
                "v = " + v +
                ", w = " + w +
                ", weight = " + weight +
                "} ";
    }
}

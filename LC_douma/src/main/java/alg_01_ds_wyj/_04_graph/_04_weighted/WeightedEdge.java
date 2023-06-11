package alg_01_ds_wyj._04_graph._04_weighted;

import jdk.nashorn.internal.ir.WhileNode;

/**
 * @Author Wuyj
 * @DateTime 2023-03-24 13:55
 * @Version 1.0
 */
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
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return " WeightedEdge {" +
                "v = " + v +
                ", w = " + w +
                ", weigh = " + weight +
                "} ";
    }
}

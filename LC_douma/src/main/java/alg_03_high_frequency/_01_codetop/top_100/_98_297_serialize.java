package alg_03_high_frequency._01_codetop.top_100;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _98_297_serialize {

    // 输入：root = [1,2,3,null,null,4,5]
    // 输出：[1,2,3,null,null,4,5]

    // 序列化
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder res = new StringBuilder();
        dfs_ser(root, res);
        // 删除最后一个 ","
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public void dfs_ser(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append("null,");
            return;
        }
        res.append(root.val).append(",");
        dfs_ser(root.left, res);
        dfs_ser(root.right, res);
    }

    // 反序列化
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] dataArr = data.split(",");
        // 将数组转成集合，集合比较方便操作
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
        return dfs_des(dataList);
    }

    public TreeNode dfs_des(LinkedList<String> dataList) {
        if (dataList.getFirst().equals("null")) {
            dataList.removeFirst();
            return null;
        }

        String value = dataList.getFirst();
        TreeNode node = new TreeNode(Integer.parseInt(value));
        // 创建节点之后，记得将该值进行删除
        dataList.removeFirst();

        TreeNode left = dfs_des(dataList);
        TreeNode right = dfs_des(dataList);

        node.left = left;
        node.right = right;

        // 简化写法
        // node.left = dfs_des(dataList);
        // node.right = dfs_des(dataList);

        return node;
    }
}

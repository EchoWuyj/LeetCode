package alg_02_train_wyj._00_day_准备;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2024-01-18 15:20
 * @Version 1.0
 */
public class Test2 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder res = new StringBuilder();
        dfs_ser(root, res);
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

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] dataArray = data.split(",");
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
        return dfs_deser(dataList);
    }

    public TreeNode dfs_deser(LinkedList<String> dataList) {
        if (dataList.getFirst().equals("null")) {
            dataList.removeFirst();
            return null;
        }

        String value = dataList.getFirst();
        TreeNode root = new TreeNode(Integer.parseInt(value));
        dataList.removeFirst();

        TreeNode left = dfs_deser(dataList);
        TreeNode right = dfs_deser(dataList);

        root.left = left;
        root.right = right;

        return root;
    }
}

package algorithm._13_dfs_bf;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-11-27 14:45
 * @Version 1.0
 */
public class LeetCode_297_TreeSerialization_无注释 {
    public class Codec {

        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            dfs_ser(root, builder);
            builder.deleteCharAt(builder.length() - 1);
            builder.append(']');
            return builder.toString();
        }

        public void dfs_ser(TreeNode root, StringBuilder builder) {
            if (root == null) {
                builder.append("null,");
                return;
            }

            builder.append(root.val).append(',');
            dfs_ser(root.left, builder);
            dfs_ser(root.right, builder);
        }

        public TreeNode deserialize(String data) {
            String[] dataArr = data.split(",");
            LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
            String firstElement = dataList.getFirst().substring(1);
            dataList.removeFirst();
            dataList.addFirst(firstElement);

            String lastElement = dataList.getLast().substring(0, dataList.getLast().length() - 1);
            dataList.removeLast();
            dataList.add(lastElement);

            return dfs_des(dataList);
        }

        public TreeNode dfs_des(LinkedList<String> dataList) {
            if (dataList.getFirst().equals("null")) {
                dataList.removeFirst();
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(dataList.getFirst()));
            dataList.removeFirst();

            node.left = dfs_des(dataList);
            node.right = dfs_des(dataList);

            return node;
        }
    }
}

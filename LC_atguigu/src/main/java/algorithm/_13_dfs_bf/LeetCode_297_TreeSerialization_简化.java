package algorithm._13_dfs_bf;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-11-27 14:47
 * @Version 1.0
 */
public class LeetCode_297_TreeSerialization_简化 {

    public class Codec {

        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            dfs_ser(root, builder);
            builder.deleteCharAt(builder.length() - 1);
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
            return dfs_des(dataList);
        }

        public TreeNode dfs_des(LinkedList<String> dataList) {
            if (dataList.getFirst().equals("null")) {
                // 删除
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

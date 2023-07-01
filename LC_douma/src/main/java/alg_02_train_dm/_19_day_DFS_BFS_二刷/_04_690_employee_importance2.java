package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 12:49
 * @Version 1.0
 */
public class _04_690_employee_importance2 {

    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    private Map<Integer, Employee> map;

    // KeyPoint 方法二 dfs 后序遍历
    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    // 后序遍历
    // => 从子节点返回上层节点，需要携带着统计信息，给上层使用
    // => 故递归函数有返回值
    private int dfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;

        // KeyPoint DFS 前序 和 DFS 后序 区别
        // 1.前序遍历：遍历子节点之前，将父节点 emp 重要性值累加到 res；
        //            再去遍历子节点，累加重要性值
        // 2.后序遍历：在遍历父节点 emp 之前，先遍历子节点，累加重要性值到 res；
        //            之后再加上父节点 emp 重要性值

        // 记录子节点重要性值
        int res = 0;
        for (int subordinateId : emp.subordinates) {
            // 将所有子节点重要性累和
            res += dfs(subordinateId);
        }

        // 注意：res 仅仅是子节点 重要性值，不能将 当前节点重要性 弄丢了
        // 最终返回值：子节点重要性 + 当前节点重要性
        return emp.importance + res;
    }
}

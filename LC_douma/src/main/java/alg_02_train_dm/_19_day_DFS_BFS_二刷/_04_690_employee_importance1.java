package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:02
 * @Version 1.0
 */
public class _04_690_employee_importance1 {
     /*
         690. 员工的重要性
         给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。

         比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。
         那么员工 1 的数据结构是 [1, 15, [2]] ，
         员工 2的 数据结构是 [2, 10, [3]] ，
         员工 3 的数据结构是 [3, 5, []] 。
         注意：虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。

         现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。

         示例：
         输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]],  1 (id)
         输出：5 + 3 + 3 = 11
         解释：员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。
              因此员工 1 的总重要度是 5 + 3 + 3 = 11 。

         提示：
         一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
         员工数量不超过 2000 。

         KeyPoint 思路
         分析业务场景
         => 抽象：一种或多种数据结构，数据结构相关问题
         => 基于数据结构，使用相应的算法解决

         本题 => 抽象：N 叉树，N 叉树遍历
              => DFS 或 BFS
     */

    // 力扣题目提供
    // 若题目没有提供，则需要自己定义
    // => 定义成类，方便使用属性
    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    // 通过 id 和 Employee 做了映射
    // => 通过 id 获取 Employee
    // key id
    // value Employee
    private Map<Integer, Employee> map;
    // res 记录：重要性 importance
    private int res;

    // KeyPoint 方法一 dfs 前序遍历
    public int getImportance(List<Employee> employees, int id) {
        res = 0;
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        dfs(id);

        return res;
    }

    // 前序遍历
    private void dfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return;

        // 前序遍历：遍历子节点之前，将父节点 emp 重要性值累加到 res，再去遍历子节点，累加重要性值
        res += emp.importance;
        for (int subId : emp.subordinates) {
            dfs(subId);
        }
    }
}

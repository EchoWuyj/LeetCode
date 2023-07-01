package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 13:33
 * @Version 1.0
 */
public class _04_690_employee_importance2 {
    public class Employee {
        public int id;
        public int importance;
        List<Integer> subordinates;
    }

    private Map<Integer, Employee> map;

    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    public int dfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;
        int res = 0;
        for (int subId : emp.subordinates) {
            res += dfs(subId);
        }
        return res + emp.importance;
    }
}

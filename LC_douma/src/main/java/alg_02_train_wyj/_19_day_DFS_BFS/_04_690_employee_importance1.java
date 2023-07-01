package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 15:02
 * @Version 1.0
 */
public class _04_690_employee_importance1 {
    public class Employee {
        public int id;
        public int importance;
        List<Integer> subordinates;
    }

    private Map<Integer, Employee> map;
    private int res;

    public int getImportance(List<Employee> employees, int id) {
        res = 0;
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        dfs(id);
        return res;
    }

    public void dfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return;
        res += emp.importance;
        for (int subId : emp.subordinates) {
            dfs(subId);
        }
    }
}

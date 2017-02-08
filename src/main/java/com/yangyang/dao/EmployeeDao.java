package com.yangyang.dao;

import com.yangyang.model.xml.Department;
import com.yangyang.model.xml.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    @Resource
    private DepartmentDao departmentDao;

    public static Integer initId = 1006;
    private static Map<Integer,Employee> employees = null;
    static {
        employees = new HashMap<>();
        employees.put(1001,new Employee(1001,"E-AA","aa@163.com","1",new Department(101,"D-AA")));
        employees.put(1002,new Employee(1002,"E-BB","bb@163.com","1",new Department(102,"D-BB")));
        employees.put(1003,new Employee(1003,"E-CC","cc@163.com","0",new Department(103,"D-CC")));
        employees.put(1004,new Employee(1004,"E-DD","dd@163.com","0",new Department(104,"D-DD")));
        employees.put(1005,new Employee(1005,"E-EE","ee@163.com","1",new Department(105,"D-EE")));
    }

    public void save(Employee employee){
        if(employee.getId() ==null) employee.setId(initId++);
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getEmployees(){
        return employees.values();
    }
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
    public void delete(Integer id){
        employees.remove(id);
    }
}

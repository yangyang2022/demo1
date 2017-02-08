package com.yangyang.dao;

import com.yangyang.model.xml.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    private static Map<Integer,Department> deps = null;
    static {

        deps =new HashMap<>();

        deps.put(101,new Department(101,"D_AA"));
        deps.put(102,new Department(102,"D_BB"));
        deps.put(103,new Department(103,"D_CC"));
        deps.put(104,new Department(104,"D_DD"));
        deps.put(105,new Department(105,"D_EE"));
    }
    public Collection<Department> getDepartments(){
        return deps.values();
    }
    public Department getDepartmentById(Integer id){
        return deps.get(id);
    }

}

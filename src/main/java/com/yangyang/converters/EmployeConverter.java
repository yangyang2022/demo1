package com.yangyang.converters;

import com.yangyang.dao.DepartmentDao;
import com.yangyang.dao.EmployeeDao;
import com.yangyang.model.xml.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmployeConverter implements Converter<String,Employee> {

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public Employee convert(String source) {
        if(source != null){
            String[] vals = source.split("-");
            if(vals.length == 4){
                int id = EmployeeDao.initId++;
                String lastName = vals[0];
                String email = vals[1];
                String gender = vals[2];
                int depId = Integer.parseInt(vals[3]);
                return new Employee(id,lastName,email,gender,departmentDao.getDepartmentById(depId));
            }
        }
        return null;
    }
}

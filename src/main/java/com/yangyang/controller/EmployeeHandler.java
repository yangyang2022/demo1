package com.yangyang.controller;

import com.yangyang.dao.DepartmentDao;
import com.yangyang.dao.EmployeeDao;
import com.yangyang.model.xml.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private DepartmentDao departmentDao;

    public static final String REDIRECT_EMPS = "redirect:/emps";

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false)Integer id,Map<String,Object> map){
        if(id != null){
            map.put("employee",employeeDao.getEmployeeById(id));
        }
    }

    @PutMapping("/emp")
    public String update(Employee employee){

        employeeDao.save(employee);

        return REDIRECT_EMPS;
    }
    @GetMapping("/emp/{id}")
    public String input(@PathVariable("id")Integer id,Map<String,Object> map){
        map.put("employee",employeeDao.getEmployeeById(id));
        map.put("deps",departmentDao.getDepartments());
        return "input";
    }
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){

        employeeDao.delete(id);

        return REDIRECT_EMPS;
    }

    @PostMapping("/emp")
    public String save(@Valid Employee employee, BindingResult br,Map<String,Object> map){
        if(br.hasErrors()){
            System.out.println("occur error ... ");
            br.getFieldErrors().forEach(e-> System.out.println(e.getField()+" : "+e.getDefaultMessage()));

            //若验证出错,转向定制的页面
            map.put("deps",departmentDao.getDepartments());
            return "input";
        }
        employeeDao.save(employee);
        return REDIRECT_EMPS;
    }
    @GetMapping("emp")
    public String input(Map<String,Object> map){

        map.put("deps",departmentDao.getDepartments());
        /**
         * springmvc 即使在添加的时候也会表单回显,否则出现"command"异常
         * 加入一个空对象即可,并且在form里面加入modelAttribute
         */
        map.put("employee",new Employee());
        return "input";
    }
    @GetMapping("emps")
    public String list(Map<String,Object> map){

        map.put("emps",employeeDao.getEmployees());

        return "list";
    }

    //WebDataBinder 是由表单数据到对象之间的映射
    //@InitBinder
    //public void initBinder(WebDataBinder binder){
        //binder.setDisallowedFields("lastName");
        //binder.setDisallowedFields("email");
    //}
}

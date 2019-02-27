package com.ycy.springmvc.crud.handler;

import com.ycy.springmvc.crud.dao.DepartmentDao;
import com.ycy.springmvc.crud.dao.EmployeeDao;
import com.ycy.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @program: springMVC-CRUD
 * @description:
 * @author: ChangYue
 * @create: 2019-02-14 17:28
 */

@Controller
public class EmployeeHandler {

    @Autowired
    public EmployeeDao employeeDao;

    @Autowired
    public DepartmentDao departmentDao;


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id,
                            Map<String, Object> map) {
        if (id != null) {
            map.put("employee", employeeDao.get(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * form 回显数据
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result, Map<String, Object> map) {
        employeeDao.save(employee);
        System.out.println(employee.toString());

        if (result.getErrorCount() > 0) {
            System.out.println("出错了");

            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + "：" + error.getDefaultMessage());
            }
            map.put("departments", departmentDao.getDepartments());
            return "input";
        }

        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

    @RequestMapping(value = "/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        return "list";
    }

}

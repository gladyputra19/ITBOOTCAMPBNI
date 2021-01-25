package com.example.Framework1.controllers;

//import com.example.Framework1.models.Department;
import com.example.Framework1.models.Employee;
import com.example.Framework1.repositories.DepartmentRepository;
import com.example.Framework1.repositories.EmployeeRepository;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String main(Model model){
//        System.out.println("Coba Cetak");
//        List<Department> departments = departmentRepository.findAll();
//        for (Department department: departments) {
//            System.out.println("Department Id   : "+department.getId());
//            System.out.println("Department Name : "+department.getName());
//        }
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("employee",new Employee());
        return "employee";
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("Coba cetak");
        //Cara 1 Set Data
//        Employee e = new Employee();
//        e.setName("Glady Putra Pratama");
//        employeeRepository.save(e);

        //Cara 2 Set Data
//        Department d1 = new Department("ADD4", "App Dev 4 (Test)");
        //Save -> Insert or Update
        employeeRepository.save(new Employee(1,"Silvia Novita"));

//        List<Department> departments = departmentRepository.findAll();

        employeeRepository.delete(new Employee("1"));

        for (Employee employee : employeeRepository.findAll()) {
            System.out.println(employee.getId());           //Menampilkan Id
            System.out.println(employee.getName());         //Menampilkan Nama
            System.out.println(employee.getDepartment().getName()); //Menampilkan nama department
            //jika di thymeleaf menggunakan as.department.name
        }

//        Employee e = new Employee(1442, "Dev");
//        //Saat Save harus di set departmentnya
//        e.setDepartment(new Department("DP001"));
//        employeeRepository.save(e);

        return "employee/";
    }

    @PostMapping("/save")
    public String save(Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employee/";
    }

    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employee/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        employeeRepository.delete(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/";
    }
}

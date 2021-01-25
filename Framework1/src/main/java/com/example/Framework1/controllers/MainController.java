/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework1.controllers;

import com.example.Framework1.models.Department;
import com.example.Framework1.models.Employee;
import com.example.Framework1.repositories.DepartmentRepository;
import org.dom4j.rule.pattern.DefaultPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author glady
 */
@Controller
public class MainController {
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/")
    public String main(Model model){
//        System.out.println("Coba Cetak");
//        List<Department> departments = departmentRepository.findAll();
//        for (Department department: departments) {
//            System.out.println("Department Id   : "+department.getId());
//            System.out.println("Department Name : "+department.getName());
//        }
        model.addAttribute("departments",departmentRepository.findAll());
        model.addAttribute("department",new Department());
        return "index";
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("Coba cetak");
        //Cara 1 Set Data
        Department d = new Department();
        d.setId("ADD3");
        d.setName("App Dev 3");
        departmentRepository.save(d);

        //Cara 2 Set Data
//        Department d1 = new Department("ADD4", "App Dev 4 (Test)");
        //Save -> Insert or Update
        departmentRepository.save(new Department("DP001","OFA"));

//        List<Department> departments = departmentRepository.findAll();

        departmentRepository.delete(new Department("DP001"));

        for (Department department : departmentRepository.findAll()) {
            System.out.println("Department Id   : "+department.getId());
            System.out.println("Department Name : "+department.getName());
        }
        return "index";
    }

    @PostMapping("/save")
    public String save(Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }

    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "index";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        departmentRepository.delete(department);
        model.addAttribute("employees", departmentRepository.findAll());
        return "index";
    }
}

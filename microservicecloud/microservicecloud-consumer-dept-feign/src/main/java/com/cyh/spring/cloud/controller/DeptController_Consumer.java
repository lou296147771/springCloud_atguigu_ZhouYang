package com.cyh.spring.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyh.spring.cloud.entity.Dept;
import com.cyh.spring.cloud.service.DeptClientService;

@RestController
public class DeptController_Consumer {

    @Autowired
    private DeptClientService service;

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable Long id) {
        System.out.println("Feign, get Dept for id: " + id);
        return this.service.get(id);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> list() {
        System.out.println("Feign, get Dept list");
        return this.service.list();
    }

    @PostMapping("/consumer/dept/add")
    public Object add(Dept dept) {
        System.out.println("Feign, add a Dept");
        return this.service.add(dept);
    }
}


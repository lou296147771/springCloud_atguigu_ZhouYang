package com.cyh.spring.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyh.spring.cloud.entity.Dept;
import com.cyh.spring.cloud.service.DeptService;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        System.out.println("提供者8001添加一个Dept: " + dept);
        return deptService.add(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable Long id) {
        System.out.println("提供者8001查询Dept，id: " + id);
        return deptService.get(id);
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list() {
        System.out.println("提供者8001查询所有Dept列表");
        return deptService.list();
    }

}

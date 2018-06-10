package com.cyh.spring.cloud.service;

import java.util.List;

import com.cyh.spring.cloud.entity.Dept;

public interface DeptService {
    boolean add(Dept dept);

    Dept get(Long id);

    List<Dept> list();
}

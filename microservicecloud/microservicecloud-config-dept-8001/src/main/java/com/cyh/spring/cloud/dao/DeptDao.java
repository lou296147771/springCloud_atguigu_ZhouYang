package com.cyh.spring.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyh.spring.cloud.entity.Dept;

@Mapper
public interface DeptDao {
    boolean addDept(Dept dept);

    Dept findById(Long id);

    List<Dept> findAll();
}

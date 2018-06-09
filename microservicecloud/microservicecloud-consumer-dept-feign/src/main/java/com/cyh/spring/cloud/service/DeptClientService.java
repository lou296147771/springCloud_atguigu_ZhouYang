package com.cyh.spring.cloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cyh.spring.cloud.entity.Dept;

//@FeignClient(value = "MICROSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSERVICECLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @GetMapping("/dept/get/{id}")
    Dept get(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    List<Dept> list();

    @PostMapping("/dept/add")
    boolean add(Dept dept);
}

package com.cyh.spring.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cyh.spring.cloud.entity.Dept;

import feign.hystrix.FallbackFactory;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable t) {
        t.printStackTrace();
        return new DeptClientService() {
            @Override
            public Dept get(Long id) {
                System.err.println("get()方法调用服务端出错，返回指定值");
                return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
                        .setDb_source("no this database in MySQL");
            }

            @Override
            public List<Dept> list() {
                System.err.println("list()方法调用服务端出错，返回指定值");
                return new ArrayList<>();
            }

            @Override
            public boolean add(Dept dept) {
                System.err.println("add()方法调用服务端出错，返回指定值");
                return false;
            }
        };
    }
}


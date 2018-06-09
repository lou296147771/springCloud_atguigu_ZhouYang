package com.cyh.spring.cloud2.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 自定义为每台机器5次
        return new FiveTimesRoundRobinRule();
    }
}


package com.cyh.spring.cloud2.rule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 轮询调用，每台机器每次被调用5次（而不是默认的1次）
 */
public class FiveTimesRoundRobinRule extends AbstractLoadBalancerRule {

    /** 总共被调用的次数，目前要求每台被调用5次 */
    private int invokedTimes = 0;
    /** 当前提供服务的机器号 */
    private int currentIndex = 0;

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }

        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes only get more restrictive.
                 */
                return null;
            }

            if (invokedTimes < 5) {
                server = upList.get(currentIndex);
                invokedTimes++;
            } else {
                invokedTimes = 0;
                currentIndex++;
                if (currentIndex >= upList.size()) {
                    currentIndex = 0;
                }
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were somehow trimmed.
                 * This is a transient condition. Retry after yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return server;
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {}

}

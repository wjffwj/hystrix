package com.wjf.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 舱壁模式，方法的线程池隔离技术
 */
@Service
public class ActivityBuildHeadService {

    @Autowired
    private RestTemplate restTemplate;


    public String firstLoginActivity(Long userId) {
        return restTemplate.postForObject("http://activity/firstLoginActivity", userId, String.class);

    }

    /**
     * 定义超时时间让用户服务不再等待活动服务
     *
     * @param userId
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "firstLoginActivity1-threadpoolwjf",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") //超过两秒就不掉了
            })
    public String firstLoginActivity1(Long userId) {
        return restTemplate.postForObject("http://activity/firstLoginActivity1", userId, String.class);
    }

    /**
     * 活动服务报错
     *
     * @param userId
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "firstLoginActivity2-threadpoolwjf",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            }, fallbackMethod = "firstLoginActivity2fallback")
    public String firstLoginActivity2(Long userId) {
        return restTemplate.postForObject("http://activity/firstLoginActivity2", userId, String.class);
    }

    public String firstLoginActivity2fallback(Long userId) {
        return "服务降级";
    }
}

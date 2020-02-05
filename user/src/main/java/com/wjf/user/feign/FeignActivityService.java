package com.wjf.user.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FeignActivityService {
    @Autowired
    private RestTemplate restTemplate;


    public String firstLoginActivity(Long userId) {
       return restTemplate.postForObject("http://activity/feign/feignActivity",userId,String.class);

    }

    /**
     * 定义超时时间让用户服务不再等待活动服务
     * @param userId
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000") //超过两秒就不掉了
            })
    public String firstLoginActivity1(Long userId) {
        return restTemplate.postForObject("http://activity/feign/feignActivity1",userId,String.class);
    }

    /**
     * 活动服务报错
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "firstLoginActivity2fallback")
    public String firstLoginActivity2(Long userId) {
        return restTemplate.postForObject("http://activity/feign/feignActivity2",userId,String.class);
    }
    public String firstLoginActivity2fallback(Long userId) {
        return "服务降级";
    }


    /**
     * 3s内 请求次数达到 2 个且失败率达50%以上就跳闸 ，跳闸后活动窗口为3s
     * 测试活动服务报错- 时间窗口概念
     * @param userId
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value = "3000"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "2"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "3000"),
            }
    )
    public String firstLoginActivity3(Long userId) {

        return restTemplate.postForObject("http://activity/feign/feignActivity2",userId,String.class);
    }
}

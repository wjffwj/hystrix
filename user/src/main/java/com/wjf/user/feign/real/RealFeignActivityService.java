package com.wjf.user.feign.real;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 使用feign进行服务调用
 */
//fallback = RealFeignActivityServiceFallBack.class,
@FeignClient(value = "activity", fallbackFactory = RealFeignActivityFallBackFactory.class )
public interface RealFeignActivityService {
    @PostMapping(value = "/feign/feignActivity")
    public String firstLoginActivity(@RequestBody Long userId);

    @PostMapping(value = "/feign/feignActivity1")
    public String firstLoginActivity1(@RequestBody Long userId);

    @PostMapping(value = "/feign/feignActivity2")
    public String firstLoginActivity2(@RequestBody Long userId);
}

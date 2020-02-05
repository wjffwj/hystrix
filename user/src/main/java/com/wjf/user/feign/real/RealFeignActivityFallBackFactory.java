package com.wjf.user.feign.real;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RealFeignActivityFallBackFactory implements FallbackFactory<RealFeignActivityService>{
    @Override
    public RealFeignActivityService create(Throwable throwable) {
        return new RealFeignActivityService() {
            @Override
            public String firstLoginActivity(Long userId) {
                return "feign执行降级10ms";
            }

            @Override
            public String firstLoginActivity1(Long userId) {
                return "超时回退factory";
            }

            @Override
            public String firstLoginActivity2(Long userId) {
                return null;
            }
        };
    }
}

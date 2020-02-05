package com.wjf.user.feign.real;

import org.springframework.stereotype.Service;

@Service
public class RealFeignActivityServiceFallBack implements RealFeignActivityService {
    @Override
    public String firstLoginActivity(Long userId) {
        return "降级";
    }

    @Override
    public String firstLoginActivity1(Long userId) {
        return "降级";
    }

    @Override
    public String firstLoginActivity2(Long userId) {
        return "降级";
    }
}

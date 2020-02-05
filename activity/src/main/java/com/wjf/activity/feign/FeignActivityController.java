package com.wjf.activity.feign;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 处理相关活动的服务接口
 */
@RestController
@RequestMapping(value = "/feign")
public class FeignActivityController {
    /**
     * 正常活动服务
     * @param id
     * @return
     */
    @PostMapping(value = "/feignActivity")
    public String firstLoginActivity(@RequestBody Long id) {
        System.out.println("ActivityController-firstLoginActivity。。。发放优惠券成功"+id);
        return "succ";
    }

    /**
     * 异常活动服务超时
     * @param id
     * @return
     */
    @PostMapping(value = "/feignActivity1")
    public String firstLoginActivity1(@RequestBody Long id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ActivityController-firstLoginActivity。。。发放优惠券成功"+id);
        return "succ";
    }

    /**
     * 异常报错
     * @param id
     * @return
     */
    @PostMapping(value = "/feignActivity2")
    public String firstLoginActivity2(@RequestBody Long id) throws Exception {
        throw new Exception("asdasdasdasdasd");
//return "succ";
    }
}

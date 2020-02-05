package com.wjf.activity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 处理相关活动的服务接口
 */
@RestController
public class ActivityController {
    /**
     * 正常活动服务
     * @param id
     * @return
     */
    @PostMapping(value = "/firstLoginActivity")
    public String firstLoginActivity(@RequestBody Long id) {
        System.out.println("ActivityController-firstLoginActivity。。。发放优惠券成功"+id);
        return "succ";
    }

    /**
     * 异常活动服务超时
     * @param id
     * @return
     */
    @PostMapping(value = "/firstLoginActivity1")
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
    @PostMapping(value = "/firstLoginActivity2")
    public String firstLoginActivity2(@RequestBody Long id) throws Exception {
        throw new Exception("asdasdasdasdasd");
//return "succ";
    }
}

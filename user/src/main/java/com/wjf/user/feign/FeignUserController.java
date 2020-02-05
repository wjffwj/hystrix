package com.wjf.user.feign;

import com.wjf.user.entity.User;
import com.wjf.user.feign.real.RealFeignActivityService;
import com.wjf.user.service.ActivityBuildHeadService;
import com.wjf.user.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 */
@RestController
@RequestMapping(value = "/feign")
public class FeignUserController {

    @Autowired
   private RealFeignActivityService realFeignActivityService;


    @Autowired
    private ActivityBuildHeadService activityBuildHeadService;//舱壁模式的类

    /**
     * 用戶正常注冊
     * @param user
     * @return
     */
    @PostMapping(value = "/feignreg")
    public String reg(@RequestBody User user) {
        System.out.println("用户注册成功。" + user.getName());
        String result = realFeignActivityService.firstLoginActivity(user.getId());
        return result;
    }

    /**
     * 活動服务响应时间长
     * @param user
     * @return
     */
    @PostMapping(value = "/feignreg1")
    public String reg1(@RequestBody User user) {
        System.out.println("用户注册成功。" + user.getName());
        String result = realFeignActivityService.firstLoginActivity1(user.getId());
        return result;
    }


    /**
     * 活動服务报错 - 降级
     * @param user
     * @return
     */
    @PostMapping(value = "/feignreg2")
    public String reg2(@RequestBody User user) {
        System.out.println("用户注册成功。" + user.getName());
        String result = realFeignActivityService.firstLoginActivity2(user.getId());
        return result;
    }




}

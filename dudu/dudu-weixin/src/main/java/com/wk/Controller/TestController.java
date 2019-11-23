package com.wk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/22
 * @Time:20:13
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("info")
    public String info(){
        System.out.println("得到的redis:"+redisTemplate);
        return "info..";
    }
}

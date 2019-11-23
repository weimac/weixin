package com.wk.project.weixin.api.accessToken;

import com.wk.project.weixin.main.MenuManager;
import com.wk.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/22
 * @Time:17:12
 */
@Service
public class AccessTokenRedis {

    private static final String REDIS_ACCESS_TOKEN="access_Token";
    public  static final String ACCESS_TOKEN_URL=" https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,String> string;

    /**
     * 用户根据key查询Redis数据库，如果key不存在，向微信服务器发送请求得到 access_toekn。
     * 将其存入Redis数据库中，并设定过期时间（2小时）。
     * 如果key存在，直接返回用户即可
     * @return
     */
    public String getAccessTokenVal(){

        if(redisTemplate.hasKey(REDIS_ACCESS_TOKEN)){

            String accessToken=string.get(REDIS_ACCESS_TOKEN);
            System.out.println("得到的Redis值为:"+accessToken);

            return accessToken;

        }else{

            String accessToken=getAccessTokenValue();
            System.out.println("微信中取出accessToken:"+accessToken);
            string.set(REDIS_ACCESS_TOKEN,accessToken,2, TimeUnit.HOURS);

            return accessToken;

        }

    }

    private String getAccessTokenValue(){

        String requestUrl=ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);

        JSONObject jsonObject=WeixinUtil.httpRequest(requestUrl,"GET",null);

        String accessToken= (String) jsonObject.get("access_token");

        return accessToken;
    }


}

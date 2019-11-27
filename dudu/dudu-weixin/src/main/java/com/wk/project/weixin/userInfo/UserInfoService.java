package com.wk.project.weixin.userInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wk.po.Weiuser;
import com.wk.project.weixin.api.accessToken.AccessTokenRedis;
import com.wk.project.weixin.util.WeixinUtil;
import com.wk.service.WeiuserService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/26
 * @Time:20:50
 */
@Service
public class UserInfoService {

    @Autowired
    private AccessTokenRedis accessTokenRedis;
    @Autowired
    private WeiuserService weiuserService;

    private static final String UserInfo_Info="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //收集用户的个人详情
    /**
     * 1.收集用户openid
     * 2.根据openid向服务器发送请求得到用户信息
     * 3.得到用户信息jsonObject转换成javaBean
     * 4.写入到数据库中
     */
    public void userInfo(String openid){

        Weiuser wu=weiuserService.selectByOpenid(openid);

        if(wu==null) {

            JSONObject jsonObject = getJSONObjectByOpenid(openid);

            Weiuser weiuser = covertJSONObject(jsonObject);

            int num= addWeiUser(weiuser);

            System.out.println(num);

        }else {
            //不做更新
        }
    }

    //1.收集用户openid
    //2.根据openid向服务器发送请求得到用户信息
    public JSONObject getJSONObjectByOpenid(String openid){

        String url=UserInfo_Info.replace("OPENID",openid).replace("ACCESS_TOKEN",accessTokenRedis.getAccessTokenVal());

        JSONObject jsonObject=WeixinUtil.httpRequest(url,"GET",null);

        return jsonObject;
    }


    //3.得到用户信息jsonObject转换成javaBean
    public Weiuser covertJSONObject(JSONObject jsonObject){

        //3.得到用户信息jsonObject转换成javaBean
        /**
         * 1.简单方式
         */
/*        Weiuser weiuser=new Weiuser();
        weiuser.setOpenid(jsonObject.getString("openid"));*/

        /**
         * 2.ObjectMapper
         */
        Weiuser weiuser=null;
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            weiuser=objectMapper.readValue(jsonObject.toString(),Weiuser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return weiuser;
    }

    public int addWeiUser(Weiuser weiuser){

       return  weiuserService.insertSelective(weiuser);
    }
}

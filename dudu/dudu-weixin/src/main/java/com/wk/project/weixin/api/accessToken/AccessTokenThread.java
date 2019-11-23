package com.wk.project.weixin.api.accessToken;

import com.wk.project.weixin.main.MenuManager;
import com.wk.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/22
 * @Time:17:11
 */
public class AccessTokenThread extends Thread {

    public  static final String ACCESS_TOKEN_URL=" https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获得access_token的值
     */
    public static String access_token_val;
    @Override
    public void run() {
      while(true){
          try {
                access_token_val=getAccessTokenValue();
                System.out.println("得到的值:"+access_token_val);
                Thread.sleep(7000000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

      }
    }

    /**
     * 通过WeiXin服务器发送get请求,得到accessToken
     * @return
     */
    private String getAccessTokenValue(){

        String requestUrl=ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);

        JSONObject jsonObject=WeixinUtil.httpRequest(requestUrl,"GET",null);

        String accessToken= (String) jsonObject.get("access_token");

        return accessToken;
    }

}

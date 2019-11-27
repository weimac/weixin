package com.wk.project.weixin.oauth;

import com.wk.project.weixin.main.MenuManager;
import com.wk.project.weixin.pojo.Menu;
import com.wk.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/26
 * @Time:16:48
 */
@Controller
@RequestMapping("oauth")
public class WeiOauth {

    //第一步：用户同意授权，获取code
    @RequestMapping("weixin")
    public void oauth(HttpServletResponse response) throws IOException {
        //授权后重定向的回调链接地址,请使用urlEncode对链接进行处理
        String rediect_url="http://hu6ifj.natappfree.cc/"+"oauth/invoke";
        //需要把地址进行转义
        try {
            rediect_url=URLEncoder.encode(rediect_url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + MenuManager.appId+
                "&redirect_uri=" +rediect_url+
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=Weimac" +
                "#wechat_redirect";

            response.sendRedirect(url);

    }
    //用户同意授权后
    //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
    @RequestMapping("invoke")
    public String invoke(HttpServletRequest request){

        String code=request.getParameter("code");
        System.out.println("得到的code:"+code);
        String state=request.getParameter("state");

    //第二步：通过code换取网页授权access_token
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +MenuManager.appId+
                "&secret=" + MenuManager.appSecret+
                "&code=" +code+
                "&grant_type=authorization_code";

        //发送请求get/set
        JSONObject jsonObject=WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());
        //获取access_token
        String access_token=  jsonObject.getString("access_token");
        String openid=jsonObject.getString("openid");

        //第三步：拉取用户信息(需scope为 snsapi_userinfo)
        String userInfoUrl="https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" +access_token+
                "&openid=" +openid+
                "&lang=zh_CN";
        //获取UserInfo
        JSONObject UserJson=WeixinUtil.httpRequest(userInfoUrl,"GET",null);

        request.setAttribute("userInfo",UserJson);

        //TODO 把json型转换成javaBean

        return "oauth";
    }

}

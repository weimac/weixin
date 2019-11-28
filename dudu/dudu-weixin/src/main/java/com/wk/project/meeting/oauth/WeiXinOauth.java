package com.wk.project.meeting.oauth;

import com.wk.mapper.WeiuserMapper;
import com.wk.po.User;
import com.wk.po.Weiuser;
import com.wk.project.weixin.main.MenuManager;
import com.wk.project.weixin.util.WeixinUtil;
import com.wk.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date:2019/11/27
 * @Time:19:12
 */
@Controller
@RequestMapping("oauth")
public class WeiXinOauth {

    @Autowired
    private WeiuserMapper weiuserMapper;

    @Autowired
    private UserService userService;

    /**
     *微信 个人中心按钮 实现功能
     */
    @RequestMapping("weixin/user")
    public void oauth(HttpServletResponse response) throws IOException {
        //授权后重定向的回调链接地址,请使用urlEncode对链接进行处理
        String rediect_url=MenuManager.REAL_URL+"oauth/weixin/user/invoke";
        //需要把地址进行转义
        try {
            rediect_url= URLEncoder.encode(rediect_url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    //第一步：用户同意授权，获取code
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + MenuManager.appId+
                "&redirect_uri=" +rediect_url+
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=Weimac" +
                "#wechat_redirect";

        response.sendRedirect(url);

    }
    //用户同意授权后
    //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
    @RequestMapping("weixin/user/invoke")
    public String invoke(HttpServletRequest request){

        String code=request.getParameter("code");

        //第二步：通过code换取网页授权access_token
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +MenuManager.appId+
                "&secret=" + MenuManager.appSecret+
                "&code=" +code+
                "&grant_type=authorization_code";

        //发送请求get/set
        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());
        String openid=jsonObject.getString("openid");

        /**
         * 判断用户是否被绑定
         * 1.根据openid去weiuser中查找
         */
        Weiuser weiuser=weiuserMapper.selectByOpenid(openid);
        if(weiuser==null){
            //用户信息在数据库中不存在
            //1.提示用户先关注微信公众号
            // 2.将收集到的信息录入到数据库中
            System.out.println("该用户信息不存在"+openid);
        }else{
            User user=userService.selectByWid(weiuser.getId());
            if (user==null){
                //用户没有绑定,跳转到登录页面
                request.setAttribute("wid",weiuser.getId());
                return "weixin/login"; //templates/weixin/login.html
            }else{
                //微信用户已经绑定,跳转到修改信息界面
                request.setAttribute("userInfo",user);
                return "weixin/user/userInfo";

            }

        }

        return "oauth";
    }


    /**
     *微信 抢单功能实现
     */
    @RequestMapping("weixin/meetingPub") // oauth/weixin/meetingPub
    public void meetingPubOauth(HttpServletResponse response) throws IOException {
        //授权后重定向的回调链接地址,请使用urlEncode对链接进行处理
        String rediect_url=MenuManager.REAL_URL+"oauth/weixin/meetingPub/invoke";
        //需要把地址进行转义
        try {
            rediect_url= URLEncoder.encode(rediect_url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //第一步：用户同意授权，获取code
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + MenuManager.appId+
                "&redirect_uri=" +rediect_url+
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=Weimac" +
                "#wechat_redirect";

        response.sendRedirect(url);
    }

    //用户同意授权后
    //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
    @RequestMapping("weixin/meetingPub/invoke")   //  oauth/weixin/meetingPub/invoke
    public String meetingPubInvoke(HttpServletRequest request){

        String code=request.getParameter("code");

        //第二步：通过code换取网页授权access_token
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +MenuManager.appId+
                "&secret=" + MenuManager.appSecret+
                "&code=" +code+
                "&grant_type=authorization_code";

        //发送请求get/set
        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());
        String openid=jsonObject.getString("openid");

        /**
         * 1.通过openid得到主键id weiuser
         * 2.通过主键id->wid 查询对象 user.getRid();
         */

        Weiuser weiuser=weiuserMapper.selectByOpenid(openid);
        if(weiuser==null){
            //1.weiuser中没有你的信息-->重新收集用户信息提示/提示:因网路异常,请重新关注公众号
            //2.没有进行绑定---->跳到登录界面
            return "因网路异常,请重新关注公众号";
        }else{
            User user=userService.selectByWid(weiuser.getId());
            if(user==null){
            return "weixin/login"; //templates/weixin/login.html
            }else{
                if(user.getRid()==1){//发单组
                    request.setAttribute("uid",user.getId());
                    return "weixin/meeting/meetingPub";
                }else if(user.getRid()==2){//抢单组
                    return "weixin/unauth";
                }else{ //查看组
                    return "";

                     }
                }
            }
    }


}

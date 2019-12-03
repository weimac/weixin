package com.wk.project.meeting.Controller;

import com.wk.mapper.WeiuserMapper;
import com.wk.po.User;
import com.wk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/27
 * @Time:19:09
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeiuserMapper weiuserMapper;

    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestParam("email") final String email,
                        @RequestParam("wid")final Integer wid){

        User user=userService.selectByEmail(email);

        if(user!=null){
            if(user.getWid()!=null){

                return "1";
                //return "该邮箱已经被其他用户占用";
            }else{

                int num = userService.updateByEmail(wid,email);
                return "2";
                //return "登录成功,该邮箱可用";

            }
        }else{
            //return "该邮箱不存在";
            return "3";
        }

    }

    @ResponseBody
    @RequestMapping("update")
    public String update(User user){

        int num =userService.updateByPrimaryKeySelective(user);

        return num+"";
    }


    /**
     * 页面跳转的controller操作
     */
    /**
     *登录功能
     */
    @RequestMapping("tologin") // /user/tologin
    public String toLogin(HttpServletRequest request) {
        String wid=request.getParameter("wid");
        request.setAttribute("wid",wid);
        return "weixin/login";
    }


    /**
     * 非权限页面
     */
    @RequestMapping("unauth")  // /user/unauth
    public String unauth() {
        return  "weixin/unauth";
    }

    /**
     * 抢单界面
     */
    @RequestMapping("meetingGrab")  // /user/meetingGrap
        public String meetingGrab(HttpServletRequest request) {
            String uid=request.getParameter("uid");
            request.setAttribute("uid",uid);
        return "weixin/meeting/meetingGrab";
    }

}

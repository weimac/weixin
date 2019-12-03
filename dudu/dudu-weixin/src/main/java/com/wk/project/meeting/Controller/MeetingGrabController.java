package com.wk.project.meeting.Controller;

import com.wk.po.Meetinggrab;
import com.wk.service.MeetinggrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/29
 * @Time:21:40
 */
@RequestMapping("meetinggrab")
@Controller
public class MeetingGrabController {

    @Autowired
    private MeetinggrabService meetinggrabService;

    /**
     * 会议抢单>可抢单>目标页面
     */
    @RequestMapping("AddMeetingGrab") // /meetinggrab/AddMeetingGrab
    public String add(@RequestParam("uid") String uid,
                      @RequestParam("pid") String pid, HttpServletRequest request){
        request.setAttribute("uid",uid);
        request.setAttribute("pid",pid);
        return "weixin/meeting/meetingGrabAdd";
    }


    /**
     * 我抢单的会议
     */

    @RequestMapping("select")       // /meetinggrab/select
    @ResponseBody
    public List<Meetinggrab> selectMeetinggrabByUid(@RequestParam("uid") String uid){

        List<Meetinggrab> list= meetinggrabService.selectMeetinggrabByUid(uid);


        return list;

    }

    @RequestMapping("add") // /meetinggrab/add
    public ModelAndView insertSelective(Meetinggrab meetinggrab){

        ModelAndView modelAndView=new ModelAndView();

        int num=meetinggrabService.insertSelective(meetinggrab);
        modelAndView.addObject("uid",meetinggrab.getUid());
        //return "weixin/meeting/meetingGrab";
        modelAndView.setViewName("weixin/meeting/meetingGrab");


        return modelAndView;

    }


}

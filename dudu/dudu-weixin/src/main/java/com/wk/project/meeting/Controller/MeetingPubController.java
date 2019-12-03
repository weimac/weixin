package com.wk.project.meeting.Controller;

import com.wk.po.Meetinggrab;
import com.wk.po.Meetingpub;
import com.wk.service.MeetinggrabService;
import com.wk.service.MeetingpubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/28
 * @Time:19:06
 */
@RequestMapping("meetingpub")
@Controller
public class MeetingPubController {

    @Autowired
    private MeetingpubService meetingpubService;

    @Autowired
    private MeetinggrabService meetinggrabService;

    /**
     *添加会议
     */
    @RequestMapping("add")
    @ResponseBody
    public int add(Meetingpub meetingpub){

        int num=meetingpubService.insertSelective(meetingpub);

        return num;
    }

    /**
     * 我发布的会议
     */

    @RequestMapping("select")       // /meetingpub/select
    @ResponseBody
    public List<Meetingpub> selectMeetingpubByUid(@RequestParam("uid") String uid){

        return meetingpubService.selectMeetingpubByUid(uid);
    }

    /**
     * 查看可查抢单的项目
     */
    @RequestMapping("grabList")       // /meetingpub/grapList
    @ResponseBody
    List<Meetingpub>  selectMeetingpubById(@RequestParam("uid") String uid,
                                           @RequestParam("tname") String tname){

        return meetingpubService.selectMeetingpubById(uid, tname);
    }


    @RequestMapping("findMeetingInfo") // /meetingpub/findMeetingInfo
    public String selectMeetingPub(@RequestParam("pid") String pid,HttpServletRequest request){

        Meetingpub meetingpub=meetingpubService.selectMeetingInfoByPid(pid);

        request.setAttribute("meetingpub",meetingpub);

        return "weixin/meeting/meetingInfo";
    }

    /**
     * 我的会议 选择讲者
     */
    @RequestMapping("chooseGrabList") // /meetingpub/chooseGrabList
    public String chooseGrabList(@RequestParam("uid") String uid,
                                 @RequestParam("pid") String pid,
                                 HttpServletRequest request){

        request.setAttribute("uid",uid);
        request.setAttribute("pid",pid);

        return "weixin/meeting/grabList";
    }

    /**
     * 加载讲者信息
     */
    @RequestMapping("findGrabInfo") // /meetingpub/findGrabInfo
    @ResponseBody
    public List<Meetinggrab> findGrabInfo(@RequestParam("pid") String pid){

        List<Meetinggrab> list=meetinggrabService.selectGrabList(pid);

        return list;
    }

    /**
     * 就选你功能
     */
    @ResponseBody
    @RequestMapping("chooseList") ///meetingpub/chooseList
    public int  chooseList(@RequestParam("uid") String uid,
                             @RequestParam("pid") String pid){
        int num=0;
        try{
           num= meetinggrabService.choooseMeetingGrabList(uid,pid);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
       return num;

    }
}

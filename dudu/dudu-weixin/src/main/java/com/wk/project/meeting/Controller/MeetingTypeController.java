package com.wk.project.meeting.Controller;

import com.wk.po.Meetingtype;
import com.wk.service.MeetingtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/28
 * @Time:17:07
 */
@Controller
@RequestMapping("meetingtype")
public class MeetingTypeController {

    @Autowired
    private MeetingtypeService meetingtypeService;


    @RequestMapping("select") // /meetingtype/select
    @ResponseBody
    public List<Meetingtype> selectByMeetingtype(){

        List<Meetingtype> list= meetingtypeService.selectMeetingtype();

        return list;
    }


}

package com.wk.service.impl;

import com.wk.mapper.MeetingtypeMapper;
import com.wk.po.Meetingtype;
import com.wk.service.MeetingtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/28
 * @Time:17:02
 */

@Service
public class MeetingtypeServiceImpl implements MeetingtypeService {


    @Autowired
    private MeetingtypeMapper meetingtypeMapper;

    @Override
    public List<Meetingtype> selectMeetingtype() {
        return meetingtypeMapper.selectMeetingtype();
    }
}

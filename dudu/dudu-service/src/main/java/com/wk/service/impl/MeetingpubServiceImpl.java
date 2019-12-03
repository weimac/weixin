package com.wk.service.impl;

import com.wk.mapper.MeetingpubMapper;
import com.wk.po.Meetingpub;
import com.wk.service.MeetingpubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/28
 * @Time:19:02
 */

@Service
public class MeetingpubServiceImpl implements MeetingpubService {

    @Autowired
    private MeetingpubMapper meetingpubMapper;


    @Override
    public int insertSelective(Meetingpub meetingpub) {

        meetingpub.setId(UUID.randomUUID().toString()); //uuid为主键

        meetingpub.setCreatedate(new Date());
        meetingpub.setStatus((short) 1);
        meetingpub.setPcode(getPcode(meetingpub.getPtime()));

        return meetingpubMapper.insertSelective(meetingpub);
    }

    @Override
    public String getPcode(String ptime) {


        String str = ptime.substring(0, 10).replace("-", "");
        System.out.println(str);

        String result = meetingpubMapper.seletMaxPcodeByPtime(str);

        if (StringUtils.isEmpty(result)) {

            return str.concat("001");

        } else {

            long l = Long.parseLong(result) + 1;

            return String.valueOf(l);

        }


    }

    /**
     * 查询所有的任务
     * @param uid
     * @return
     */
    @Override
    public List<Meetingpub> selectMeetingpubByUid(String uid) {
        return meetingpubMapper.selectMeetingpubByUid(uid);
    }

    @Override
    public List<Meetingpub> selectMeetingpubById(String uid, String tname) {
        return meetingpubMapper.selectMeetingpubById(uid, tname);
    }

    @Override
    public Meetingpub selectMeetingInfoByPid(String pid) {
        return meetingpubMapper.selectMeetingInfo(pid);
    }
}

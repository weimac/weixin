package com.wk.service;

import com.wk.po.Meetinggrab;
import com.wk.po.Meetingpub;

import java.util.List;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/28
 * @Time:19:02
 */

public interface MeetingpubService {

    /*
    发布会议功能
     */
    int insertSelective(Meetingpub record);

    /**
     * pcode编码的生成
     */
    String getPcode(String ptime);


    /**
     *查找所有的发布项目
     */
    List<Meetingpub> selectMeetingpubByUid(String uid);

    /**
     *查询所有可以抢单的项目
     */
    List<Meetingpub>  selectMeetingpubById(String uid,String tname);

    /**
     *查询会议信息
     */
    Meetingpub selectMeetingInfoByPid(String pid);
}

package com.wk.service;

import com.wk.po.Meetinggrab;
import com.wk.po.Meetingpub;

import java.util.List;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/29
 * @Time:21:42
 */
public interface MeetinggrabService {

    /**
     *查找所有的抢单的项目
     */
    List<Meetinggrab> selectMeetinggrabByUid(String uid);

    /**
     * 添加抢单信息
     */
    int insertSelective(Meetinggrab meetinggrab);

    /**
     *抢单者列表
     */
    List<Meetinggrab> selectGrabList(String pid);

    /**
     * 就选你功能
     */
    int choooseMeetingGrabList(String uid,String pid)throws RuntimeException;

}

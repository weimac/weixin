package com.wk.service.impl;

import com.wk.mapper.MeetinggrabMapper;
import com.wk.po.Meetinggrab;
import com.wk.service.MeetinggrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/29
 * @Time:21:43
 */
@Service
public class MeetinggrabServiceImpl implements MeetinggrabService {


    @Autowired
    private MeetinggrabMapper meetinggrabMapper;

    @Override
    public List<Meetinggrab> selectMeetinggrabByUid(String uid) {
        return meetinggrabMapper.selectMeetinggrabByUid(uid);
    }

    @Override
    public int insertSelective(Meetinggrab meetinggrab) {

        meetinggrab.setId(UUID.randomUUID().toString());
        meetinggrab.setCreatedate(new Date());
        meetinggrab.setGrabstatus(0);//未匹配选择状态
        meetinggrab.setStatus((short) 1);

        return meetinggrabMapper.insertSelective(meetinggrab);
    }


    @Override
    public List<Meetinggrab> selectGrabList(String pid) {

        return meetinggrabMapper.selectGrabList(pid);
    }

    /**
     * Spring事务机制
     * @param uid
     * @param pid
     * @return
     */
    @Override
    @Transactional
    public int choooseMeetingGrabList(String uid, String pid)throws RuntimeException {

        int num=meetinggrabMapper.updateMeetingGrabFail(pid);

        if(num<1){
            System.out.println("抢单失败匹配异常");
            throw new RuntimeException("抢单失败匹配异常");
        }

        int num1=meetinggrabMapper.updateMeetingGrabSucc(uid, pid);
        if(num1<0){
            System.out.println("抢单成功匹配异常");
            throw new RuntimeException("抢单成功匹配异常");
        }
        return 1;
    }
}

package com.wk.mapper;

import com.wk.po.Meetingpub;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingpubMapper {
    int deleteByPrimaryKey(String id);

    int insert(Meetingpub record);

    int insertSelective(Meetingpub record);

    Meetingpub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meetingpub record);

    int updateByPrimaryKey(Meetingpub record);

    /**
     *查询会议信息
     */
    @Select("SELECT pub.id,pub.ptime,u.`name`,u.telephone from meetingpub pub\n" +
            "left join user u\n" +
            "on pub.uid=u.id\n" +
            "where pub.id=#{pid}")
    Meetingpub selectMeetingInfo(String pid);


    /**
     * 根据会议时间查询pcode
     */
    @Select("select max(pcode) from meetingpub where  Left(pcode,8)=#{ptime}")
    String seletMaxPcodeByPtime(String ptime);

    @Select("select * from meetingpub where uid=#{uid} and status=1 order by pcode desc")
    List<Meetingpub>  selectMeetingpubByUid(String uid);

    /**
     * 我的抢单列表
     * Map方式
     * param1,param2
     * tname=-1 查询所有的单
     */

    List<Meetingpub>  selectMeetingpubById(String uid,String tname);


}
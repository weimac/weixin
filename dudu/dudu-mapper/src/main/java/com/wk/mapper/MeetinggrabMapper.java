package com.wk.mapper;

import com.wk.po.Meetinggrab;
import com.wk.po.Meetingpub;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetinggrabMapper {
    int deleteByPrimaryKey(String id);

    int insert(Meetinggrab record);

    /**
     * 添加抢单信息
     */
    int insertSelective(Meetinggrab meetinggrab);

    Meetinggrab selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meetinggrab record);

    int updateByPrimaryKey(Meetinggrab record);

    @Select("select grab.*,pub.ptitle,pub.pcode,pub.tname,pub.pzone from meetinggrab grab\n" +
            "left join meetingpub pub\n" +
            "on grab.pid=pub.id\n" +
            "where grab.uid=#{uid} and grab.status=1 order by grab.grabDate desc")
    List<Meetinggrab> selectMeetinggrabByUid(String uid);

    @Select("select u.name,u.province,u.city,grab.* from meetinggrab grab\n" +
            "LEFT join user u\n" +
            "on grab.uid=u.id\n" +
            "where pid=#{pid} ORDER BY grab.createDate asc ")
    List<Meetinggrab> selectGrabList(String pid);


    /**
     * 就选你功能
     * 1.将所有的抢单,根据pid改成匹配失败,grabStatus=2
     * 2.将指定的用户(作为讲者)改状态为1,匹配成功
     */
    @Update("update meetinggrab set grabStatus=2,grabDate=NOW() where pid=#{pid}")
    int updateMeetingGrabFail(String pid);

    @Update("update meetinggrab set grabStatus=1,grabDate=NOW() where pid=#{pid} and uid=#{uid}")
    int updateMeetingGrabSucc(String uid,String pid);



}
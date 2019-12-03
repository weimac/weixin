package com.wk.mapper;

import com.wk.po.Meetingtype;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Meetingtype record);

    int insertSelective(Meetingtype record);

    Meetingtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meetingtype record);

    int updateByPrimaryKey(Meetingtype record);

    @Select("select * from meetingtype where status='1' order by sortnum asc")
    List<Meetingtype> selectMeetingtype();
}
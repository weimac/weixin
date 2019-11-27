package com.wk.mapper;

import com.wk.po.Weiuser;
import org.apache.ibatis.annotations.Select;

public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);

    //根据openid用户是否存在
    @Select("select * from weiuser where openid=#{openid}")
    Weiuser selectByOpenid(String openid);
}
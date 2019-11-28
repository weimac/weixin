package com.wk.mapper;

import com.wk.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据wid查询user是否存在
     */
    @Select("select * from user where wid=#{wid}")
    User selectByWid(Integer wid);


    /**
     * 根据wid查询user是否存在
     */
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);

    /**
     *根据邮箱信息添加wid
     */

    @Update("update user set wid=#{wid} where email=#{email}")
    int updateByEmail( Integer wid,String email);


    /**
     * 根据openid的值查询得到user对象
     */
    @Select("select * from user where wid =(select id  from weiuser where openid=#{openid})")
    User selectUserByOpenId(String openid);
}
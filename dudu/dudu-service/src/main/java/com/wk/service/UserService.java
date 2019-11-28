package com.wk.service;

import com.wk.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/27
 * @Time:19:45
 */
public interface UserService {

  /***********TODO 后台端
   */





    /*********TODO 微信端
     * 根据wid查询user是否存在
     */
    User selectByWid(Integer wid);


    /**
     *根据邮箱查询用户信息
     */
    User selectByEmail(String email);

    /**
     *根据邮箱信息添加wid
     */
    int updateByEmail( Integer wid,String email);


    /**
     * 更新user
     */
    int updateByPrimaryKeySelective(User record);


    /**
     * 根据openid的值查询得到user对象
     */
    User selectUserByOpenId(String openid);

}

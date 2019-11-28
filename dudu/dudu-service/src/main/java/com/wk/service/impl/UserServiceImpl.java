package com.wk.service.impl;

import com.wk.mapper.UserMapper;
import com.wk.po.User;
import com.wk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/27
 * @Time:19:46
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByWid(Integer wid) {
        return userMapper.selectByWid(wid);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int updateByEmail(Integer wid, String email) {
        return userMapper.updateByEmail(wid,email);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User selectUserByOpenId(String openid) {
        return userMapper.selectUserByOpenId(openid);
    }
}

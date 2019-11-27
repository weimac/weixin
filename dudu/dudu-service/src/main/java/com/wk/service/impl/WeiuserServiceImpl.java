package com.wk.service.impl;

import com.wk.mapper.WeiuserMapper;
import com.wk.po.Weiuser;
import com.wk.service.WeiuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/26
 * @Time:20:38
 */
@Service
public class WeiuserServiceImpl implements WeiuserService {

    @Autowired
    private WeiuserMapper weiuserMapper;


    @Override
    public Weiuser selectByOpenid(String openid) {
        return weiuserMapper.selectByOpenid(openid);
    }

    @Override
    public int insertSelective(Weiuser record) {
        return weiuserMapper.insertSelective(record);
    }
}

package com.wk.service;

import com.wk.po.Weiuser;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/26
 * @Time:20:37
 */
public interface WeiuserService {

    /**
     * 根据用户openid判断是否存在
     * @param openid
     * @return
     */
    Weiuser selectByOpenid(String openid);


    /**
     * 添加用户
     * @param record
     * @return
     */
    int insertSelective(Weiuser record);
}
